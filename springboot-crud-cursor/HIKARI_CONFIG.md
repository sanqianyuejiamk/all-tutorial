# HikariCP 连接池配置说明

## 概述

HikariCP 是 Spring Boot 默认的高性能 JDBC 连接池实现。本项目已配置 HikariCP 连接池，并提供了开发和生产环境的配置示例。

## 配置文件

### 1. 默认配置 (`application.yml`)
- 适用于开发环境
- 使用 H2 内存数据库
- 连接池大小：最小 5，最大 20

### 2. 开发环境配置 (`application-dev.yml`)
- 最小连接数：2
- 最大连接数：10
- 启用 SQL 日志

### 3. 生产环境配置 (`application-prod.yml`)
- 使用 MySQL 数据库
- 最小连接数：10
- 最大连接数：50
- 关闭 SQL 日志
- 启用连接泄漏检测

## 主要配置参数说明

| 参数 | 说明 | 默认值 | 推荐值 |
|------|------|--------|--------|
| `minimum-idle` | 最小空闲连接数 | 等于 maximum-pool-size | 5-10 |
| `maximum-pool-size` | 最大连接池大小 | 10 | 20-50 |
| `connection-timeout` | 连接超时时间（毫秒） | 30000 | 30000 |
| `idle-timeout` | 空闲连接最大存活时间（毫秒） | 600000 | 600000 |
| `max-lifetime` | 连接最大存活时间（毫秒） | 1800000 | 1800000 |
| `leak-detection-threshold` | 连接泄漏检测阈值（毫秒） | 0（禁用） | 60000 |

## 使用不同环境配置

### 开发环境
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 生产环境
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

或在 `application.yml` 中设置：
```yaml
spring:
  profiles:
    active: prod
```

## 连接池监控

### 1. 日志监控
项目启动时会自动记录连接池状态，之后每 5 分钟记录一次。

### 2. API 监控端点
访问 `GET /api/health/pool` 可以查看当前连接池状态。

示例响应：
```json
{
  "status": "Pool: HikariCP | Active: 2 | Idle: 3 | Total: 5 | Max: 20 | Min: 5 | Waiting: 0"
}
```

## 配置建议

### 开发环境
- `minimum-idle`: 2-5
- `maximum-pool-size`: 10-20
- 启用 SQL 日志便于调试

### 生产环境
- `minimum-idle`: 10-20
- `maximum-pool-size`: 根据数据库服务器性能调整（通常 20-100）
- 关闭 SQL 日志
- 启用连接泄漏检测
- 设置合理的 `max-lifetime`（建议 30 分钟）

## 性能优化建议

1. **连接池大小**
   - 计算公式：`connections = ((core_count * 2) + effective_spindle_count)`
   - 对于大多数应用，20-50 个连接足够

2. **连接超时**
   - `connection-timeout` 应该小于数据库服务器的超时时间
   - 默认 30 秒通常足够

3. **连接生命周期**
   - `max-lifetime` 应该比数据库服务器的连接超时时间短
   - 建议设置为 30 分钟（1800000 毫秒）

4. **连接泄漏检测**
   - 生产环境建议启用
   - `leak-detection-threshold` 设置为 60000（1 分钟）

## 切换到 MySQL

1. 在 `pom.xml` 中添加 MySQL 驱动依赖：
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. 使用 `application-prod.yml` 配置，或修改 `application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: your_username
    password: your_password
```

## 常见问题

### 1. 连接池耗尽
- 检查是否有连接泄漏（启用 `leak-detection-threshold`）
- 增加 `maximum-pool-size`
- 检查数据库服务器性能

### 2. 连接超时
- 检查网络连接
- 增加 `connection-timeout`
- 检查数据库服务器状态

### 3. 连接泄漏
- 确保所有数据库操作都在 try-with-resources 或 finally 块中关闭
- 启用连接泄漏检测
- 检查长时间运行的事务

## 参考资源

- [HikariCP 官方文档](https://github.com/brettwooldridge/HikariCP)
- [Spring Boot 数据源配置](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.datasource)

