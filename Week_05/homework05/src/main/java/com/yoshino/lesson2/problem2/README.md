
### maven/spring的profile机制，都有什么用法？

- spring的profile支持通过在主配置文件中配置spring.profile.active指定不同环境配置文件
- maven的profile可以作为spring的profile配置文件的补充：
    - 在打包的时候 通过 package -P value 指定spring 配置文件中 spring.profile.active的值来动态的给不同环境打包，更加方便

组合打包方式的优点：
1. 该方式优点：打包后不需要通过命令行参数来切换不同环境的配置文件，把指定环境的这一步放到了maven打包的命令上
2. 该方式其实是利用了maven的profile功能和SpringBoot的profile相结合使用
    
```text
spring:
  profiles:
    active: @activedProperties@
```

```text
<profiles>
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <activedProperties>dev</activedProperties>
            </properties>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <activedProperties>test</activedProperties>
            </properties>
        </profile>
    </profiles>
```

```text
mvn clean package -P profile的id
```
