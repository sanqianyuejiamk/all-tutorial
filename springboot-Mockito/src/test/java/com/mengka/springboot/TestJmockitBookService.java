package com.mengka.springboot;

import com.google.gson.Gson;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.manager.NoteManager;
import com.mengka.springboot.repository.NoteRepository;
import com.mengka.springboot.service.impl.BookServiceImpl;
import com.mengka.springboot.utils.TimeUtil;
import com.mysql.jdbc.ResultSetImpl;
import lombok.extern.slf4j.Slf4j;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *  https://www.cnblogs.com/shoren/p/jmokit-summary.html
 *
 *  https://www.baeldung.com/jmockit-advanced-usage
 *
 * @author mengka
 * @Date 2022-03-13 20:30
 */
@Slf4j
@RunWith(JMockit.class)
public class TestJmockitBookService {

    private static int ac = 0;

    @Tested
    @Mocked
    BookServiceImpl bookService;

    @Injectable
    NoteRepository noteRepository;

    @Injectable
    NoteManager noteManager;

    @Test
    public void testGetNextNoteIdSafe(@Mocked final Connection connection,
                                      @Mocked final Statement statement,@Mocked final ResultSet resultSet)throws Exception{
        new Expectations(DriverManager.class){
            {
                DriverManager.getConnection(anyString);
                result = connection;

                connection.createStatement();
                result = statement;

                statement.executeQuery(anyString);
                result = resultSet;

                resultSet.next();
                result = true;
            }
        };
        Long nextId = bookService.getNextNoteIdSafe();
        Assert.assertEquals(0L,nextId.longValue());
    }

    @Test
    public void testGetNextNoteIdSafeError()throws Exception{
        new Expectations(DriverManager.class){
            {
                DriverManager.getConnection(anyString);
                result = new Exception();
            }
        };
        Long nextId = bookService.getNextNoteIdSafe();
        Assert.assertNull(nextId);
    }

    /**
     *  https://jmockit.github.io/tutorial/Introduction.html
     *
     *  https://stackoverflow.com/questions/36950210/mocking-jdbcs-connectionpreparestatement-always-returns-null-with-jmockit
     *  https://examples.javacodegeeks.com/core-java/mockito/mockito-mock-database-connection-example/
     *
     * @param connection
     * @param ps
     * @throws Exception
     */
    @Test
    public void testGetNextNoteId(@Mocked final Connection connection,@Mocked final PreparedStatement ps,
                                  @Mocked final Statement statement,@Mocked final ResultSet resultSet)throws Exception{
        new Expectations(DriverManager.class){
            {
                DriverManager.getConnection(anyString);
                result = connection;

                connection.createStatement();
                result = statement;

                statement.executeQuery(anyString);
                result = resultSet;

                resultSet.next();
                result = true;

//                connection.prepareStatement(anyString);
//                result = with(new Delegate<PreparedStatement>() {
//                    public PreparedStatement prepareStatement(String sql) throws SQLException {
//                        return ps;
//                    }
//                });

//                ps.executeQuery();
//                result = with(new Delegate<ResultSet>() {
//                    public ResultSet executeQuery() throws SQLException {
//                        return resultSet;
//                    }
//                });

            }
        };
        new Expectations(ResultSetImpl.class){
            {
                //ResultSetImpl.next();

            }
        };
//        new Expectations() {
//            {
//                ps.executeQuery();
//                result = resultSet;
//
//                resultSet.next();
//                result = true;
//
//                resultSet.getLong(anyString);
//                result = 1L;
//
//                resultSet.getString(anyString);
//                result = "test";
//
//                resultSet.getDate(anyString);
//                result = null;
//            }
//        };

//        ResultSet resultSet = new MockUp<ResultSet>(){
//            @Mock
//            boolean next() throws SQLException{
//                return true;
//            }
//            @Mock
//            long getLong(String columnLabel) throws SQLException{
//                return 1L;
//            }
//            @Mock
//            String getString(String columnLabel) throws SQLException{
//                return "test";
//            }
//            @Mock
//            java.sql.Date getDate(String columnLabel) throws SQLException{
//                return null;
//            }
//        }.getMockInstance();
//        Deencapsulation.setField(bookService,"resultSet",resultSet);

        Long nextId = bookService.getNextNoteId();
        Assert.assertEquals(0L,nextId.longValue());
    }

//    @Before
//    public void initThread() {
//        mockresultSet = new MockUp<ResultSet>() {
//            @Mock
//            public boolean next() throws SQLException {
//                return true;
//            }
//        }.getMockInstance();
//        preparedStatement = new MockUp<PreparedStatement>() {
//            @Mock
//            ResultSet executeQuery() throws SQLException {
//                return mockresultSet;
//            }
//        }.getMockInstance();
//    }


