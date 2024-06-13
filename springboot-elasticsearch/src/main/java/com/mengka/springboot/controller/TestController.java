package com.mengka.springboot.controller;

import com.mengka.springboot.domain.Article;
import com.mengka.springboot.domain.Author;
import com.mengka.springboot.domain.Book;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.ArticleRepository;
import com.mengka.springboot.repository.BookRepository;
import com.mengka.springboot.service.NoteService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author mengka
 * @Date 2021-12-15 17:31
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NoteService noteService;

    private static final boolean bucket4j = false;

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     *  更多例子
     *  /Users/hyy044101331/work_github/work_baeldung_demo/tutorials/persistence-modules/spring-data-elasticsearch
     *
     * @return
     */
    @GetMapping("t1")
    public ResponseEntity<String> t1() {
        log.info("-------, 调用t1接口");

        final Author johnSmith = new Author("John Smith");
        final Author johnDoe = new Author("John Doe");

        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(asList(johnSmith, johnDoe));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Search engines");
        article.setAuthors(asList(johnDoe));
        article.setTags("search engines", "tutorial");
        articleRepository.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(asList(johnSmith));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(asList(johnDoe));
        article.setTags("elasticsearch");
        articleRepository.save(article);

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("t2")
    public ResponseEntity<String> t2() {
        log.info("-------, 调用t2接口");

        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameUsingCustomQuery("Smith", PageRequest.of(0, 10));
        long total = articleByAuthorName.getTotalElements();
        List<Article> articles = articleByAuthorName.getContent();
        articles.stream().forEach(e -> log.info(e.toString()));

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("t3")
    public ResponseEntity<String> t3() {
        log.info("-------, 调用t3接口");

        final QueryBuilder builder = nestedQuery("authors", boolQuery().must(termQuery("authors.name", "smith")), ScoreMode.None);

        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder)
                .build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        long total = articles.getTotalHits();
        List<SearchHit<Article>> searchHits = articles.getSearchHits();
        List<Article> articles1 = searchHits.stream().map(e -> e.getContent()).collect(Collectors.toList());
        articles1.stream().forEach(e -> log.info(e.toString()));

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("t4")
    public ResponseEntity<String> t4() {
        log.info("-------, 调用t4接口");

        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("title", "spring elasticsearch").slop(1))
                .build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        long total = articles.getTotalHits();
        List<SearchHit<Article>> searchHits = articles.getSearchHits();
        List<Article> articles1 = searchHits.stream().map(e -> e.getContent()).collect(Collectors.toList());
        articles1.stream().forEach(e -> log.info(e.toString()));
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("t5")
    public ResponseEntity<String> t5() {
        log.info("-------, 调用t5接口");

        final Query searchQuery = new NativeSearchQueryBuilder().withFilter(regexpQuery("title", ".*tutorial.*"))
                .build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        long total = articles.getTotalHits();
        List<SearchHit<Article>> searchHits = articles.getSearchHits();
        List<Article> articles1 = searchHits.stream().map(e -> e.getContent()).collect(Collectors.toList());
        articles1.stream().forEach(e -> log.info(e.toString()));

        return ResponseEntity.ok("Hello World");
    }

    /**
     *  http://127.0.0.1:8071/world
     *
     * @return
     */
    @GetMapping("world")
    public ResponseEntity<String> world() {
        log.info("-------, 调用hello接口");


        bookRepository.updateNameById("英语",1L);


        List<Book> books = bookRepository.findByNameAndPriceAndTenantId("数学",100L,"2002");

        if(bucket4j){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok("Hello World");
    }

    /**
     *  分页查询
     *
     *  http://127.0.0.1:8071/t1/2
     *
     *  【0,10】第一页
     *  【1,10】第二页
     *  【2,10】第三页
     *
     * @param pageNum
     * @return
     */
    @GetMapping("t1/{pageNum}")
    public ResponseEntity<String> t1Page(@PathVariable int pageNum) {
        log.info("-------, 调用t1 page接口");

        Page<Note> list = noteService.getNoteList(pageNum,10);
        Iterator<Note> u = list.iterator();
        while (u.hasNext()){
            log.info(new Gson().toJson(u.next()));
        }

        return ResponseEntity.ok("Hello World");
    }
}
