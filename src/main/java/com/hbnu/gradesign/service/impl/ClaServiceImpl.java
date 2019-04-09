package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ClaMapper;
import com.hbnu.gradesign.domain.Cla;
import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaServiceImpl implements ClaService {

	@Autowired
	private ClaMapper cm;

	@Override
	public Cla getClaById(Integer id) {
		return cm.getClaById(id);
	}

	@Override
	@Cacheable(cacheNames = "clas")
	public PackData getClas() {
		PackData packData = new PackData();
		List<Cla> clas = cm.getClas();
		if (clas.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("班级查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(clas);
			packData.setMsg("班级查询成功");
		}
		return packData;
	}
}
