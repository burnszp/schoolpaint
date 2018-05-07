//Puzzle.java 主函数

package Game;

import Frame.*;
import Arg.*;
import PictureOperation.*;
import Function.*;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class Puzzle extends CardFrame {
	private boolean start;
	private int fWidth = this.getWidth();
	private int fHeight = this.getHeight();
	private GameOver gOver;
	private boolean index = true;
	public timeing tt = new timeing();

	public Puzzle() {
		start = false;
		ImageIcon icon = new ImageIcon("icon/Ok.png");
		this.setSize(200 + fWidth, 200 + fHeight);
		this.setIconImage(icon.getImage());
		this.setVisible(true);
	}

	// 多线程显示时间
	class timeing implements Runnable {
		public boolean isrunning = false;
		private long begin;
		private long wait;
		private long now;

		public void stop() {
			begin = System.currentTimeMillis();
			wait = 0;
			now = 0;
			isrunning = false;
		}

		public void run() {
			isrunning = true;

			begin = System.currentTimeMillis();
			wait = 0;
			now = 0;
			while (gOver.result == false) {

				try {
					Thread.sleep(500);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (index ) {
					now = System.currentTimeMillis();
					re1.setText("时间：" + (now - begin - wait) / 1000);
					re2.setText("步数：" + gOver.getStep());
				} else {

					wait = System.currentTimeMillis() - now;
				}

			}

		}
	}

	private void startGame() {
		if (start)
			return;
		start = true;
		updateMenuBegin();

	}

	public void endGame() {
		updateMenuBegin();
		initMenuBackground();

		start = false;
		JOptionPane.showMessageDialog(null, "时间:" + gOver.getTime() + "s\n"
				+ "步数:" + gOver.getStep());
		Grades g = new Grades(this);
		g.set((int) gOver.getTime(), gOver.getStep());
		this.dispose();
//		new Puzzle();
	}

	protected void FrameLostFocus() {
		if (start && index) {
			nextCard();
			if (gOver != null)
				gOver.pause();
			index = false;
		}
	}

	protected void FrameGetFocus() {
		nextCard();
		if (gOver != null)
			gOver.pause();
		index = true;
	}

	public void menuNewClick() {
		Split sp = Split.get();
		BufferedImage[][] image;
		if (!sp.set(getFilename()) || (image = sp.divid(gettype())) == null) {
			JOptionPane.showMessageDialog(null, "图片不存在！\n请重新选择~");
			return;
		}
		startGame();

		this.setSize(fWidth, fHeight);
		this.setVisible(true);
		int len = Split.level[gettype()];
		int row = image.length;
		int cal = image[0].length;
		gOver = new GameOver(this);

		JButton[][] button = new JButton[row][cal];
		Matrix matrix = new Matrix(button, panel[0], len, gOver);
		matrix.init(image);
		this.setSize(cal * len + fWidth, row * len + fHeight);
		this.setVisible(true);
		if (tt.isrunning)
			tt.stop();
		else
			new Thread(tt).start();
	}

	public void menuGradesClick() {
		Grades g = new Grades(this);
		g.show();
	}

	public void menuShowClick() throws IOException {
		new ShowImage(getFilename());
	}

	public void menuExitClick() {
		System.exit(0);
	}

	public void menuHelpClick() {
		String help0 = "通过移动每一个小图片，最终拼为原本的完整图片.\n\n";
		String help1 = "您可以单击空白区周围的小图片，可以使它移动到空白区。";
		JOptionPane.showMessageDialog(null, help0 + help1);
	}

	public void menuAboutClick() {
		String version = "版本: ver  1.0.0 \n";
		String author = "作者: 杨宇威  杨羿  杨世盛\n";
		String email = "联系地址: 林科大东园22栋208 ";
		JOptionPane.showMessageDialog(null, version + author + email);
	}

	public void addClick() {

		FileDialog fd = new FileDialog(this, "图片选择", FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile() == null)
			return;
		String imagename = fd.getDirectory() + fd.getFile();// 文件路径+文件名

		Copy ff = new Copy(imagename, "background\\" + fd.getFile());

		JOptionPane.showMessageDialog(this, "图片已经成功添加！", "提示",
				JOptionPane.INFORMATION_MESSAGE);
		dispose();
		Puzzle app = new Puzzle();

	}// 增加图片

	public static void main(String[] args) {
		Puzzle app = new Puzzle();
	}

}
