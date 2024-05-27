package com.mengka.springboot.service;

import com.mengka.springboot.domain.Note;
import java.util.List;

/**
 * @author mengka
 * @version 2021/4/17
 * @since
 */
public interface BookService {

    String foo();

    Note findNote(Long id);

    List<Note> findAll();

    Long getNextNoteId();

    /**
     *  try安全写法
     *
     * @return
     */
    Long getNextNoteIdSafe();

    String selectNoteList();
}
