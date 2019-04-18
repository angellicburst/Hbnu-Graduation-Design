package com.hbnu.gradesign.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource({"classpath:config/path.properties"})
@Component
@Getter
@Setter
public class PathProperties {

	@Value("${menu.img.save.path}")
	private String menuImgSavePath;

	@Value("${student.add.templates.save.path}")
	private String stuTemSavePath;
}
