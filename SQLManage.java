package javaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLManage {
	Connection con;
	
	SQLManage() throws SQLException {
		String usr = "root";
		String pass = "password";
		String url = "jdbc:mysql://localhost:3306/atm";
		con = DriverManager.getConnection(url, usr, pass);
	}
	
	public ResultSet check(String usr, String pass) throws SQLException {
		String str = "SELECT * FROM users WHERE card = '"+ usr +"' AND pin = '"+ pass+ "'";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		return rst;
	}
	
	public void deposit(int amt, int id) throws SQLException {
		String str = "UPDATE users SET bal = bal + "+amt+" WHERE id = "+id;
		Statement stm = con.createStatement();
		stm.executeUpdate(str);
		int bal = balCheck(id);
		str = "INSERT INTO transactions (id, amount, stat, bal) VALUES("+id+", "+amt+", 'dep', "+bal+")";
		Statement stm2 = con.createStatement();
		stm2.executeUpdate(str);
	}
	
	public int withdraw(int amt, int id) throws SQLException {
		int bal = balCheck(id);
		if(bal >= amt) {
			String str = "UPDATE users SET bal = bal - "+amt+" WHERE id = "+id;
			Statement stm = con.createStatement();
			stm.executeUpdate(str);
			bal -= amt;
			str = "INSERT INTO transactions (id, amount, stat, bal) VALUES("+id+", "+amt+", 'wit', "+bal+")";
			Statement stm2 = con.createStatement();
			stm2.executeUpdate(str);
			return 1;
		}
		return 0;
	}
	
	public void pinchange(String pin, int id) throws SQLException {
		String str = "UPDATE users SET pin = '"+pin+"' WHERE id = " + id;
		Statement stm = con.createStatement();
		stm.executeUpdate(str);
	}
	
	public int balCheck(int id) throws SQLException {
		String str = "SELECT bal FROM users WHERE id = " + id;
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		return rst.getInt("bal");
	}
	
	public ResultSet stmt(int id) throws SQLException {
		String str = "SELECT * FROM transactions WHERE id = " + id + " order by transid desc";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		return rst;
	}
	
	public void adding(String card, String pin, String name, String bal) throws SQLException {
		String str = "INSERT INTO users (card, pin, uname, bal) values ('" +card+ "', '"+pin+"', '"+name+"', "+bal+")";
		Statement stm = con.createStatement();
		stm.executeUpdate(str);
	}
}