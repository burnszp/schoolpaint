package com.burns.school.paint.test;
/*******************************************************************************
 * @project: SchoolPaint_2
 * @package: com.burns.school.paint.test
 * @file: T.java
 * @author: zhangpei
 * @created: 2018-4-25
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

import java.io.File;
 import java.io.IOException;
 import java.net.URL;

 public class MyUrlDemo {

     
     public static void main(String[] args) {
         MyUrlDemo muDemo = new MyUrlDemo();
         try {
             muDemo.showURL();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }

     public void showURL() throws IOException {

         // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
         File f = new File(this.getClass().getResource("/1.png").getPath());
         System.out.println(f);

         // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
         File f2 = new File(this.getClass().getResource("").getPath());
         System.out.println(f2);

         // 第二种：获取项目路径    D:\git\daotie\daotie
         File directory = new File("");// 参数为空
         String courseFile = directory.getCanonicalPath();
         System.out.println(courseFile);

 
         // 第三种：  file:/D:/git/daotie/daotie/target/classes/
         URL xmlpath = this.getClass().getClassLoader().getResource("");
         System.out.println(xmlpath);

 
         // 第四种： D:\git\daotie\daotie
         System.out.println(System.getProperty("user.dir"));
         /*
          * 结果： C:\Documents and Settings\Administrator\workspace\projectName
          * 获取当前工程路径
          */

         // 第五种：  获取所有的类路径 包括jar包的路径
         System.out.println(System.getProperty("java.class.path"));
         
         
         File firstPicFile = new File("ppt/1.png");
         System.out.println(firstPicFile.getAbsolutePath());

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