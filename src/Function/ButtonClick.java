//ButtonClick.java
package Function;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class ButtonClick implements ActionListener//鼠标点击事件，点击鼠标使图片与空格换位
{
	private JButton [][] button;//建立按钮，图片分割后每个窗格就是一个按钮
	private point pint;
	private int row;
	private int col;
	private int [][] matrix;
	private GameOver gOver;
	private boolean end;
	
	public ButtonClick(JButton [][] b, point p, int [][] m, int r, int c, GameOver g)
	{
		button = b;
		pint = p;
		matrix = m;
		row = r;
		col = c;
		gOver = g;
		end = false;
	}//初始化
	public void actionPerformed(ActionEvent e)//点击移动图片事件
	{
		if (end || !pint.neighbor(row, col))//如果结束或点击图片周围没有空格时
			return;
		
		int r = pint.getRow();//获取点击图片按钮行坐标
		int c = pint.getCol();//获取点击图片按钮列坐标
		
		//图片与空白交换
		button[r][c].setIcon(button[row][col].getIcon());
		button[row][col].setIcon(null);
		pint.set(row, col);
		
		//图片序号和空白序号交换
		int t = matrix[r][c];
		matrix[r][c] = matrix[row][col];
		matrix[row][col] = t;
		
		end = gOver.judge();//判断游戏是否结束
		
		
	}
}	
