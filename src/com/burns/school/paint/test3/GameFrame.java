/*******************************************************************************
 * @project: SchoolPaint_7
 * @package: com.burns.school.paint.test3
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-29
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

//GameFrame类------设置游戏的打开窗口类，创建了一个菜单面板存放游戏开始和游戏结束两个按钮，并且对游戏的窗口进行了基本设置，这是整个游戏的入口。

package com.burns.school.paint.test3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public JPanel pane1 = new JPanel();
    public JButton button1 = new JButton("游戏开始");
    public JButton button2 = new JButton("游戏结束");
    public GameFrame()
    {
        super("拼图游戏");
        pane1.setLayout(new FlowLayout());
        pane1.add(button1);
        pane1.add(button2);
        Container con = this.getContentPane();
        con.add(pane1,BorderLayout.NORTH);
        final GamePanel gamepane = new GamePanel();
        con.add(gamepane,BorderLayout.CENTER);
        this.setBounds(300, 300, 600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent e)
            {
                gamepane.OutOfOrder();
            }
        });
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent e)
            {
                System.exit(1);
            }
        });
    }

    public static void main(String[] args) {
        new GameFrame();
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