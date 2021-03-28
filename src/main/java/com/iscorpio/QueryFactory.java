package com.iscorpio;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iscorpio.filter.GroupByFilter;
import com.iscorpio.filter.LimitFilter;
import com.iscorpio.filter.OrderFilter;
import com.iscorpio.filter.WhereFilter;
import com.iscorpio.struct.common.StatementInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 4:24 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class QueryFactory {

    public static <T> List<T> query(StatementInfo<T> info) {
        List<T> origin = info.getOrigin();
        if (CollectionUtils.isEmpty(origin)) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> originMArray = origin
                .stream()
                .map(BeanUtil::beanToMap)
                .collect(Collectors.toList());
        if (info.getWheres() != null) {
            originMArray = WhereFilter.filter(originMArray, info.getWheres());
        }
        if (info.getGroups() != null) {
            originMArray = GroupByFilter.filter(originMArray, info.getGroups());
        }
        if (info.getOrders() != null) {
            originMArray = OrderFilter.filter(originMArray, info.getOrders());
        }
        if (info.getLimit() != null) {
            originMArray = LimitFilter.filter(originMArray, info.getLimit());
        }
        Class<T> tClass = ClassUtil.getClass(origin.iterator().next());
        return CollectionUtils.isEmpty(originMArray)
                ? Collections.emptyList()
                : originMArray
                .stream()
                .map(m -> BeanUtil.toBean(m, tClass)).collect(Collectors.toList());
    }
}
