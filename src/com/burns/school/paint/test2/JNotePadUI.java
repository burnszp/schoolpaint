package com.burns.school.paint.test2;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint.test2
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-21
 * @purpose:
 * 
 * @version: 1.0
 * 
 *           Revision History at the end of file.
 * 
 *           Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

public class JNotePadUI extends JFrame {

	public JNotePadUI() {

		super("新建文本"); // 设置标题

		this.setBounds(200, 100, 400, 600); // 设置大小

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置关闭

		try {

			String src = "/img/school_logo.jpg"; // 图片路径
			Image image =ImageIO.read(JNotePadUI.class.getClassLoader().getResource(src));
//			Image image = ImageIO.read(this.getClass().getResource(src));

			// 创建图片对象

			this.setIconImage(image); // 设置图标

		} catch (IOException e) {

			e.printStackTrace();

		}

		this.setVisible(true); // 设置可见

	}

	public static void main(String[] args) {

		new JNotePadUI();

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
