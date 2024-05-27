package com.mengka.springboot.service.impl;

import com.google.common.collect.Lists;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.manager.NoteManager;
import com.mengka.springboot.repository.NoteRepository;
import com.mengka.springboot.service.BookService;
import com.mengka.springboot.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**resultSet
 * @author mengka
 * @version 2021/4/17
 * @since
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteManager noteManager;

    @Override
    public String foo() {
        String data = "[Just for test.."+ TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        log.info("-----, data = "+data);
        return data;
    }

    @Override
    public Note findNote(Long id) {
        try {
            Optional<Note> studentResponse = noteRepository.findById(id);
            Note note = studentResponse.get();

            note.setContent(noteManager.getNoteDescribe());
            return note;
        }catch (Exception e){
            log.error("findNote error! id = "+id,e);
        }
        return null;
    }

    /**
     *  查找note数据
     */
    @Override
    public List<Note> findAll() {
        Iterable<Note> noteIterable = noteRepository.findAll();
        List<Note> notes = Lists.newArrayList(noteIterable);
        notes.forEach(e -> e.setContent(noteManager.getNoteDescribe()));

        return notes;
    }

    @Override
    public Long getNextNoteId(){
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet1 = null;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            //connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mengka?user=root&password=544027354");
            connect = DriverManager.getConnection("jdbc:mysql://114.115.222.116:3306/mengka?user=root&password=544027354");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            /** test2:update by id **/
            int u1 = statement.executeUpdate("update note set content='test133' where id=1");
            log.info("executeUpdate u1 = "+u1);

            /** test1:get id **/
            // Result set get the result of the SQL query
            resultSet1 = statement.executeQuery("select AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'mengka' AND TABLE_NAME = 'note' limit 1");

            while (resultSet1.next()) {
                Long id = resultSet1.getLong("AUTO_INCREMENT");
                log.info("getNextNoteId id: " + id);
                return id;
            }

            /** test3:select all **/
//            preparedStatement = connect.prepareStatement("SELECT id, title, content, create_time, update_time from mengka.note");
//            resultSet = preparedStatement.executeQuery();
//
//            // ResultSet is initially before the first data set
//            while (resultSet.next()) {
//                // It is possible to get the columns via name
//                // also possible to get the columns via the column number
//                // which starts at 1
//                // e.g. resultSet.getSTring(2);
//                Long id = resultSet.getLong("id");
//                String website = resultSet.getString("title");
//                String summary = resultSet.getString("content");
//                Date date = resultSet.getDate("create_time");
//                System.out.println("id: " + id);
//                System.out.println("Website: " + website);
//                System.out.println("summary: " + summary);
//                System.out.println("Date: " + date);
//            }
        }catch (Exception e) {
            log.error("getNextNoteId error!",e);
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public Long getNextNoteIdSafe() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try(Connection connect = DriverManager.getConnection("jdbc:mysql://114.115.222.116:3306/mengka?user=root&password=544027354");
                Statement statement = connect.createStatement();){
                try(ResultSet resultSet1 = statement.executeQuery("select AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'mengka' AND TABLE_NAME = 'note' limit 1")){
                    while (resultSet1.next()) {
                        Long id = resultSet1.getLong("AUTO_INCREMENT");
                        log.info("getNextNoteId id: " + id);
                        return id;
                    }
                }
            }
        }catch (Exception e) {
            log.error("getNextNoteIdSafe error!",e);
        }
        return null;
    }

    @Override
    public String selectNoteList() {
        Connection connect = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            //connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mengka?user=root&password=544027354");
            connect = DriverManager.getConnection("jdbc:mysql://114.115.222.116:3306/mengka?user=root&password=544027354");

            /** test3:select all **/
            preparedStatement = connect.prepareStatement("SELECT id, title, content, create_time, update_time from mengka.note");
            resultSet = preparedStatement.executeQuery();

            // ResultSet is initially before the first data set
            while (getResultNext(resultSet)) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
                Long id = resultSet.getLong("id");
                String website = resultSet.getString("title");
                String summary = resultSet.getString("content");
                System.out.println("id: " + id);
                System.out.println("Website: " + website);
                System.out.println("summary: " + summary);
                Date date = resultSet.getDate("create_time");
            }
        }catch (Exception e) {
            log.error("getNextNoteId error!",e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean getResultNext(ResultSet resultSet)throws SQLException {
        return resultSet.next();
    }
}
