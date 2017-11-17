/**
 * ExecuteThread.java
 * com.wodexiangmu
 * Copyright (c) 2017, 北京科技有限公司版权所有.
*/

package com.wodexiangmu;

import java.sql.ResultSet;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   刘旭利
 * @Date	 2017年11月17日 	 
 */
public class ExecuteThread implements Runnable {
	public static int i = -1;

	public void run() {
		while (i < 210000) {
			int j = addi();
			try {
				ResultSet resultset = DataExchange.getRemouteData(i);
				if (resultset != null) {
					MysqlExchange.insertLocalData(resultset, j);
				}
				System.out.println("已经插入" + (j + 1) * 100 + "条记录");
			} catch (Exception e) {
				System.out.println("插入" + (j + 1) * 100 + "失败");
			}
		}
	}

	public synchronized static int addi() {
		i = i + 1;
		return i;
	}

	public static void main(String[] args) {

	}
}
