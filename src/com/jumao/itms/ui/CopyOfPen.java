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

/**
 * 线条
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class CopyOfPen extends Shape {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, String>> oldPointMap = new HashMap<String, Map<String, String>>();
	private Map<String, Map<String, String>> flagPointMap = new HashMap<String, Map<String, String>>();

	protected CopyOfPen(Color color1, float stroke1, String type1, int x, int y) {
		super(color1, stroke1, type1, x, y);
	}

	@Override
	public void draw(Graphics2D graphics2d, int multiple, boolean flag,boolean add) {
		int x = 0;
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(stroke));
		{
			if (multiple == 0) {
				for (int i = 0; i < pointList.size(); i++) {

					String key = pointList.get(i);
					String[] pointArr = key.split("_");
					int xx = Integer.parseInt(pointArr[0]);
					int yy = Integer.parseInt(pointArr[1]);
					if (oldPointMap.get(key) == null) {
						// System.out.println("key::" + key);
						Random r = new Random();
						Map<String, String> oldPoint = new HashMap<String, String>();
						graphics2d.drawLine(xx, yy, xx, yy);
						int yyyy = (int) stroke;
						for (int y = 0; y < yyyy; y++) {

							int p = r.nextInt(yyyy);
							int q = r.nextInt(yyyy);
							graphics2d.drawLine(xx + p, yy + q , xx + p, yy
									+ q );
							oldPoint.put(Integer.toString(xx + p),
									Integer.toString(yy + q));
						}
						oldPointMap.put(key, oldPoint);
					} else {
						Map<String, String> oldPoint = oldPointMap.get(key);
						graphics2d.drawLine(xx, yy, xx, yy);
						for (String xkey : oldPoint.keySet()) {
							int yyy = Integer.parseInt(oldPoint.get(xkey));
							int xxx = Integer.parseInt(xkey);

							graphics2d.drawLine(xxx, yyy, xxx, yyy);
						}
					}
				}
			} else if (multiple < 0) {
				x = (Math.abs(multiple) + 1);
				for (int i = 0; i < pointList.size(); i++) {

					String key = pointList.get(i);
					String[] pointArr = key.split("_");
					int xx = Integer.parseInt(pointArr[0]);
					int yy = Integer.parseInt(pointArr[1]);
					if (oldPointMap.get(key) == null) {
						// System.out.println("key::" + key);
						Random r = new Random();
						Map<String, String> oldPoint = new HashMap<String, String>();
						graphics2d.drawLine(xx / x, yy / x, xx / x, yy / x);
						int yyyy = (int) stroke;
						for (int y = 0; y < yyyy; y++) {

							int p = r.nextInt(yyyy);
							int q = r.nextInt(yyyy);
							graphics2d.drawLine((xx + p) / x, (yy + q) / x,
									(xx + p) / x, (yy + q) / x);
							oldPoint.put(Integer.toString(xx + p),
									Integer.toString(yy + q));
						}
						oldPointMap.put(key, oldPoint);
					} else {
						if (flag) {
							Map<String, String> oldPoint = oldPointMap.get(key);
							graphics2d.drawLine(xx /x, yy /x, xx /x, yy /x);
							for (String xkey : oldPoint.keySet()) {
								int yyy = Integer.parseInt(oldPoint.get(xkey));
								int xxx = Integer.parseInt(xkey);

								graphics2d.drawLine(xxx /x, yyy /x, xxx /x,
										yyy /x);
							}
							flagPointMap.put(key, oldPoint);
						} else {
							Map<String, String> oldPoint = flagPointMap.get(key);
							if(oldPoint==null){
								Map<String, String> oldPoint1 = oldPointMap.get(key);
								graphics2d.drawLine(xx, yy, xx, yy);
								for (String xkey : oldPoint1.keySet()) {
									int yyy = Integer.parseInt(oldPoint1.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx, yyy, xxx,
											yyy);
								}
							
							}else{

								graphics2d.drawLine(xx /x, yy /x, xx /x, yy /x);
								for (String xkey : oldPoint.keySet()) {
									int yyy = Integer.parseInt(oldPoint.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx /x, yyy /x, xxx /x,
											yyy /x);
								}
							
							}
						}
					}
				}
			} else {
				x = multiple + 1;
				for (int i = 0; i < pointList.size(); i++) {

					String key = pointList.get(i);
					String[] pointArr = key.split("_");
					int xx = Integer.parseInt(pointArr[0]);
					int yy = Integer.parseInt(pointArr[1]);
					if (oldPointMap.get(key) == null) {
						System.out.println("key::" + key);
						Random r = new Random();
						Map<String, String> oldPoint = new HashMap<String, String>();
						// graphics2d.drawLine(xx/x, yy/x, xx/x, yy/x);
						// System.out.println("(xx):"+(xx));
						// System.out.println("(yy):"+(yy));
						int yyyy = (int) stroke;
						for (int y = 0; y < yyyy; y++) {

							int p = r.nextInt(yyyy);
							int q = r.nextInt(yyyy);
							System.out.println("p:" + p);
							System.out.println("q:" + q);
							// System.out.println("(xx + p):"+(xx + p));
							// System.out.println("(yy + q):"+(yy + q));
							graphics2d.drawLine((xx + p), (yy + q), (xx + p),
									(yy + q));
							oldPoint.put(Integer.toString(xx + p),
									Integer.toString(yy + q));
						}
						oldPointMap.put(key, oldPoint);
					} else {
						if (flag) {
							Map<String, String> oldPoint = oldPointMap.get(key);
							graphics2d.drawLine(xx * x, yy * x, xx * x, yy * x);
							for (String xkey : oldPoint.keySet()) {
								int yyy = Integer.parseInt(oldPoint.get(xkey));
								int xxx = Integer.parseInt(xkey);

								graphics2d.drawLine(xxx * x, yyy * x, xxx * x,
										yyy * x);
							}
							flagPointMap.put(key, oldPoint);
						} else {
							Map<String, String> oldPoint = flagPointMap.get(key);
							if(oldPoint==null){
								Map<String, String> oldPoint1 = oldPointMap.get(key);
								graphics2d.drawLine(xx, yy, xx, yy);
								for (String xkey : oldPoint1.keySet()) {
									int yyy = Integer.parseInt(oldPoint1.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx, yyy, xxx,
											yyy);
								}
							
							}else{

								graphics2d.drawLine(xx /x, yy /x, xx /x, yy /x);
								for (String xkey : oldPoint.keySet()) {
									int yyy = Integer.parseInt(oldPoint.get(xkey));
									int xxx = Integer.parseInt(xkey);

									graphics2d.drawLine(xxx /x, yyy /x, xxx /x,
											yyy /x);
								}
							
							}
						}
					}
				}
			}
		}

	}

}
