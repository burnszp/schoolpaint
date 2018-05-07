package com.jumao.itms.data;

import java.util.List;

/**
 * 图片属性
 * @author SongFei
 * @date 2015年8月6日
 */
public class ImageParam {

	private int width;

	private int height;

	private ColorParam color;

	private List<LineParam> lines;

	private List<TextParam> texts;

	public ImageParam() {

	}

	public ImageParam(int width, int height, ColorParam color) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ColorParam getColor() {
		return color;
	}

	public void setColor(ColorParam color) {
		this.color = color;
	}

	public List<LineParam> getLines() {
		return lines;
	}

	public void setLines(List<LineParam> lines) {
		this.lines = lines;
	}

	public List<TextParam> getTexts() {
		return texts;
	}

	public void setTexts(List<TextParam> texts) {
		this.texts = texts;
	}

}
