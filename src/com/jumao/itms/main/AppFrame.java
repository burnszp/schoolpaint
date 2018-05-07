package com.jumao.itms.main;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import sun.tools.jar.resources.jar;

import com.burns.school.paint.DrawFrame;
import com.burns.school.paint.test.FontText;
import com.burns.school.paint.test.PsImage;
import com.jumao.itms.ui.ConfigDialog;
import com.jumao.itms.ui.DrawingBoard;
import com.jumao.itms.ui.Shape;

/**
 * 主界面
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class AppFrame extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
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

	// private JButton maobijiacunB;
	// private JButton maobijianxiB;
	private JButton penB;
	private JButton cutB1;
	private JButton selectColorB;
	private JLabel colorLabel;
	/**
	 * 保存并打印
	 */
	private JButton saveAndPrintB;
	private JButton rotateButton;// 旋转
	private JButton paperChangeButton;// 换纸
	private String path;
	private JButton rotate90Button;
	private JButton biggerButton;
	private JButton littleButton;

	public AppFrame(String path) {
		this.path = path;
		initGUI();
	}

	private void initGUI() {
		setSize(690, 450);
		setTitle("涂色板");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		// Button
		configButton = new JButton();
		penB = new JButton();
		ovalButton = new JButton();
		triangleButton = new JButton();
		lineButton = new JButton();
		rotateButton = new JButton();
		rotate90Button = new JButton();
		biggerButton = new JButton();
		littleButton = new JButton();
		cancelButton = new JButton();
		paperChangeButton = new JButton();
		exitButton = new JButton();
		// clearButton = new JButton();
		// textButton = new JButton();
		backButton = new JButton();
		pictureButton = new JButton();
		saveAndPrintB = new JButton();

		cutB1 = new JButton();
		// selectColorB = new JButton("选择颜色");
		// colorLabel = new JLabel();

		// maobijiacunB = new JButton();
		// maobijianxiB = new JButton();

		// ToolBar
		// 按add顺序依次加入
		toolbar = new JToolBar();
		toolbar.add(configButton);
		toolbar.add(penB);
//		toolbar.add(ovalButton);
//		toolbar.add(triangleButton);
//		toolbar.add(lineButton);
//		toolbar.add(rotateButton);
//		toolbar.add(biggerButton);
//		toolbar.add(littleButton);
		
		toolbar.add(cancelButton);
		toolbar.add(paperChangeButton);
		// toolbar.add(textButton);
		// toolbar.add(clearButton);
		// toolbar.add(backButton);
		toolbar.add(pictureButton);
		toolbar.add(saveAndPrintB);
		// toolbar.add(exitButton);
		// toolbar.add(cutB1);
		// toolbar.add(selectColorB);
		// toolbar.add(colorLabel);

		// toolbar.add(maobijiacunB);
		// toolbar.add(maobijianxiB);

		// borad
		board = new DrawingBoard(path);

		cutB1.setSize(50, 50);
		cutB1.setFont(font);
		cutB1.setText("屏幕截图");
		cutB1.addActionListener(this);

		configButton.setIcon(createImage("config.png"));
		configButton.setSize(50, 50);
		configButton.setFont(font);
		configButton.setText("设置");
		configButton.addActionListener(this);

		penB.setFont(font);
		penB.setText("毛笔");
		penB.addActionListener(this);

		rotateButton.setFont(font);
		rotateButton.setText("旋转");
		rotateButton.addActionListener(this);
		
		rotate90Button.setFont(font);
		rotate90Button.setText("旋转90°");
		rotate90Button.addActionListener(this);
		
		biggerButton.setFont(font);
		biggerButton.setText("放大");
		biggerButton.addActionListener(this);
		
		littleButton.setFont(font);
		littleButton.setText("縮小");
		littleButton.addActionListener(this);

		ovalButton.setIcon(createImage("oval.png"));
		ovalButton.setFont(font);
		ovalButton.setText("圆形");
		ovalButton.addActionListener(this);

		triangleButton.setIcon(createImage("triangle.png"));
		triangleButton.setFont(font);
		triangleButton.setText("三角形");
		triangleButton.addActionListener(this);

		lineButton.setIcon(createImage("line.png"));
		lineButton.setFont(font);
		lineButton.setText("线条");
		lineButton.addActionListener(this);

		// textButton.setIcon(createImage("text.png"));
		// textButton.setFont(font);
		// textButton.setText("文字");
		// textButton.addActionListener(this);

		// clearButton.setIcon(createImage("clear.png"));
		// clearButton.setFont(font);
		// clearButton.setText("换纸");
		// clearButton.addActionListener(this);

		cancelButton.setIcon(createImage("cancel.png"));
		cancelButton.setFont(font);
		cancelButton.setText("撤销");
		cancelButton.addActionListener(this);

		paperChangeButton.setFont(font);
		paperChangeButton.setText("换纸");
		paperChangeButton.addActionListener(this);

		backButton.setIcon(createImage("back.png"));
		backButton.setFont(font);
		backButton.setText("回退");
		backButton.addActionListener(this);

		pictureButton.setIcon(createImage("save_image.png"));
		pictureButton.setFont(font);
		pictureButton.setText("生成图片");
		pictureButton.addActionListener(this);

		saveAndPrintB.setIcon(createImage("save_image.png"));
		saveAndPrintB.setFont(font);
		saveAndPrintB.setText("保存并打印");
		saveAndPrintB.addActionListener(this);

		exitButton.setIcon(createImage("exit.png"));
		exitButton.setFont(font);
		exitButton.setText("退出");
		exitButton.addActionListener(this);

		// selectColorB.addActionListener(this);
		//
		//
		// colorLabel.setPreferredSize(new Dimension(100, 50));
		// colorLabel.setOpaque(true);
		// colorLabel.setBorder(BorderFactory.createTitledBorder("颜色显示"));

		// maobijiacunB.setFont(font);
		// maobijiacunB.setText("毛笔加粗");
		// maobijiacunB.addActionListener(this);
		//
		// maobijianxiB.setFont(font);
		// maobijianxiB.setText("毛笔减细");
		// maobijianxiB.addActionListener(this);

		getContentPane().add(toolbar, "North");
//		JScrollPane jsp = new JScrollPane();
//		jsp.setViewportView(board);
		getContentPane().add(board, "Center");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == penB) {
			drawAll(4, null);
		} 
		if (e.getSource() == ovalButton) {
			drawAll(0, null);
		} 
		if (e.getSource() == triangleButton) {
			drawAll(1, null);
		} 
		if (e.getSource() == lineButton) {
			drawAll(2, null);
		} 
		if (e.getSource() == textButton) {
			String roadName = JOptionPane.showInputDialog("请输入文字名称!");
			if (roadName == null || "".equals(roadName)) {
				JOptionPane.showMessageDialog(null, "文字不能为空", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			drawAll(3, roadName);
		} 
		if (e.getSource() == paperChangeButton) {
			board.clearBoard();
			// 清空记录
			board.shapes.clear();
			board.cancel_shapes.clear();
		} 
		if (e.getSource() == cancelButton) {
			if (board.shapes.size() < 1) {
				JOptionPane.showMessageDialog(null, "无操作可撤销！");
				return;
			}
			// 获取上一个元素
			int my_index = board.shapes.indexOf(board.currentShape);
			Shape before = my_index > 0 ? board.shapes.get(my_index - 1) : null;
			if (!board.cancel_shapes.contains(board.currentShape)) {
				// 从list中移除当前
				board.shapes.remove(board.currentShape);
				// 记录撤销的shape
				board.cancel_shapes.add(board.currentShape);
				// 变shape为当前
				board.currentShape = before;
			}
			board.repaint();
		} 
		if (e.getSource() == backButton) {
			if (board.cancel_shapes.size() < 1) {
				JOptionPane.showMessageDialog(null, "无操作可回退！");
				return;
			}
			// 撤销、回退操作结果数据如下
			// 1 2 3 4 5 -----> 5 4 3 2 1 -----> 1 2 3 4 5
			Shape shape = board.cancel_shapes
					.get(board.cancel_shapes.size() - 1);
			if (!board.shapes.contains(shape)) {
				// 移除删除记录并还原初始记录
				board.cancel_shapes.remove(shape);
				board.shapes.add(shape);
				board.currentShape = shape;
			}
			board.repaint();
		} 
		if (e.getSource() == pictureButton) {
			String picPath = board.saveImage();
			board.writeWordInPic(picPath);
			JOptionPane
			.showMessageDialog(this, "已成功保存到" + "【" + picPath + "】");
		} 
		if (e.getSource() == saveAndPrintB) {
			board.saveAndPrint();
		} 
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		if (e.getSource() == configButton) {
			ConfigDialog config = new ConfigDialog(board);
			config.setLocationRelativeTo(null);
			config.setVisible(true);
			// } else if (e.getSource() == maobijiacunB) {
			// ((Graphics2D) board.getGraphics()).setStroke(new BasicStroke(1));
			// } else if (e.getSource() == maobijianxiB) {
			// ((Graphics2D) board.getGraphics()).setStroke(new BasicStroke(1));
		}
		if (e.getSource() == cutB1) {

			try {
				ScreenShotWindow ssw = new ScreenShotWindow();
				ssw.setVisible(true);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}

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

			// 获取颜色的 ARGB 各个分量值
			int alpha = selectedColor.getAlpha();
			int red = selectedColor.getRed();
			int green = selectedColor.getGreen();
			int blue = selectedColor.getBlue();

			colorLabel.setText("A=" + String.format("%02x", alpha) + ", "
					+ String.format("#%02x%02x%02x", red, green, blue));

			System.out.println(selectedColor);
			board.setBgColor(selectedColor);
		}
		//旋转
		if (e.getSource() == rotateButton) {
			board.rotate();
		}
		
		if (e.getSource() == rotate90Button) {
			board.rotate90();
//			board.drawTransImage(g, board., drawy, zoom);
			
		}
		
		if (e.getSource() == biggerButton) {
			board.multiple++;
			board.flag=true;
			board.add=true;
			board.repaint();
//			board.drawTransImage(g, board., drawy, zoom);
			
		}
		
		if (e.getSource() == littleButton) {
			if(board.multiple<=1){
				 JOptionPane.showMessageDialog(null, "再缩小就没法涂色了！");
			}else{
				
				board.multiple--;
				board.flag=true;
				board.add=false;
				board.repaint();
			}
//			board.drawTransImage(g, board., drawy, zoom);
			
		}
		
		
		
		
		

	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				AppFrame inst = new AppFrame("wwby.png");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
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
			// 给图片增加文字
			{
				// 使用默认时区和语言环境获得一个日历。
				Calendar rightNow = Calendar.getInstance();
				/*
				 * 用Calendar的get(int field)方法返回给定日历字段的值。 HOUR 用于 12 小时制时钟 (0 -
				 * 11)，HOUR_OF_DAY 用于 24 小时制时钟。
				 */
				Integer year = rightNow.get(Calendar.YEAR);
				Integer month = rightNow.get(Calendar.MONTH) + 1; // 第一个月从0开始，所以得到月份＋1
				Integer day = rightNow.get(rightNow.DAY_OF_MONTH);

				StringBuffer data = new StringBuffer();
				data.append(year).append(".").append(month).append(".")
						.append(day);

				FontText[] ftArr = new FontText[3];
				ftArr[0] = new FontText("杨柳青年画", 1, "#CC2BAC", 40, "黑体");
				ftArr[1] = new FontText("第三届全国基础教育信息化应用展示交流", 1, "#CC2BAC", 40,
						"黑体");
				ftArr[2] = new FontText(data.toString(), 1, "#CC2BAC", 40, "黑体");

				ImageIcon imgIcon = new ImageIcon(path);
				Image img = imgIcon.getImage();
				int width = img.getWidth(null);
				int height = img.getHeight(null);
				BufferedImage bimage = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);

				Graphics2D g = bimage.createGraphics();
				g.setColor(Color.BLACK);
				g.setBackground(Color.GREEN);
				g.drawImage(img, 0, 0, null);
				Font font = new Font(null, Font.BOLD, 15);
				g.setFont(font);

				FontMetrics metrics = new FontMetrics(font) {
				};
				Rectangle2D bounds = metrics.getStringBounds(
						ftArr[0].getText(), null);
				int textWidth = (int) bounds.getWidth();
				int textHeight = (int) bounds.getHeight();
				int left = 0;
				int top = textHeight;

				FontMetrics metrics1 = new FontMetrics(font) {
				};
				Rectangle2D bounds1 = metrics1.getStringBounds(
						ftArr[1].getText(), null);
				int textWidth1 = (int) bounds1.getWidth();
				int textHeight1 = (int) bounds1.getHeight();
				int left1 = textWidth1;
				int top1 = textHeight1;

				FontMetrics metrics2 = new FontMetrics(font) {
				};
				Rectangle2D bounds2 = metrics2.getStringBounds(
						ftArr[2].getText(), null);
				int textWidth2 = (int) bounds2.getWidth();
				int textHeight2 = (int) bounds2.getHeight();
				int left2 = textWidth2;
				int top2 = textHeight2;

				g.drawString(ftArr[0].getText(), left, top);
				g.drawString(ftArr[1].getText(), width - left1, height - top1
						- top2);
				g.drawString(ftArr[2].getText(), width - left2, height - top2);

				g.dispose();

				try {
					FileOutputStream out = new FileOutputStream(path);
					ImageIO.write(bimage, "JPEG", out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
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
