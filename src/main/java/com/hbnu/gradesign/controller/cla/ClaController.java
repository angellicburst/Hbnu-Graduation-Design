package com.hbnu.gradesign.controller.cla;

import com.hbnu.gradesign.entity.Cla;
import com.hbnu.gradesign.entity.pojo.PackData;
import com.hbnu.gradesign.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClaController {

	@Autowired
	private ClaService cs;

	/**
	 * 获取所有班级·
	 * @return
	 */
	@RequestMapping(value = "/getClas",method = RequestMethod.POST)
	public PackData getClas() {
		return cs.getClas();
	}

	/**
	 * 通过专业ID获取班级
	 * @param majorId
	 * @return
	 */
	@RequestMapping(value = "/getClasByMaj",method = RequestMethod.POST)
	public List<Cla> getClasByMaj(Integer majorId) {
		return cs.getClasByMajorId(majorId);
	}
}
