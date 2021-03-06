### 第十一周作业
1. （选做）命令行下练习操作 Redis 的各种基本数据结构和命令。

2. （选做）分别基于 jedis，RedisTemplate，Lettuce，Redission 实现 redis 基本操作的 demo，可以使用 spring-boot 集成上述工具。

3. （选做）spring 集成练习:
   -[x] 实现 update 方法，配合 @CachePut
   -[x] 实现 delete 方法，配合 @CacheEvict  
    [update和delete实现](./cache/src/main/java/io/kimmking/cache/service/UserServiceImpl.java)
   -[x] 将示例中的 spring 集成 Lettuce 改成 jedis 或 redisso
        - 替换lettuce配置项为如下所示
        - [jedisRedisConfig](./cache/src/main/java/com/yoshino/cache/config/JedisRedisConfig.java)
```text
spring:
  redis:
    jedis:
      pool:
        max-active: 16
        max-wait: 10
        max-idle: 8
        min-idle: 0
    timeout: 100
```
   
4. （必做）基于 Redis 封装分布式数据操作：
   [分布式加锁减库存的demo](./homework1101/src/main/java/com/yoshino/homework1101/RedisDemo.java)
    -[x] 在 Java 中实现一个简单的[分布式锁](./homework1101/src/main/java/com/yoshino/homework1101/redis/RedisLock.java)
    -[x] 在 Java 中实现一个[分布式计数器](./homework1101/src/main/java/com/yoshino/homework1101/redis/RedisCounter.java)，模拟减库存。
5. （必做）基于 Redis 的 PubSub 实现订单异步处理
    -[x] [demo](./homework1102/src/main/java/com/yoshino/homework1102/RedisPubSubDemo.java)


1. （挑战☆）基于其他各类场景，设计并在示例代码中实现简单 demo：

实现分数排名或者排行榜；
实现全局 ID 生成；
基于 Bitmap 实现 id 去重；
基于 HLL 实现点击量计数；
以 redis 作为数据库，模拟使用 lua 脚本实现前面课程的外汇交易事务。
2. （挑战☆☆）升级改造项目：

实现 guava cache 的 spring cache 适配；
替换 jackson 序列化为 fastjson 或者 fst，kryo；
对项目进行分析和性能调优。
3. （挑战☆☆☆）以 redis 作为基础实现上个模块的自定义 rpc 的注册中心。