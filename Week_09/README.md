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
    - [account操作](./homework0902/demo0902-order/src/main/java/com/yoshino/order/service/impl/OrderServiceImpl.java)
    - [account操作](./homework0902/demo0902-account/src/main/java/com/yoshino/account/service/impl/AccountServiceImpl.java)
    - [sql](./homework0902/sql/hmily-demo.sql)
    - 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
    - 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
    - 只设计了订单表和账户表，账户表分为rmb账户和dollar账户\
    流程: 下单然后根据订单id，开启分布式事务进行账户的扣减操作
        1. try阶段：扣减A账户的rmb余额，增加rmb冻结数量；扣减B账户的dollar余额，增加dollar冻结数量
        2. confirm阶段：扣减A账户的rmb冻结数量，并增加A账户的dollar余额；扣减B账户的dollar冻结数量，并增加B账户的rmb余额
        3. cancel阶段：回退try阶段的操作