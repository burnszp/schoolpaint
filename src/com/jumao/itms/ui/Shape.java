package com.jumao.itms.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;

/**
 * 图形抽象类
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public abstract class Shape extends JLabel implements MouseMotionListener {

	private static final long serialVersionUID = 1L;

	/** 画笔大小 */
	protected float stroke;
	/** 画笔颜色 */
	protected Color color;
	/** 图形类型 */
	protected String type;

	protected int startX = 0;
	protected int startY = 0;
	protected int endX = 0;
	protected int endY = 0;
	protected int currentX = 0;
	protected int currentY = 0;
	protected int currentD = 0;
	protected List<String> pointList = new ArrayList<String>();
		

	
	private Graphics g;

	protected Shape(Color color1, float stroke1, String type1, int x, int y) {
		type = type1;
		color = color1;
		stroke = stroke1;
		startX = endX = currentX = x;
		startY = endY = currentY = y;

		
		addMouseMotionListener(this);
	}


	public void mouseDragged(MouseEvent mouseevent) {
//		if("maobi".equals(type)){
//			
//			currentX = mouseevent.getX();
//			currentY = mouseevent.getY();
//			
//			Random r = new Random();
//			
//			g.drawLine(currentX, currentY, currentX, currentY);
//			for (int i = 0; i < 10; i++) {
//				int p = r.nextInt(10);
//				int q = r.nextInt(10);
//				g.drawLine(currentX + p, currentY + q, currentX + p,
//						currentY + q);
//			}
//		}
		endX = mouseevent.getX();
		endY = mouseevent.getY();

		if("maobi".equals(type)){
			String x_y = Integer.toString(endX)+"_"+Integer.toString(endY);
			pointList.add(x_y);
		}
		// 变成十字架拖放的时候，需要计算圆形和三角形
		if (DrawingBoard.cursor == 1) {
			currentD = Math.abs(startX - endX);

			if (startX > endX) {
				currentX = endX;
			}
			if (startY > endY) {
				currentY = endY;
			}
		} else {// 拖放完毕之后再次点击只需要控制位置即可
			currentX = mouseevent.getX();
			currentY = mouseevent.getY();
		}
	}

	public void mouseMoved(MouseEvent mouseevent) {
		// 鼠标移动
	}

	/**
	 * 调用画板来画图
	 * 
	 * @param graphics2d
	 *            画板的graphics
	 * @param multiple 
	 * @param flag 
	 */
	public abstract void draw(Graphics2D graphics2d, int multiple, boolean flag,boolean add);

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
