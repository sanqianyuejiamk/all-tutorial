# springboot-d2c-demo

### 接口重试
```
http://127.0.0.1:8072/getInvoice
```

```
[2021-12-04 15:40:55,969] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 36 getInvoice() call starts here 
[2021-12-04 15:41:01,036] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 36 getInvoice() call starts here 
[2021-12-04 15:41:06,042] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 36 getInvoice() call starts here 
[2021-12-04 15:41:11,046] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 36 getInvoice() call starts here 
[2021-12-04 15:41:16,052] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 36 getInvoice() call starts here 
[2021-12-04 15:41:16,056] [http-nio-8072-exec-1] [INFO] -  c.f.s.c.RetryController 43 ---RESPONSE FROM FALLBACK METHOD--- 
```

### 接口超时
```
http://127.0.0.1:8072/futureTimeout
```

超时时间设置3s
```
-------futureTimeout, 2864ms
-------futureTimeout, 3004ms  //超时
-------futureTimeout, 3014ms  //超时
-------futureTimeout, 3006ms  //超时
-------futureTimeout, 3005ms  //超时
-------futureTimeout, 1387ms
```

### Retry参考文档
1. https://javatechonline.com/how-to-implement-fault-tolerance-in-microservices-using-resilience4j/
2. https://www.infoq.cn/article/l3b9wcuxqjh1drfjei87