    @Test
    public void testSelectNoteList(@Mocked final Connection connection,@Mocked final PreparedStatement preparedStatement,
                                   @Mocked final ResultSet resultSet)throws Exception{
        //PreparedStatement preparedStatement = new com.mysql.jdbc.PreparedStatement(null,null);
        new Expectations(DriverManager.class){
            {
                DriverManager.getConnection(anyString);
                result = connection;

                connection.prepareStatement(anyString);
                result = preparedStatement;
//                result = with(new Delegate<PreparedStatement>() {
//                    public PreparedStatement prepareStatement(String sql) throws SQLException {
//                        return preparedStatement;
//                    }
//                });

                preparedStatement.executeQuery();
                result = resultSet;

                resultSet.next();
                result = ac++<=1?true:false;

                resultSet.getLong(anyString);
                result = 1L;

                resultSet.getString(anyString);
                result = "test";

                resultSet.getDate(anyString);
                result = new Exception();
            }
        };
        /**
        ResultSet mockresultSet = new MockUp<ResultSet>() {
            @Mock
            public boolean next() throws SQLException {
                return true;
            }
        }.getMockInstance();
        PreparedStatement preparedStatement = new MockUp<PreparedStatement>() {
            @Mock
            ResultSet executeQuery() throws SQLException {
                return mockresultSet;
            }
        }.getMockInstance();
        Deencapsulation.setField(bookService, "resultSet", mockresultSet);
        Deencapsulation.setField(bookService, "preparedStatement", preparedStatement);
         **/
//        new Expectations(com.mysql.jdbc.PreparedStatement.class) {
//            {
//                preparedStatement.executeQuery();
//                result = with(new Delegate<ResultSet>() {
//                    public ResultSet executeQuery() throws SQLException {
//                        return resultSet;
//                    }
//                });
//
//                resultSet.next();
//                result = true;
//            }
//        };
//        new Expectations(CommonUtil.class) {
//            {
//                CommonUtil.getResultNext((ResultSet)any);
//                result = true;
//            }
//        };

        String nextId = bookService.selectNoteList();
        Assert.assertNull(nextId);
    }


    @Test
    public void testInstance(){
        new Expectations(){
            {
                noteManager.getNoteDescribe();
                result = "ok";

                Note note = Note.builder().title("mk note").content(TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)).build();
                noteRepository.findById(anyLong);
                result = Optional.of(note);
            }
        };

        Optional<Note> optional = noteRepository.findById(1L);
        Assert.assertTrue("mk note".equals(optional.get().getTitle()));

        Note oneNote = bookService.findNote(1L);
        log.info("oneNote = {}",new Gson().toJson(oneNote));

        Assert.assertEquals("mk note", oneNote.getTitle());
    }

    @Test
    public void testInstance_Mock(){
        new Expectations(){
            {
                noteManager.getNoteDescribe();
                result = "ok";
            }
        };
        NoteRepository noteRepository = new MockUp<NoteRepository>(){

            @Mock
            List<Note> findAll(){
                List<Note> list = new ArrayList<>();
                list.add(Note.builder().title("mk note1").content("11").build());
                list.add(Note.builder().title("mk note2").content("22").build());
                return list;
            }
        }.getMockInstance();
        Deencapsulation.setField(bookService,"noteRepository",noteRepository);
        List<Note> notes = bookService.findAll();
        Assert.assertEquals("ok", notes.get(0).getContent());
    }

    @Test
    public void testInjectObj(final @Injectable NoteManager noteManager){
        new Expectations(){
            {
                noteManager.getNoteDescribe();
                result = "ok";
            }
        };

        System.out.println("desc = "+noteManager.getNoteDescribe());
        Assert.assertTrue("ok".equals(noteManager.getNoteDescribe()));
    }
}
