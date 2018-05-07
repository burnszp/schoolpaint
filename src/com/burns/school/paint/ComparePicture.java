/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint
 * @file: ComparePicture.java
 * @author: zhangpei
 * @created: 2018-4-21
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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.burns.school.paint.util.BMPLoader;

public class ComparePicture extends JDialog implements ActionListener {

	private JTextField picTF1;
	private JButton picB1;
	private JLabel picL1;
	private JTextField picTF2;
	private JButton picB2;
	private JLabel picL2;
	private JTextField resultTF;
	private JButton compareB;
	
	public static void main(String[] args) {
		ComparePicture pm = new ComparePicture();
	}

	public ComparePicture() {
		super();
		setModal(true);
		setSize(800, 600);
		initUI();
		setVisible(true);
	}

	private void initUI() {
		setTitle("图片对比");
		setLayout(new GridLayout(3,1));
		setLocationRelativeTo(null);

		picTF1 = new JTextField();
		picB1 = new JButton("选择图片1");
		picB1.addActionListener(this);
		picL1 = new JLabel();
		
		JPanel picPanel1 = new JPanel(new BorderLayout());
		picPanel1.add(picTF1,BorderLayout.WEST);
		picPanel1.add(picB1,BorderLayout.EAST);
		picPanel1.add(picL1,BorderLayout.CENTER);
		
		picTF2 = new JTextField();
		picB2 = new JButton("选择图片2");
		picB2.addActionListener(this);
		picL2 = new JLabel();
		
		JPanel picPanel2 = new JPanel(new BorderLayout());
		picPanel2.add(picTF2,BorderLayout.WEST);
		picPanel2.add(picB2,BorderLayout.EAST);
		picPanel2.add(picL2,BorderLayout.CENTER);
		
		compareB = new JButton("开始对比");
		compareB.addActionListener(this);
		resultTF = new JTextField();
		JPanel picPanel3 = new JPanel(new BorderLayout());
		picPanel3.add(compareB,BorderLayout.WEST);
		picPanel3.add(resultTF,BorderLayout.CENTER);
		
		getContentPane().add(picPanel1);
		getContentPane().add(picPanel2);
		getContentPane().add(picPanel3);
		
		

		picB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == picB1) {

			JFileChooser fc = new JFileChooser();
			int returnval = fc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				// 获取这个被选中的文件
				File f = fc.getSelectedFile();
				// 文件名
				String fileName = f.getName();
				// 文件绝对路径
				String filePath = fc.getSelectedFile().getAbsolutePath();
				// 把路径显示在textfield中
				picTF1.setText(filePath);
				//
				// 获取这个图片本身
				ImageIcon image = new ImageIcon(filePath);
				// 获取图片的宽和高
				int width = image.getIconWidth();
				int height = image.getIconHeight();
				if (width > height) {
					// 宽图 设置成600*450
					Image scaledImage = image.getImage().getScaledInstance(600,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					picL1.setIcon(scaledIcon);
					picL1.setHorizontalAlignment(0);
					picL1.setVerticalAlignment(0);
				} else {
					// 高图 设置成300*450
					Image scaledImage = image.getImage().getScaledInstance(300,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					picL1.setIcon(scaledIcon);
					picL1.setHorizontalAlignment(0);
					picL1.setVerticalAlignment(0);
				}

				// 下面的代码用于遍历同一个文件夹下的其他图片

			}
			//

		}
		
		if (e.getSource() == picB2) {

			JFileChooser fc = new JFileChooser();
			int returnval = fc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				// 获取这个被选中的文件
				File f = fc.getSelectedFile();
				// 文件名
				String fileName = f.getName();
				// 文件绝对路径
				String filePath = fc.getSelectedFile().getAbsolutePath();
				// 把路径显示在textfield中
				picTF2.setText(filePath);
				//
				// 获取这个图片本身
				ImageIcon image = new ImageIcon(filePath);
				// 获取图片的宽和高
				int width = image.getIconWidth();
				int height = image.getIconHeight();
				if (width > height) {
					// 宽图 设置成600*450
					Image scaledImage = image.getImage().getScaledInstance(600,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					picL2.setIcon(scaledIcon);
					picL2.setHorizontalAlignment(0);
					picL2.setVerticalAlignment(0);
				} else {
					// 高图 设置成300*450
					Image scaledImage = image.getImage().getScaledInstance(300,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					picL2.setIcon(scaledIcon);
					picL2.setHorizontalAlignment(0);
					picL2.setVerticalAlignment(0);
				}

				// 下面的代码用于遍历同一个文件夹下的其他图片

			}
			//

		}
		
		if (e.getSource() == compareB) {
			if(!"".equals(picTF1.getText().trim())&&!"".equals(picTF2.getText()!=null)){
				String result = BMPLoader.compareImage(picTF1.getText(), picTF2.getText());
				resultTF.setText(result);
				
			}else{
				JOptionPane.showMessageDialog(ComparePicture.this, "请先选择两个要对比的图片");
			}
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
