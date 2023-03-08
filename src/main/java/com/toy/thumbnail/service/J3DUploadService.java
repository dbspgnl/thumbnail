package com.toy.thumbnail.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class J3DUploadService {

	public String getObjTest() {	
		try {
			
			File file1 = new File("d:/data/1.txt"); // abc
			File file11 = new File("d:/data/1-1.txt"); // abc
			File file2 = new File("d:/data/2.txt"); // def

			byte[] first1 = Files.readAllBytes(file1.toPath());
			byte[] first11 = Files.readAllBytes(file11.toPath());
			byte[] second2 = Files.readAllBytes(file2.toPath());
		
			System.out.println(Arrays.equals(first1, first11)); // true
			System.out.println(Arrays.equals(first1, second2)); // false

			boolean equal = isEqual(file1.toPath(), file11.toPath());
			if (equal) {
				System.out.println("Files are equal.");
			}
			else {
				System.out.println("Files are not equal.");
			}


		} catch (Exception e) {
			System.out.println(e);
		}
		return "작업중";
	}

	private static boolean isEqual(Path firstFile, Path secondFile)
	{
		try {
			long size = Files.size(firstFile);
			if (size != Files.size(secondFile)) {
				return false;
			}
			if (size < 2048)
			{
				return Arrays.equals(Files.readAllBytes(firstFile),
								Files.readAllBytes(secondFile));
			}
			try (BufferedReader bf1 = Files.newBufferedReader(firstFile);
				BufferedReader bf2 = Files.newBufferedReader(secondFile))
				{
					int ch;
					while ((ch = bf1.read()) != -1)
					{
						if (ch != bf2.read()) {
							return false;
						}
					}
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean isEqual(MultipartFile firstFile, File localFile)
	{
		Path secondFile = localFile.toPath();
		System.out.println(secondFile.getFileName());
		try {
			long size =firstFile.getSize();
			if (size != Files.size(secondFile)) {
				return false;
			}
			try 
				(
					BufferedReader bf = new BufferedReader(new InputStreamReader(firstFile.getInputStream()));
					// BufferedReader bf2 = Files.newBufferedReader(secondFile, StandardCharsets.UTF_8);
					// new BufferedReader(new InputStreamReader(new FileInputStream("a.txt"),"utf-8"));
					BufferedReader bf2 = new BufferedReader(new InputStreamReader(new FileInputStream(secondFile.toString())));
					
				)
				{
					int ch;
					while ((ch = bf.read()) != -1)
					{
						System.out.println("bf:  "+bf.read());
						System.out.println("bf2: "+bf2.read());

						if (ch != bf2.read()) {
							return false;
						}
					}
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Object fileTest(MultipartFile file) {
		// File file2 = new File("d:/data/2.txt"); // def
		File file11 = new File("D:/pv-storage/mv_eng_dl08v_assets_all.bundle"); // def
		// System.out.println(isEqual(file, file2));
		System.out.println(isEqual(file, file11));
		return null;
	}

	public File convertFile(MultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

}
