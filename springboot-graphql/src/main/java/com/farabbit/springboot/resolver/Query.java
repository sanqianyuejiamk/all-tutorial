package com.farabbit.springboot.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.farabbit.springboot.entity.Location;
import com.farabbit.springboot.repository.LocationRepository;
import org.springframework.stereotype.Component;

/**
 * @author mengka
 * @Date 2022-01-05 17:40
 */
@Component
public class Query implements GraphQLQueryResolver {

    private LocationRepository locationRepository;

    public Query(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Iterable<Location> findAllLocations() {
        return locationRepository.findAll();
    }
}
