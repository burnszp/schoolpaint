package com.jumao.itms.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * 文字
 * @author SongFei
 * @date 2015年8月6日
 */
public class Text extends Shape {

	private static final long serialVersionUID = 1L;
	/** 设置名称 */
	private String roadName;
	/** 文字的开始X */
	private int lineStartX = 0;
	/** 文字的开始Y */
	private int lineStartY = 0;
	
	/** 样式 */
	private String textFace;
	private int textStyle = 0;
	private int textSize = 20;

	protected Text(Color color1, String type1, int x, int y, String context, String face, int style, int size) {
		super(color1, 0, type1, x, y);// 文字类型无需设置画笔大小
		this.roadName = context;
		this.textFace = face;
		this.textStyle = style;
		this.textSize = size;
	}

	@Override
	public void draw(Graphics2D graphics2d, int multiple,boolean flag,boolean add) {
		// 旋转
		// graphics2d.rotate(1, currentX, currentY);
		// 字体
		graphics2d.setColor(color);
		graphics2d.setFont(new Font(textFace, textStyle, textSize));
		graphics2d.drawString(roadName, currentX, currentY);

		lineStartX = currentX;
		lineStartY = currentY;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public int getLineStartX() {
		return lineStartX;
	}

	public void setLineStartX(int lineStartX) {
		this.lineStartX = lineStartX;
	}

	public int getLineStartY() {
		return lineStartY;
	}

	public void setLineStartY(int lineStartY) {
		this.lineStartY = lineStartY;
	}

	public String getTextFace() {
		return textFace;
	}

	public void setTextFace(String textFace) {
		this.textFace = textFace;
	}

	public int getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(int textStyle) {
		this.textStyle = textStyle;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
}
