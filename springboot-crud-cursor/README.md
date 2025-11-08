# Spring Boot CRUD 示例项目

这是一个完整的Spring Boot CRUD操作示例项目，演示了用户管理和图书管理的基本功能。

## 功能特性

### 用户管理
- ✅ 创建用户 (Create)
- ✅ 查询用户 (Read)
- ✅ 更新用户 (Update)
- ✅ 删除用户 (Delete)

### 图书管理
- ✅ 创建图书 (Create)
- ✅ 查询图书 (Read)
- ✅ 更新图书 (Update)
- ✅ 删除图书 (Delete)
- ✅ 按作者查询图书
- ✅ 按书名搜索图书

### 通用功能
- ✅ 数据验证
- ✅ 异常处理
- ✅ RESTful API设计

## 技术栈

- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (内存数据库)
- **Lombok** (简化代码)
- **Java 17**

## 项目结构

```
src/
├── main/
│   ├── java/com/example/cruddemo/
│   │   ├── CrudDemoApplication.java      # 主应用类
│   │   ├── controller/
│   │   │   ├── UserController.java       # 用户REST控制器
│   │   │   └── BookController.java       # 图书REST控制器
│   │   ├── service/
│   │   │   ├── UserService.java          # 用户业务逻辑层
│   │   │   └── BookService.java          # 图书业务逻辑层
│   │   ├── repository/
│   │   │   ├── UserRepository.java       # 用户数据访问层
│   │   │   └── BookRepository.java       # 图书数据访问层
│   │   ├── entity/
│   │   │   ├── User.java                 # 用户实体类
│   │   │   └── Book.java                 # 图书实体类
│   │   ├── dto/
│   │   │   ├── UserDto.java              # 用户请求DTO
│   │   │   ├── UserResponseDto.java      # 用户响应DTO
│   │   │   ├── BookDto.java              # 图书请求DTO
│   │   │   └── BookResponseDto.java      # 图书响应DTO
│   │   └── exception/
│   │       ├── ResourceNotFoundException.java
│   │       ├── DuplicateEmailException.java
│   │       ├── DuplicateIsbnException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.yml               # 配置文件
└── pom.xml                                # Maven配置
```

## 快速开始

### 前置要求

- JDK 17 或更高版本
- Maven 3.6+ 

### 运行步骤

1. **克隆或下载项目**

2. **编译项目**
   ```bash
   mvn clean compile
   ```

3. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

   或者使用IDE直接运行 `CrudDemoApplication.java`

4. **访问应用**
   - API基础URL: `http://localhost:8080`
   - H2控制台: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - 用户名: `sa`
     - 密码: (留空)

## API 文档

## 用户管理 API

### 1. 创建用户

**POST** `/api/users`

**请求体:**
```json
{
  "name": "张三",
  "email": "zhangsan@example.com",
  "age": 25
}
```

