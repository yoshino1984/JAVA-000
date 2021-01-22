## 说明

第一个版本：完全基于内存queue的消息队列，实现了最基础的三个功能：
- 创建topic
- 订阅topic和poll消息
- 发送消息到topic

第二个版本：自定义Queue:去掉内存Queue，设计自定义Queue，实现消息确认和消费offset 
- 自定义内存Message数组模拟Queue。 
- 使用指针记录当前消息写入位置。 
- 对于每个命名消费者，用指针记录消费位置。

第三个版本：基于SpringMVC实现MQServer，拆分broker和client(包括producer和consumer) 
- 将Queue保存到web server端 
- 设计消息读写API接口，确认接口，提交offset接口 
- producer和consumer通过httpclient访问Queue 
- 实现消息确认，offset提交 
- 实现consumer从offset增量拉取


第四个版本：功能完善MQ 4、增加多种策略（各条之间没有关系，可以任意选择实现）
- 考虑实现消息过期，消息重试，消息定时投递等策略
- 考虑批量操作，包括读写，可以打包和压缩
- 考虑消息清理策略，包括定时清理，按容量清理等
- 考虑消息持久化，存入数据库，或WAL日志文件，或BookKeeper
- 考虑将spring mvc替换成netty下的tcp传输协议

第五个版本：体系完善MQ 5、对接各种技术（各条之间没有关系，可以任意选择实现）
- 考虑封装 JMS 1.1 接口规范
- 考虑实现 STOMP 消息规范
- 考虑实现消息事务机制与事务管理器
- 对接Spring
- 对接Camel或Spring Integration
- 优化内存和磁盘的使用