package com.mengka.springboot.component;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.mengka.springboot.domain.LyyzGaokao;
import com.mengka.springboot.repository.LyyzGaokaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author mengka
 * @version 2020/12/11
 * @since
 */
@Order(value = 30)
@Component
@Slf4j
public class LyyzStartupRunner implements CommandLineRunner {

    private static final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(10);//有界队列

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 30,
            TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private LyyzGaokaoRepository lyyzGaokaoRepository;

    @Override
    public void run(String... args) throws Exception {
//        Document document = Jsoup.connect("http://www.lyyz.net/gklq.asp?currentpage=555")
////                .proxy(proxy)
//                .get();


        //速率是每秒10个允许
//        final RateLimiter rateLimiter = RateLimiter.create(1);
//
//        IntStream.rangeClosed(11,613).boxed()
//                .forEach(x -> {
//                    rateLimiter.acquire();
//                    executor.execute(new LyyzTask(x,lyyzGaokaoRepository));
//                });

    }

    /**
     *  爬取高考成绩
     *
     */
    public static class LyyzTask implements Runnable{

        Integer pageNum;

        LyyzGaokaoRepository lyyzGaokaoRepository;

        public LyyzTask(Integer pageNum,LyyzGaokaoRepository lyyzGaokaoRepository){
            this.pageNum = pageNum;
            this.lyyzGaokaoRepository = lyyzGaokaoRepository;
        }

        @Override
        public void run() {
            try {
                String direct_uri = "http://www.lyyz.net/gklq.asp?currentpage="+this.pageNum;
                Document document = Jsoup.parse(new URL(direct_uri).openStream(), "GBK", direct_uri);

                Element element = document.getElementById("list_containers");
                Element element1 = element.child(0);
                Elements elements1 = element1.children();

                for (int i = 0; i < elements1.size(); i++) {
                    Element element2 = elements1.get(i);
                    Elements ulElements = element2.select("ul");
                    Element ulElement = ulElements.get(0);
                    Elements liElements = ulElement.children();
                    LyyzGaokao lyyzGaokao = new LyyzGaokao();
                    for(int j=0;j< liElements.size();j++){
                        Element dataElement = liElements.get(j);
                        String content = dataElement.text();
                        System.out.println(content);
                        if(j==0){
                            lyyzGaokao.setYearOfGraduation(Integer.valueOf(content));
                        }else if(j==1){
                            lyyzGaokao.setName(content.trim());
                        }else if(j==2){
                            lyyzGaokao.setSex("男".equals(content.trim())?1:2);
                        }else if(j==3){
                            lyyzGaokao.setWl(content.trim());
                        }else if(j==4){
                            lyyzGaokao.setUniversity(content.trim());
                        }else if(j==5){
                            if(StringUtils.isNotBlank(content)) {
                                lyyzGaokao.setMiddleSchool(content.trim());
                            }
                        }else if(j==6){
                            if(StringUtils.isNotBlank(content)) {
                                lyyzGaokao.setPrimarySchool(content.trim());
                            }
                        }
                    }

                    LyyzGaokao lyyzGaokaoExist = lyyzGaokaoRepository.findByYearOfGraduationAndNameAndAndUniversity(lyyzGaokao.getYearOfGraduation(),lyyzGaokao.getName(),lyyzGaokao.getUniversity());
                    if(lyyzGaokaoExist == null){
                        lyyzGaokaoRepository.save(lyyzGaokao);
                    }else{
                        log.error("已经存在的记录，"+new Gson().toJson(lyyzGaokaoExist));
                    }
                }

                write_file(this.pageNum);

            }catch (Exception e){
                log.error("爬取页面[{}]异常",this.pageNum,e);
            }
        }
    }


    private static void write_file(Integer pageNum){
        try {
            String path = "/Users/hyy044101331/work_farabbit/all-tutorial/springboot-mvc-other2/MyFile.txt";
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.newLine();
            bufferedWriter.write(pageNum+" ok");

            bufferedWriter.close();
        }catch (Exception e){

        }
    }
}
