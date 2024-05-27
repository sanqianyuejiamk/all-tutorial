package com.didispace.chapter38;

import com.didispace.chapter38.p.Company;
import com.didispace.chapter38.p.CompanyRepository;
import com.didispace.chapter38.s.Note;
import com.didispace.chapter38.s.NoteRepository;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author mengka
 * @Date 2022-01-22 15:44
 */
@Order(value = 3)
@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    @Resource
    DataSource primaryDataSource;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("DATASOURCE = " + primaryDataSource);

        log.info("Display all customers...");
        Iterable<Note> noteIterable = noteRepository.findAll();
//        noteIterable.forEach(x -> System.out.println(x));

        List<Note> notes = Lists.newArrayList(noteIterable);

        //输出包含"test node"标题的Note
        notes.forEach(x -> println(x));

        Iterable<Company> companyIterable = companyRepository.findAll();
        List<Company> companys = Lists.newArrayList(companyIterable);
        companys.forEach(x -> println(x));
    }

    private void println(Note note){
        String noteJson = new Gson().toJson(note);
        log.info("noteJson = {}",noteJson);
    }

    private void println(Company note){
        String noteJson = new Gson().toJson(note);
        log.info("CompanyJson = {}",noteJson);
    }
}