**响应:**
```json
{
  "id": 1,
  "name": "张三",
  "email": "zhangsan@example.com",
  "age": 25,
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 2. 获取所有用户

**GET** `/api/users`

**响应:**
```json
[
  {
    "id": 1,
    "name": "张三",
    "email": "zhangsan@example.com",
    "age": 25,
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 3. 根据ID获取用户

**GET** `/api/users/{id}`

**响应:**
```json
{
  "id": 1,
  "name": "张三",
  "email": "zhangsan@example.com",
  "age": 25,
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 4. 更新用户

**PUT** `/api/users/{id}`

**请求体:**
```json
{
  "name": "李四",
  "email": "lisi@example.com",
  "age": 30
}
```

**响应:**
```json
{
  "id": 1,
  "name": "李四",
  "email": "lisi@example.com",
  "age": 30,
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T11:00:00"
}
```

### 5. 删除用户

**DELETE** `/api/users/{id}`

**响应:** `204 No Content`

## 图书管理 API

### 1. 创建图书

**POST** `/api/books`

**请求体:**
```json
{
  "title": "Spring Boot实战",
  "author": "张三",
  "isbn": "978-7-121-12345-6",
  "price": 59.99,
  "publishDate": "2024-01-01"
}
```

**响应:**
```json
{
  "id": 1,
  "title": "Spring Boot实战",
  "author": "张三",
  "isbn": "978-7-121-12345-6",
  "price": 59.99,
  "publishDate": "2024-01-01",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 2. 获取所有图书

**GET** `/api/books`

**响应:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot实战",
    "author": "张三",
    "isbn": "978-7-121-12345-6",
    "price": 59.99,
    "publishDate": "2024-01-01",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 3. 根据ID获取图书

**GET** `/api/books/{id}`

**响应:**
```json
{
  "id": 1,
  "title": "Spring Boot实战",
  "author": "张三",
  "isbn": "978-7-121-12345-6",
  "price": 59.99,
  "publishDate": "2024-01-01",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 4. 根据作者获取图书

**GET** `/api/books/author/{author}`

**响应:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot实战",
    "author": "张三",
    "isbn": "978-7-121-12345-6",
    "price": 59.99,
    "publishDate": "2024-01-01",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 5. 根据书名搜索图书

**GET** `/api/books/search?title={title}`

**示例:** `GET /api/books/search?title=Spring`

**响应:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot实战",
    "author": "张三",
    "isbn": "978-7-121-12345-6",
    "price": 59.99,
    "publishDate": "2024-01-01",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 6. 更新图书

**PUT** `/api/books/{id}`

**请求体:**
```json
{
  "title": "Spring Boot高级实战",
  "author": "李四",
  "isbn": "978-7-121-12345-7",
  "price": 79.99,
  "publishDate": "2024-02-01"
}
```

**响应:**
```json
{
  "id": 1,
  "title": "Spring Boot高级实战",
  "author": "李四",
  "isbn": "978-7-121-12345-7",
  "price": 79.99,
  "publishDate": "2024-02-01",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-02-01T11:00:00"
}
```

### 7. 删除图书

**DELETE** `/api/books/{id}`

**响应:** `204 No Content`

## 测试示例

### 使用 cURL

```bash
# 创建用户
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"张三","email":"zhangsan@example.com","age":25}'

# 获取所有用户
curl http://localhost:8080/api/users

# 获取指定用户
curl http://localhost:8080/api/users/1

# 更新用户
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"李四","email":"lisi@example.com","age":30}'

# 删除用户
curl -X DELETE http://localhost:8080/api/users/1

# 创建图书
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot实战","author":"张三","isbn":"978-7-121-12345-6","price":59.99,"publishDate":"2024-01-01"}'

# 获取所有图书
curl http://localhost:8080/api/books

# 获取指定图书
curl http://localhost:8080/api/books/1

# 根据作者查询图书
curl http://localhost:8080/api/books/author/张三

# 搜索图书
curl "http://localhost:8080/api/books/search?title=Spring"

# 更新图书
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot高级实战","author":"李四","isbn":"978-7-121-12345-7","price":79.99,"publishDate":"2024-02-01"}'

# 删除图书
curl -X DELETE http://localhost:8080/api/books/1
```

### 使用 Postman

1. 导入API集合（可选）
2. 设置Base URL为 `http://localhost:8080`
3. 按照上述API文档进行测试

## 数据验证

项目包含以下验证规则：

### 用户验证规则
- **name**: 不能为空
- **email**: 不能为空，必须是有效的邮箱格式
- **age**: 不能为空，必须大于0

### 图书验证规则
- **title**: 不能为空
- **author**: 不能为空
- **price**: 不能为空，必须大于0
- **isbn**: 可选，如果提供则必须唯一

## 异常处理

项目包含全局异常处理，会返回友好的错误信息：

- `404`: 资源未找到
- `409`: 邮箱重复 / ISBN重复
- `400`: 参数验证失败
- `500`: 服务器内部错误

## 注意事项

- 本项目使用H2内存数据库，数据在应用重启后会丢失
- 如需持久化数据，可以配置MySQL、PostgreSQL等数据库
- 生产环境请关闭H2控制台并配置适当的数据库连接

## 扩展建议

- 添加分页和排序功能
- 添加搜索和过滤功能
- 添加用户认证和授权
- 添加单元测试和集成测试
- 集成Swagger/OpenAPI文档
- 添加日志记录
- 配置生产环境数据库

## 许可证

ISC


