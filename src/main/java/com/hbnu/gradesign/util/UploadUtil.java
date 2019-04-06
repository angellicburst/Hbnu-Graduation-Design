package com.hbnu.gradesign.util;

import com.hbnu.gradesign.domain.pojo.PackData;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作工具类
 */
public class UploadUtil {

	/**
	 * 单文件上传
	 *
	 * @param fileUpload
	 * @return
	 */
	public static PackData uploadFile(MultipartFile fileUpload) {
		PackData packData = new PackData();
		String newFileName = null;
		try {
			//判断上传文件是否为空
			if (fileUpload.isEmpty()) {
				packData.setCode(400);
				packData.setMsg("上传文件为空");
				return packData;
			}

			//获取文件名
			String fileName = fileUpload.getOriginalFilename();
			//加上时间毫秒避免重名
			//newFileName = System.currentTimeMillis() + "_" + fileName;
			newFileName = fileName;
			//指定路径存储图片
			File file = new File("F:/Program/study/GraDesign/Hbnu-Graduation-Design/src/main/resources/static/images/menu/" + newFileName);
			//不存在新建
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			//上传文件到指定路径

			fileUpload.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			packData.setCode(400);
			packData.setMsg("上传失败");
			return packData;
		}
		packData.setCode(200);
		packData.setMsg("上传成功");
		packData.setObj(newFileName);
		return packData;
	}

	/**
	 * 单文件删除
	 *
	 * @param filename
	 * @return
	 */
	public static PackData delFile(String filename) {
		PackData packData = new PackData();
		File file = new File("F:/Program/study/GraDesign/Hbnu-Graduation-Design/src/main/resources/static/images/menu/" + filename);

		if (file.exists()) {
			if (file.delete()) {
				packData.setCode(200);
				packData.setMsg("文件删除成功");
			} else {
				packData.setCode(400);
				packData.setMsg("文件删除失败");
			}
		} else {
			packData.setCode(404);
			packData.setMsg("文件不存在");
		}

		return packData;
	}
}
