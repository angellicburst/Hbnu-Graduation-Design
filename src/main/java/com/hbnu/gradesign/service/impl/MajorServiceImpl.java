package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MajorMapper;
import com.hbnu.gradesign.domain.Major;
import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorMapper mm;

	@Override
	public Major getMajorById(Integer id) {
		return mm.getMajorById(id);
	}

	@Override
	@Cacheable(cacheNames = "majors")
	public PackData getMajors() {
		PackData packData = new PackData();
		List<Major> majors = mm.getMajors();
		if (majors.isEmpty()) {
			packData.setCode(404);
			packData.setMsg("专业查询为空");
		} else {
			packData.setCode(200);
			packData.setObjs(majors);
			packData.setMsg("专业查询成功");
		}
		return packData;
	}
}
