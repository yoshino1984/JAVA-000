学习笔记
### 第十二周作业
1. （必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。
   1. [config配置文件](./config)
   2. 启动和操作、验证集群下数据读写的命令步骤。
   
```shell
# 启动 redis，replica命令在config中，以6376为master
redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6376.conf

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6377.conf

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6378.conf
```
```shell

#验证主从
redis-cli -p 6376      
       
127.0.0.1:6376> info Replication
# Replication
role:master
connected_slaves:2
slave0:ip=127.0.0.1,port=6377,state=online,offset=60741,lag=0
slave1:ip=127.0.0.1,port=6378,state=online,offset=60741,lag=0
master_replid:7d0ecb17b62c266085a1251d19055016407e248c
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:60741
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:60741
```

```shell
# 哨兵启动
redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/sentinel26376.conf --sentinel

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/sentinel26377.conf --sentinel

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/sentinel26377.conf --sentinel

# 哨兵集群验证
% redis-cli -p 26376
127.0.0.1:26376> info Sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=127.0.0.1:6376,slaves=2,sentinels=3
```

- cluster
```shell
sed redis6376.conf 's/6376/6375/g' >> redis6375.conf
sed redis6376.conf 's/6376/6374/g' >> redis6374.conf

# 启动节点 6376 6375 6374 用以组建最小的cluster集群 
redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6376.conf

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6375.conf

redis-server /Users/xiaoyi/IdeaProjects/git/JAVA-000/Week_12/config/redis6374.conf

redis-cli --cluster create 127.0.0.1:6376 127.0.0.1:6375 127.0.0.1:6374
```
```text
>>> Performing hash slots allocation on 3 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
M: a77c687f200b6cd3b39f020cee2090c1cb34cb02 127.0.0.1:6376
   slots:[0-5460] (5461 slots) master
M: c1fee66c6bcb51b3356a80cd4fe89a41c8dfcf77 127.0.0.1:6375
   slots:[5461-10922] (5462 slots) master
M: e2cf5a6eb4f59af0bc6d22dc41d745173e7dd3ee 127.0.0.1:6374
   slots:[10923-16383] (5461 slots) master
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join

>>> Performing Cluster Check (using node 127.0.0.1:6376)
M: a77c687f200b6cd3b39f020cee2090c1cb34cb02 127.0.0.1:6376
   slots:[0-5460] (5461 slots) master
M: e2cf5a6eb4f59af0bc6d22dc41d745173e7dd3ee 127.0.0.1:6374
   slots:[10923-16383] (5461 slots) master
M: c1fee66c6bcb51b3356a80cd4fe89a41c8dfcf77 127.0.0.1:6375
   slots:[5461-10922] (5462 slots) master
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```
集群验证
```text
% redis-cli -p 6376                                                      
127.0.0.1:6376> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:3
cluster_size:3
cluster_current_epoch:3
cluster_my_epoch:1
cluster_stats_messages_ping_sent:30
cluster_stats_messages_pong_sent:30
cluster_stats_messages_publish_sent:90
cluster_stats_messages_sent:150
cluster_stats_messages_ping_received:28
cluster_stats_messages_pong_received:30
cluster_stats_messages_meet_received:2
cluster_stats_messages_received:60
```



---

2. （选做）练习示例代码里下列类中的作业题:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

3. （选做☆）练习 redission 的各种功能。

4. （选做☆☆）练习 hazelcast 的各种功能。

5. （选做☆☆☆）搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。