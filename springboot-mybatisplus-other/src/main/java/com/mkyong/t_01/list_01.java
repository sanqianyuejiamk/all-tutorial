package com.mkyong.t_01;

import com.google.gson.Gson;
import com.mkyong.transfor.BookTransfer;
import com.mkyong.utils.TidGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
public class list_01 {

    private final BookTransfer userTransf = BookTransfer.INSTANCE;

    @Test
    public void test_list(){
        TidGenerator generator = new TidGenerator(10);
        List<BookDO> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            BookDO bookDO = BookDO.builder().id("101"+i).name("天文学_x"+i).build();
            bookDO.setReqstNo(generator.nextTid());
            bookDO.setBookAddr(new BookAddrDO("361000","杭州拱墅区"));
            list.add(bookDO);
        }


        List<BookDTO> bookDTOs = userTransf.toModel(list);

        System.out.println("---xxx--- bookDTO: "+new Gson().toJson(bookDTOs));
    }
}
