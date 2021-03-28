package com.iscorpio.struct;

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
public class GroupBy implements StatementStruct {

    /**
     * 分组的列名称
     */
    private String group;

}
