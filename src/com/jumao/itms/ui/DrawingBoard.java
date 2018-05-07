package com.jumao.itms.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;

import com.burns.school.paint.test.FontText;
import com.burns.school.paint.test.PsImage;
import com.jumao.itms.data.ColorParam;
import com.jumao.itms.data.ImageParam;
import com.jumao.itms.data.LineParam;
import com.jumao.itms.data.PointParam;
import com.jumao.itms.data.TextParam;

/**
 * 画板
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class DrawingBoard extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;
	// BasicStroke 类定义针对图形图元轮廓呈现属性的一个基本集合
	// public static final float STROKES[] = new float[] {10.0F, 9.5F, 9.0F,
	// 8.5F, 8.0F, 7.5F, 7.0F, 6.5F, 6.0F, 5.5F, 5.0F, 4.5F, 4.0F, 3.5F, 3.0F,
	// 2.5F, 2.0F, 1.5F, 1.0F};

	/** 绘图类型（0、圆形 1、三角形 2、线条 3、文字） */
	private int tool = 0;
	/** 鼠标形状（0、箭头 1、十字架） */
	public static int cursor = 0;
	/** 当前的图形 */
	public Shape currentShape;
	/** 图形集合 */
	public ArrayList<Shape> shapes;
	/** 撤销的图形集合 */
	public ArrayList<Shape> cancel_shapes;
	/** 文字内容 */
	private String context;
	/** 图片类 */
	private ImageParam image;

	// 画笔默认颜色
	/** 圆形默认画笔颜色 */
	public Color oval_color = Color.GREEN;
	/** 三角形默认画笔颜色 */
	public Color tria_color = Color.GREEN;
	/** 线条默认画笔颜色 */
	public Color line_color = Color.GREEN;
	/** 毛笔默认画笔颜色 */
	public Color brushPen_color = Color.GREEN;
	/** 文字默认画笔颜色 */
	public Color text_color = Color.GREEN;

	// 画笔默认大小
	/** 圆形默认画笔大小 */
	public float oval_stroke = 10.0F;
	/** 三角形默认画笔大小 */
	public float tria_stroke = 10.0F;
	/** 线条默认画笔大小 */
	public float line_stroke = 10.0F;
	/** 线条默认画笔大小 */
	public float brushPen_stroke = 10.0F;

	// 文字画笔设置不起作用，需要设置文字的样式
	/** 字体 */
	public String text_face = "微软雅黑";
	/** 样式 */
	public int text_style = 0;
	/** 大小 */
	public int text_size = 20;

	/** 画板背景图片路径 */
	public String path = "wwby.png";

	private int old_width = 0;
	private int old_height = 0;
	private int new_width = 0;
	private int new_height = 0;
	
	
	public boolean flag = false;//调用repaint（paintComponent）时是否放大，缩小
	public boolean add = true;

	private Color color;

	 //水平翻转比例的标志。-1表示需要进行水平翻转
    int m_nFlipXScale = 1 ;
    //垂直翻转比例的标志。-1表示需要进行垂直翻转
    int m_nFlipYScale = 1 ; 
    //旋转的角度。因为工程需要，代码中直接写成了90，可以根据具体需要动态修改，以符合实际情况
    int roteAngle = 0 ;    
    //缩放比例。默认的比例0表示没有翻转，具体的翻转大小通过一个方法:getZoomSize()获取
    float zoomLevel = 1 ;

	private ImageIcon icon;

	public int multiple = 1; 

	public DrawingBoard(String path) {
		setSize(658, 415);
		setBackground(Color.GREEN);
		this.path = path;
		// 初始化集合
		shapes = new ArrayList<Shape>();
		cancel_shapes = new ArrayList<Shape>();
		// 添加监听
		addMouseListener(this);
		addMouseMotionListener(this);

//		image = new ImageParam(658, 800, new ColorParam(0, 0, 0));
//		image.setLines(new ArrayList<LineParam>());
//		image.setTexts(new ArrayList<TextParam>());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		// 绘制背景图
		icon = createImageIcon(path);
		System.out.println("new_width:"+new_width);
		System.out.println("new_height:"+new_height);
		
		float zoomw = getZoomSize(new_width,multiple ) ;
        float zoomh = getZoomSize(new_height,multiple) ;
        
//		JLabel jl = new JLabel();
		graphics2d
		.drawImage(icon.getImage(), 0, 0, (int)(zoomw), (int)(zoomh), this);
//		JScrollPane jsp = new JScrollPane();
//		jsp.setViewportView(jl);
//		this.add(jsp);

//		 drawTransImage(g,new_width,new_height,zoomLevel) ;
		// 绘制画板中的所有图形
		if (null != shapes && shapes.size() > 0) {
			for (int i = 0; i < shapes.size(); i++) {
				((Shape) shapes.get(i)).draw(graphics2d,multiple,flag,add);
			}
		}
	}


	 public static final float getZoomSize(int sourceSize,float zoomLevel)
    {
        if (zoomLevel == 1)
              return sourceSize ;
        else
        if (zoomLevel < 0)
            return sourceSize / (Math.abs(zoomLevel)) ;
        else
            return sourceSize * (zoomLevel) ;
      }   

	@Override
	public void mousePressed(MouseEvent e) {
		flag=false;
//		getGraphics().setColor(Color.RED);
		if (cursor == 1) {
			if (e.getButton() == 1) {
				switch (tool) {
				case 0: // 圆形
					currentShape = new Oval(oval_color, oval_stroke, "oval",
							e.getX(), e.getY());
					break;
				case 1: // 三角形
					currentShape = new Triangle(tria_color, tria_stroke,
							"triangle", e.getX(), e.getY());
					break;
				case 2: // 线条
					currentShape = new Line(line_color, line_stroke, "line",
							e.getX(), e.getY());
					break;
				case 3: // 文字
					currentShape = new Text(text_color, "text", e.getX(),
							e.getY(), context, text_face, text_style, text_size);
					break;
				case 4: // 毛笔
					// Graphics g = super.getGraphics();
					System.out.println("e.getX():"+e.getX());
					System.out.println("e.getY()"+e.getY());
					currentShape = new Pen(brushPen_color, brushPen_stroke, "maobi", e.getX(), e.getY());
					// currentShape = new Line(line_color, line_stroke, "maobi",
					// e.getX(), e.getY());
					// break;
				default://
					break;
				}
				shapes.add(currentShape);
				repaint();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		if (tool == 4) {
//
//			new_width = e.getX();
//			new_height = e.getY();
//			Random r = new Random();
//			Graphics g = getGraphics();
//			g.setColor(color);
//			g.drawLine(new_width, new_height, new_width, new_height);
//			for (int i = 0; i < 10; i++) {
//				int p = r.nextInt(10);
//				int q = r.nextInt(10);
//				g.drawLine(new_width + p, new_height + q, new_width + p,
//						new_height + q);
//			}
//			repaint();
//		}

		// if (cursor != 1) {
		// JOptionPane.showMessageDialog(this, "请选择一种将要绘制的类型！");
		// return;
		// }
		if (getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
			if (e.getX() < old_width) {
				new_width = e.getX();
				repaint();
			}
		} else if (getCursor().getType() == Cursor.S_RESIZE_CURSOR) {
			if (e.getY() < old_height) {
				new_height = e.getY();
				repaint();
			}
		} else if (getCursor().getType() == Cursor.SE_RESIZE_CURSOR) {
			if (e.getX() < old_width && e.getY() < old_height) {
				new_width = e.getX();
				new_height = e.getY();
				repaint();
			}
		} else {
			if (null != currentShape) {
				currentShape.mouseDragged(e);
				repaint();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		cursor = 0;
		// setCursor(new Cursor(cursor));
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imageT = new ImageIcon(getClass().getResource("maobi3.png"))
				.getImage();
		setCursor(tk.createCustomCursor(imageT, new Point(10, 10), "norm"));
		;

		// 添加一条线路
		if (currentShape instanceof Line ) {
			Line temp = (Line) currentShape;
			LineParam line = new LineParam();
			line.setSize(12);
			line.setStart(new PointParam(temp.getLineStartX(), temp
					.getLineStartY()));
			line.setEnd(new PointParam(temp.getLineEndX(), temp.getLineEndY()));
			image.getLines().add(line);
		}

//		// 添加一条线路
//		if (currentShape instanceof Pen ) {
//			Pen temp = (Pen) currentShape;
//			LineParam line = new LineParam();
//			line.setSize(12);
//			line.setStart(new PointParam(temp.getLineStartX(), temp
//					.getLineStartY()));
//			line.setEnd(new PointParam(temp.getLineEndX(), temp.getLineEndY()));
//			image.getLines().add(line);
//		}

		// 添加一行文字
		if (currentShape instanceof Text) {
			// 开始点
			PointParam start = new PointParam(
					((Text) currentShape).getLineStartX(),
					((Text) currentShape).getLineStartY());
			String word = ((Text) currentShape).getRoadName();
			// 文字的排序
			TextParam text = new TextParam(null, word,
					((Text) currentShape).getTextSize(), start, null, null);
			image.getTexts().add(text);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 3) {
			if (null != currentShape) {
				AttributeDialog dialog = new AttributeDialog(this);
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);
			}
		} else if (e.getButton() == 1) {
			if (null != currentShape) {
				currentShape.mouseDragged(e);
				repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标进入
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imageT = new ImageIcon(getClass().getResource("maobi3.png"))
				.getImage();
		setCursor(tk.createCustomCursor(imageT, new Point(10, 10), "norm"));
		;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 鼠标退出
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imageT = new ImageIcon(getClass().getResource("maobi3.png"))
				.getImage();
		setCursor(tk.createCustomCursor(imageT, new Point(10, 10), "norm"));
		;
		// 鼠标移动
		// {
		// if (getCursor().getType() == 1) {
		// return;
		// }
		// if ((e.getX() == new_width || e.getX() == new_width + 1
		// || e.getX() == new_width - 1 || e.getX() == new_width + 2
		// || e.getX() == new_width - 2 || e.getX() == new_width + 3 || e
		// .getX() == new_width - 3)
		// && (e.getY() == new_height || e.getY() == new_height + 1
		// || e.getY() == new_height - 1
		// || e.getY() == new_height + 2
		// || e.getY() == new_height - 2
		// || e.getY() == new_height + 3 || e.getY() == new_height - 3)) {
		// setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		// } else {
		// if ((e.getX() == new_width || e.getX() == new_width + 1
		// || e.getX() == new_width - 1
		// || e.getX() == new_width + 2 || e.getX() == new_width - 2)
		// && (getCursor().getType() != 5)) {
		// setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
		// } else if ((e.getY() == new_height
		// || e.getY() == new_height + 1
		// || e.getY() == new_height - 1
		// || e.getY() == new_height + 2 || e.getY() == new_height - 2)
		// && (getCursor().getType() != 5)) {
		// setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
		// } else {
		// setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		// }
		// }
		// }
	}

	/**
	 * 保存绘制好的图像
	 */
	public String saveImage() {
		String filePath = null;
		try {
			BufferedImage bimg = new BufferedImage(new_width, new_height,
					BufferedImage.TYPE_INT_RGB);
			
			Graphics2D graphics2d = bimg.createGraphics();
			paint(graphics2d);// 关键
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
			System.out.println(com.getPath());
			filePath = com.getPath() + "\\" + System.currentTimeMillis()
					+ ".jpg";
			File file = new File(filePath);
			ImageIO.write(bimg, "jpg", file);
//			JOptionPane
//					.showMessageDialog(this, "已成功保存到" + "【" + filePath + "】");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "保存出错");
			e.printStackTrace();
		}
		
		return filePath;
	}
	
	public void writeWordInPic(String path){

		//使用默认时区和语言环境获得一个日历。  
        Calendar    rightNow    =    Calendar.getInstance();     
        /*用Calendar的get(int field)方法返回给定日历字段的值。 
        HOUR 用于 12 小时制时钟 (0 - 11)，HOUR_OF_DAY 用于 24 小时制时钟。*/  
        Integer year = rightNow.get(Calendar.YEAR);   
        Integer month = rightNow.get(Calendar.MONTH)+1; //第一个月从0开始，所以得到月份＋1  
        Integer day = rightNow.get(rightNow.DAY_OF_MONTH);
        
        StringBuffer data = new StringBuffer();
        data.append(year).append(".").append(month).append(".").append(day);
        
		FontText[] ftArr = new FontText[3];
    	ftArr[0] = new FontText("杨柳青年画", 1, "#CC2BAC", 40, "黑体");
    	ftArr[1] = new FontText("第三届全国基础教育信息化应用展示交流", 1, "#CC2BAC", 40, "黑体");
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
        
        
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(ftArr[0].getText(), null);
        int textWidth = (int) bounds.getWidth();
        int textHeight = (int) bounds.getHeight();
        int left = 0;
        int top = textHeight;
        
        FontMetrics metrics1 = new FontMetrics(font){};
        Rectangle2D bounds1 = metrics1.getStringBounds(ftArr[1].getText(), null);
        int textWidth1 = (int) bounds1.getWidth();
        int textHeight1 = (int) bounds1.getHeight();
        int left1 = textWidth1;
        int top1 = textHeight1;
        
        FontMetrics metrics2 = new FontMetrics(font){};
        Rectangle2D bounds2 = metrics2.getStringBounds(ftArr[2].getText(), null);
        int textWidth2 = (int) bounds2.getWidth();
        int textHeight2 = (int) bounds2.getHeight();
        int left2 = textWidth2;
        int top2 = textHeight2;
        
        
        
        g.drawString(ftArr[0].getText(), left, top);
        g.drawString(ftArr[1].getText(), width-left1, height-top1-top2);
        g.drawString(ftArr[2].getText(), width-left2, height-top2);
        
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
	
	/**
	 * 打印绘制好的图像
	 */
	public void printImage(String filePath) {
		File file = new File(filePath);
        if (file.exists()) {
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
        	JOptionPane.showMessageDialog(null, "没有找到要打印的文件："+filePath);
        }
    }

	/**
	 * 清楚屏幕已绘制的图像
	 */
	public void clearBoard() {
		shapes.clear();
		image.getLines().clear();
		image.getTexts().clear();
		repaint();
	}

	/**
	 * 选择图形类型
	 * 
	 * @param i
	 *            0、圆形 1、三角形 2、线条 3、文字
	 */
	public void setTool(int i) {
		if (i < 0 || i > 6) {
			throw new IllegalArgumentException("Invaild Tool Specified!");
		} else {
			tool = i;
		}
	}

	/**
	 * 设置画笔类型（每种图形画笔不同）
	 * 
	 * @param size
	 *            画笔大小
	 * @param type
	 *            图形
	 */
	public void setStrokeSize(float size, String type) {
		if (null == currentShape) {
			throw new NullPointerException("currentShap is Null!");
		}
		if (size < 0 || size > 10) {
			throw new IllegalArgumentException("Invaild Weight Specified!");
		}
		if ("oval".equals(type)) {
			oval_stroke = size;
		}
		if ("tria".equals(type)) {
			tria_stroke = size;
		}
		if ("line".equals(type)) {
			line_stroke = size;
		}
		currentShape.stroke = size;
		repaint();
	}

	/**
	 * 设置当前图形横坐标
	 * 
	 * @param x
	 *            横坐标
	 */
	public void setCurrentX(int x) {
		currentShape.currentX = x;
		repaint();
	}

	/**
	 * 设置当前图形纵坐标
	 * 
	 * @param y
	 *            纵坐标
	 */
	public void setCurrentY(int y) {
		currentShape.currentY = y;
		repaint();
	}

	/**
	 * 设置当前图形缩放比例（主要是圆形和三角形）
	 * 
	 * @param d
	 *            比例
	 */
	public void setCurrentD(int d) {
		currentShape.currentD = d;
		repaint();
	}

	/**
	 * 设置当前图形颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setCurrentColor(Color color) {
		currentShape.color = color;
		repaint();
	}

	/**
	 * 设置当前文字图形的字体
	 * 
	 * @param name
	 *            字体
	 */
	public void setCurrentTxtFontFace(String name) {
		((Text) currentShape).setTextFace(name);
		repaint();
	}

	/**
	 * 设置当前文字图形的样式
	 * 
	 * @param style
	 *            样式
	 */
	public void setCurrentTxtFontStyle(String style) {
		if (style.equals("常规")) {
			((Text) currentShape).setTextStyle(Font.PLAIN);
		}
		if (style.equals("粗体")) {
			((Text) currentShape).setTextStyle(Font.BOLD);
		}
		if (style.equals("斜体")) {
			((Text) currentShape).setTextStyle(Font.ITALIC);
		}
		repaint();
	}

	/**
	 * 设置当前文字图形的大小
	 * 
	 * @param size
	 *            大小
	 */
	public void setCurrentTxtFontSize(String size) {
		((Text) currentShape).setTextSize(Integer.valueOf(size));
		repaint();
	}

	/**
	 * 获取图片
	 * 
	 * @param path
	 *            图片路径
	 * @return ImageIcon
	 */
	private ImageIcon createImageIcon(String path) {
		if (null != path && !"".equals(path)) {
			// BufferedImage im = ImageIO.read(new File("images/" + path));
			// ImageIcon temp = new ImageIcon(im);
			ImageIcon image = null;
			if("wwby.png".equals(path)||"cfdy.png".equals(path)||"c.png".equals(path)){
				image = new ImageIcon("images/" + path);
			}else{
				image = new ImageIcon(path);
			}
			if (old_width == 0) {
				new_width = old_width = image.getIconWidth();
			}
			if (old_height == 0) {
				new_height = old_height = image.getIconHeight();
			}
			return image;
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public ImageParam getImage() {
		return image;
	}

	public void setImage(ImageParam image) {
		this.image = image;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setBgColor(Color selectedColor) {
		color = selectedColor;
	}

	public void saveAndPrint() {
		String picPath = saveImage();
		writeWordInPic(picPath);
		JOptionPane
		.showMessageDialog(this, "已成功保存到" + "【" + picPath + "】");
		printImage(picPath);
		
	}

	public void rotate() {
		//方法一：先把图片保存到本地，然后再读取图片到界面上。这样简单，但是缺点是无法撤销旋转前已经绘制的图形
		//方法二：把绘制数据直接全部旋转，这样复杂，优点是可以撤销旋转前已经绘制的图形，
		//采用方法一：
		String picPath = saveImage();

    	File image = new File(picPath);
		PsImage psi = null;
		try {
			psi = new PsImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String picNewPath = psi.rotate(90);
		this.shapes.clear();
	
		this.path = picNewPath;
		System.out.println("picNewPath:"+picNewPath);
		repaint();
	}

	/**
	 * 旋转90°
	* @Title: rotate90 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void rotate90() {

        roteAngle += 90 ;
        roteAngle %= 360 ;
        repaint();
    
	}

}
