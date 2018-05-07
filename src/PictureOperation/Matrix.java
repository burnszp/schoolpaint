//Matrix.java
package PictureOperation;
import Function.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
public class Matrix
{
	private JButton [][] button;
	private JPanel panel;
	private int row;
	private int col;
	private int [][] matrix;
	private GameOver gOver;
	public Matrix(JButton [][] b, JPanel p, int len, GameOver g)
	{
		this.button = b;
		this.panel = p;
		this.gOver = g;
		
		row = b.length;//³¤
		col = b[0].length;//¿í
		
		matrix = new int[row][col];
		matrix[row-1][col-1] = row * col;
		gOver.start(matrix);
		panel.removeAll();
		point pint = new point(row-1, col-1);
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
			{
				button[i][j] = new JButton();
				button[i][j].setBounds(j*len, i*len, len, len);
				button[i][j].addActionListener(new ButtonClick(button, pint, matrix, i, j, gOver));
				panel.add(button[i][j]);
			}
	}
	public void init(BufferedImage [][] image)
	{
		if (button == null || image == null)
			return;
		ImageIcon icon;
		int r, c, rad, d, m;
		boolean [] visit = new boolean[row*col-1];
		for (int i = 0; i < row*col - 1; i++)
		{
			r = i / col;
			c = i % col;
			
			rad = (int)(Math.random()*(row*col-1-i));
			for (d = m = 0; d < row*col - 1 && m <= rad; d++)
				if (!visit[d])
					m++;
			matrix[i/col][i%col] = d;
			visit[--d] = true;
			icon = new ImageIcon(image[d/col][d%col]);//·Ö¸î
			button[i/col][i%col].setIcon(icon);
		}
	}
}


