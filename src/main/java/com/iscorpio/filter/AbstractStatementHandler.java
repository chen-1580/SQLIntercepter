package com.iscorpio.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 4:27 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class AbstractStatementHandler<T extends StatementFilter> {

    List<T> handler = new ArrayList<>();
}
