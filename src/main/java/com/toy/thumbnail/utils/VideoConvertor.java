package com.toy.thumbnail.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.springframework.stereotype.Component;

@Component
public class VideoConvertor {
	
	public void toImageFrame(File source) throws Exception {
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

	public void getThumbnail(File source) throws Exception {
		FileChannelWrapper filech = NIOUtils.readableChannel(source);
		FrameGrab grab = FrameGrab.createFrameGrab(filech);
		grab.seekToSecondPrecise(0);
		Picture picture = grab.getNativeFrame();
		BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
		String fileName = source.getName().split(".mp4")[0]+".png";
		ImageIO.write(bufferedImage, "png", new File("d:/data/"+fileName));
		filech.close();
	}

}
