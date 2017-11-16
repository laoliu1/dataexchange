/**
 * MysqlExchange.java
 * com.wodexiangmu
 * Copyright (c) 2017, 北京科技有限公司版权所有.
*/

package com.wodexiangmu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class MysqlExchange {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String localurl = "jdbc:mysql://127.0.0.1:3306/shifenzheng?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
	//MySQL配置时的用户名
	private static String user = "root";
	//MySQL配置时的密码
	private static String password = "123456";
	private static Statement localstmt = null;
	private static Connection localconn = null;
	static {
		//加载驱动程序
		try {
			Class.forName(driver);
			// 连接数据库
			localconn = DriverManager.getConnection(localurl, user, password);
			// 建立Statement对象
			localstmt = localconn.createStatement();
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void insertLocalData(ResultSet rs, int i) {
		try {
			int temp = 1;
			while (rs.next()) {
				PreparedStatement pstmt = null;
				try {
					StringBuffer sql = new StringBuffer(
							"insert into cdsgus (Name, CardNo, Descriot, CtfTp, CtfId, Gender, Birthday, Address, Zip, Dirty, District1, District2, District3, District4, District5, District6, FirstNm, LastNm, Duty, Mobile, Tel, Fax, EMail, Nation, Taste, Education, Company, CTel, CAddress, CZip, Family, Version, id)"
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					pstmt = localconn.prepareStatement(sql.toString());
					pstmt.setString(1, rs.getString("name"));
					pstmt.setString(2, rs.getString("cardno"));
					pstmt.setString(3, rs.getString("descriot"));
					pstmt.setString(4, rs.getString("ctftp"));
					pstmt.setString(5, rs.getString("CtfId"));
					pstmt.setString(6, rs.getString("Gender"));
					pstmt.setString(7, rs.getString("Birthday"));
					pstmt.setString(8, rs.getString("Address"));
					pstmt.setString(9, rs.getString("Zip"));
					pstmt.setString(10, rs.getString("Dirty"));
					pstmt.setString(11, rs.getString("District1"));
					pstmt.setString(12, rs.getString("District2"));
					pstmt.setString(13, rs.getString("District3"));
					pstmt.setString(14, rs.getString("District4"));
					pstmt.setString(15, rs.getString("District5"));
					pstmt.setString(16, rs.getString("District6"));
					pstmt.setString(17, rs.getString("FirstNm"));
					pstmt.setString(18, rs.getString("LastNm"));
					pstmt.setString(19, rs.getString("Duty"));
					pstmt.setString(20, rs.getString("Mobile"));
					pstmt.setString(21, rs.getString("Tel"));
					pstmt.setString(22, rs.getString("Fax"));
					pstmt.setString(23, rs.getString("EMail"));
					pstmt.setString(24, rs.getString("Nation"));
					pstmt.setString(25, rs.getString("Taste"));
					pstmt.setString(26, rs.getString("Education"));
					pstmt.setString(27, rs.getString("Company"));
					pstmt.setString(28, rs.getString("CTel"));
					pstmt.setString(29, rs.getString("CAddress"));
					pstmt.setString(30, rs.getString("CZip"));
					pstmt.setString(31, rs.getString("Family"));
					pstmt.setString(32, rs.getString("Version"));
					pstmt.setLong(33, rs.getLong("id"));
					pstmt.execute();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("插入第" + (i * 100 + temp) + "条失败");
					continue;

				} finally {
					if (pstmt != null) {
						pstmt.close();
					}
					temp++;
				}
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			try {
				ResultSet remouteData = DataExchange.getRemouteData(i);
				insertLocalData(remouteData, i);
				System.out.println("已经插入" + (i + 1) * 100 + "条记录");
			} catch (Exception e) {
				System.out.println("插入" + (i + 1) * 100 + "失败");
			}
		}
	}
}
