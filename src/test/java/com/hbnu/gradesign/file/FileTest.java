package com.hbnu.gradesign.file;

import com.hbnu.gradesign.properties.PathProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PathProperties pathProperties;

	@Test
	public void getFullPath() throws FileNotFoundException {
		System.out.println(pathProperties.getMenuImgSavePath());
		System.out.println(ResourceUtils.getFile("classpath:templates"));

	}
}
