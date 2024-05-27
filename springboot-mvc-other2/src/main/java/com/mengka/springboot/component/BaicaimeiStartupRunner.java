package com.mengka.springboot.component;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @author mengka
 * @version 2021/3/16
 * @since
 */
@Order(value = 30)
@Component
@Slf4j
public class BaicaimeiStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        String direct_uri = "https://s.taobao.com/search?initiative_id=tbindexz_20170306&ie=utf8&spm=a21bo.2017.201856-taobao-item.2&sourceId=tb.index&search_type=item&ssid=s5-e&commend=all&imgfile=&q=%E8%90%A5%E5%85%BB%E5%B8%88%E5%87%8F%E8%82%A5&suggest=history_1&_input_charset=utf-8&wq=&suggest_query=&source=suggest";
        Document document = Jsoup.parse(new URL(direct_uri).openStream(), "GBK", direct_uri);


        Element element = document.getElementById("mainsrp-itemlist");
        //log.info("--------, "+element.childrenSize());
    }
}
