package com.iscorpio.struct;

import com.iscorpio.struct.common.StatementStruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 2:11 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBy implements StatementStruct {

    /**
     * 排序的名称
     */
    private String colName;
    /**
     * 排序的升降序
     */
    private Sort sort;

    public enum Sort {
        ASC,
        DESC,
        ;

        Sort() {
        }
    }
}
