### 使用教程
> 温馨提示：Thymeleaf 最为显著的特征是增强属性，任何属性都可以通过th:xx 来完成交互，例如th:value最终会覆盖value属性。

#### 一、基础语法
##### 变量表达式  ${}
使用方法：直接使用th:xx = "${}" 获取对象属性 。例如：
```
<form id="userForm">
    <input id="id" name="id" th:value="${user.id}"/>
    <input id="username" name="username" th:value="${user.username}"/>
    <input id="password" name="password" th:value="${user.password}"/>
</form>

<div th:text="hello"></div>

<div th:text="${user.username}"></div>
```
##### 选择变量表达式 *{}
使用方法：首先通过th:object 获取对象，然后使用th:xx = "*{}"获取对象属性。
```
<form id="userForm" th:object="${user}">
    <input id="id" name="id" th:value="*{id}"/>
    <input id="username" name="username" th:value="*{username}"/>
    <input id="password" name="password" th:value="*{password}"/>
</form>
```
##### 链接表达式 @{}
使用方法：通过链接表达式@{}直接拿到应用路径，然后拼接静态资源路径。例如：
```
<script th:src="@{/webjars/jquery/jquery.js}"></script>
<link th:href="@{/webjars/bootstrap/css/bootstrap.css}" rel="stylesheet" type="text/css">
```
##### 片段表达式 ~{}
片段表达式是Thymeleaf的特色之一，细粒度可以达到标签级别，这是JSP无法做到的。
片段表达式拥有三种语法：

- ~{ viewName } 表示引入完整页面
- ~{ viewName ::selector} 表示在指定页面寻找片段 其中selector可为片段名、jquery选择器等
- ~{ ::selector} 表示在当前页寻找

使用方法：首先通过th:fragment定制片段 ，然后通过th:replace 填写片段路径和片段名。例如：
```
<head th:fragment="static">
        <script th:src="@{/webjars/jquery/3.3.1/jquery.js}"></script>
</head>

<div th:replace="~{common/head::static}"></div>
```
在实际使用中，我们往往使用更简洁的表达，去掉表达式外壳直接填写片段名。例如：
```
<div th:replace="common/head::static"></div>
```
值得注意的是，使用替换路径th:replace 开头请勿添加斜杠，避免部署运行的时候出现路径报错。（因为默认拼接的路径为spring.thymeleaf.prefix = classpath:/templates/）
##### 消息表达式
即通常的国际化属性：#{msg} 用于获取国际化语言翻译值。例如：
```
<title th:text="#{user.title}"></title>
```
##### 其它表达式
在基础语法中，默认支持字符串连接、数学运算、布尔逻辑和三目运算等。例如：
```
<input name="name" th:value="${'I am '+(user.name!=null?user.name:'NoBody')}"/>
```
#### 二、迭代循环
想要遍历List集合很简单，配合th:each 即可快速完成迭代。例如遍历用户列表：
```
<div th:each="user:${userList}">
    账号：<input th:value="${user.username}"/>
    密码：<input th:value="${user.password}"/>
</div>
```
在集合的迭代过程还可以获取状态变量，只需在变量后面指定状态变量名即可，状态变量可用于获取集合的下标/序号、总数、是否为单数/偶数行、是否为第一个/最后一个。例如：
```
<div th:each="user,stat:${userList}" th:class="${stat.even}?'even':'odd'">
    下标：<input th:value="${stat.index}"/>
    序号：<input th:value="${stat.count}"/>
    账号：<input th:value="${user.username}"/>
    密码：<input th:value="${user.password}"/>
</div>
```
如果缺省状态变量名，则迭代器会默认帮我们生成以变量名开头的状态变量 xxStat， 例如：
```
<div th:each="user:${userList}" th:class="${userStat.even}?'even':'odd'">
    下标：<input th:value="${userStat.index}"/>
    序号：<input th:value="${userStat.count}"/>
    账号：<input th:value="${user.username}"/>
    密码：<input th:value="${user.password}"/>
</div>
```
#### 三、条件判断
条件判断通常用于动态页面的初始化，例如：
```
<div th:if="${userList}">
    <div>的确存在..</div>
</div>
```
如果想取反则使用unless 例如：
```
<div th:unless="${userList}">
    <div>不存在..</div>
</div>
```
#### 四、日期格式化
使用默认的日期格式(toString方法) 并不是我们预期的格式：Mon Dec 03 23:16:50 CST 2018
```
<input type="text" th:value="${user.createTime}"/>
```
此时可以通过时间工具类#dates来对日期进行格式化：2018-12-03 23:16:50
```
<input type="text" th:value="${#dates.format(user.createTime,'yyyy-MM-dd HH:mm:ss')}"/>
```
#### 五、内联写法
（1）为什么要使用内联写法？答：因为 JS无法获取服务端返回的变量。<br/>
（2）如何使用内联表达式？答：标准格式为：[[${xx}]] ，可以读取服务端变量，也可以调用内置对象的方法。例如获取用户变量和应用路径：
```
<script th:inline="javascript">
    var user = [[${user}]];`
    var APP_PATH = [[${#request.getContextPath()}]];
    var LANG_COUNTRY = [[${#locale.getLanguage()+'_'+#locale.getCountry()}]];
</script>
```
（3）标签引入的JS里面能使用内联表达式吗？答：不能！内联表达式仅在页面生效，因为Thymeleaf只负责解析一级视图，不能识别外部标签JS里面的表达式。
#### 六、国际化
例如在国际化文件中编写了user.title这个键值，然后使用#{}读取这个KEY即可获取翻译。
```
<title th:text="#{user.title}">用户登陆</title>
```