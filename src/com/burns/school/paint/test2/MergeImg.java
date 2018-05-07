/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint.test2
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-22
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test2;

import java.awt.Graphics2D;

import java.awt.Image;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.FileInputStream;

import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

public class MergeImg {

	public static void main(String[] args) {

		try {

			String bigPath = "D:/eclipse_wks/SchoolPaint/src/com/burns/school/paint/school_logo.jpg";

//			String smallPath = "F:\\tomcat8\\webapps\\file_uploads/qrcode/2017/12/a.png";
//
//			String middlePath = "F:\\tomcat8\\webapps\\file_uploads/qrcode/2017/12/394804306601050112.png";

//			String outFile = "F:\\tomcat8\\webapps\\file_uploads/qrcode/2017/12/2.png";

			dealImg(bigPath);

//			dealImg(smallPath);
//
//			dealImg(middlePath);

//			BufferedImage big = ImageIO.read(new File(bigPath));

//			BufferedImage middle = ImageIO.read(new File(middlePath));
//
//			BufferedImage small = ImageIO.read(new File(smallPath));

//			Graphics2D g = middle.createGraphics();

//			int x = (middle.getWidth() - small.getWidth()) / 2;
//
//			int y = (middle.getHeight() - small.getHeight()) / 2;
//
//			g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);

//			Graphics2D gb = big.createGraphics();

//			int xb = (big.getWidth() - middle.getWidth()) / 2;
//
//			int yb = (big.getHeight() - middle.getHeight()) / 2;
//
//			gb.drawImage(middle, xb, yb, middle.getWidth(), middle.getHeight(),
//
//			null);

//			gb.dispose();
//
//			ImageIO.write(big, outFile.split("png")[1], new File(outFile));

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 
	 * 给原图去白边
	 * 
	 * @param imgPath
	 */

	public static void dealImg(String imgPath) {

		File file = new File(imgPath);

		InputStream is;

		try {

			is = new FileInputStream(file);

			BufferedImage bufferedImage = ImageIO.read(is);

			Image image = (Image) bufferedImage;

			ImageIcon imageIcon = new ImageIcon(image);

			bufferedImage = new BufferedImage(

			imageIcon.getIconWidth(), imageIcon.getIconHeight(),

			BufferedImage.TYPE_4BYTE_ABGR);

			Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();

			g2D.drawImage(imageIcon.getImage(), 0, 0,

			imageIcon.getImageObserver());

			int alpha = 0;

			for (int i = bufferedImage.getMinY(); i < bufferedImage

			.getHeight(); i++) {

				for (int j = bufferedImage.getMinX(); j < bufferedImage

				.getWidth(); j++) {

					int rgb = bufferedImage.getRGB(j, i);

					int R = (rgb & 0xff0000) >> 16;

					int G = (rgb & 0xff00) >> 8;

					int B = (rgb & 0xff);

					if (((255 - R) < 30) && ((255 - G) < 30)

					&& ((255 - B) < 30)) {

						rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);

					}

					bufferedImage.setRGB(j, i, rgb);

				}

			}

			g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

			ImageIO.write(bufferedImage, "png", new File("D:/eclipse_wks/SchoolPaint/src/com/burns/school/paint/school_logo_1.jpg"));// 直接输出文件

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

		}

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
