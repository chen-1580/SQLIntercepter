package com.iscorpio.filter;

import com.alibaba.fastjson.JSONArray;
import com.iscorpio.struct.GroupBy;
import com.iscorpio.utils.ValidatorUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 1:57 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class GroupByFilter {

    /**
     * 过滤list中需要分组的字段
     * TODO 目前只group了1个字段,对该字段分组后的值直接获取第一个, 无法像sql标准group一样对列中每个字段做限制
     * @param origins 原始列表
     * @param groups 分组对象
     * @return 分组后的对象
     */
    public static List<Map<String, Object>> filter(List<Map<String, Object>> origins, List<GroupBy> groups) {
        ValidatorUtils.validateParam(origins, groups.parallelStream().map(GroupBy::getGroup).collect(Collectors.toSet()));
        for (GroupBy group : groups) {
            Map<Object, List<Map<String, Object>>> collect = origins.stream().collect(Collectors.groupingBy(g -> g.get(group.getGroup())));
            origins = collect.entrySet().stream().map(m -> m.getValue().get(0)).collect(Collectors.toList());
        }
        return origins;
    }
}
