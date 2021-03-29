package com.iscorpio.parser;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.iscorpio.utils.QueryFactory;
import com.iscorpio.parser.mock.User;
import com.iscorpio.struct.GroupBy;
import com.iscorpio.struct.Limit;
import com.iscorpio.struct.OrderBy;
import com.iscorpio.struct.Where;
import com.iscorpio.struct.common.Pair;
import com.iscorpio.struct.common.StatementInfo;

import java.util.List;

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
        queryWithLimit();
        queryWithSort();
        queryWithGroup();
        queryWithWhere();
        queryWithAll();
    }

    private static void queryWithLimit() {
        Limit limit = new Limit(2, 3);
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .limit(limit)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println("limit =====>" + JSON.toJSONString(query));
    }

    private static void queryWithSort() {
        List<OrderBy> orders = ListUtil.of(new OrderBy("gender", OrderBy.Sort.DESC), new OrderBy("age", OrderBy.Sort.DESC));
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .orders(orders)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println("orders =====>" + JSON.toJSONString(query));
    }

    private static void queryWithGroup() {
        List<GroupBy> groups = ListUtil.of(new GroupBy("gender"), new GroupBy("age"));
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .groups(groups)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println("groups =====>" + JSON.toJSONString(query));
    }

    private static void queryWithWhere() {
        List<Where> wheres = ListUtil.of(
                new Where(new Pair<>("gender", "F"), Where.Option.EQ, Where.AndOr.AND),
                new Where(new Pair<>("deptName", "研发部"), Where.Option.EQ, Where.AndOr.OR),
                new Where(new Pair<>("deptName", "产品部"), Where.Option.EQ, Where.AndOr.OR),
                new Where(new Pair<>("age", 30), Where.Option.GE, Where.AndOr.AND)
        );
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .wheres(wheres)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println("wheres =====>" + JSON.toJSONString(query));
    }

    private static void queryWithAll() {
        Limit limit = new Limit(0, 3);
        List<OrderBy> orders = ListUtil.of(new OrderBy("gender", OrderBy.Sort.DESC), new OrderBy("age", OrderBy.Sort.DESC));
        List<GroupBy> groups = ListUtil.of(new GroupBy("gender"), new GroupBy("age"));
        List<Where> wheres = ListUtil.of(
                new Where(new Pair<>("gender", "F"), Where.Option.EQ, Where.AndOr.AND),
                new Where(new Pair<>("deptName", "研发部"), Where.Option.EQ, Where.AndOr.OR),
                new Where(new Pair<>("deptName", "产品部"), Where.Option.EQ, Where.AndOr.OR),
                new Where(new Pair<>("age", 30), Where.Option.GE, Where.AndOr.AND)
        );
        StatementInfo<User> info = StatementInfo.<User>builder()
                .origin(user)
                .orders(orders)
                .groups(groups)
                .wheres(wheres)
                .limit(limit)
                .build();
        List<User> query = QueryFactory.query(info);
        System.out.println("all =====>" + JSON.toJSONString(query));
    }






}
