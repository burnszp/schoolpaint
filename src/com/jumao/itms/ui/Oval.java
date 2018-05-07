package com.jumao.itms.ui;

import java.awt.*;

/**
 * 圆形
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class Oval extends Shape {

	private static final long serialVersionUID = 1L;

	public Oval(Color color, float stroke, String type, int x, int y) {
		super(color, stroke, type, x, y);
	}

	public void draw(Graphics2D graphics2d, int multiple,boolean flag,boolean add) {
		int x = 0;
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		{
			if (multiple == 0) {
				graphics2d.drawOval(currentX, currentY, currentD, currentD);
			} else if (multiple < 0) {
				x = (Math.abs(multiple) + 1);
				graphics2d.drawOval(currentX / x, currentY / x, currentD / x,
						currentD / x);
			} else {
				x = multiple + 1;
				graphics2d.drawOval(currentX * x, currentY * x, currentD * x,
						currentD * x);
			}
		}

	}
}
