/**
 * DataExchange.java
 * com.wodexiangmu
 * Copyright (c) 2017, 北京科技有限公司版权所有.
*/

package com.wodexiangmu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   刘旭利
 * @Date	 2017年11月15日 	 
 */
public class DataExchange {

	public static ResultSet getRemouteData(int i) {
		String remouteurl = "jdbc:sqlserver://192.168.1.25:1433;DatabaseName=shifenzheng;";
		//String remoutesql = "select top " + i * 100 + " * from dbo.cdsgus";
		String remoutesql = "SELECT TOP 100 * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM cdsgus  )   as A  WHERE RowNumber > 100*("
				+ i + ") ";
		Statement remoutestmt;
		Connection remouteconn;
		ResultSet remouters = null;
		try {
			// 连接数据库
			remouteconn = DriverManager.getConnection(remouteurl, "sa", "123456");
			// 建立Statement对象
			remoutestmt = remouteconn.createStatement();
			remouters = remoutestmt.executeQuery(remoutesql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return remouters;
	}
}
