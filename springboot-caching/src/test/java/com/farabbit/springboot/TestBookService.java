package com.farabbit.springboot;

import com.farabbit.springboot.config.CacheConfig;
import com.farabbit.springboot.domain.Book;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.BookService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import redis.embedded.RedisServer;
import java.io.IOException;
import java.util.Optional;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author mengka
 * @Date 2024-06-16 15:34
 */
@Slf4j
@Import({ CacheConfig.class, BookService.class })
//@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@ImportAutoConfiguration(classes = { CacheAutoConfiguration.class, RedisAutoConfiguration.class })
@EnableCaching
public class TestBookService {

    @Autowired
    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

//    @Before
//    public void before(){
//        Book note = new Book().setName("数学o1").setPrice(100).setTenantId("2001");
//
//        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(note));
//    }

    @Test
    public void test_find_note(){
        Book note = new Book().setName("数学o1").setPrice(100).setTenantId("2001");
        given(bookRepository.findById(1L))
                .willReturn(Optional.of(note));

        Book oneNoteMiss = bookService.getBookById(1L);
        Book oneNoteHit = bookService.getBookById(1L);
        log.info("oneNote = {}",new Gson().toJson(oneNoteMiss));

        assertThat(oneNoteMiss).isEqualTo(note);
        assertThat(oneNoteHit).isEqualTo(note);

        Assert.assertEquals("数学o1", oneNoteMiss.getName());
        assertThat(itemFromCache()).isEqualTo(note);
    }

    private Object itemFromCache() {
        return cacheManager.getCache("itemCache").get(1L).get();
    }

    @TestConfiguration
    static class EmbeddedRedisConfiguration {

        private final RedisServer redisServer;

        public EmbeddedRedisConfiguration() throws IOException {
            this.redisServer = new RedisServer();
        }

        @PostConstruct
        public void startRedis() throws IOException {
            redisServer.start();
        }

        @PreDestroy
        public void stopRedis() throws IOException {
            this.redisServer.stop();
        }
    }
}
