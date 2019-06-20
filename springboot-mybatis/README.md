## springboot中对数据库密码加密
 
1、 pom文件加入依赖，如图：
```
<dependency>
   <groupId>com.github.ulisesbocchio</groupId>
   <artifactId>jasypt-spring-boot-starter</artifactId>
   <version>2.1.0</version>
</dependency>
```

2、 生成密钥
- 找到你本地maven仓库的jasypt的jar包，在该目录下打开cmd命令窗口（例如jar在D盘）
    1. d:   
    2. cd:D:\workspaceV6R3\m2-maven3.5.4\repository\org\jasypt\jasypt\1.9.2
    3. 执行 java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="cape" password=test algorithm=PBEWithMD5AndDES

其中input为你的明文密码，密码为cape，password为你的私钥，algorithm这个是一个规则，切勿更改！！！

生成的OUTPUT（例如：WzutA7LJi4puujqp6Qh++A==）就是加密之后的密文（密码）

3、 springboot配置文件中添加配置
```
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/springboottest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
    username: root
    password: ENC(WzutA7LJi4puujqp6Qh++A==)
jasypt:
  encryptor:
    password: test
```
**注意:第一个password对应第二步中ARGUEMENTS中的password，第二个password对应第二步中OUTPUT中的结果，形式一定要加上ENC(you password)**

4、 通过命令解密密码(和生成密钥方法一样)
> java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="WzutA7LJi4puujqp6Qh++A==" password=test algorithm=PBEWithMD5AndDES


   



