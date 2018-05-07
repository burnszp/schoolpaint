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
 * 颜色属性
 * @author SongFei
 * @date 2015年8月6日
 */
public class ColorParam {

	/** 红色的返回，在0~255之间 */
	private int colR;
	/** 绿色的返回，在0~255之间 */
	private int colG;
	/** 蓝色的返回，在0~255之间 */
	private int colB;

	public ColorParam(int colR, int colG, int colB) {
		super();
		this.colR = colR;
		this.colG = colG;
		this.colB = colB;
	}

	public int getColR() {
		return colR;
	}

	public void setColR(int colR) {
		this.colR = colR;
	}

	public int getColG() {
		return colG;
	}

	public void setColG(int colG) {
		this.colG = colG;
	}

	public int getColB() {
		return colB;
	}

	public void setColB(int colB) {
		this.colB = colB;
	}
}
