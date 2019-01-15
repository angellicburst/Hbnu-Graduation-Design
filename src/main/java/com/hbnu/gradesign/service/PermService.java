package com.hbnu.gradesign.service;

import java.util.Set;

public interface PermService {
	Set<String> getPermsByUserId(Integer userId);
}
