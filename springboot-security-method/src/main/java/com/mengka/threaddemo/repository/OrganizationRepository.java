package com.mengka.threaddemo.repository;

import com.mengka.threaddemo.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);
}
