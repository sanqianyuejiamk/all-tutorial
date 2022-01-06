package com.farabbit.springboot.repository;

import com.farabbit.springboot.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author mengka
 * @Date 2022-01-05 17:37
 */
@RepositoryRestResource(collectionResourceRel = "location", path = "location")
public interface LocationRepository extends CrudRepository<Location, Long> {
}
