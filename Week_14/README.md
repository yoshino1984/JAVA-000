**周四作业：**

1.（选做）自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。

2.（必做）思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。
- [实现了 MQ 第二个版本](./kmq-core/src/main/java/io/kimmking/kmq/core/Kmq.java)：自定义Queue:去掉内存Queue，设计自定义Queue，实现消息确认和消费offset
    1. 增加了一个group的概念，每个 consumer 都属于一个 group，每个 group 都有唯一的一个 offset
       consumer 拉取时传递 group获取offset，然后返回 offset 对应的消息
    2. confirm 时需要传递确认的消息 id，确认完毕之后，移动 group 的 offset

3.（挑战☆☆☆☆☆）完成所有其他版本的要求。期限一年。

**周六作业：**
1.（选做）思考一下自己负责的系统，或者做过的系统，能否描述清楚其架构。

2.（选做）考虑一下，如果让你做一个针对双十一，某东某宝半价抢 100 个 IPhone 的活动系统，你该如何考虑，从什么地方入手。

3.（选做）可以自行学习以下参考书的一两本。
推荐架构书籍：
《软件架构》Mourad Chabane Oussalah
《架构实战 - 软件架构设计的过程》Peter EeLes
《软件系统架构 - 使用视点和视角与利益相关者合作》Nick Rozanski
《企业 IT 架构转型之道》
《大型网站技术架构演进与性能优化》
《银行信息系统架构》
《商业银行分布式架构实践》



# 先欠着，周四补