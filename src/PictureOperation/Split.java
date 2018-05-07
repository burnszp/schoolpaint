//Split.java
package PictureOperation;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import Arg.*;
//Singleton class
public class Split
{
	public static final int HARD = 0;
	public static final int NORMAL = 1;
	public static final int EASY = 2;
	public static final String pvo = "png";
	public static final int [] level = {
		72, 90, 120
	};
	private static Split Ob;
	private String filename;
	private String path;
	private Split() {}
	public static Split get()
	{
		if (Ob == null)
			Ob = new Split();
		return Ob;
	}
	public boolean set(String fn)
	{
		filename = fn;
		path = Arg.path + "/" + filename;
		File file = new File(path);
		return file.exists();
	}
	public BufferedImage[][] divid(int type)
	{
		try
		{
			if (filename == null)
				return null;
			BufferedImage image = ImageIO.read(new File(path));
			BufferedImage imagei = new BufferedImage(360, 360,BufferedImage.TYPE_INT_RGB );
			imagei.getGraphics().drawImage(image, 0, 0, 360, 360, null);
			image=imagei;
			int len = level[type];
			int cal = image.getWidth() / len;
			int row = image.getHeight() / len;
			BufferedImage [][] subimage = new BufferedImage[row][cal];
			for (int i = 0; i < row; i++)
				for (int j = 0; j < cal; j++)
					subimage[i][j] = image.getSubimage(j*len,  
i*len, len, len);
			return subimage;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
