package com.burns.school.paint.test;

/*******************************************************************************
 * @project: SchoolPaint_7
 * @package: com.burns.school.paint.test
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-28
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

class J_JPanel extends JPanel {
	/**
   * 
   */
	private static final long serialVersionUID = 1L;
	Image m_image;
	int showpicture = 0;

	@SuppressWarnings("static-access")
	public J_JPanel() throws IOException {
		J_Puzzle a = new J_Puzzle();
		if (a.changetime == -1)
			showpicture = 1;
//		File f = new File("123454.jpg");
		File f = new File("D:/eclipse_wks/SchoolPaint_7/src/com/burns/school/paint/test/123454.jpg");
		m_image = ImageIO.read(f);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(m_image, 0, 0, 501, 321, this);
	}
}

public class J_Puzzle extends JFrame implements ActionListener {
	/**
   * 
   */
	private static final long serialVersionUID = 1L;
	int i, j;
	static int changetime = 0;
	Container c = getContentPane();
	JButton b[] = new JButton[16];
	ImageIcon ic[][] = new ImageIcon[3][3];

	public J_Puzzle() throws IOException {

		super("拼图小游戏");

		String pic_name[] = new String[9];
		for (i = 0; i < 3; i++)
			for (j = 0; j < 3; j++) {
				int m = i * 3 + j + 1;
				pic_name[m - 1] = "D:/eclipse_wks/SchoolPaint_7/src/com/burns/school/paint/test/33_0" + String.valueOf(m) + ".jpg";
				ic[i][j] = new ImageIcon(pic_name[m - 1]);
			}

		JMenuBar mBar = new JMenuBar();
		setJMenuBar(mBar);

		int k = 0;

		JMenu[] m = { new JMenu("菜单(M)"), new JMenu("帮助(H)") };
		char mC[][] = { { 'M', 'H' }, { 'S', 'X', 'C', 'Z' }, { 'E', 'T' } };
		JMenuItem mItem[][] = {
				{ new JMenuItem("开始(S)"), new JMenuItem("重置(X)"),
						new JMenuItem("背景更换(C)"), new JMenuItem("退出(Z)") },
				{ new JMenuItem("查看样图(E)"), new JMenuItem("关于(T)") } };
		for (i = 0; i < 2; i++) {
			mBar.add(m[i]);
			m[i].setMnemonic(mC[0][i]);
			if (i == 0)
				k = 0;
			else
				k = 1;
			for (j = 0; j < 4 - i - k; j++) {
				m[i].add(mItem[i][j]);
				mItem[i][j].setMnemonic(mC[i + 1][j]);
				mItem[i][j].setAccelerator(KeyStroke.getKeyStroke("ctrl"
						+ mC[i + 1][j]));
				mItem[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						JMenuItem mItem = (JMenuItem) e.getSource();
						if (mItem.getText().equalsIgnoreCase("重置(X)")
								|| mItem.getText().equalsIgnoreCase("开始(S)")) {
							int location[][] = { { 17, 13 }, { 17, 103 },
									{ 17, 193 }, { 17, 283 }, { 107, 13 },
									{ 107, 103 }, { 107, 193 }, { 107, 283 },
									{ 197, 13 }, { 197, 103 }, { 197, 193 },
									{ 197, 283 }, { 287, 13 }, { 287, 103 },
									{ 287, 193 }, { 287, 283 } };
							int rd_number[] = new int[16];
							rd_number = randomnumber();
							for (i = 1; i < 16; i++)
								b[i].setLocation(
										location[rd_number[i - 1] - 1][0],
										location[rd_number[i - 1] - 1][1]);
						} else if (mItem.getText().equalsIgnoreCase("背景更换(C)")) {

							changetime++;
							for (i = 0; i < 15; i++) {
								b[i + 1].setIcon(null);
								b[i + 1].setIcon(ic[changetime][i]);
							}
							if (changetime == 1)
								changetime = -1;
						} else if (mItem.getText().equalsIgnoreCase("退出(Z)")) {
							int a = JOptionPane.showConfirmDialog(null,
									"您确定退出游戏？");
							if (a == 0)
								System.exit(0);
						} else if (mItem.getText().equalsIgnoreCase("查看样图(E)")) {

							JFrame jj = new JFrame("样图");
							jj.setSize(501, 321);
							jj.setLocation(
									Toolkit.getDefaultToolkit().getScreenSize().width / 3 - 360,
									Toolkit.getDefaultToolkit().getScreenSize().height / 4);
							jj.setVisible(true);
							Container c1 = jj.getContentPane();

							try {
								c1.add(new J_JPanel(), BorderLayout.CENTER);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (mItem.getText().equalsIgnoreCase("关于(T)")) {
							JOptionPane.showMessageDialog(null,
									"简单拼图小游戏\n制作人：菜鸟");
						}

					}

				});
			}

		}
		m[0].insertSeparator(1);
		m[1].insertSeparator(1);

		GridBagLayout gr = new GridBagLayout();
		c.setLayout(gr);

		int gx[] = { 0, 1, 2 };
		int gy[] = { 0, 1, 2 };
		int k1;
		Dimension d = new Dimension(167,107);
		String s_number;
		GridBagConstraints gc = new GridBagConstraints();
		for (i = 0; i < 3; i++) {
			// if(i==3){
			// k1=4;
			// }else k1=5;
			for (j = 0; j < 3; j++) {
				gc.gridx = gx[j];
				gc.gridy = gy[i];
				gc.fill = GridBagConstraints.NONE;
				s_number = String.valueOf(j + i*3);
				b[j + i * 3] = new JButton(s_number, ic[i][j]);
				b[j + i * 3].setPreferredSize(d);
				b[j + i * 3].setFont(new Font("宋体", Font.PLAIN, 0));
				gr.setConstraints(b[j + i * 3], gc);
				c.add(b[j + i* 3]);

			}
		}
		for (i = 0; i < 9; i++)
			b[i].addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int j;
		JButton b = (JButton) e.getSource();
		Point p = b.getLocation();
		Point p1 = null;
		for (j = -1; j < 2; j++) {
			if (p.y + j * 90 > 283 || p.y + j * 90 < 13)
				continue;
			else {
				Component a = c.getComponentAt(p.x, p.y + j * 90);
				if (a.getHeight() != 90)
					p1 = new Point(p.x, p.y + j * 90);
			}
		}
		for (j = -1; j < 2; j++) {
			if (p.x + j * 90 > 287 || p.x + j * 90 < 17)
				continue;

			else {
				Component a = c.getComponentAt(p.x + j * 90, p.y);
				if (a.getHeight() != 90)
					p1 = new Point(p.x + j * 90, p.y);
			}
		}
		if (p1 != null)
			b.setLocation(p1.x, p1.y);
		if (check() == true)
			JOptionPane.showMessageDialog(null, "恭喜您成功了");
	}

	/* 产生随机数 */
	public int[] randomnumber() {
		Random rd = new Random();
		int n[] = new int[9];
		for (int i = 0; i < 9; i++) {
			int temp = rd.nextInt(9) + 1;
			n[i] = temp;
			for (int j = 0; j < i; j++)
				if (n[j] == temp) {
					i--;
					break;
				}
		}
		return n;
	}

	/* 判断是否排序成功 */
	public boolean check() {
		Point location[] = new Point[9];
		boolean bo = false;
		int count = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				location[i * 3 + j] = new Point(17 + j * 90, 13 + i * 90);

		for (int i = 0; i < 15; i++) {
			if (b[i + 1].getLocation().x == location[i].x
					&& b[i + 1].getLocation().y == location[i].y)
				count++;
			if (count == 15)
				bo = true;
		}
		return bo;
	}

	public static void main(String args[]) throws IOException {
		J_Puzzle app = new J_Puzzle();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		app.setSize(501, 321);
		app.setVisible(true);
		app.setResizable(false);
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
