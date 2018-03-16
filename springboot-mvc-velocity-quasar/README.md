# springboot-mvc-velocity-quasar

## quasar开源协程库
```
<dependency> 
  <groupId>co.paralleluniverse</groupId> 
  <artifactId>quasar-core</artifactId> 
  <version>0.7.6</version> 
</dependency>
```


### javaagent启动参数
quasar fiber的运行需要织入一些指令，用于调用栈的保存和恢复，quasar提供了三种方式进行织入(AOT、javaagent、ClassLoader)
```
jvm启动参数：-javaagent:quasar-core-0.7.6.jar
```

### spring boot quasar
```
        <dependency>
            <groupId>co.paralleluniverse</groupId>
            <artifactId>comsat-spring-boot</artifactId>
            <version>0.7.0</version>
        </dependency>
```

```
mvn clean install
java -javaagent:../quasar-core-0.7.6.jar -jar spring-boot-1.0-SNAPSHOT-cap.jar
```