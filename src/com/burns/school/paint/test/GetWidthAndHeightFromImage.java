/*******************************************************************************
 * @project: SchoolPaint_7
 * @package: com.burns.school.paint.test
 * @file: GetWidthAndHeightFromImage.java
 * @author: zhangpei
 * @created: 2018-4-29
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GetWidthAndHeightFromImage {
	public static void main(String[] args) throws IOException {
		File f = new File("D:/eclipse_wks/SchoolPaint_7/src/com/burns/school/paint/test/33_01.jpg");
		BufferedImage m_image = ImageIO.read(f);
		System.out.println(m_image.getWidth());
		System.out.println(m_image.getHeight());
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