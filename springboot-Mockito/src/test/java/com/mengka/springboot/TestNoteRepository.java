package com.mengka.springboot;

import com.google.common.collect.Lists;
import com.mengka.springboot.component.CommonFunction;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author mengka
 * @version 2021/4/17
 * @since
 */
@Slf4j
public class TestNoteRepository extends BaseTest {

    @Autowired
    NoteRepository noteRepository;

    @Test
    public void test_find_note(){
        /**
         *  查找note数据
         */
        Iterable<Note> noteIterable = noteRepository.findAll();
        List<Note> notes = Lists.newArrayList(noteIterable);

        //byAuthor函数：检索包含指定auther的文章
        BiFunction<String, List<Note>, List<Note>> byAuthor =
                (name, articles) -> articles.stream()
                        .filter(a -> a.getTitle().contains(name))
                        .collect(Collectors.toList());

        //输出包含"test node"标题的Note
        List<Note> applyNotes = byAuthor.apply("test node 1..",notes);
        applyNotes.forEach(CommonFunction::println);
    }
}
