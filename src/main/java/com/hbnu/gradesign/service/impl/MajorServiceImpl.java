package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MajorMapper;
import com.hbnu.gradesign.entity.Major;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorMapper mm;

	/**
	 * 根据Id查询单个专业
	 * @param id
	 * @return
	 */
	@Override
	public Major getMajorById(Integer id) {
		return mm.getMajorById(id);
	}

	/**
	 * 查询所有的专业
	 * 加入缓存
	 * @return
	 */
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

	/**
	 * 根据关系表通过院系查询专业
	 * @param departmentId
	 * @return
	 */
	@Override
	public List<Major> getMajorsByDepartmentId(Integer departmentId) {
		return mm.getMajorsByDepartmentId(departmentId);
	}
}
