# springboot-graphql

### 查询Location
```
curl \
--request POST 'localhost:8071/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query{findAllLocations{id\n name}}"}'
```

### 更新Location
```
curl \
--request POST 'localhost:8071/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation{updateLocationName(newName:\"new name22\",id:\"1\"){id}}"}'
```

### 删除Location
```
curl \
--request POST 'localhost:8071/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation{deleteLocation(id:\"1\")}"}'
```

### 新增Location
```
curl \
--request POST 'localhost:8071/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation{newLocation(name:\"add new name22\",address:\"add address22\"){id}}"}'
```