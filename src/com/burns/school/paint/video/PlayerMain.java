/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint.video
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-20
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

/*主程序*/

package com.burns.school.paint.video;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class PlayerMain {
	public static Window frame;
	
	public PlayerMain() {

		
		//环境配置，将vlc sdk导入到eclipse
		
		//if(RuntimeUtil.isWindows()){					}
		NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC\\sdk\\lib");	//导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        //System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		//创建主程序界面运行窗体
		EventQueue.invokeLater(new Runnable() {
			
			private String videoPath ="C:/burns/schoolpaint/video/年画五道工序VA0.mp4";

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					frame=new Window();
					frame.setVisible(true);
					//frame.getMediaPlayer().playMedia("F:\\Videos\\Let.mp4");	//直接播放视屏，参数是视屏文件的绝对路径
					frame.getMediaPlayer().prepareMedia(videoPath );		//控制播放视屏
					new SwingWorker<String, Integer>() {

						@Override
						protected String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (true) {	//获取视频播放进度并且按百分比显示
								long total=frame.getMediaPlayer().getLength();
								long curr=frame.getMediaPlayer().getTime();
								float percent=(float)curr/total;
								publish((int)(percent));
								Thread.sleep(100);
							}	
							//return null;
						}
						
						protected void process(java.util.List<Integer> chunks) {
							for(int v:chunks){
								frame.getProgressBar().setValue(v);
							} 
						}
					}.execute();
				}catch(Exception e){
					e.printStackTrace();
				}
				frame.getMediaPlayer().playMedia(videoPath);
			}
		});
		
	
	}

	
	//private static final String NATIVE_LIBRARY_SEARCH_PATH = "D:\\Program Files\\VideoLAN\\VLC\\sdk\\lib";
//	public static void main(String[] args) {
//		PlayerMain pm = new PlayerMain();
//	}

	public static void main(String[] args) {
		
		//环境配置，将vlc sdk导入到eclipse
		
		//if(RuntimeUtil.isWindows()){					}
		NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC\\sdk\\lib");	//导入的路径是vlc的安装路径
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        //System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		//创建主程序界面运行窗体
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					frame=new Window();
					frame.setVisible(true);
//					frame.getMediaPlayer().playMedia("C:/burns/schoolpaint/video/年画五道工序VA0.mp4");	//直接播放视屏，参数是视屏文件的绝对路径
//					frame.getMediaPlayer().prepareMedia("C:/burns/schoolpaint/video/年画五道工序VA0.mp4");		//控制播放视屏
					new SwingWorker<String, Integer>() {

						@Override
						protected String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (true) {	//获取视频播放进度并且按百分比显示
								long total=frame.getMediaPlayer().getLength();
								long curr=frame.getMediaPlayer().getTime();
								float percent=(float)curr/total;
								publish((int)(percent*100));
								Thread.sleep(100);
							}	
							//return null;
						}
						
						protected void process(java.util.List<Integer> chunks) {
							for(int v:chunks){
								frame.getProgressBar().setValue(v);
							} 
						}
					}.execute();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
//		frame.getMediaPlayer().playMedia("C:/burns/schoolpaint/video/年画五道工序VA0.mp4");
	}
	
	//打开文件
	public static void openVideo() {
		JFileChooser chooser=new JFileChooser();
		int v=chooser.showOpenDialog(null);
		if(v==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			frame.getMediaPlayer().playMedia(file.getAbsolutePath());
		}
	}
	
	//退出播放
//	public static void exit() {
//		frame.getMediaPlayer().release();
//		System.exit(0);
//	}

	//实现播放按钮的方法
	public static void play() {
		frame.getMediaPlayer().play();
	}
	
	//实现暂停按钮的方法
	public static void pause() {
		frame.getMediaPlayer().pause();
	}
	
	//实现停止按钮的方法
	public static void stop() {
		frame.getMediaPlayer().stop();
	}
	
	//实现点击进度条跳转的方法
	public static void jumpTo(float to) {
		frame.getMediaPlayer().setTime((long)(to*frame.getMediaPlayer().getLength()));
	}
	
	//实现控制声音的方法
	public static void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}
	
}



/*******************************************************************************
 * <B>Revision History</B><BR>
 * [type 'revision' and press Alt + / to insert revision block]<BR>
 * 
 * 
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/