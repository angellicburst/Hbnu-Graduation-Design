package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper {
    Major getMajorById(Integer id);

    List<Major> getMajors();
}