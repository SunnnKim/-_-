package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javabean.BbsDao;
import javabean.MemberDao;

public class BbsWriteView extends JFrame implements ActionListener {

	JLabel idLabel, titleLabel, contentLabel;
	JTextField idTxt, titleTxt;
	JTextArea contentArea;
	JButton write;
	
	MemberDao mdao;
	BbsDao bdao;
	
	public BbsWriteView() {
		super("write");
		setLayout(null);
		// Id
		// 아이디 레이블 
		idLabel = new JLabel("I D");
		idLabel.setBounds(10, 20, 50, 20);
		idLabel.setFont(new Font(null, Font.BOLD,15));
		idLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(idLabel);
		
		// 아이디텍스트필드 
		idTxt = new JTextField();
		idTxt.setBounds(80, 20, 250, 20);
		add(idTxt);
		// 아이디는 접속한 계정의 사용자로, memberdao에서 가져온다 
		mdao = MemberDao.getInstance();
		String loginId = mdao.getLoginId();
		idTxt.setText(loginId);
		idTxt.setEditable(false);
		
		// title 
		// 타이틀 레이블 
		titleLabel = new JLabel("제목");
		titleLabel.setBounds(10, 50, 50, 20);
		titleLabel.setFont(new Font(null, Font.BOLD,15));
		titleLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(titleLabel);
		
		// 타이틀 텍스트필드 
		titleTxt = new JTextField();
		titleTxt.setBounds(80, 50, 250, 20);
		add(titleTxt);
		
		
		
		
		// contents
		// 내용 레이블 
		contentLabel = new JLabel("내용");
		contentLabel.setBounds(10, 80, 50, 20);
		contentLabel.setFont(new Font(null, Font.BOLD,15));
		contentLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(contentLabel);
		
		// 내용 텍스트애리어 
		contentArea = new JTextArea();
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setBounds(50, 110, 300, 250);
		add(scrPane);
		
		
		// 버튼추가하기
		
		write = new JButton("글쓰기");
		write.setBounds(150, 380, 100, 50);
		write.addActionListener(this);
		add(write);
		
		
		
	
		setVisible(true);
		setBounds(500, 100, 400, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton nowBtn = (JButton)e.getSource();
		
		if(nowBtn == write) {
			String id = idTxt.getText();
			String title = titleTxt.getText().trim();
			String content = contentArea.getText().trim();
			// 빈 내용일때
			if(title.equals("")) {
				JOptionPane.showMessageDialog(null, "제목을 입력하세요!");
				return;
			}
			if(content.equals("")) {
				JOptionPane.showMessageDialog(null, "내용을 입력하세요!");
				return;
			}
			
			// db에 새로운 글 넣기
			bdao = BbsDao.getInstance();
			boolean write = bdao.wirteBbs(id, title, content);
			if(write) {
				JOptionPane.showMessageDialog(null, "새로운 글을 추가했습니다!");
				dispose();
				new BbsListView();
			}
			
			
		}
		
		
	}
	
	
	
	

}
