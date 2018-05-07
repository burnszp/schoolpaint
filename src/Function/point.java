//point.java
package Function;
public class point//坐标
{
	int row;//行坐标
	int col;//列坐标
	public point(int r, int c)
	{
		row = r;
		col = c;
	}//构造函数初始化
	public void set(int r, int c)
	{
		row = r;
		col = c;
	}//设置坐标
	public int getRow()
	{
		return row;
	}//获取行坐标
	public int getCol()
	{
		return col;
	}//获取列坐标
	public boolean neighbor(int r, int c)
	{
		int n = Math.abs(row - r) + Math.abs(col - c);
		return n == 1;
	}//判断(r,c)与(row,col)是否相邻，相邻则返回true
}
