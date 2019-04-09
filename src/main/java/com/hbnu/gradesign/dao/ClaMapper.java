package com.hbnu.gradesign.dao;

import com.hbnu.gradesign.domain.Cla;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaMapper {
    Cla getClaById(Integer id);

    List<Cla> getClas();
}