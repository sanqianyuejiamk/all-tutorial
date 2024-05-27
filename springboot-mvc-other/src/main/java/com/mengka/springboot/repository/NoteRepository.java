package com.mengka.springboot.repository;

import com.mengka.springboot.domain.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by jjmendoza on 14/7/2017.
 */
@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    /**
     *  根据标题查找文章
     *
     * @param title
     * @return
     */
    Note findByTitle(String title);

    /**
     *  返回最大ID
     *
     * @param limit
     * @return
     */
    @Query(value = "SELECT id FROM note order by id desc limit :limit", nativeQuery = true)
    Long findMaxIdDesc(@Param("limit") int limit);

    /**
     *  返回Top n篇文章
     *
     * @param limit
     * @return
     */
    @Query(value = "SELECT * FROM note order by id desc limit :limit", nativeQuery = true)
    public List<Note> findTopN(@Param("limit") int limit);
}
