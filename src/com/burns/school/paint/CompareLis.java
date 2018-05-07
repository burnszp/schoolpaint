/*******************************************************************************
 * @project: Java_Image
 * @package: image.java.ttht
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-19
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint;  
  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.ArrayList;  
  
import javax.swing.JDialog;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JTextField;  
  
@SuppressWarnings("serial")  
public class CompareLis extends JDialog implements ActionListener {  
    public String str, str1, str2;  
    public JTextField id1;  
    public JTextField id2;  
    public JLabel label1;  
    public JLabel label2;  
    public JLabel input1;  
    public JLabel input2;  
  
    public ArrayList<Shape> shape;  
  
    public void setText(JTextField id1, JTextField id2, ArrayList<Shape> shape) {  
        this.id1 = id1;  
        this.id2 = id2;  
        this.shape = shape;  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        str = e.getActionCommand();  
  
        str1 = id1.getText();  
        str2 = id2.getText();  
  
        int input1 = Integer.parseInt(str1);  
        int input2 = Integer.parseInt(str2);  
  
        if (input1 <= 0 || input1 > shape.size() || input2 <= 0 && input2 > shape.size()) {  
            JOptionPane.showMessageDialog(null, "输入越界！！！");  
        }  
        String type1 = shape.get(input1 - 1).type;  
        String type2 = shape.get(input2 - 1).type;  
        double length1 = shape.get(input1 - 1).length;  
        double length2 = shape.get(input2 - 1).length;  
        double area1 = shape.get(input1 - 1).S;  
        double area2 = shape.get(input2 - 1).S;  
        double perimeter1 = shape.get(input1 - 1).C;  
        double perimeter2 = shape.get(input2 - 1).C;  
  
        if (str.equals("开始比较")) {  
            // 输入的两个id相同时  
            if (str1.equals(str2) == true) {  
                JOptionPane.showMessageDialog(null, "输入错误！！！");  
            }  
  
            // 输入的id范围错误  
            else if (input1 <= 0 || input1 > shape.size() || input2 <= 0 || input2 > shape.size()) {  
                JOptionPane.showMessageDialog(null, "输入的id越界");  
            }  
  
            // 对比的两个图形形状不同  
            else if (type1.equals(type2) == false) {  
                // 两个图形一个为实心椭圆，一个为空心椭圆  
                if (type1.equals("空心椭圆") && type2.equals("实心椭圆") || (type1.equals("实心椭圆")) && type2.equals("空心椭圆")) {  
  
                    if (area1 >= area2) {  
                        JOptionPane.showMessageDialog(null, "第一个图形面积更大");  
                    } else {  
                        JOptionPane.showMessageDialog(null, "第二个图形面积更大");  
                    }  
                    // frame.add(label2);  
  
                    // JLabel label3 = new JLabel();  
                    // label3.setPreferredSize(new Dimension(300, 30));  
                    if (perimeter1 >= perimeter2) {  
                        JOptionPane.showMessageDialog(null, "第一个图形周长更大");  
                    } else {  
                        JOptionPane.showMessageDialog(null, "第二个图形周长更大");  
                    }  
  
                }  
  
                // 两个图形形状不同  
                else {  
                    JOptionPane.showMessageDialog(null,  
                            "id为" + input1 + "的图形是" + type1 + "\nid为" + input2 + "的图形是" + type2 + "\n形状不同，无法比较！");  
  
                }  
  
            }  
            // 相同形状的图形的比较  
            else {  
  
                if (type1.equals("直线") && type2.equals("直线")) {  
  
                    if (length1 >= length2) {  
                        JOptionPane.showMessageDialog(null, "第一条直线更长");  
                    } else {  
                        JOptionPane.showMessageDialog(null, "第二条直线更长");  
                    }  
                } else {  
                    // JLabel label2 = new JLabel();  
                    // label2.setPreferredSize(new Dimension(300, 30));  
                    if (area1 >= area2) {  
                        JOptionPane.showMessageDialog(null, "第一个图形面积更大");  
                    } else {  
                        JOptionPane.showMessageDialog(null, "第二个图形面积更大");  
                    }  
                    // frame.add(label2);  
  
                    // JLabel label3 = new JLabel();  
                    // label3.setPreferredSize(new Dimension(300, 30));  
                    if (perimeter1 >= perimeter2) {  
                        JOptionPane.showMessageDialog(null, "第一个图形周长更大");  
  
                    } else {  
                        JOptionPane.showMessageDialog(null, "第二个图形周长更大");  
                    }  
                    // frame.add(label3);  
                    //  
                    //  
                    // frame.setVisible(true);  
  
                }  
            }  
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