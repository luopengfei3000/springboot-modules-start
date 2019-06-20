## SpringBoot 快速开启事务

#### 方法一：使用@Transactional注解
1. 在启动类中添加开启事务注解
```
@SpringBootApplication
@EnableTransactionManagement//开启事务支持
public class SpringbootTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTransactionalApplication.class, args);
    }

}
```

2. 在service类中需要事务的方法上添加注解@Transactional
```
@Transactional
public void saveOrUpdateUser() throws Exception{
    try {
        User u = new User();
        u.setId(new Random().nextInt(10000) +1);
        u.setAge(88);
        u.setName("88");
        userMapper.addUser(u);
        u.setId(null);
        userMapper.addUser(u);//id不为空，发生异常
    }catch (Exception e){
        e.printStackTrace();;
        throw new Exception();
    }
}

```
**注意:如果适用try...catch抛出异常，则catch中必须使用throw new Exception()抛出异常，否则事务不回滚。**

#### 方法二：注解事务声明式事务的方式

1. 编写类TxAdviceInterceptor
```
@Aspect
//@Component 事务依然生效
@Configuration
public class TxAdviceInterceptor {

    private static final int TX_METHOD_TIMEOUT = 5;
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.example.springboottransactional..service.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
        //return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
```
**类似于springmvc xml中配置的使用，insert,update,delete等开头方法一般添加事务处理**

**[Springboot 事务不回滚问题总结](http://note.youdao.com/noteshare?id=9e99388f15cdf87049c3cf30e775ff5d)**


   



