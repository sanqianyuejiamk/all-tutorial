# README #

This branch contains code for https://dzone.com/articles/doing-more-with-springdoc-openapi.

### How do I get set up? ###
Preerquisites:
* Java 8  
* Maven 3  
* Git  

Follow steps in article- https://dzone.com/articles/doing-more-with-springdoc-openapi.
Its a simple maven based spring boot project.

Or else.
clone the project in an empty folder.   
* git clone -b springdoc-openapi-doingmore https://github.com/teq-niq/sample.git  
* cd sample  
* mvn clean package  
* java -jar target/sample-0.0.1.jar  
  

Do also read earlier article https://dzone.com/articles/openapi-3-documentation-with-spring-boot  



### 代码框架

>文章"框架_统一异常处理_01"

```
    @RequestMapping(path = "/bb", method = RequestMethod.GET)
    public List<BookDO> findByLastName(@RequestParam(name = "name", required = true) @NotNull
                                       @NotBlank
                                       @Size(max = 10) String name) {
        List<BookDO> bookDOList = new ArrayList<>();
        BookDO bookDO1 = new BookDO(101L,name);
        BookDO bookDO2 = new BookDO(102L,"数学");
        bookDOList.add(bookDO1);
        bookDOList.add(bookDO2);

        int nxt = ran.nextInt(10);
        if (nxt >= 5) {
            throw new RuntimeException("Breaking logic");
        }

        return bookDOList;
    }
```
