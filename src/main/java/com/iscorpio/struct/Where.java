package com.iscorpio.struct;

import com.iscorpio.struct.common.Pair;
import com.iscorpio.struct.common.StatementStruct;
import lombok.Data;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 2:11 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
public class Where implements StatementStruct {
    /**
     * 过滤需要的键值对
     * case where a=b and b=c or c=d
     */
    private Pair<String, Object> filter;
    /**
     * 连接操作符
     */
    private Option option;

    public enum Option {
        AND,
        OR,
        ;

        Option() {
        }
    }

}
