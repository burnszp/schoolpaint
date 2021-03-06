/*******************************************************************************
 * @project: SchoolPaint_2
 * @package: com.burns.school.paint.test
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-25
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test;  
  
import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.lang.reflect.Method;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
import java.security.AccessController;  
import java.security.PrivilegedAction;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGEncodeParam;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
import com.sun.pdfview.PDFFile;  
import com.sun.pdfview.PDFPage;  
  
public class PDFchangToImage {  
	private static String instructiopath ="C:/burns/schoolpaint/杨柳青年画制作流程.pdf";
	private static String picturepath = "C:/burns/schoolpaint/杨柳青年画制作流程";

	public static void main(String[] args) {
		changePdfToImg(instructiopath, picturepath);
	}
	
    public static int changePdfToImg(String instructiopath,String picturepath) {  
        int countpage =0;  
        try {  
            //instructiopath ="D:/instructio/2015-05-16/Android 4编程入门经典.pdf"  
            //picturepath = "D:/instructio/picture/2015-05-16/";  
              
            File file = new File(instructiopath);  
            RandomAccessFile raf = new RandomAccessFile(file, "r");  
            FileChannel channel = raf.getChannel();  
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,  
                    0, channel.size());  
            PDFFile pdffile = new PDFFile(buf);  
            //创建图片文件夹  
            File dirfile = new File(picturepath);  
               if(!dirfile.exists()){  
                     dirfile.mkdirs();  
                }  
            //获得图片页数  
            countpage = pdffile.getNumPages();  
            for (int i = 1; i <= pdffile.getNumPages(); i++) {  
                PDFPage page = pdffile.getPage(i);  
                Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()  
                        .getWidth()), ((int) page.getBBox().getHeight()));  
                int n = 2;  
                /** 图片清晰度（n>0且n<7）【pdf放大参数】 */  
                Image img = page.getImage(rect.width * n, rect.height * n,  
                        rect, /** 放大pdf到n倍，创建图片。 */  
                        null, /** null for the ImageObserver */  
                        true, /** fill background with white */  
                        true /** block until drawing is done */  
                );  
                BufferedImage tag = new BufferedImage(rect.width * n,  
                        rect.height * n, BufferedImage.TYPE_INT_RGB);  
                tag.getGraphics().drawImage(img, 0, 0, rect.width * n,  
                        rect.height * n, null);  
                /** 
                 * File imgfile = new File("D:\\work\\mybook\\FilesNew\\img\\" + 
                 * i + ".jpg"); if(imgfile.exists()){ 
                 * if(imgfile.createNewFile()) { System.out.println("创建图片："+ 
                 * "D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg"); } else { 
                 * System.out.println("创建图片失败！"); } } 
                 */  
                FileOutputStream out = new FileOutputStream(picturepath+"/" + i  
                        + ".png");  
                /** 输出到文件流 */  
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
                JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);  
                param2.setQuality(1f, true);  
                /** 1f~0.01f是提高生成的图片质量 */  
                encoder.setJPEGEncodeParam(param2);  
                encoder.encode(tag);  
                /** JPEG编码 */  
                out.close();  
            }  
            channel.close();  
            raf.close();  
            unmap(buf);  
            /** 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法 */  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return countpage;  
          
    }  
  
    @SuppressWarnings("unchecked")  
    public static void unmap(final Object buffer) {  
        AccessController.doPrivileged(new PrivilegedAction() {  
            public Object run() {  
                try {  
                    Method getCleanerMethod = buffer.getClass().getMethod(  
                            "cleaner", new Class[0]);  
                    getCleanerMethod.setAccessible(true);  
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod  
                            .invoke(buffer, new Object[0]);  
                    cleaner.clean();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                return null;  
            }  
        });  
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