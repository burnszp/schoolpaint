/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint
 * @file: PDFView.java
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

package com.burns.school.paint;

import javax.swing.JDialog;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

/**
 * 查看pdf，把pdf每页分成一个图片，分别阅览。
 * 
 * @ClassName: PDFView
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Burns[张沛]
 * @date 2018-4-20 下午3:39:26
 * 
 */
public class PictureShow extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton jButton1;
	private JLabel jLabel2; // 显示图片的区域
	private JButton jButton3;
	private JButton jButton2;
	private JTextField jTextField1;
	private JLabel jLabel1;
	private int count = 0;
	private int num = 0;
	private ArrayList<String> list = new ArrayList<String>();
	private JScrollPane jsp;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PictureShow inst = new PictureShow(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public PictureShow(File firstPicFile) {
		super();
		setModal(true);
		initGUI();
		afterGUI(firstPicFile);
	}

	private void afterGUI(File firstPicFile) {
		// 文件名
		String fileName = firstPicFile.getName();
		// 文件绝对路径
		String filePath = firstPicFile.getAbsolutePath();
		// 把路径显示在textfield中
		jTextField1.setText(filePath);
		//
		this.setTitle(fileName + "文档展示");
		// 获取这个图片本身
		ImageIcon image = new ImageIcon(filePath);
		// 获取图片的宽和高
		int width = image.getIconWidth();
		int height = image.getIconHeight();
		if (width > height) {
			// 宽图 设置成600*450
			Image scaledImage = image.getImage().getScaledInstance(2454,
					1735, Image.SCALE_DEFAULT);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			jLabel2.setIcon(scaledIcon);
			jLabel2.setHorizontalAlignment(0);
			jLabel2.setVerticalAlignment(0);
		} else {
			// 高图 设置成300*450
			Image scaledImage = image.getImage().getScaledInstance(1190,
					1682, Image.SCALE_DEFAULT);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			jLabel2.setIcon(scaledIcon);
			jLabel2.setHorizontalAlignment(0);
			jLabel2.setVerticalAlignment(0);
		}

		// 下面的代码用于遍历同一个文件夹下的其他图片

		File[] fileList = firstPicFile.getParentFile().listFiles();
		for (int i = 0; i < fileList.length; i++) {

			if (fileList[i].isFile()) {

				String[] part = fileList[i].getName().split("\\.");
				if (part[1].equals("jpg") || part[1].equals("JPG")
						|| part[1].equals("GIF")
						|| part[1].equals("gif")
						|| part[1].equals("png")
						|| part[1].equals("PNG")
						|| part[1].equals("bmp")
						|| part[1].equals("BMP")) {

					list.add(fileList[i].getAbsolutePath());
					num++;

					if (fileList[i].getAbsolutePath().equals(filePath)) {
						count = num;
					}
				}

			}

		}

	}

	private void initGUI() {

		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setTitle("文档展示");
			{
				jLabel1 = new JLabel();
				jLabel1.setText("\u6253\u5f00");
			}
			{
				jTextField1 = new JTextField();
			}
			{
				// 浏览....
				jButton1 = new JButton();
				jButton1.setText("\u6d4f\u89c8...");
				jButton1.addActionListener(this);
			}
			{
				// 显示图片的区域
				jLabel2 = new JLabel();
				jLabel2.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
						1, false));
				jLabel2.setSize(1190, 1682);
				
				jsp = new JScrollPane();
				jsp.setViewportView(jLabel2);
			}
			{
				jButton2 = new JButton();
				jButton2.setText("\u4e0a\u4e00\u9875");
				jButton2.addActionListener(this);
			}
			{
				jButton3 = new JButton();
				jButton3.setText("\u4e0b\u4e00\u9875");
				jButton3.addActionListener(this);
			}
			thisLayout
					.setVerticalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap()
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													jLabel1,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													20,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													jTextField1,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													jButton1,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													jsp,
													GroupLayout.Alignment.LEADING,
													0, 1680, Short.MAX_VALUE)
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addGap(210)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addGroup(
																					GroupLayout.Alignment.LEADING,
																					thisLayout
																							.createSequentialGroup()
																							.addComponent(
																									jButton2,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE)
																							.addGap(0,
																									11,
																									Short.MAX_VALUE))
																			.addGroup(
																					GroupLayout.Alignment.LEADING,
																					thisLayout
																							.createSequentialGroup()
																							.addGap(11)
																							.addComponent(
																									jButton3,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.PREFERRED_SIZE)
																							.addGap(0,
																									0,
																									Short.MAX_VALUE)))
															.addGap(253)))
							.addContainerGap(24, 24));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													jButton2,
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													79,
													GroupLayout.PREFERRED_SIZE)
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addGap(24)
															.addComponent(
																	jLabel1,
																	GroupLayout.PREFERRED_SIZE,
																	54,
																	GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addComponent(
																	jsp,
																	GroupLayout.PREFERRED_SIZE,
																	1000,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	jButton3,
																	GroupLayout.PREFERRED_SIZE,
																	82,
																	GroupLayout.PREFERRED_SIZE))
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addComponent(
																	jTextField1,
																	GroupLayout.PREFERRED_SIZE,
																	428,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(28)
															.addComponent(
																	jButton1,
																	GroupLayout.PREFERRED_SIZE,
																	98,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(19)
															.addGap(0,
																	20,
																	Short.MAX_VALUE))));
			pack();
			this.setSize(1190, 600);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 浏览
		if (e.getSource() == jButton1) {

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
				jTextField1.setText(filePath);
				//
				this.setTitle(fileName + "文档展示");
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
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);
				} else {
					// 高图 设置成300*450
					Image scaledImage = image.getImage().getScaledInstance(1190,
							1682, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);
				}

				// 下面的代码用于遍历同一个文件夹下的其他图片

				File[] fileList = f.getParentFile().listFiles();
				for (int i = 0; i < fileList.length; i++) {

					if (fileList[i].isFile()) {

						String[] part = fileList[i].getName().split("\\.");
						if (part[1].equals("jpg") || part[1].equals("JPG")
								|| part[1].equals("GIF")
								|| part[1].equals("gif")
								|| part[1].equals("png")
								|| part[1].equals("PNG")
								|| part[1].equals("bmp")
								|| part[1].equals("BMP")) {

							list.add(fileList[i].getAbsolutePath());
							num++;

							if (fileList[i].getAbsolutePath().equals(filePath)) {
								count = num;
							}
						}

					}

				}

			}
			//
		} else if (e.getSource() == jButton2) {
			if (count >= 1) {
				String path = list.get(count - 1);
				// System.out.println(path);
				ImageIcon image = new ImageIcon(path);
				int width = image.getIconWidth();
				int height = image.getIconHeight();
				if (width > height) {
					Image scaledImage = image.getImage().getScaledInstance(600,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);

				} else {
					Image scaledImage = image.getImage().getScaledInstance(1190,
							1682, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);
				}
				jTextField1.setText(path);
				File file = new File(path);
				this.setTitle(file.getName() + "文档展示");
				count--;
				// System.out.println(path+"\t"+count);
			} else {
				JOptionPane.showMessageDialog(null, "已经是第一页！");
			}
		} else if (e.getSource() == jButton3) {
			if (count < list.size() - 1) {
				// System.out.println(list.get(0));
				String path = list.get(count + 1);
				ImageIcon image = new ImageIcon(path);
				int width = image.getIconWidth();
				int height = image.getIconHeight();
				if (width > height) {
					Image scaledImage = image.getImage().getScaledInstance(600,
							450, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);
				} else {
					Image scaledImage = image.getImage().getScaledInstance(1190,
							1682, Image.SCALE_DEFAULT);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					jLabel2.setIcon(scaledIcon);
					jLabel2.setHorizontalAlignment(0);
					jLabel2.setVerticalAlignment(0);
				}
				jTextField1.setText(path);
				File file = new File(path);
				this.setTitle(file.getName() + "文档展示");
				count++;
				// System.out.println(path+"\t"+count);
			} else {
				JOptionPane.showMessageDialog(null, "已经是最后一页！");
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
