package com.burns.school.paint.test2;
/*******************************************************************************
 * @project: SchoolPaint
 * @package: com.burns.school.paint.test2
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-21
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

import javax.swing.*; 
public class A extends JFrame { 
    ImageIcon test = new ImageIcon(new String("D:/eclipse_wks/SchoolPaint/images/16x16.ico"));//第一句 
    public A() { 
        super("演示自定义标题栏的图标的方法"); 
        this.setIconImage(test.getImage()); //第二句 
         this.setSize(400,200); 
        this.show(); 
} 
    public static void main(String[] args) { 
        A jIcon = new A();       
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