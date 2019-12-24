package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BbsWriteView extends JFrame implements WindowListener {

	
	public BbsWriteView() {
		super("write");
		setLayout(null);
		// Id 
		JLabel idLabel = new JLabel("I D");
		idLabel.setBounds(10, 20, 50, 20);
		idLabel.setFont(new Font(null, Font.BOLD,15));
		idLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(idLabel);
		
		JTextField idTxt = new JTextField();
		idTxt.setBounds(80, 20, 250, 20);
		add(idTxt);
		
		// title 
		JLabel titleLabel = new JLabel("제목");
		titleLabel.setBounds(10, 50, 50, 20);
		titleLabel.setFont(new Font(null, Font.BOLD,15));
		titleLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(titleLabel);
		
		JTextField titleTxt = new JTextField();
		titleTxt.setBounds(80, 50, 250, 20);
		add(titleTxt);
		
		
		
		
		// contents
		JLabel contentLabel = new JLabel("내용");
		contentLabel.setBounds(10, 80, 50, 20);
		contentLabel.setFont(new Font(null, Font.BOLD,15));
		contentLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(contentLabel);
		
		JTextArea contentArea = new JTextArea();
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setBounds(30, 110, 300, 250);
		add(scrPane);
		
		
		// 버튼추가하기
	
		setVisible(true);
		setBounds(500, 100, 400, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

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

}
