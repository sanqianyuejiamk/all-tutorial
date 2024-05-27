## Redis-aop使用

### maven依赖
```
		<dependency>
			<groupId>com.bkatwal</groupId>
			<artifactId>redis-aop</artifactId>
			<version>1.0.1-SNAPSHOT</version>
		</dependency>
```

```java
@Service
public class MengkaManager {

    @CachePut(cacheNames = {
            "PUT_CACHE1" }, keyExpression = "#param1,#param2")
    public TestPojo saveByIdAndName(int id, String name) {
        return new TestPojo(id, name);
    }
}
```

```
        mengkaManager.saveByIdAndName(1, "testName");

        TestPojo testPojo = (TestPojo) redisCacheService
                .getFromCache("PUT_CACHE1", CacheUtil.buildCacheKey(new Object[] { 1, "testName" }));
        logger.info("--------, testPojo = {}",new Gson().toJson(testPojo));
```

### Redis命令
Kyro序列化
```
/Users/hyy044101331/java_tools/redis-6.2.1
./redis-cli -h 127.0.0.1 -p 6379
```

#### 查看所有keys
```
127.0.0.1:6379> keys CACHE
1) "CACHE"

127.0.0.1:6379> type CACHE
hash
```

### 查看hash key
```
127.0.0.1:6379> hkeys CACHE
1) "\xac\xed\x00\x05t\x00\x04key1"
```

### 查看hash value
```
127.0.0.1:6379> hvals CACHE
1) "\x03\x01valu\xe5"
```


