//ShowImage.java
package PictureOperation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Arg.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
public class ShowImage extends JFrame//预览图片
{
	public ShowImage(String fn) throws IOException
	{
		super("预览");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		String path = Arg.path + "/" + fn;
		ImageIcon image = new ImageIcon(path);
		Image ima;
		BufferedImage images = ImageIO.read(new File(path));
		BufferedImage imagei = new BufferedImage(360, 360,BufferedImage.TYPE_INT_RGB );
		imagei.getGraphics().drawImage(images, 0, 0, 360, 360, null);
		ima=imagei;
		image=new ImageIcon(ima);
		c.add(new JLabel(image));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(image.getIconWidth()+10, image.getIconHeight()+30);
		setVisible(true);
	}
}
