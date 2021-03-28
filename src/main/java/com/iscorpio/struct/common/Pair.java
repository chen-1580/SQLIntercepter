package com.iscorpio.struct.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 2:35 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pair<K, V> {

    private K key;
    private V val;

    private Pair(){}

    public Pair(K k, V v) {
        this.key = k;
        this.val = v;
    }

}
