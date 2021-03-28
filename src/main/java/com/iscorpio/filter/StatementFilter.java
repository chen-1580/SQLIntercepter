package com.iscorpio.filter;

import com.iscorpio.struct.common.StatementStruct;

import java.util.List;
import java.util.Map;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 2:45 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public interface StatementFilter {

    List<Map<String, Object>> filter(List<Map<String, Object>> origin);
}
