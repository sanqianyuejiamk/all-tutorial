package com.mengka.springboot.injecting_mocks_in_spring;

import com.mengka.springboot.Demo2Application;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.manager.NoteManager;
import com.mengka.springboot.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *  We use the @ActiveProfiles annotation to enable the “test” profile and activate the mock configuration we wrote earlier.
 *
 *  Because of this, Spring autowires a real instance of the BookService class, but a mock of the NoteManager class.
 *
 *  https://www.baeldung.com/injecting-mocks-in-spring
 *
 *
 *  TODO: 更多学习
 *  https://www.baeldung.com/mockito-series
 *
 *
 * [xxx]:
 *  It's also possible (though not recommended) to avoid using environment profiles in such tests.
 *  To do so, remove the @Profile and @ActiveProfiles annotations
 *  and add an @ContextConfiguration(classes = TestConfiguration.class) annotation to the BookServiceUnitTest class.
 *
 * @author mengka
 * @version 2021/4/18
 * @since
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Demo2Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceUnitTest {

    @Autowired
    private BookService bookService;

    /** noteManager injecting-mocks-in-spring */
    @Autowired
    private NoteManager noteManager;

    @Before
    public void before(){
        /**
         * We configure the desired behavior of the mock, then call the method which we want to test
         * and assert that it returns the value that we expect.
         */
        Mockito.when(noteManager.getNoteDescribe()).thenReturn("Mock note Describe");
    }

    @Test
    public void test_injecting_mocks_in_spring(){
        Note note = bookService.findNote(1L);
        String mockNoteDescribe = note.getContent();

        Assert.assertEquals("Mock note Describe", mockNoteDescribe);
    }
}
