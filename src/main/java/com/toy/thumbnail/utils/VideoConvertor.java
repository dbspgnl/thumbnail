package com.toy.thumbnail.utils;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class VideoConvertor {
	
	public void getThumbnail(File source) throws Exception {
		double plusSize = 0.5; // 초 단위로 이미지 추출
		int threadSize = 8; // 작업 쓰레드 수
		VideoThread[] videoThread = new VideoThread[threadSize];
		for(int i = 0; i < videoThread.length; i++){
			videoThread[i] = new VideoThread(source, threadSize, i, plusSize);		
			videoThread[i].start();
		}
		boolean runFlag = true;
		while(runFlag){
			Thread.sleep(1000);
			runFlag = false;
			for (int i = 0; i < threadSize; i++) {
				if(videoThread[i].isAlive())
				runFlag = true;
			}
		}
	}
}
