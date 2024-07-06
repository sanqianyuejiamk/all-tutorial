package com.mengka.threaddemo.service;

import com.mengka.threaddemo.domain.Note;
import com.mengka.threaddemo.repository.NoteRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author mengka
 * @Date 2024-07-06 19:40
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @PreAuthorize("@ss.hasPermi('动总总负责人')")
    public List<Note> findAll(){
        Iterable<Note> noteIterable = noteRepository.findAll();
//        noteIterable.forEach(x -> System.out.println(x));

        List<Note> notes = Lists.newArrayList(noteIterable);
        return notes;
    }
}
