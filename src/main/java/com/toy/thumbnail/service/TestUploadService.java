package com.toy.thumbnail.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TestUploadService {

	public String sameFileCheck(MultipartFile[] files, String[] dates) {
		try {
			for (int i = 0; i < files.length; i++) {
				System.out.println("==================");
				MultipartFile file = files[i];
				Long date = Long.parseLong(dates[i]);
				File local = new File("D:/pv-storage/" + file.getOriginalFilename()); 
				
				System.out.println("멀티파일 이름: " + file.getOriginalFilename()); // upload_test/mv_eng_dl08v_assets_all.bundle
				System.out.println("로컬파일 이름: " + local.getName()); // mv_eng_dl08v_assets_all.bundle
				System.out.println("멀티파일 사이즈: " + file.getSize());
				System.out.println("로컬파일 사이즈: " + local.length());
				System.out.println("멀티파일 수정일: " + date);
				System.out.println("로컬파일 수정일: " + local.lastModified());
				
			}

			// Long lastModified = Long.parseLong(date);
			// File local = new File("D:/pv-storage/mv_eng_dl08v_assets_all.bundle"); // def
			// File local2 = new File("D:/pv-storage/commontable_assets_all_cb188321bcbf0ac7cd41b5c94353dc0a.bundle"); // def
			// System.out.println("multi: "+lastModified);
			// System.out.println("local: "+local.lastModified());
			// System.out.println(file.getOriginalFilename());
			// System.out.println(local.getName());
			// if(lastModified == local.lastModified() && file.getOriginalFilename().equals(local.getName())){
			// 	System.out.println("같은 이름 / 같은 수정 날짜 의 파일");
			// 	return "성공";
			// }
			// else{
			// 	System.out.println("같은 파일이라 할 수 없음");
			// 	return "실패";
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
