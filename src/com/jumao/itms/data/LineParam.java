/**
 * 浙江大华技术股份有限公司 版权所有
 * 创建时间：2013年11月12日
 * 功能描述：线选
 *   
 * 修改记录：
 *  
 */
package com.jumao.itms.data;

/**
 * 线条属性
 * @author SongFei
 * @date 2015年8月6日
 */
public class LineParam {

	/** 线的颜色，默认为绿色 */
	private ColorParam color;
	/** 路段id，可为空 */
	private Long roadSegmentId;
	/** 线段宽度默认为20 */
	private Integer size = 10;
	/** 起点 */
	private PointParam start;
	/** 终点 */
	private PointParam end;
	/** 线段的方向，默认为空，1表示向前，2表示向后 */
	private Integer direction;

	public ColorParam getColor() {
		return color;
	}

	public void setColor(ColorParam color) {
		this.color = color;
	}

	public Long getRoadSegmentId() {
		return roadSegmentId;
	}

	public void setRoadSegmentId(Long roadSegmentId) {
		this.roadSegmentId = roadSegmentId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public PointParam getStart() {
		return start;
	}

	public void setStart(PointParam start) {
		this.start = start;
	}

	public PointParam getEnd() {
		return end;
	}

	public void setEnd(PointParam end) {
		this.end = end;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
}
