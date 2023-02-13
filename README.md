### 1. 背景
在Saas环境下,要求各租户的数据是隔离的,该插件主要是为了解决这个问题,即:在进行CRUD时,可以定制化自己的data和collection.

### 2. 集成步骤
1) 添加依赖

```
<dependency>
    <groupId>help.lixin.mongodb</groupId>
    <artifactId>mongodb-sharding-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2) 配置插件(可选)
> 为什么要用JDK自定的SPI,是因为:我是对:MongoDatabaseFactory手动代理,发现在手动代理这一阶段是没有办法拿到ApplicationContext,注于:IMongoCollectionNameCustomizer和IMongoDatabaseCustomizer.  

```
# 对CollectionName进行自定义
META-INF/services/help.lixin.mongodb.plugin.IMongoCollectionNameCustomizer
# 对Database进行自定义
META-INF/services/help.lixin.mongodb.plugin.IMongoDatabaseCustomizer
```

3) 案例(参考example)

