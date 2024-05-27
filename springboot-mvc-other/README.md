## Zookeeper常用命令

### 启动zookeeper
```
/Users/hyy044101331/java_tools/zookeeper-3.4.6/bin/zkServer.sh start
```

### 进入命令行
```
bin/zkCli.sh -server 127.0.0.1:2181
```

### 创建节点数据

```
[zk: 127.0.0.1:2181(CONNECTED) 1] create /MyFirstZNode ZNodeVal
Created /MyFirstZNode
```

获取节点数据
```
[zk: 127.0.0.1:2181(CONNECTED) 5] get /MyFirstZNode
ZNodeVal
cZxid = 0x2
ctime = Sat Nov 07 15:52:12 CST 2020
mZxid = 0x2
mtime = Sat Nov 07 15:52:12 CST 2020
pZxid = 0x2
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 8
numChildren = 0
```

### 更新节点数据
```
set /MyFirstZNode ZNodeValUpdated

[zk: 127.0.0.1:2181(CONNECTED) 7] get /MyFirstZNode                
ZNodeValUpdated
```

### 代码地址
```
/Users/hyy044101331/work_farabbit/all-tutorial/springboot-mvc-other
```