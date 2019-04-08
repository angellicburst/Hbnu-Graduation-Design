package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Cla;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaMapper {
    Cla getClaById(Integer id);
}