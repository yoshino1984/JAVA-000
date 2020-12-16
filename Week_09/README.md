学习笔记

### Week09 作业题目（周四）：
- 3.（必做）改造自定义 RPC 的程序，提交到 GitHub：
    - 尝试将服务端写死查找接口实现类变成泛型和反射；
        - [通过注解的方式查找服务实现](./rpc01/rpcfx-demo-provider/src/main/java/io/kimmking/rpcfx/demo/provider/DemoResolver.java)
    - 尝试将客户端动态代理改成 AOP，添加异常处理；
        - [byteBuddy实现](./rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/Rpcfx.java)getByteBuddyProxy()
    - 尝试使用 Netty+HTTP 作为 client 端传输方式。
        - [使用netty方式同步client传输](./rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/netty)

### Week09 作业题目（周六）：
- 3.（必做）结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:
    - (未完成)
    - 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
    - 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
    - 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。