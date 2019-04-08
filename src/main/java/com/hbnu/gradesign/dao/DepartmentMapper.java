package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {
    Department getDepartmentById(Integer id);
}