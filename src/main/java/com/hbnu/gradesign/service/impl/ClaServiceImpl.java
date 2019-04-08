package com.hbnu.gradesign.service.impl;

import com.hbnu.gradesign.dao.ClaMapper;
import com.hbnu.gradesign.domain.Cla;
import com.hbnu.gradesign.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaServiceImpl implements ClaService {

	@Autowired
	private ClaMapper cm;

	@Override
	public Cla getClaById(Integer id) {
		return cm.getClaById(id);
	}
}
