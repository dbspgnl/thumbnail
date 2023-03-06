package com.toy.thumbnail.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class VideoThread extends Thread {
	private int threadNo;
	private int threadSize;
	private double plusSize;
	private File source;

	public VideoThread(File source, int threadSize, int threadNo, double plusSize) {
		this.source = source;
		this.threadNo = threadNo;
		this.threadSize = threadSize;
		this.plusSize = plusSize;
	}
		
	public void run() {
		FrameGrab grab;
		try {
			grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
			for(int m=0; m<120; m++){ // 스샷 번호 (프레임 수) 0이면 1장
				if (m% threadSize == threadNo){
					double startSec = m * plusSize;
					// System.out.println(threadNo); // 동작하고 있는 쓰레드 번호 표시
					int frameCount = 1;
					grab.seekToSecondPrecise(startSec);
					for (int i = 0; i < frameCount; i++) {
						Picture picture = grab.getNativeFrame();
						// for JDK(jcodec-javase)
						BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
						ImageIO.write(bufferedImage, "png", new File("d:/data/"+m+".png"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
