package com.mengka.springboot.component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/5/12
 * @since farabbit2.0
 */
@Order(value = 3)
@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("----------, DATASOURCE = " + dataSource);

        log.info("----------, Display all customers...");

        /**
         *  新增note数据
         */
//        Arrays.asList("test node 2..", "test node 3..").forEach(v -> {
//                    Note note = Note.builder().title(v).content("a test").build();
//                    note.setCreateTime(new Date());
//                    note.setUpdateTime(new Date());
//                    this.noteRepository.saveAndFlush(note);
//                });



        /**
         *  查找note数据
         */
//        Iterable<Note> noteIterable = noteRepository.findAll();
////        noteIterable.forEach(x -> System.out.println(x));
//
//        List<Note> notes = Lists.newArrayList(noteIterable);
//
//        //byAuthor函数：检索包含指定auther的文章
//        BiFunction<String, List<Note>, List<Note>> byAuthor =
//                (name, articles) -> articles.stream()
//                        .filter(a -> a.getTitle().contains(name))
//                        .collect(Collectors.toList());
//
//        //输出包含"test node"标题的Note
//        List<Note> applyNotes = byAuthor.apply("test node",notes);
//        applyNotes.forEach(x -> println(x));
    }

    private void println(Note note){
        String noteJson = new Gson().toJson(note);
        log.info("-----------, noteJson = {}",noteJson);
    }
}
