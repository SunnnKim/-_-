package main;

import db.DBConnection;
import view.LoginView;

public class mainClass {
	public static void main(String[] args) {
		
		DBConnection.initConnection();
		LoginView jv = new LoginView();
	}
}
