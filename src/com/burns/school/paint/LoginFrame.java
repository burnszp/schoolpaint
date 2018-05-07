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

//登录界面  
package com.burns.school.paint;  
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
  
public class LoginFrame extends JFrame implements ActionListener{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -1956740047534561910L;
	private JButton loginB;
	private JTextField usernaeTF;
	private JPasswordField passwordPF;

	public LoginFrame() {
	}  
      
    public void login() {  
          
        setSize(550,550);  
        setResizable(false);
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("杨柳青年画教程"); 
        

//        String image = new String("small.ico"); //建立一个字符串保存要用的图标的路径，关于java的路径请参考其它文档 
//        ImageIcon test = new ImageIcon(this.getClass().getResource(image)); //建立一个ImageIcon类，产生setIconImage()方法需要的Image数据 
//        frame.setIconImage(test.getImage());          //设置图标
          
        JPanel panel = new JPanel();       //北边的面板（背景图片）  
        getContentPane().add(panel,BorderLayout.NORTH);  
          
        JPanel panel2 = new JPanel();       //西边的面板（头像图片）  
        getContentPane().add(panel2,BorderLayout.WEST);  
          
        JPanel panel3 = new JPanel();     //中间的面板  
        getContentPane().add(panel3,BorderLayout.CENTER);  
          
        ImageIcon image_logo = new ImageIcon(this.getClass().getResource("login_bg.png"));    //背景图片  
        int login_bg_height = image_logo.getIconHeight();
        int login_bg_width = image_logo.getIconWidth();
        System.out.println(login_bg_height);
        System.out.println(login_bg_width);
        JLabel lab = new JLabel(image_logo);  
        lab.setPreferredSize(new Dimension(550,350));  
        panel.add(lab);         
          
        ImageIcon image2 = new ImageIcon(this.getClass().getResource("pen.png"));    //头像图片  
        JLabel lab2 = new JLabel(image2);  
        lab2.setPreferredSize(new Dimension(50,50));  
        panel2.add(lab2);  
        
        JLabel label = new JLabel("账号：");  
        panel3.add(label);  
          
        usernaeTF = new JTextField("1");   
        usernaeTF.setPreferredSize(new Dimension(400, 30));  
        panel3.add(usernaeTF);  
          
        JLabel label2 = new JLabel("密码：");  
        panel3.add(label2);  
          
        passwordPF = new JPasswordField("1");  
        passwordPF.setPreferredSize(new Dimension(400, 30));  
        panel3.add(passwordPF);  
          
          
          
        loginB = new JButton("☺登录☺ ");  
        loginB.setFont(new Font("正楷", Font.BOLD, 20));
        loginB.setPreferredSize(new Dimension(120, 50));  
        panel3.add(loginB);  
        
//        JLabel lable = new JLabel("@杨柳青二中");
//        panel3.add(lable);  
          
//        LoginLis lis = new LoginLis(this);  
        loginB.addActionListener(this);  
              
        setVisible(true);  
          
    }  
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        LoginFrame log = new LoginFrame();  
        log.login();  
          
  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==loginB ){
			String username = usernaeTF.getText();
			String password = passwordPF.getText();

			if ((username.equals("1")) && (password.equals("1"))) {
				DrawFrame df = new DrawFrame();
				dispose();
			} else {
				JOptionPane.showMessageDialog(LoginFrame.this, "账号或密码输入错误！！！账户、密码都是1");
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