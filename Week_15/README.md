# 简略总结系列

## JVM
- 学到了jvm基础知识、字节码、类加载机制、内存模型以及GC算法
- 其次这部分最有用的还是学到了命令行工具的使用方法，以前不习惯使用命令行，但是从各个情况下的GC调试日志作业开始也渐渐习惯了
- 同时也了解到了可量化可测量的重要性，没有可量化的前提，所做的优化就没有事实的检测证据。

后续方向：首先整理复习

## NIO
- 了解了一些基本的概念
    - 同步/异步
    - 阻塞/非阻塞
    - BIO、NIO、AIO
    - Reactor/Proactor
- 学到了Netty相关的知识，包括序列化编码、粘包半包等的优化方式等
    - 网络这部分知识后续需要继续学习
- 了解了业务网关和流量网关的区别\

后续方向：网络相关的内容、零拷贝等概念的澄清、netty源码

## 并发编程

- 澄清了基本的概念：线程及其生命周期、并发相关的性质
- 多线程：jdk中多线程的相关的类及其参数影响
- 并发：锁和并发工具类

后续方向：《Java 并发编程实战》、AQS源码分析

## 开放框架

- Spring技术体系：核心就是IOC和AOP 以及其他
- ORM体系：对于 JPA、Hibernate、Mybatis

后续方向：spring核心模块（IOC和AOP）的源码解读、找一个好用的Mybatis代码自动生成工具、Hibernate学习

## MySQL 数据库和 SQL

### 关系型数据库

- 数据库设计范式

### MySQL

- 数据库原理

    - 执行流程
    - 执行引擎和状态
    - 索引原理

- MySQL配置优化
- 数据库表设计优化

### SQL

- 事务

    - ACID
    - InnoDB
    - 事务的隔离级别

- 锁

    - 表级锁
    - 行级锁
    - 死锁

- log

    - undo log
    - redo log

- SQL 优化

    - 建表、修改表结构、数据量、数据更新、
      模糊查询、连接查询、索引失效等

### 常见场景分析

- 主键生成预优化
- 分页
- 快速导入导出数据
- 乐观锁与悲观锁

    - 死锁问题等

## 分库分表

### MySQL高可用与读写分离

