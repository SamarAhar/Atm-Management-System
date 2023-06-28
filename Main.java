package javaProject;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws InterruptedException, SQLException {
		Login login = new Login();
		login.loginView();
	}
}