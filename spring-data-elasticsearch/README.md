

spring data elasticsearch 4.0

需要 elasticsearch7.0 以上

参考链接

- [官网-可以初始化项目](https://spring.io/projects/spring-data-elasticsearch) 



主要是公司同事突然找到我，需求帮助解决ES问题：他是在6.5下开发，客户方es是7.0. 结果写好的项目，连接客户es时代码就启动报错。他又不熟悉es

故此我写了这么一个demo




## 注意

spring data elasticsearch 4.0 时，es配置写成这种已经被弃用了，**别再**如下面这样写了


```yaml

spring:
  data:
    elasticsearch:
      cluster-name: my-application
      cluster-nodes: 192.168.1.6:9300
      repositories:
        enabled: true
        
```
