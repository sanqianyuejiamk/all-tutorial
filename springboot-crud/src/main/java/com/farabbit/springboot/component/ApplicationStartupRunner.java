package com.farabbit.springboot.component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.farabbit.springboot.domain.Note;
import com.farabbit.springboot.repository.NoteRepository;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;

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
        log.info("DATASOURCE = " + dataSource);

        HikariDataSource newds = (HikariDataSource)dataSource;
        System.out.println("DATASOURCE = " + newds.getMaximumPoolSize());

        log.info("Display all customers...");
        Iterable<Note> noteIterable = noteRepository.findAll();
//        noteIterable.forEach(x -> System.out.println(x));

        List<Note> notes = Lists.newArrayList(noteIterable);

        Note note = new Note().setTitle("title222").setContent("content333");
        noteRepository.save(note);

        //输出包含"test node"标题的Note
        notes.forEach(x -> println(x));
    }

    private void println(Note note){
        String noteJson = new Gson().toJson(note);
        log.info("noteJson = {}",noteJson);
    }
}
