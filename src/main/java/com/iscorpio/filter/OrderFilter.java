package com.iscorpio.filter;


import com.iscorpio.struct.OrderBy;
import com.iscorpio.utils.ValidatorUtils;

import java.util.List;
import java.util.Map;
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

    /**
     * 对原始数组做自然排序
     * @param origin 原始列表
     * @param orders 排序列表
     * @return 排序后的列表
     */
    public static List<Map<String, Object>> filter(List<Map<String, Object>> origin, List<OrderBy> orders) {
        ValidatorUtils.validateParam(origin, orders.parallelStream().map(OrderBy::getColName).collect(Collectors.toSet()));
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

}
