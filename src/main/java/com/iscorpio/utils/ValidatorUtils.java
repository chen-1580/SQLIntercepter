package com.iscorpio.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 陈恺翔
 * @description 校验类
 * @createdate 2021/3/29 10:45 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class ValidatorUtils {

    /**
     * 校验数组中元素与keys是否匹配
     * @param origin 表
     * @param keys 过滤key
     */
    public static void validateParam(List<Map<String, Object>> origin, Set<String> keys) {
        if (CollectionUtils.isEmpty(origin) || CollectionUtils.isEmpty(keys)) {
            return;
        }
        Set<String> allKeys = new HashSet<>();
        Set<String> originKeys = origin.iterator().next().keySet();
        allKeys.addAll(originKeys);
        allKeys.addAll(keys);
        if (allKeys.size() != originKeys.size()) {
            throw new IllegalArgumentException("不支持的key");
        }

    }
}
