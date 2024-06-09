package com.farabbit.springboot.repository;

import com.farabbit.springboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author mengka
 * @Date 2021-11-28 15:32
 */
@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

    default int wasteTime() {
        int i = Integer.MIN_VALUE;
        while(i < Integer.MAX_VALUE) {
            i++;
        }
        return i;
    }
}
