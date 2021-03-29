package com.iscorpio.struct;

import com.iscorpio.struct.common.StatementStruct;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 陈恺翔
 * @description limit元数据
 * @createdate 2021/3/28 2:11 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
@AllArgsConstructor
public class Limit implements StatementStruct {

    private Integer offset;
    private Integer size;

}
