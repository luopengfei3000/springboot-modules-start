## SpringBoot整合定时任务和异步任务

### 一、SpringBoot定时任务概述

#### SpringBoot使用注解方式开启定时任务

- 启动类里面 @EnableScheduling开启定时任务，自动扫描
- 定时任务业务类 加注解 @Component被容器扫描
- 定时执行的方法加上注解 @Scheduled(fixedRate=2000) 定期执行一次

#### SpringBoot常用定时任务配置
1、cron 定时任务表达式 ：
```
@Scheduled(cron="*/1 * * * * *") 表示每秒执行一次
```
crontab 工具 https://tool.lu/crontab/

```
Java(Spring)
*    *    *    *    *    *    *
-    -    -    -    -    -    -
|    |    |    |    |    |    |
|    |    |    |    |    |    + year [optional]
|    |    |    |    |    +----- day of week (0 - 7) (Sunday=0 or 7)
|    |    |    |    +---------- month (1 - 12)
|    |    |    +--------------- day of month (1 - 31)
|    |    +-------------------- hour (0 - 23)
|    +------------------------- min (0 - 59)
+------------------------------ second (0 - 59)
```

### 二、SpringBoot 异步任务

1. 启动类里面使用@EnableAsync注解开启功能，自动扫描

2. 定义异步任务类并使用@Component标记组件被容器扫描,异步方法加上@Async

注意点： 
- 要把异步任务封装到类里面，不能直接写到Controller 
- 增加Future<String> 并返回结果 AsyncResult<String>("task执行完成"); 
- 如果需要拿到结果 需要判断全部的 task.isDone()
