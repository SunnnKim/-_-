package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.BbsDto;
import dto.MemberDto;
import javabean.BbsDao;
import javabean.MemberDao;

public class ShowBbsView extends JFrame implements ActionListener {
	
	JLabel label[];
	JTextField txt[];
	JTextArea content;
	JScrollPane scrPane;
	JButton update, delete, back;
	
	int sequenceNum;
	MemberDao loginId;
	BbsDao dao;
	BbsDto bbsDto;
	
	
	
	public ShowBbsView(int sequenceNum) {
		super("글보기");
		
		this.sequenceNum = sequenceNum;
		dao = BbsDao.getInstance();
		loginId = MemberDao.getInstance();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 480);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		add(panel);
		
		String labelList[] = {"작성자", "작성일", "조회수", "제목","내용" };
		label = new JLabel[5];
		for (int i = 0; i < labelList.length; i++) {
			label[i] = new JLabel(labelList[i]);
			label[i].setBounds(30, 20+(i*30), 50 ,30);
			label[i].setHorizontalAlignment(JLabel.RIGHT);
			panel.add(label[i]);
		}
		
		txt = new JTextField[4];
		for (int i = 0; i < txt.length; i++) {
			txt[i] = new JTextField();
			txt[i].setBounds(90, 25+(i*30),250, 20);
			txt[i].setEditable(false);
			panel.add(txt[i]);
		}
		
		// 디비에서 텍스트필드 채우기 
		
		bbsDto = dao.getSelectedBbs(sequenceNum);
		txt[0].setText(bbsDto.getId());
		txt[1].setText(bbsDto.getWdate());
		txt[2].setText(bbsDto.getReadcount()+"");
		txt[3].setText(bbsDto.getTitle());
		
		
		
		content = new JTextArea();
		scrPane = new JScrollPane(content);
		scrPane.setBounds(50, 170, 290, 200);
		panel.add(scrPane);
		content.setText(bbsDto.getContents());
		content.setEditable(false);
		
		
		// 버튼 보이기 
		if(bbsDto.getId().equals(loginId.getLoginId())) {
			
			update = new JButton("수정");
			update.setBounds(50, 385, 80, 30);
			update.addActionListener(this);
			panel.add(update);
			
			delete = new JButton("삭제");
			delete.setBounds(150, 385, 80, 30);
			delete.addActionListener(this);
			panel.add(delete);
			
		}
		
		back = new JButton("뒤로가기");
		back.setBounds(250, 385, 90, 30);
		back.addActionListener(this);
		panel.add(back);
		
		
		
		
		
		
		
		setBounds(500, 100, 400, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)e.getSource();
		if(btn == back) {
			dispose();
			new BbsListView();
		}
		if(btn == update) {
			dispose();
			new UpdateView(sequenceNum);
		}
		if(btn == delete) {
			int result = JOptionPane.showConfirmDialog(null,"삭제하시겠습니까?", "Confirm",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
			
				
				
				
				//삭제하는 경우의 코드
				
				boolean b = dao.deleteBbs(sequenceNum);
				if(!b) {
					JOptionPane.showMessageDialog(null, "글을 삭제하지 못했습니다!");
					return;
				}
				JOptionPane.showMessageDialog(null, "글을 삭제했습니다.");
				dispose();
				new BbsListView();
				return;
			}else if(result == JOptionPane.CLOSED_OPTION) {
				return;
			}
		}
	}

}
