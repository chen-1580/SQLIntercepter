package com.iscorpio.struct.common;

import com.iscorpio.struct.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 陈恺翔
 * @description 存放表达式大对象
 * @createdate 2021/3/28 2:10 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatementInfo<T> {

    List<T> origin;
    List<Where> wheres;
    List<GroupBy> groups;
    List<OrderBy> orders;
    Limit limit;

    public StatementInfo(List<T> origin, List<Where> wheres, List<GroupBy> groups, List<OrderBy> orders, Limit limit) {
        this.origin = origin;
        this.wheres = wheres;
        this.groups = groups;
        this.orders = orders;
        this.limit = limit;
    }
}
