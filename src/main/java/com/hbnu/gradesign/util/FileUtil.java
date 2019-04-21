package com.hbnu.gradesign.util;

import com.hbnu.gradesign.entity.pojo.PackData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件操作工具类
 */

public class FileUtil {

	private static transient Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * 单文件上传
	 *
	 * @param fileUpload
	 * @return
	 */
	public static PackData uploadFile(MultipartFile fileUpload, String savePath) {
		PackData packData = new PackData();
		String newFileName = null;
		try {
			//判断上传文件是否为空
			if (fileUpload.isEmpty()) {
				packData.setCode(400);
				packData.setMsg("上传文件为空");
				throw new RuntimeException("文件不存在");
			} else {
				//获取文件名
				String fileName = fileUpload.getOriginalFilename();
				//加上时间毫秒避免重名
				//newFileName = System.currentTimeMillis() + "_" + fileName;
				newFileName = fileName;
				//指定路径存储图片
				File file = new File(savePath + newFileName);
				//不存在新建
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				//上传文件到指定路径

				fileUpload.transferTo(file);
			}


		} catch (IllegalStateException | IOException e) {
			packData.setCode(400);
			packData.setMsg("上传失败");
			throw new RuntimeException("上传失败");
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
	public static PackData delFile(String filename, String savePath) {
		PackData packData = new PackData();
		File file = new File(savePath + filename);

		if (file.exists()) {
			if (file.delete()) {
				packData.setCode(200);
				packData.setMsg("文件删除成功");
			} else {
				packData.setCode(400);
				packData.setMsg("文件删除失败");
				throw new RuntimeException("文件删除失败");
			}
		} else {
			packData.setCode(404);
			packData.setMsg("文件不存在");
			throw new RuntimeException("文件不存在");
		}

		return packData;
	}

	/**
	 * 文件下载
	 * @param response
	 * @param filePath
	 * @throws RuntimeException
	 */
	public static PackData downloadFile(HttpServletResponse response, String filePath) throws RuntimeException, UnsupportedEncodingException {
		PackData packData = new PackData();

		File file = new File(filePath);

		if(!file.exists()) {
			packData.setCode(404);
			packData.setMsg("文件不存在");
			throw new RuntimeException("文件不存在");
		} else {
			BufferedInputStream bin = null;// 输入流缓存流
			BufferedOutputStream bout = null;// 输出流缓存流

			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("multipart/form-data;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; fileName=stuTemplate.xls;filename*=utf-8''"+ URLEncoder.encode("stuTemplate.xls","UTF-8"));

			try {
				//缓存流
				bin = new BufferedInputStream(new FileInputStream(file));
				bout = new BufferedOutputStream(response.getOutputStream());
				byte[] b = new byte[1024 * 5];// 缓存数组
				int len = 0;

				while ((len = bin.read(b)) != -1) {
					bout.write(b, 0, len);
					bout.flush();
				}
			} catch (FileNotFoundException e) {
				//这里抛出RuntimeException,实际时抛出自定义异常,方便在上一层捕捉自定义异常,对异常信息统一管理,并返会前台
				packData.setCode(400);
				packData.setMsg("文件读取异常");
				log.error("文件读取异常", e);
				throw new RuntimeException("文件读取异常");
			} catch (IOException e) {
				packData.setCode(400);
				packData.setMsg("上传文件IO异常");
				log.error("上传文件IO异常", e);
				throw new RuntimeException("上传文件IO异常");
			} finally {
				try {
					// 关闭缓存流的时候会将输入输出流给关闭
					if (bin != null) {
						bin.close();
					}
					if (bout != null) {
						bout.close();
					}
				} catch (IOException e) {
					packData.setCode(400);
					packData.setMsg("文件上传异常");
					log.error("文件上传异常", e);
					throw new RuntimeException("文件上传异常");

				}

			}
		}
		return packData;
	}

}
