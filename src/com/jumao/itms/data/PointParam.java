/**
 * 浙江大华技术股份有限公司 版权所有
 * 创建时间：2013年10月30日
 * 功能描述：
 *   
 * 修改记录：
 *  
 */
package com.jumao.itms.data;

/**
 * 坐标属性
 * @author SongFei
 * @date 2015年8月6日
 */
public class PointParam {

	/** 横坐标 */
	private int x;
	/** 纵坐标 */
	private int y;

	public PointParam() {

	}

	public PointParam(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
