package com.jumao.itms.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * 线条
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class Pen extends Shape {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, String>> oldPointMap = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, String>> flagPointMap = new HashMap<String, Map<String, String>>();

	protected Pen(Color color1, float stroke1, String type1, int x, int y) {
		super(color1, stroke1, type1, x, y);
	}

	@Override
	public void draw(Graphics2D graphics2d, int multiple, boolean flag,
			boolean add) {
		int x = 0;
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		{
			if (multiple >= 1) {
				x = multiple;
				for (int i = 0; i < pointList.size(); i++) {

					String key = pointList.get(i);
					String[] pointArr = key.split("_");
					int xx = Integer.parseInt(pointArr[0]);
					int yy = Integer.parseInt(pointArr[1]);
					// 如果是第一次画线，则直接画
					if (oldPointMap.get(key) == null) {
//						System.out.println("key::" + key);
						Random r = new Random();
						Map<String, String> oldPoint = new HashMap<String, String>();
						graphics2d.drawLine(xx, yy, xx, yy);
						// System.out.println("(xx):"+(xx));
						// System.out.println("(yy):"+(yy));
						int yyyy = (int) stroke;
						for (int y = 0; y < yyyy; y++) {

							int p = r.nextInt(yyyy);
							int q = r.nextInt(yyyy);
							// System.out.println("p:" + p);
							// System.out.println("q:" + q);
//							System.out.println("(xx + p):" + (xx + p));
//							System.out.println("(yy + q):" + (yy + q));
							graphics2d.drawLine((xx + p), (yy + q), (xx + p),
									(yy + q));
							oldPoint.put(Integer.toString(xx + p),
									Integer.toString(yy + q));
						}
						oldPointMap.put(key, oldPoint);
					} else {
						if (flag) {
							if (add) {
								// 以前画过的的需要放大。
								graphics2d.drawLine(xx * x, yy * x, xx * x, yy
										* x);
								Map<String, String> oldPoint = oldPointMap
										.get(key);
								for (String xkey : oldPoint.keySet()) {
									int yyy = Integer.parseInt(oldPoint
											.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx * x, yyy * x, xxx
											* x, yyy * x);
								}
							} else {
								// 以前画过的的需要放大。
								graphics2d.drawLine(xx * x, yy * x, xx * x, yy
										* x);
								Map<String, String> oldPoint = oldPointMap
										.get(key);
								for (String xkey : oldPoint.keySet()) {
									int yyy = Integer.parseInt(oldPoint
											.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx * x, yyy * x, xxx
											* x, yyy * x);
								}
							}
						} else {
							graphics2d.drawLine(xx, yy, xx, yy);
							Map<String, String> oldPoint = oldPointMap.get(key);
							for (String xkey : oldPoint.keySet()) {
								int yyy = Integer.parseInt(oldPoint.get(xkey));
								int xxx = Integer.parseInt(xkey);

								graphics2d.drawLine(xxx, yyy, xxx, yyy);
							}

						}
					}
					// } else {

					// // 如果是已经画过的线了，则判断是否需要放大和缩小
					// Map<String, String> oldPoint = oldPointMap.get(key);
					// if (flag) {
					// if (add) {
					// graphics2d.drawLine(xx * x, yy * x, xx * x, yy
					// * x);
					// for (String xkey : oldPoint.keySet()) {
					// int yyy = Integer.parseInt(oldPoint
					// .get(xkey));
					// int xxx = Integer.parseInt(xkey);
					//
					// graphics2d.drawLine(xxx * x, yyy * x, xxx
					// * x, yyy * x);
					// }
					//
					// } else {
					// graphics2d.drawLine(xx / x, yy / x, xx / x, yy
					// / x);
					// for (String xkey : oldPoint.keySet()) {
					// int yyy = Integer.parseInt(oldPoint
					// .get(xkey));
					// int xxx = Integer.parseInt(xkey);
					//
					// graphics2d.drawLine(xxx / x, yyy / x, xxx
					// / x, yyy / x);
					// }
					// }
					// // flagPointMap.put(key, oldPoint);
					// } else {
					// graphics2d.drawLine(xx, yy, xx, yy);
					// Map<String, String> oldPoint1 = new HashMap<String,
					// String>();
					// for (String xkey : oldPoint.keySet()) {
					// int yyy = Integer.parseInt(oldPoint.get(xkey));
					// int xxx = Integer.parseInt(xkey);
					//
					// graphics2d.drawLine(xxx, yyy, xxx, yyy);
					// oldPoint.put(Integer.toString(xxx),
					// Integer.toString(yyy));
					// System.out.println("(xxx):"+(xxx));
					// System.out.println("(yyy):"+(yyy));
					// }
					// oldPointMap.put(key, oldPoint1);
					// }
					// }
				}
			} else {
				// JOptionPane.showMessageDialog(null, "再缩小就没法涂色了！");
			}
		}

	}

}
