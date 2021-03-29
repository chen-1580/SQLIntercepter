package com.iscorpio.struct;

import com.iscorpio.struct.common.Pair;
import com.iscorpio.struct.common.StatementStruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author 陈恺翔
 * @description where元数据
 * @createdate 2021/3/28 2:11 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
@AllArgsConstructor
public class Where implements StatementStruct {
    /**
     * 过滤需要的键值对
     * case where a=b and b=c or c=d
     */
    private Pair<String, Object> filter;
    /**
     * 操作符
     */
    private Option option;
    /**
     * and或者or
     */
    private AndOr andOr;


    @Getter
    public enum Option {
        /**
         * equals =
         */
        EQ("="),
        /**
         * great than >
         */
        GT(">"),
        /***
         * great equals >=
         */
        GE(">="),
        /**
         * less than <
         */
        LT("<"),
        /**
         * less equals <=
         */
        LE("<="),
        /**
         * not equal
         */
        NE("!=")
        ;

        private final String opt;

        Option(String opt) {
            this.opt = opt;
        }
    }

    public enum AndOr {
        AND,
        OR,
        ;

        AndOr() {
        }
    }

}
