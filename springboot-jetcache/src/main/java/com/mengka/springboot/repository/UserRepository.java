package com.mengka.springboot.repository;

import com.mengka.springboot.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mengka
 * @Date 2021-12-12 16:14
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("update User set name = :name where id = :userId")
    int updateUserNameById(String name, Long userId);
}
