/*******************************************************************************
 * @project: SchoolPaint_3
 * @package: com.burns.school.paint.test
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-26
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;

public class CopyOfTestImg {

    public static void main(String[] args) {
    	FontText[] ftArr = new FontText[3];
    	ftArr[0] = new FontText("杨柳青年画", 1, "#CC2BAC", 40, "黑体");
    	ftArr[1] = new FontText("第三届全国基础教育信息化应用展示交流", 1, "#CC2BAC", 40, "黑体");
    	ftArr[2] = new FontText("2018.4.25", 1, "#CC2BAC", 40, "黑体");
        String filePath = "D:/eclipse_wks/SchoolPaint_3/images/wwby_ct.png";
        String outPath = "C:/burns/schoolpaint/wwby_ct_text1.png";
        drawTextInImg(filePath, outPath, ftArr);

    }

    public static void drawTextInImg(String filePath,String outPath, FontText[] ftArr) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image img = imgIcon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bimage.createGraphics();
        g.setColor(Color.BLACK);
        g.setBackground(Color.GREEN);
        g.drawImage(img, 0, 0, null);
        Font font = new Font(null, Font.BOLD, 15);
        g.setFont(font);
        
        
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(ftArr[0].getText(), null);
        int textWidth = (int) bounds.getWidth();
        int textHeight = (int) bounds.getHeight();
        int left = 0;
        int top = textHeight;
        
        FontMetrics metrics1 = new FontMetrics(font){};
        Rectangle2D bounds1 = metrics1.getStringBounds(ftArr[1].getText(), null);
        int textWidth1 = (int) bounds1.getWidth();
        int textHeight1 = (int) bounds1.getHeight();
        int left1 = textWidth1;
        int top1 = textHeight1;
        
        FontMetrics metrics2 = new FontMetrics(font){};
        Rectangle2D bounds2 = metrics2.getStringBounds(ftArr[2].getText(), null);
        int textWidth2 = (int) bounds2.getWidth();
        int textHeight2 = (int) bounds2.getHeight();
        int left2 = textWidth2;
        int top2 = textHeight2;
        
        
        
        g.drawString(ftArr[0].getText(), left, top);
        g.drawString(ftArr[1].getText(), width-left1, height-top1-top2);
        g.drawString(ftArr[2].getText(), width-left2, height-top2);
        
        g.dispose();

        try {
            FileOutputStream out = new FileOutputStream(outPath);
            ImageIO.write(bimage, "JPEG", out);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // color #2395439
    public static Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}


/*******************************************************************************
 * <B>Revision History</B><BR>
 * [type 'revision' and press Alt + / to insert revision block]<BR>
 * 
 * 
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/