- 单机的问题
- 主从复制

    - 原理

        - binlog
        - Binlog(Row/Statement模式）

    - 局限

- 读写分离
- 高可用

    - 定义与好处
    - 集群与主从切换
    - 高可用阶段

        - 主从手动切换
        - MHA
        - MGR
        - MySQL Cluster
        - Orchestrator

### 数据库拆分与分库分表

- 拆分的原因

    - 读写压力
    - 高可用性
    - 容量问题

- 拆分导致的问题

    - 数据一致性问题

- 垂直拆分

    - 拆库拆表
    - 优缺点

- 水平拆分

    - 分库分表
    - 优缺点

- 数据分类管理
- 相关框架和中间件

    - Java框架层面

        - TDDL
        - Apache ShardingSphere-JDBC

            - 直接在业务代码使用。
            - 支持常见的数据库和 JDBC。
            - Java only。

    - 中间件层面

        - DRDS（商业闭源）
        - Apache ShardingSphere-Proxy

            - 作为中间件，独立部署，对业务端透明。
            - 任何语言平台的系统都可以接入，可以使用 mysql 命令或者 IDE 操作。
            - 对业务系统侵入性小。

        - MyCat/DBLE
        - Cobar
        - Vitness
        - KingShard

- 数据迁移（优缺点）

    - 全量
    - 全量+增量
    - binlog+全量+增量

### 分布式事务

- 原因

    - 分布式系统带来的数据一致性问题

- XA

    - 强一致
    - 性能差
    - 2PC
    - XA标准概念和执行过程
    - 框架

        - Atomikos
        - narayana
        - SEATE

    - 存在的问题

- BASE柔性事务

    - 定义

        - 基本可用
          （Basically Available）

            - 不需要保证分布式事务参与方一定同时在线

        - 柔性状态
          （Soft state）

            - 允许系统状态更新有一定的延迟，这个延迟对客户来说不一定能够觉察

        - 最终一致性
          （Eventually consistent）

            - 通常是通过消息传递的方式保证系统的最终一致性

        - 通过放宽对强一致性要求，来换取系统吞吐量的提升

    - 性能较好，但实现复杂，有些对业务侵入性高
    - 框架和实现

        - 实现方式

            - TCC

                - 通过手动补偿处理
                - 三阶段

                    - try、confirm、cancel

            - AT

                - 铜鼓自动补偿处理

            - SAGA

                - 没有 try阶段，直接提交事务，每个操作都有一个回滚动作，发生异常直接回滚

        - 柔性事务下的隔离级别
        - 框架

            - seata
            - hmily

## RPC 和微服务

### RPC基本原理

- 核心是代理机制。
- 1.本地代理存根: Stub
- 2.本地序列化反序列化
- 3.网络通信
- 4.远程序列化反序列化
- 5.远程服务存根: Skeleton
- 6.调用实际业务服务
- 7.原路返回服务结果
- 8.返回给本地调用方

### RPC技术框架

- Corba/RMI/.NET Remoting
- JSON RPC, XML RPC，WebService(Axis2, CXF)
- Hessian, Thrift, Protocol Buffer, gRPC

### Dubbo

- 介绍

    - 主要功能

        - 基础功能：RPC调用

          框架分层设计，可任意组装和扩展。


         - 多协议（序列化、传输、RPC）
         - 服务注册发现
         - 配置、元数据管理
    
      - 扩展功能
    
         - 集群：负载均衡
         - 治理：路由
         - 控制台：管理与监控

- 技术原理

    - 整体架构
    - SPI应用
    - 服务暴露、引用
    - 集群与路由
    - 泛化引用

        - GenericService

    - 隐式传参

        - Context模式

    - mock

- 应用场景

    - 分布式服务化改造
    - 开放平台
    - 直接作为前端使用的后端（BFF）
    - 通过服务化建设中台

- 最佳实践

    - 开发分包

        - API包

    - 环境隔离与分组

        - 部署多套
        - 多注册中心机制
        - group机制
        - 版本机制

    - 参数配置

        - 通用参数以 consumer 端为准，没设置才使用 provider 数值
        - consumer端

            - timeout
            - retries
            - loadbalance
            - actives

        - provider端

            - threads
            - executes

    - 容器化部署
    - 运维与监控
    - 分布式事务

        - 柔性事务

            - SAGA、TCC、AT
            - Seata、hmily

        - 不支持 XA

    - 重试与幂等

### 深入微服务

- 分布式服务治理

    - soa、esb、微服务之间的区别与联系

- XX中心

    - 配置中心
    - 注册中心
    - 元数据中心

- 服务的注册与发现
- 服务的集群与路由
- 服务的过滤与流控

### 微服务架构

- 发展历程

    - 响应式微服务
    - 服务网格与云原生
    - 数据库网格
    - 单元化架构

- 应用场景

    - 大规模复杂业务系统中

- 最佳实践

    - 遗留系统改造

        - ①功能剥离、数据解耦
        - ②自然演进、逐步拆分
        - ③小步快跑、快速迭代
        - ④灰度发布、谨慎试错
        - ⑤提质量线、还技术债

    - 恰当力度拆分

        - 高内聚低耦合
        - 拆分要点视阶段而定

    - 扩展立方体

        - 1. 水平复制：复制系统
             2.功能解耦：拆分业务
             3.数据分区：切分数据

    - 自动化管理

        - 测试、部署、运维

    - 完善监控体系

        - 1.业务监控
        - 2.系统监控
        - 3.容量规划
        - 4.报警预警
        - 5.运维流程
        - 6.故障处理

### Spring Cloud技术体系

- Config/Eureka/Consul
- Zuul/Zuul2/Spring Cloud Gateway
- Feign/Ribbon
- Hytrix/Alibaba Sentinel

### 微服务相关框架与工具

- APM：应用性能监控

    - Apache Skywalking
    - Pinpoint
    - Zipkin
    - Jaeger

- 监控

    - ELK
    - promethus + Grafana
    - MQ + 时序数据库（influxDB/openTSDB等）

- 权限控制

    - CAS+SSO(TGT、ST)
    - JWT/Token OAuth2.0
    - SpringSecurity Apache Shiro

- 数据处理
- 网关与通信

    - 1、流量网关与WAF(Nginx/OR/Kong/Apisix)
    - 2、业务网关(Zuul/Zuul2/SCG/Soul)
    - 3、REST与其他协议之争（websocket/actor/rsocket/mq...）

## 分布式缓存

### 缓存技术

- 缓存

    - 适合缓存的数据

        - 静态数据、准静态数据、中间状态数据
        - 热数据、读写别较大的数据

    - 本质

        - 空间换时间

    - 加载时机

        - 启动时全量加载
        - 懒加载

            - 同步
            - 异步

    - 缓存的有效性

        - 读写比
        - 命中率

    - 数据同步可能会有问题

- 本地缓存

    - map
    - Hibernate/MyBatis都有Cache
    - Guava Cache
    - Spring Cache
    - 缺点

        - 集群规模增大时，读写放大
        - 堆内存 GC
        - 和执行业务抢资源

- 远程缓存

    - 集中处理缓存
    - 框架

        - 缓存中间件

            - Redis/Memcached

        - 内存网格

            - Hazelcast/Ignite

- 缓存策略

    - 容量：资源有限
    - 过期策略

- 缓存常见问题和解决

    - 缓存穿透
    - 缓存击穿
    - 缓存雪崩

### Redis详解

- 基本功能

    - 基本数据结构

        - 字符串、散列、列表、集合、有序集合

    - 高级数据结构

        - Bitmaps、Hyperloglogs、GEO

    - 是否单线程

        - IO线程
        - 内存处理线程（主要指的是这个为单线程）

            - 单线程 ==> 高性能的核心

- 使用场景

    - 业务数据缓存
    - 业务数据处理
    - 全局一致计数
    - 高效统计计数
    - 发布订阅与Stream
    - 分布式锁

- Java客户端

    - Jedis
    - Lettuce
    - Redission

- Spring整合

    - Spring Data Redis

        - RedisTemplate

    - Spring Boot
    - Spring Cache 集成
    - MyBatis 集成

- 高级功能

    - 事务
    - Lua 脚本
    - 管道技术
    - 备份与恢复

        - RDB
        - AOF

- 性能优化

    - 内存优化
    - CPU优化

- 分区

    - 容量和分区

### Redis高可用

- Redis 主从复制

    - 单机到集群

- Redis Sentinel 主从切换

    - 走向高可用

- Redis Cluster 走向分片

    - 全自动分库分表

### Redission

- Redis 的 Java分布式组件库

    - 基于 Netty NIO，API线程安全

- 大量丰富的分布式功能

    - 分布式锁
    - 线程安全集合

### Hazelcast

- 标准的内存网格
- 基本概念与架构
- 部署模式、数据分区、集群与高可用、事务支持、数据亲密性

## 分布式消息队列

### 系统间通信方式（优缺点）

- 基于文件
  基于共享内存
  基于IPC（进程间通信）
  基于Socket
  基于数据库
  基于RPC
- MQ

### 消息队列基础

- 基本概念

    - queue、message、producer、consumer

- 消息模式

    - 点对点

        - PTP Point-To-Point
        - 对应于Queue

            - 单点投递

    - 发布订阅

        - PubSub，Publish-Subscribe
        - 对应于Topic

            - 广播投递

- 消息处理的保障

    - 可靠性
    - 事务型

- 消息的有序性
- 消息协议

    - STOMP

        - simple text object message protocol
        - 类似于json交互，全是文本的

    - JMS

        - Java Message Service
        - 是关于应用层的 API 协议

            - JMS与Message server 的关系
              类似于 Jdbc 与 MySQL的关系
            - 作为客户端的协议比较完善

        - Message 结构 与 Queue 概念

            -
            - Body\Header\Property, messages types
            - Queue\Topic\TemporaryQueue\TemporaryTopic
            - Connection\Session\Producer\Consumer\DurableSubscription

        - Messageing

            - PTP&Pub-Sub
            - 持久化
            - 事务机制
            - 确认机制
            - 临时队列

    - AMQP

        - 高级消息队列协议

    - MQTT

        - 专门给遥感传输等场景使用的mq协议

    - XMPP

        - 早期给IM的mq协议
        - 开源例子：Jabber

    - Open Messaging

        - alibaba 推出 rocketMq 之后提出的

- 开源消息中间件/消息队列

    - 1、ActiveMQ/RabbitMQ

        - 主要基于内存，不支持堆积
        - 支持 queue 和 topic
        - 支持 JMS 和 AMQP等

    - 2、Kafka/RocketMQ

        - 引入磁盘，消息堆积的能力
        - 只支持 topic
        - 本身不支持 JMS 和 AMQP等

    - 3、Apache Pulsar

        - 存储和计算节点分离

### ActiveMQ详解

### Kafka详解

- 定义和设计目标
- 基本概念

    - Broker
    - Topic
    - Partition
    - Producer
    - Consumer
    - Consumer Group

- 集群配置

    - 备注

        - 1、ISR：In-Sync Replica
        - 2、Rebalance：broker和consumer group的rebalance
        - 3、热点分区：需要重新平衡

- 高级特性

    - 生产者特性
    - 消费者特性

### MQ实现

## AOP、TX、ORM