package com.pinyougou.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pinyougou.utils.FastDFSClient;

import entity.JSONResult;

/**
 * 图片上传控制层
 * 
 * @author 89568
 *
 */

@RestController
public class UploadController {
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;
	
	@RequestMapping("/contnetUpload")
	public JSONResult upload(MultipartFile file) {
		JSONResult json = new JSONResult();
		// 1创建一个faseFds的客户端
		// 1、取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

		try {
			// 2、创建一个 FastDFS 的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			// 3执行上传流程
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			System.out.println("path="+path);
			// 4返回拼接字符串路径
			String url = FILE_SERVER_URL+path;
			//5返回
			System.out.println("URl="+url);
			json.setSuccess(true);
			json.setMessage(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
}
