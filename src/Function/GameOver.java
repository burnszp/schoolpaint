//GameOver.java 记录步数和时间及游戏是否完成
package Function;
import java.util.Date;
import Game.Puzzle;
import javax.swing.JLabel;
public class GameOver
{
	public boolean gameOver;
	public boolean stop;
	private int [][] matrix;
	private Puzzle Main;
	private int step;
	private long now;
	private long countTime;
	public boolean result=false;
	
	public GameOver (Puzzle frm)
	{
		Main = frm;
	}
	public void start(int [][] m)
	{
		gameOver = false;
		matrix = m;
		step = 0;
		Date d = new Date();
		now = d.getTime();
		stop = false;
		countTime = 0;
	}
	public boolean judge()
	{
		if (gameOver)
		{	
			result = true;
			return true;
		}
		step++;
		int c = 1;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (matrix[i][j] != (c++))
				{
					result=false;
					return false;
					
				}	
		
		Date d = new Date();
		countTime += d.getTime() - now;
		Main.endGame();
		return (gameOver = true);
		
	}
	
	public void pause()
	{
		Date d = new Date();

		if (stop)
			now = d.getTime();
		else
			countTime += d.getTime() - now;
		stop = !stop;
	}
	public int getStep()
	{
		return step;
	}
	public long getTime()
	{
		return countTime / 1000;
	}
}


