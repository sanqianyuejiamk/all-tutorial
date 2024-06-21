package com.farabbit.springboot.service;

import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     *  https://www.baeldung.com/spring-cache-tutorial
     *  https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-caching-2
     *
     *
     *  @Cacheable(value = "product", keyGenerator = "customKeyGenerator")
     *
     *  自定义key：
     *   https://medium.com/simform-engineering/spring-boot-caching-with-redis-1a36f719309f
     *
     *  参考：spring_cache_x03
     *
     *  /Users/hyy044101331/work_github/work_demo/spring-boot-redis-example
     *
     * @param id
     * @return
     */
    @Cacheable(value = "itemCache", key = "#id")
    public Book getBookById(long id){
        log.info("BookService getBookById id = "+id);
        Book book63 = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        return book63;
    }

    @CachePut(cacheNames = "itemCache", key = "#book.id")
    public Book editBook(Book book){
        book.setCreatedAt(new Date());
        book.addFeature("x4","test222");
        book.addFeature("x5","test333");
        return bookRepository.save(book);
    }

    @CachePut(cacheNames = "itemCache", key = "#book.id")
    public Book insertBook(Book book){
        book.setCreatedAt(new Date());
        return bookRepository.save(book);
    }

    @CacheEvict(cacheNames = "itemCache", key = "#id", beforeInvocation = true)
    public String removeBookById(long id){
        bookRepository.deleteById(id);
        return "success";
    }

    @Cacheable("itemCache")
    public List<Book> findAll() {
        log.info("-----, findAll..");
        return bookRepository.findAll();
    }

    /**
     *  127.0.0.1:6379> keys *itemCache*
     * 1) "itemCache::\xe6\x95\xb0\xe5\xad\xa6x1"
     * 2) "itemCache::SimpleKey []"
     *
     * @param name
     * @return
     */
    @Cacheable("itemCache")
    public List<Book> findByName(String name) {
        log.info("-----, findByName..");
        return bookRepository.findByName(name);
    }
}
