package com.toy.thumbnail.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PDFConvertor {
	// @Test
    // void contextLoads() {
    //     try{
    //         String filePath = "pdf_test.pdf";
    //         File file = new File(filePath);
    //         ClassPathResource classPathResource = new ClassPathResource(filePath);
    //         InputStream is = new BufferedInputStream(classPathResource.getInputStream());
    //         conversionPdf2Img(is,"peter");

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public List<String> conversionPdf2Img(InputStream is, String uniqueId) {
        List<String> savedImgList = new ArrayList<>(); //저장된 이미지 경로 list
        try {
            PDDocument pdfDoc = PDDocument.load(is); //Document 생성
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            String resultImgPath = "/Users/peterseo/study/temp/"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); // 폴더 생성
            
            // PDF페이지 루프
            for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
                String imgFileName = resultImgPath + "/" + i + ".png";

                //DPI 설정
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 72, ImageType.RGB);

                // 이미지로 만든다.
                ImageIOUtil.writeImage(bim, imgFileName , 72);

                //저장 완료된 이미지 경로를 list에 추가
                savedImgList.add(imgFileName);
            }
            pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.
        }catch (FileNotFoundException e) {
			e.printStackTrace();
        }catch (IOException e) {
			e.printStackTrace();
        }
        return savedImgList;
    }

	public File conversionPdf2Img(File pdfFile) {
		String tempPath ="D:/data/";
        File pdfImgFile = null;
        if(!pdfFile.exists())
            return pdfImgFile;

        try {
            //Document 생성
            PDDocument pdfDoc = PDDocument.load(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            String resultImgPath = "D:/data/"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); // 폴더 생성

            // 첫페이지, DPI 72, 컬러이미지 설정
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 72, ImageType.RGB);
            // String imgFileName = tempPath + UUID.randomUUID()+"_pdf.png";
            String imgFileName = tempPath + pdfFile.getName().split(".pdf")[0] +".png";
            // 이미지로 만든다.
            ImageIOUtil.writeImage(bim, imgFileName , 72);

            //저장 완료된 이미지를 File객체화
            pdfImgFile = new File(imgFileName);

            pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.

        }catch (IOException e) {
			e.printStackTrace();
        }catch (Exception e) {
			e.printStackTrace();
        }
        return pdfImgFile;
    }

	public File conversionPdf2Img(MultipartFile multipartPdfFile) {
		File pdfImgFile = null;
		try {
			InputStream pdfFile = multipartPdfFile.getInputStream();
			String tempPath ="D:/data/";
			String filename = multipartPdfFile.getOriginalFilename();
            //Document 생성
            PDDocument pdfDoc = PDDocument.load(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            String resultImgPath = "D:/data/"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); // 폴더 생성

            // 첫페이지, DPI 72, 컬러이미지 설정
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 72, ImageType.RGB);
            // String imgFileName = tempPath + UUID.randomUUID()+"_pdf.png";
            String imgFileName = tempPath + filename.split(".pdf")[0] +".png";
            // 이미지로 만든다.
            ImageIOUtil.writeImage(bim, imgFileName , 72);

            //저장 완료된 이미지를 File객체화
            pdfImgFile = new File(imgFileName);

            pdfDoc.close(); 
			pdfFile.close();

        }catch (IOException e) {
			e.printStackTrace();
        }catch (Exception e) {
			e.printStackTrace();
        }
        return pdfImgFile;
    }

}
