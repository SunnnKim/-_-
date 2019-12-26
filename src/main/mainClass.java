package main;

import db.DBConnection;
import view.LoginView;
import view.ShowBbsView;

public class mainClass {
	public static void main(String[] args) {
		
		DBConnection.initConnection();
//		ShowBbsView sb = new ShowBbsView();
		LoginView jv = new LoginView();
	}
}
