package com.mkyong.t_01;

import com.google.gson.Gson;
import com.mkyong.transfor.BookTransfer;
import com.mkyong.utils.TidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
@Slf4j
public class taa {

    private final BookTransfer userTransf = BookTransfer.INSTANCE;

    @Test
    public void test1(){
        TidGenerator generator = new TidGenerator(10);
        BookDTO bookDTO = BookDTO.builder().id("101").name("天文学").reqstNo(generator.nextTid()).build();
        BookDO bookDO = userTransf.toDO(bookDTO);

        System.out.println("---xxx--- bookDO: "+new Gson().toJson(bookDO));
    }

    @Test
    public void test2(){
        TidGenerator generator = new TidGenerator(10);
        BookDO bookDO = BookDO.builder().id("101").name("天文学").build();
        bookDO.setReqstNo(generator.nextTid());
        bookDO.setBookAddr(new BookAddrDO("361000","杭州拱墅区"));
        BookDTO bookDTO = userTransf.toModel(bookDO);

        System.out.println("---xxx--- bookDTO: "+new Gson().toJson(bookDTO));
    }
}
