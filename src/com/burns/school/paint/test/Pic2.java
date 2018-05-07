/*******************************************************************************
 * @project: SchoolPaint_8
 * @package: com.burns.school.paint.test
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-5-1
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Pic2 {

	public static void main(String[] args) throws Exception {
		BufferedImage bi = transform(ImageIO.read(new File(
				"src/test/ccsJLPT.JPG")));
		ImageIO.write(bi, "jpg", new File("src/test/out.jpg"));
	}

	private static BufferedImage transform(BufferedImage originalImage) {

		AffineTransform tx = new AffineTransform();

		// last, width = height and height = width :)
		tx.translate(originalImage.getHeight() / 2,
				originalImage.getWidth() / 2);
		tx.rotate(Math.PI / 2);
		// first - center image at the origin so rotate works OK
		tx.translate(-originalImage.getWidth() / 2,
				-originalImage.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);

		// new destination image where height = width and width = height.
		BufferedImage newImage = new BufferedImage(originalImage.getHeight(),
				originalImage.getWidth(), originalImage.getType());
		op.filter(originalImage, newImage);

		return newImage;
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
