package com.mengka.springboot.repository;

import com.mengka.springboot.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author mengka
 * @Date 2022-01-27 14:52
 */
@RepositoryRestResource(collectionResourceRel = "location2", path = "location2")
public interface LocationRepository extends JpaRepository<Location, Long> {
}
