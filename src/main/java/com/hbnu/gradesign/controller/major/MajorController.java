package com.hbnu.gradesign.controller.major;

import com.hbnu.gradesign.domain.pojo.PackData;
import com.hbnu.gradesign.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MajorController {

	@Autowired
	private MajorService ms;

	@RequestMapping(value = "/getMajors",method = RequestMethod.POST)
	public PackData getMajors() {
		return ms.getMajors();
	}
}
