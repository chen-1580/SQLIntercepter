package com.iscorpio.filter;

import com.alibaba.fastjson.JSONArray;
import com.iscorpio.struct.Limit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 1:57 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class LimitFilter {

    public static List<Map<String, Object>> filter(List<Map<String, Object>> origin, Limit limit) {
        if (limit == null) {
            return origin;
        }
        Stream<Map<String, Object>> stream = origin.stream();
        if (limit.getOffset() != null) {
            return stream.skip(limit.getOffset()).limit(limit.getSize()).collect(Collectors.toList());
        } else {
            return stream.limit(limit.getSize()).collect(Collectors.toList());
        }
    }
}
