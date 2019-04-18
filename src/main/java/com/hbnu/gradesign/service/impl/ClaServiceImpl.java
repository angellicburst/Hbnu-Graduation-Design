package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ClaMapper;
import com.hbnu.gradesign.entity.Cla;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaServiceImpl implements ClaService {

	@Autowired
	private ClaMapper cm;

	/**
	 * 通过Id查询单个班级
	 * @param id
	 * @return
	 */
	@Override
	public Cla getClaById(Integer id) {
		return cm.getClaById(id);
	}

	/**
	 * 查询所有班级
	 * 加入缓存
	 * @return
	 */
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

	/**
	 * 关系表通过专业查询了班级
	 * @param majorId
	 * @return
	 */
	@Override
	public List<Cla> getClasByMajorId(Integer majorId) {
		return cm.getClasByMajorId(majorId);
	}
}
