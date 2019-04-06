package com.hbnu.gradesign.controller.role;

import com.hbnu.gradesign.domain.Role;
import com.hbnu.gradesign.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

	@Autowired
	private RoleService rs;

	@RequestMapping(value = "/getRoles",method = RequestMethod.POST)
	public List<Role> getRoles() {
		return rs.getRoles();
	}
}
