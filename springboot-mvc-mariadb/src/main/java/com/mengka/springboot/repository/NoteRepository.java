package com.mengka.springboot.repository;

import com.mengka.springboot.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by jjmendoza on 14/7/2017.
 */

@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends JpaRepository<Note, Long> {
}
