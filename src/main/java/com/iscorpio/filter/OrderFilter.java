package com.iscorpio.filter;


import cn.hutool.core.comparator.CompareUtil;
import com.alibaba.fastjson.JSONArray;
import com.iscorpio.struct.OrderBy;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 1:57 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class OrderFilter {

    public static List<Map<String, Object>> filter(List<Map<String, Object>> origin, List<OrderBy> orders) {
        checkOrderKeys(origin, orders);
        AtomicInteger i = new AtomicInteger(orders.size());
        while (i.getAndDecrement() > 0) {
            origin = origin.stream().sorted((s1, s2) -> {
                OrderBy orderBy = orders.get(i.get());
                String key = orderBy.getColName();
                if (orderBy.getSort() == OrderBy.Sort.DESC) {
                    return s1.get(key).toString().compareToIgnoreCase(s2.get(key).toString());
                } else {
                    return s2.get(key).toString().compareToIgnoreCase(s1.get(key).toString());
                }
            }).collect(Collectors.toList());
        }
        return origin;
    }

    private static void checkOrderKeys(List<Map<String, Object>> origin, List<OrderBy> orders) {
        Map<String, Object> next = origin.iterator().next();
        Set<String> keys = next.keySet();
        orders.parallelStream().forEach(f -> {
            if (!keys.contains(f.getColName())) {
                throw new IllegalArgumentException("排序参数不匹配");
            }
        });
    }

}
