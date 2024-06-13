package com.mengka.springboot.service;

import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.NoteRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2022-01-17 13:56
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // TODO clean: 2022-01-17T15:33:06.873
    public void getNoteListTEST() {
        int pageNum = 1;
        int pageSize = 10;
        Page<Note> page = getNoteList(pageNum, pageSize);
        page.get().forEach(x -> System.out.println(new Gson().toJson(x)));
        System.out.println(page.getTotalElements());
    }

    public Page<Note> getNoteList(int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Note> users = noteRepository.findAll(pageable);
        return users;
    }
}
