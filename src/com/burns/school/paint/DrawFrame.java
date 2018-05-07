/*******************************************************************************
 * @project: Java_Image
 * @package: image.java.ttht
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-19
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

//登录监听，画板界面  
package com.burns.school.paint;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Game.Puzzle;

import com.burns.school.paint.video.PlayerMain;
import com.jumao.itms.main.AppFrame;
import com.jumao.itms.ui.ConfigDialog;
import com.jumao.itms.ui.DrawingBoard;

public class DrawFrame extends JFrame implements ActionListener {

	public Graphics g;
	private JButton selectColorB;
	private JButton cutB1;
	public static JLabel colorLabel;

	/** 上方工具栏 */
	private JToolBar toolbar;
	/** 圆形按钮 */
	private JButton ovalButton;
	/** 退出按钮 */
	private JButton exitButton;
	/** 清屏按钮 */
	private JButton clearButton;
	/** 三角形按钮 */
	private JButton triangleButton;
	/** 文字按钮 */
	private JButton textButton;
	/** 线条按钮 */
	private JButton lineButton;
	/** 撤销按钮 */
	private JButton cancelButton;
	/** 回退按钮 */
	private JButton backButton;
	/** 生成图片 */
	private JButton pictureButton;
	/** 设置 */
	private JButton configButton;
	/** 绘图面板 */
	private DrawingBoard board;

	private static final Font font = new Font("微软雅黑", 0, 12);

	private JButton testButton1;
	private JButton testButton2;
	private JButton testButton3;
	private JButton colorB;
	private JButton historyB;
	private JButton videoShowB;
	private JButton pictureCompareB;
	private JButton processB;
	private JButton gameB;
	private JPanel panel_04;
	private JButton mould1B;
	private JButton mould2B;
	private JLabel imageL_end;
	private ImageIcon img_end;
	private String imgPath = "images/wwby_ct.png";
	private JButton mould3B;

	public DrawFrame() {

		setTitle("杨柳青二中年画教程");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println(1111111);
//				super.windowClosing(e);
				// 加入动作
				CloseMainUiDialog cmud = new CloseMainUiDialog();
				cmud.setVisible(true);
			}
		});
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screensize.getHeight();
		setSize(820, 400);
		setLocationRelativeTo(null);

		/**
		 * 添加菜单
		 */
		 {
		 // 初始化一个菜单栏
		 JMenuBar menuBar = new JMenuBar();
		
		 // 初始化菜单
		 JMenu menu = new JMenu("功能");
//		 JMenuItem showDocument = new JMenuItem("教程展示");
//		 showDocument.addActionListener(new ActionListener() {
//		
//		 @Override
//		 public void actionPerformed(ActionEvent e) {
//		
//		 PictureShow inst = new PictureShow();
//		 inst.setLocationRelativeTo(null);
//		 inst.setVisible(true);
//		
//		 }
//		 });
		 JMenuItem showVideo = new JMenuItem("视频展示");
		 showVideo.addActionListener(new ActionListener() {
		
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 String videoPath = "D:/eclipse_wks/SchoolPaint/videos/video.mp4";
		 PlayerMain pm = new PlayerMain();
		
		 }
		 });
		//
		// JMenuItem comparePic = new JMenuItem("图片对比");
		// comparePic.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// ComparePicture pm = new ComparePicture();
		//
		// }
		// });
		//
		// menu.add(showDocument);
		 menu.add(showVideo);
		// menu.add(comparePic);
		//
		 // 把菜单添加到菜单栏
		 menuBar.add(menu);
		
		 // 设置菜单栏
		 setJMenuBar(menuBar);
		 }

		/**
		 * // 东边面板
		 */
