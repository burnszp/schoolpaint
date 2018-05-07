/*******************************************************************************
 * @project: SchoolPaint_7
 * @package: com.burns.school.paint
 * @file: CloseMainUiDialog.java
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

package com.burns.school.paint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CloseMainUiDialog extends JFrame{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		CloseMainUiDialog cmud = new CloseMainUiDialog();
		cmud.setVisible(true);
	}

	public CloseMainUiDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intUI();
	}

	private void intUI() {
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("感谢您的参与，此软件版权所有为杨柳青第二中学，严禁盗用");
		label1.setFont(new Font("宋体", Font.BOLD, 13));
		panel.add(label1,BorderLayout.NORTH);
		
		
		
		JTextArea label3 = new JTextArea("（注：扫描二维码即可获得制作杨柳青年画的制作流程。）\n下载密码：8z0a");
		label3.setEditable(false);
		label3.setFont(new Font("宋体", Font.BOLD, 15));
		panel.add(label3,BorderLayout.CENTER);
		
		JPanel impPanel = new JPanel();
		JLabel label2 = new JLabel();
		ImageIcon img = new ImageIcon("images/new1.jpg");// 创建图片对象
		label2.setIcon(img);
		impPanel.add(label2);
		panel.add(impPanel,BorderLayout.SOUTH);
		
		getContentPane().add(panel,BorderLayout.CENTER);
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		
		
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