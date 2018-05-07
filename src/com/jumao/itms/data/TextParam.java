/**
 * 浙江大华技术股份有限公司 版权所有
 * 创建时间：2013年11月12日
 * 功能描述：文本
 *   
 * 修改记录：
 *  
 */
package com.jumao.itms.data;

/**
 * 文字属性
 * @author SongFei
 * @date 2015年8月6日
 */
public class TextParam {

	/** 路段id */
	private Long roadSegmentId;
	/** 文字内容 */
	private String textContent;
	/** 文字的大小默认为30 */
	private Integer size = 30;
	/** 文字的开始点 */
	private PointParam point;
	/** 字的颜色 */
	private ColorParam color;
	/** 文字的方向，默认为1；1为水平，2为垂直 */
	private Integer direction = 1;

	public TextParam(Long roadSegmentId, String textContent, Integer size,
			PointParam point, ColorParam color, Integer direction) {
		super();
		this.roadSegmentId = roadSegmentId;
		this.textContent = textContent;
		this.size = size;
		this.point = point;
		this.color = color;
		this.direction = direction;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Long getRoadSegmentId() {
		return roadSegmentId;
	}

	public void setRoadSegmentId(Long roadSegmentId) {
		this.roadSegmentId = roadSegmentId;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public PointParam getPoint() {
		return point;
	}

	public void setPoint(PointParam point) {
		this.point = point;
	}

	public ColorParam getColor() {
		return color;
	}

	public void setColor(ColorParam color) {
		this.color = color;
	}

}