//		{
//			JPanel panel_01 = new JPanel();// 东边面板
//			panel_01.setSize(new Dimension(160, height - 100));
//			panel_01.setLayout(new BorderLayout());
//			add(panel_01, BorderLayout.EAST);
//
//			JPanel selectColorPanel = new JPanel();
//			selectColorB = new JButton("选择颜色");
//			selectColorPanel.add(selectColorB);
//			selectColorB.addActionListener(this);
//
//			colorLabel = new JLabel();
//			colorLabel.setPreferredSize(new Dimension(100, 50));
//			colorLabel.setOpaque(true);
//			colorLabel.setBorder(BorderFactory.createTitledBorder("颜色显示"));
//			selectColorPanel.add(colorLabel);
//			panel_01.add(selectColorPanel, BorderLayout.NORTH);
//
//		}

		/**
		 * // 中间面板
		 */
		{

			panel_04 = new JPanel();// 中间面板
			panel_04.setLayout(new BorderLayout());
			img_end = new ImageIcon("images/wwby_ct.png");// 创建图片对象

			int img_height = img_end.getIconHeight();
			int img_width = img_end.getIconWidth();

			 imageL_end = new JLabel();
			imageL_end.setIcon(img_end);
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(imageL_end);
			panel_04.add(jsp);
			//
			panel_04.setPreferredSize(new Dimension(img_width, img_height));
			panel_04.setBackground(new Color(0, 77, 0));
			add(panel_04, BorderLayout.CENTER);

		}

		/**
		 * 西边面板（存放图形按钮）
		 */
		{
			JPanel panel_03 = new JPanel(); // 西边面板（存放图形按钮）
			panel_03.setPreferredSize(new Dimension(110, 600));
			panel_03.setBackground(Color.RED);
			add(panel_03, BorderLayout.WEST);

			historyB = new JButton("年画历史");
			panel_03.add(historyB);
			historyB.addActionListener(this);

			processB = new JButton("制作流程");
			panel_03.add(processB);
			processB.addActionListener(this);

			videoShowB = new JButton("年画视频");
			panel_03.add(videoShowB);
			videoShowB.addActionListener(this);

			colorB = new JButton("开始涂色");
			panel_03.add(colorB);
			colorB.addActionListener(this);


			
			mould1B = new JButton("涂色模版一");
			panel_03.add(mould1B);
			mould1B.addActionListener(this);
			
			mould2B = new JButton("涂色模版二");
			panel_03.add(mould2B);
			mould2B.addActionListener(this);
			
			mould3B = new JButton("涂色模版三");
			panel_03.add(mould3B);
			mould3B.addActionListener(this);
			
			pictureCompareB = new JButton("图片完成度");
//			panel_03.add(pictureCompareB);
			pictureCompareB.addActionListener(this);
			
			gameB = new JButton("年画游戏");
			panel_03.add(gameB);
			gameB.addActionListener(this);
			// cutB1 = new JButton("屏幕截图");
			// panel_03.add(cutB1);
			// cutB1.addActionListener(this);
			//
			// String[] str_01 = { "毛笔减细", "毛笔加粗", "毛笔", "橡皮" };
			//
			// for (int i = 0; i < str_01.length; i++) {
			// JButton button_01 = new JButton(str_01[i]);
			// panel_03.add(button_01);
			// button_01.setPreferredSize(new Dimension(100, 30));
			// button_01.addActionListener(this);
			// }
		}

		setVisible(true);

		if (g == null) {
			g = getGraphics();
		}
		System.out.println("界面.g = " + g);

	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		// 绘制背景图
		img_end = new ImageIcon(imgPath);// 创建图片对象
		System.out.println(imgPath);
		int img_height = img_end.getIconHeight();
		int img_width = img_end.getIconWidth();
		g
		.drawImage(img_end.getImage(), 0, 0, img_width, img_height, this);
//
		 imageL_end = new JLabel();
		imageL_end.setIcon(img_end);
