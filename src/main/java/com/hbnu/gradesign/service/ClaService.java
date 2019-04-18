package com.hbnu.gradesign.service;

import com.hbnu.gradesign.entity.Cla;
import com.hbnu.gradesign.entity.pojo.PackData;

import java.util.List;

public interface ClaService {
	Cla getClaById(Integer id);

	PackData getClas();

	List<Cla> getClasByMajorId(Integer majorId);
}
