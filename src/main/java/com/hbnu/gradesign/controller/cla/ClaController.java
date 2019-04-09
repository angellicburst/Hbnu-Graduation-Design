package com.hbnu.gradesign.controller.cla;

import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClaController {

	@Autowired
	private ClaService cs;

	@RequestMapping(value = "/getClas",method = RequestMethod.POST)
	public PackData getClas() {
		return cs.getClas();
	}
}
