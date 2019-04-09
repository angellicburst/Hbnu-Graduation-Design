package com.hbnu.gradesign.service;

import com.hbnu.gradesign.domain.Cla;
import com.hbnu.gradesign.domain.pojo.PackData;

public interface ClaService {
	Cla getClaById(Integer id);

	PackData getClas();
}
