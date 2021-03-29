### 该demo为练习仿真sql查询服务
主程序分为3个包
- filter
  > filter中定义where,group,order,limit 4种过滤器
- struct
  > 该包中定义了一些基本的数据结构
- utils
  > 该包定义了使用的工具

在test目录下,使用TestQuery中的main方法测试流程

PS
因为group by在聚合的时候,需要指定每个字段的分组条件,所以分组列表只有1个组,
该分组只取了每一组的第一个元素