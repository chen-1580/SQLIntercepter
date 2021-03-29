package com.iscorpio.parser.mock;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 陈恺翔
 * @description
 * @createdate 2021/3/28 2:02 下午
 * @modifier
 * @updatedate
 * @vesion 1.0
 */
@Data
public class User {

    private String name;
    private Integer age;
    private String gender;
    private String deptName;
    private String team;

    public static List<User> getUserTable() {
        String[] name = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        String[] gender = new String[]{"F", "M"};
        String[] deptName = new String[]{"研发部", "产品部", "人力部"};
        Random random = new Random();
        return IntStream
                .rangeClosed(0, 9)
                .mapToObj(m -> {
                    User user = new User();
                    user.setName(name[m]);
                    user.setAge(random.nextInt(80));
                    user.setGender(gender[random.nextInt(2)]);
                    user.setDeptName(deptName[random.nextInt(3)]);
                    user.setTeam("team" + random.nextInt(3));
                    return user;
                }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getUserTable()));
    }
}
