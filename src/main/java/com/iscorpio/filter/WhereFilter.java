package com.iscorpio.filter;

import cn.hutool.core.util.NumberUtil;
import com.iscorpio.struct.Where;
import com.iscorpio.struct.common.Pair;
import com.iscorpio.utils.ValidatorUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 1:57 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class WhereFilter {

    private static final Set<Where.Option> NUMBER_OPTION_CLASS = new HashSet<>(4);

    static {
        NUMBER_OPTION_CLASS.add(Where.Option.GT);
        NUMBER_OPTION_CLASS.add(Where.Option.GE);
        NUMBER_OPTION_CLASS.add(Where.Option.LT);
        NUMBER_OPTION_CLASS.add(Where.Option.LE);
    }

    /**
     * 通过where表达式过滤
     * 因为or为并列关系,所以分组前置处理
     * 后续处理and关系
     * @param origin 原始数组
     * @param wheres 过滤条件
     * @return 过滤结果集
     */
    public static List<Map<String, Object>> filter(List<Map<String, Object>> origin, List<Where> wheres) {
        if (CollectionUtils.isEmpty(origin) || CollectionUtils.isEmpty(wheres)) {
            return origin;
        }
        ValidatorUtils.validateParam(origin, wheres.parallelStream().map(m -> m.getFilter().getKey()).collect(Collectors.toSet()));
        validatorWheres(wheres);
        Map<Where.AndOr, List<Where>> andOrListMap = wheres.parallelStream().collect(Collectors.groupingBy(Where::getAndOr));
        for (Map.Entry<Where.AndOr, List<Where>> andOrListEntry : andOrListMap.entrySet()) {
            Where.AndOr key = andOrListEntry.getKey();
            List<Where> value = andOrListEntry.getValue();
            if (Where.AndOr.OR.equals(key)) {
                origin = addOrResult(origin, value);
            } else {
                for (Where where : value) {
                    origin = addAndResult(origin, where);
                }
            }
        }
        return origin;
    }



    /**
     * 过滤and条件
     *
     * @param origin 原始列表
     * @param where  条件
     */
    private static List<Map<String, Object>> addAndResult(List<Map<String, Object>> origin, Where where) {
        Pair<String, Object> filter = where.getFilter();
        return origin.stream().filter(f -> {
            Where.Option option = where.getOption();
            String key = filter.getKey();
            if (NUMBER_OPTION_CLASS.contains(option)) {
                Number value = (Number) filter.getVal();
                switch (option) {
                    case GE:
                        return ((Number) f.get(key)).doubleValue() >= value.doubleValue();
                    case GT:
                        return ((Number) f.get(key)).doubleValue() > value.doubleValue();
                    case LE:
                        return ((Number) f.get(key)).doubleValue() <= value.doubleValue();
                    case LT:
                        return ((Number) f.get(key)).doubleValue() < value.doubleValue();
                    default:
                        return false;
                }
            } else {
                String value = filter.getVal().toString();
                switch (option) {
                    case EQ:
                        return f.get(key).equals(value);
                    case NE:
                        return !f.get(key).equals(value);
                    default:
                        return false;
                }
            }
        }).collect(Collectors.toList());
    }

    private static List<Map<String, Object>> addOrResult(List<Map<String, Object>> origin, List<Where> value) {
        List<Map<String, Object>> originCopy = new ArrayList<>(origin.size());
        value.forEach(f -> originCopy.addAll(addAndResult(origin, f)));
        return originCopy;
    }

    /**
     * 校验where中parameter的合法性
     *
     * @param wheres where条件组
     */
    private static void validatorWheres(List<Where> wheres) {
        wheres.parallelStream().forEach(f -> {
            if (NUMBER_OPTION_CLASS.contains(f.getOption())) {
                if (!NumberUtil.isNumber(f.getFilter().getVal().toString())) {
                    throw new IllegalArgumentException("非法的数字类型");
                }
            }
        });
    }
}
