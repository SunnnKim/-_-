package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dto.MemberDto;
import javabean.MemberDao;

public class LoginView extends Frame implements WindowListener, ActionListener {
	JButton login;
	JButton join;
	TextField idTxt;
	TextField pwTxt;
	
	
	public LoginView() {
		
		super("Login");
		setLayout(null);
		
		JLabel id = new JLabel("ID");
		id.setBounds(100, 80, 50, 30);
		id.setFont(new Font("Ink Free", Font.PLAIN, 15));
		add(id);
		
		idTxt = new TextField();
		idTxt.setBounds(150, 80, 150, 30);
		add(idTxt);
		
		
		JLabel pw = new JLabel("PW");
		pw.setBounds(100, 120, 50, 30);
		pw.setFont(new Font("Ink Free", Font.PLAIN, 15));
		add(pw);
		
		pwTxt = new TextField();
		pwTxt.setBounds(150, 120, 150, 30);
		add(pwTxt);
		
		
		 login = new JButton("Login");
		login.setBounds(100, 180, 90, 50);
		login.addActionListener(this);
		add(login);
		
		
		join = new JButton("Join");
		join.setBounds(230, 180, 90, 50);
		join.addActionListener(this);
		add(join);
		
		
		
		setBounds(200, 100, 400, 300);
		setVisible(true);
		addWindowListener(this);
		setBackground(Color.WHITE);
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton nowBtn = (JButton)e.getSource();
		if(nowBtn == login) {
			
			String id = idTxt.getText();
			String pwd = pwTxt.getText();
			if(id.trim().equals("")|| pwd.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디/패스워드를 모두 입력하세요!");
				return;
			}
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.login(id, pwd);
			idTxt.setText("");
			pwTxt.setText("");
			
			if(dto!=null) {
				dao.setLoginId(id);
				JOptionPane.showMessageDialog(null, dao.getLoginId()+ "님, 로그인 성공!");
				dispose();
				new BbsListView();
			}
			else {
				JOptionPane.showMessageDialog(null, "로그인실패! \n아이디/패스워드를 잘못 입력했습니다.");
				
			}
			
			
			
			
		}
		else if( nowBtn == join) {
			
			dispose();
			new JoinView();
			
			
		}
		
		
		
		
	}

}
