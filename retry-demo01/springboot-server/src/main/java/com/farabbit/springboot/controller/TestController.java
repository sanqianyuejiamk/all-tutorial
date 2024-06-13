package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.*;
import com.farabbit.springboot.repository.BookRepository;
import com.farabbit.springboot.service.NoteService;
import com.farabbit.springboot.utils.TimeUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static Map<String, AtomicInteger> reqMap = new ConcurrentHashMap();

    private static final boolean bucket4j = false;
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

    @RequestMapping(path = "/r1", method = RequestMethod.POST)
    public ResponseEntity<RetryRspDto> r1(@RequestBody RetryReq retryReq) throws Exception{
        log.info("---user--- {}",new Gson().toJson(retryReq));


        AtomicInteger count = reqMap.get(retryReq.getRequestNo());
        if(count == null){
            count = new AtomicInteger(0);
        }

        Thread.sleep(new Random().nextInt(10)*1000);

        String result = retryReq.getName() + "-->>" + TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);

        if(count.incrementAndGet()<3){
            log.info(count.get()+" < 3 ,"+retryReq.getRequestNo()+" fail..");
            reqMap.put(retryReq.getRequestNo(),count);
            return new ResponseEntity(new RetryRspDto().setRetCode("-1").setMessage(result),HttpStatus.TOO_MANY_REQUESTS);
        }

        log.info(count.get()+" >= 3 ,"+retryReq.getRequestNo()+" success..");
        return ResponseEntity.ok(new RetryRspDto().setRetCode("0").setMessage(result));
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

    @GetMapping("/t2")
    public ResponseEntity<String> t2() {
        log.info("---user--- ");

//        Book book = new Book().setName("数学x1").setPrice(101L).setTenantId("2001").setCreatedAt(new Date());
//        book.addFeature("x1","test111");
//        bookRepository.save(book);

        return ResponseEntity.ok("Hello World");
    }
}
