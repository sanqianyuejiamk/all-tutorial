package com.mengka.springboot.jpa.repository;

import com.mengka.springboot.jpa.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
