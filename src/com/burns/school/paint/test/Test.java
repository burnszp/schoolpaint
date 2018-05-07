/*******************************************************************************
 * @project: SchoolPaint_10
 * @package: com.burns.school.paint.test
 * @file: Test.java
 * @author: zhangpei
 * @created: 2018-5-3
 * @purpose:
 * 
 * @version: 1.0
 * 
 * Revision History at the end of file.
 * 
 * Copyright 2018 AcconSys All rights reserved.
 ******************************************************************************/

package com.burns.school.paint.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		System.out.println(list.contains("2"));
		
		String s1 = "11";
		String s2 ="22";
		String s3="22";
		list.add(s1);
		list.add(s2);
		System.out.println(list.contains(s3));
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