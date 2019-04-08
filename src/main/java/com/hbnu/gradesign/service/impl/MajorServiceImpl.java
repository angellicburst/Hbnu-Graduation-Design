package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.MajorMapper;
import com.hbnu.gradesign.domain.Major;
import com.hbnu.gradesign.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorMapper mm;

	@Override
	public Major getMajorById(Integer id) {
		return mm.getMajorById(id);
	}
}
