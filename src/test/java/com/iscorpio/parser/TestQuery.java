package com.iscorpio.parser;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iscorpio.QueryFactory;
import com.iscorpio.parser.mock.User;
import com.iscorpio.struct.OrderBy;
import com.iscorpio.struct.common.StatementInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 1:35 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
public class TestQuery {

    static List<User> user = init();

    private static List<User> init() {
        List<User> userTable = User.getUserTable();
        String json = JSON.toJSONString(userTable, SerializerFeature.PrettyFormat);
        System.out.println(json);
        return userTable;
    }

    public static void main(String[] args) {
        // List<User> withWhere = QueryFactory.query();

        queryWithSort();
    }

    private static void queryWithSort() {
        List<OrderBy> orders = ListUtil.of(new OrderBy("gender", OrderBy.Sort.DESC), new OrderBy("age", OrderBy.Sort.DESC));
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .orders(orders)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println(JSON.toJSONString(query));
    }








}
