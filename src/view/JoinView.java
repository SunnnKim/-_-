package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dto.MemberDto;
import javabean.MemberDao;

public class JoinView extends Frame implements WindowListener, ActionListener{
	JButton joinBtn;
	JButton checkId;
	TextField txt[];
	
	public JoinView() {
	
		super("Join");
		setLayout(new GridLayout(6,0));
		
		JPanel p[] = new JPanel[6];
		for (int i = 0; i < p.length; i++) {
			p[i] = new JPanel();
			p[i].setSize(450, 90);
			p[i].setLayout(null);
			p[i].setBackground(Color.WHITE);
			add(p[i]);
		}
		
		// p[1] 아이디
		JLabel title = new JLabel("J O I N");
		title.setSize(450, 100);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Segoe Print", Font.BOLD, 30));
		p[0].add(title);
		
		
	
		
		String labels[] = {" I D ", " P W ", "NAME ","EMAIL"};
		JLabel l[] = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			l[i] = new JLabel(labels[i]);
			l[i].setBounds(0, 30, 130, 30);
			l[i].setHorizontalAlignment(JLabel.RIGHT);
			l[i].setFont(new Font("Segoe Print", Font.PLAIN, 13));
			p[i+1].add(l[i]);
		}
		
		txt = new TextField[4];
		for (int i = 0; i < 4; i++) {
			txt[i] = new TextField();
			txt[i].setBounds(150, 30, 200, 30);
			p[i+1].add(txt[i]);
		}
		
		// 아이디중복 찾기 버튼 
		checkId = new JButton("중복검사");
		checkId.setBounds(360, 30, 90, 30);
		checkId.addActionListener(this);
		p[1].add(checkId);
		
		
		
		joinBtn = new JButton("회 원 가 입 ");
		joinBtn.setBounds(150, 10, 180, 50);
		joinBtn.addActionListener(this);
		p[5].add(joinBtn);
	
		
		
		setBounds(100, 100, 500, 540);
		setVisible(true);
		addWindowListener(this);
		
	
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
		if(nowBtn ==  joinBtn) { // 회원가입 버튼 
			for (int i = 0; i < txt.length; i++) {
				if(txt[i].getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요!");
					return;
				}
			}
			
			
			String id = txt[0].getText();
			String pwd = txt[1].getText();
			String name = txt[2].getText();
			String email = txt[3].getText();
			int auth = 1;
			for (int i = 0; i < txt.length; i++) {
				txt[i].setText("");
			}
			
			MemberDto dto = new MemberDto(id, pwd, name, email, auth); 
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.addMember(dto);
			
			if(b) {
				JOptionPane.showMessageDialog(null, " 회원가입 완료! ");
				dispose();
				new LoginView();
			}
			else JOptionPane.showMessageDialog(null, "회원가입 할 수 없습니다. ");
			
		}
		else if ( nowBtn == checkId) {
			// 아이디 중복찾기 버튼 
						if(txt[0].getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "id를 입력하시오!");
							return;
						}

						MemberDao dao = MemberDao.getInstance();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
						String id = txt[0].getText().trim();
						boolean check = dao.getId(id); // true일때 이미 있는 아이디 
						if(check) {
							JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다!!!");
						}else {
							JOptionPane.showMessageDialog(null, "아이디 사용 가능");
							
						}
		}
		
		
		
	}
	

}