//		panel_04.add(imageL_end);
		//
		panel_04.setSize(new Dimension(img_width, img_height));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == historyB) {
//			System.out.println(this.getClass().getResource("/1.png").getPath());
			// File firstPicFile = new
			// File(this.getClass().getResource("/1.png").getPath());
			File firstPicFile = new File("history/1.png");
			PictureShow inst = new PictureShow(firstPicFile);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);

		}

		if (e.getSource() == processB) {
//			System.out.println(this.getClass().getResource("/1.png").getPath());
			// File firstPicFile = new
			// File(this.getClass().getResource("/1.png").getPath());
			File firstPicFile = new File("process/1.png");
			PictureShow inst = new PictureShow(firstPicFile);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);

		}

		if (e.getSource() == videoShowB) {

			try {
				// Runtime.getRuntime()
				// .exec("\"f:\\Program Files (x86)\\Thunder Network\\Xmp\\Program\\XMP.exe\" e:\\mp4\\high歌.mp4");
				// Runtime.getRuntime().exec("C:/burns/schoolpaint/video/年画五道工序VA0.mp4");
				Desktop.getDesktop().open(new File("video/年画五道工序VA0.mp4"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// String videoPath = "C:/burns/schoolpaint/video/年画五道工序VA0.mp4";
			// PlayerMain pm = new PlayerMain(videoPath);
			// pm.frame.getMediaPlayer().playMedia(videoPath);

		}

		if (e.getSource() == pictureCompareB) {

			ComparePicture pm = new ComparePicture();

		}

		if (e.getSource() == selectColorB) {
			// 颜色按钮
			// JButton but = new JButton();
			// but = (JButton) e.getSource();
			// 显示颜色选取器对话框, 返回选取的颜色（线程将被阻塞, 直到对话框被关闭）
			Color selectedColor = JColorChooser.showDialog(null, "选取颜色", null);

			// 如果用户取消或关闭窗口, 则返回的 color 为 null
			if (selectedColor == null) {
				return;
			}

			// 把选取的颜色设置为标签的背景
			colorLabel.setBackground(selectedColor);
			panel_04.setBackground(selectedColor);

			// 获取颜色的 ARGB 各个分量值
			int alpha = selectedColor.getAlpha();
			int red = selectedColor.getRed();
			int green = selectedColor.getGreen();
			int blue = selectedColor.getBlue();

			colorLabel.setText("A=" + String.format("%02x", alpha) + ", "
					+ String.format("#%02x%02x%02x", red, green, blue));
			System.out.println(selectedColor);

		}

		if (e.getSource() == cutB1) {

			try {
				ScreenShotWindow ssw = new ScreenShotWindow();
				ssw.setVisible(true);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == ovalButton) {
			drawAll(0, null);
		} else if (e.getSource() == triangleButton) {
			drawAll(1, null);
		} else if (e.getSource() == lineButton) {
			drawAll(2, null);
		} else if (e.getSource() == textButton) {
			String roadName = JOptionPane.showInputDialog("请输入文字名称!");
			if (roadName == null || "".equals(roadName)) {
				JOptionPane.showMessageDialog(null, "文字不能为空", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			drawAll(3, roadName);
		} else if (e.getSource() == clearButton) {
			board.clearBoard();
			// 清空记录
			board.shapes.clear();
			board.cancel_shapes.clear();
		} else if (e.getSource() == cancelButton) {
			if (board.shapes.size() < 1) {
				JOptionPane.showMessageDialog(null, "无操作可撤销！");
				return;
			}
			// 获取上一个元素
			int my_index = board.shapes.indexOf(board.currentShape);
			com.jumao.itms.ui.Shape before = my_index > 0 ? board.shapes
					.get(my_index - 1) : null;
			if (!board.cancel_shapes.contains(board.currentShape)) {
				// 从list中移除当前
				board.shapes.remove(board.currentShape);
				// 记录撤销的shape
				board.cancel_shapes.add(board.currentShape);
				// 变shape为当前
				board.currentShape = before;
			}
			board.repaint();
		} else if (e.getSource() == backButton) {
			if (board.cancel_shapes.size() < 1) {
				JOptionPane.showMessageDialog(null, "无操作可回退！");
				return;
			}
			// 撤销、回退操作结果数据如下
			// 1 2 3 4 5 -----> 5 4 3 2 1 -----> 1 2 3 4 5
			com.jumao.itms.ui.Shape shape = board.cancel_shapes
					.get(board.cancel_shapes.size() - 1);
			if (!board.shapes.contains(shape)) {
				// 移除删除记录并还原初始记录
				board.cancel_shapes.remove(shape);
				board.shapes.add(shape);
				board.currentShape = shape;
			}
			board.repaint();
		} else if (e.getSource() == pictureButton) {
			String picPath = board.saveImage();
			JOptionPane
			.showMessageDialog(this, "已成功保存到" + "【" + picPath + "】");
			
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == configButton) {
			ConfigDialog config = new ConfigDialog(board);
			config.setLocationRelativeTo(null);
			config.setVisible(true);
		} else if (e.getSource() == testButton1) {
			board.path = "test1.jpg";
			board.repaint();
		} else if (e.getSource() == testButton2) {
			board.path = "test2.jpg";
			board.repaint();
		} else if (e.getSource() == testButton3) {
			board.path = "test3.jpg";
			board.repaint();
		}

		if (e.getSource() == colorB) {
			String path = null;
			if("images/cfdy_ct.png".equals(imgPath)){
				path = "cfdy.png";
			}
			if("images/c_ct.png".equals(imgPath)){
				path = "c.png";
			}
			if("images/wwby_ct.png".equals(imgPath)){
				path = "wwby.png";
			}
			AppFrame inst = new AppFrame(path);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);
		}
		
		if(e.getSource() == gameB){
			Puzzle app = new Puzzle();
		}
		
		if(e.getSource() == mould1B){
			System.out.println("模版一");
			imgPath = "images/cfdy_ct.png";
			img_end = new ImageIcon(imgPath);// 创建图片对象
			imageL_end.setIcon(img_end);
		}
		
		if(e.getSource() == mould2B){

			
			imgPath = "images/c_ct.png";
			img_end = new ImageIcon(imgPath);// 创建图片对象
			imageL_end.setIcon(img_end);		
		}
		if(e.getSource() == mould3B){

			System.out.println("模版三");
			imgPath = "images/wwby_ct.png";
			img_end = new ImageIcon(imgPath);// 创建图片对象
			imageL_end.setIcon(img_end);	
		
		}
		
		

	}

	/**
	 * 获取图片
	 * 
	 * @param path
	 *            图片路径
	 * @return ImageIcon
	 */
	private ImageIcon createImage(String path) {
		if (null != path && !"".equals(path)) {
			return new ImageIcon("images/" + path);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * 抽取的公共方法，规避冗余
	 * 
	 * @param type
	 *            类型
	 */
	private void drawAll(int type, String name) {
		board.setTool(type);
		DrawingBoard.cursor = 1;
		board.setCursor(new Cursor(1));
		// 绘制文字
		if (type == 3 && null != name) {
			board.setContext(name);
		}
	}

}

/*
 * 截图窗口
 */
class ScreenShotWindow extends JDialog {
	private int orgx, orgy, endx, endy;
	private BufferedImage image = null;
	private BufferedImage tempImage = null;
	private BufferedImage saveImage = null;

	private ToolsWindow tools = null;

	public ScreenShotWindow() throws AWTException {
		// 获取屏幕尺寸
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, d.width, d.height);

		// 截取屏幕
		Robot robot = new Robot();
		image = robot
				.createScreenCapture(new Rectangle(0, 0, d.width, d.height));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 鼠标松开时记录结束点坐标，并隐藏操作窗口
				orgx = e.getX();
				orgy = e.getY();

				if (tools != null) {
					tools.setVisible(false);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// 鼠标松开时，显示操作窗口
				if (tools == null) {
					tools = new ToolsWindow(ScreenShotWindow.this, e.getX(), e
							.getY());
				} else {
					tools.setLocation(e.getX(), e.getY());
				}
				tools.setVisible(true);
				tools.toFront();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// 鼠标拖动时，记录坐标并重绘窗口
				endx = e.getX();
				endy = e.getY();

				// 临时图像，用于缓冲屏幕区域放置屏幕闪烁
				Image tempImage2 = createImage(
						ScreenShotWindow.this.getWidth(),
						ScreenShotWindow.this.getHeight());
				Graphics g = tempImage2.getGraphics();
				g.drawImage(tempImage, 0, 0, null);
				int x = Math.min(orgx, endx);
				int y = Math.min(orgy, endy);
				int width = Math.abs(endx - orgx) + 1;
				int height = Math.abs(endy - orgy) + 1;
				// 加上1防止width或height0
				g.setColor(Color.BLUE);
				g.drawRect(x - 1, y - 1, width + 1, height + 1);
				// 减1加1都了防止图片矩形框覆盖掉
				saveImage = image.getSubimage(x, y, width, height);
				g.drawImage(saveImage, x, y, null);

				ScreenShotWindow.this.getGraphics().drawImage(tempImage2, 0, 0,
						ScreenShotWindow.this);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		RescaleOp ro = new RescaleOp(0.8f, 0, null);
		tempImage = ro.filter(image, null);
		g.drawImage(tempImage, 0, 0, this);
	}

	// 保存图像到文件
	public void saveImage(ToolsWindow toolsWindow) throws IOException {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("保存");

		// 文件过滤器，用户过滤可选择文件
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG",
				"jpg");
		jfc.setFileFilter(filter);

		// 初始化一个默认文件（此文件会生成到桌面上）
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		String fileName = sdf.format(new Date());
		File filePath = FileSystemView.getFileSystemView().getHomeDirectory();
		File defaultFile = new File(filePath + File.separator + fileName
				+ ".jpg");
		jfc.setSelectedFile(defaultFile);

		int flag = jfc.showSaveDialog(this);
		if (flag == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String path = file.getPath();
			// 检查文件后缀，放置用户忘记输入后缀或者输入不正确的后缀
			if (!(path.endsWith(".jpg") || path.endsWith(".JPG"))) {
				path += ".jpg";
			}
			// 写入文件
			ImageIO.write(saveImage, "jpg", new File(path));
			toolsWindow.dispose();
			ScreenShotWindow.this.dispose();
		}
	}
}

/*
 * 操作窗口
 */
class ToolsWindow extends JDialog {
	private ScreenShotWindow parent;

	public ToolsWindow(ScreenShotWindow parent, int x, int y) {
		this.parent = parent;
		this.init();
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
	}

	private void init() {

		this.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar("Java 截图");

		// 保存按钮
		// JButton saveButton = new JButton(new ImageIcon("images/save.gif"));
		JButton saveButton = new JButton("保存");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					parent.saveImage(ToolsWindow.this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(saveButton);

		// 关闭按钮
		// JButton closeButton = new JButton(new ImageIcon("images/close.gif"));
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				ToolsWindow.this.dispose();

			}
		});
		toolBar.add(closeButton);

		this.add(toolBar, BorderLayout.NORTH);
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
