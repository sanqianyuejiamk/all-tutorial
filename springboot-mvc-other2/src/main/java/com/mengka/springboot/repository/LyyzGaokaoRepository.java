package com.mengka.springboot.repository;

import com.mengka.springboot.domain.LyyzGaokao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author mengka
 * @version 2020/12/11
 * @since
 */
@RepositoryRestResource(collectionResourceRel = "lyyz_gaokao", path = "lyyz_gaokao")
public interface LyyzGaokaoRepository extends JpaRepository<LyyzGaokao, Long> {

    LyyzGaokao findByYearOfGraduationAndNameAndAndUniversity(Integer yearOfGraduation,String name,String university);
}
