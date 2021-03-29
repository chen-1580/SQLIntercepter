package com.iscorpio.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import com.iscorpio.filter.GroupByFilter;
import com.iscorpio.filter.LimitFilter;
import com.iscorpio.filter.OrderFilter;
import com.iscorpio.filter.WhereFilter;
import com.iscorpio.struct.common.StatementInfo;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈恺翔
 * @description 查询工厂
 * @createdate 2021/3/28 4:24 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class QueryFactory {

    /**
     * 根据组合条件查询对应结果
     * 因为查询中需要平凡的对对象的键做校验和获取
     * 所以转换为map方便后续操作
     * 在使用完毕转回来
     *
     * @param info 组合条件对象
     * @param <T>  查询对象
     * @return 查询结果表
     */
    public static <T> List<T> query(StatementInfo<T> info) {
        List<T> origin = info.getOrigin();
        if (CollectionUtils.isEmpty(origin)) {
            return Collections.emptyList();
        }
        // 将bin转化为map,方便取key
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
        // 将map转化为bean
        Class<T> tClass = ClassUtil.getClass(origin.iterator().next());
        return CollectionUtils.isEmpty(originMArray)
                ? Collections.emptyList()
                : originMArray
                .stream()
                .map(m -> BeanUtil.toBean(m, tClass)).collect(Collectors.toList());
    }
}
