package com.farabbit.springboot.repository;

import com.farabbit.springboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mengka
 * @Date 2021-11-28 15:32
 */
@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("update Book set name = :name where id = :id")
    int updateNameById(String name, Long id);

    List<Book> findByNameAndPriceAndTenantId(String name, Integer price, String tenantId);

    List<Book> findByName(String name);

    default int wasteTime() {
        int i = Integer.MIN_VALUE;
        while(i < Integer.MAX_VALUE) {
            i++;
        }
        return i;
    }
}
