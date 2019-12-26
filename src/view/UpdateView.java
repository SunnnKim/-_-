package view;

import java.awt.Color;
import java.awt.Font;
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
import javabean.BbsDao;

public class UpdateView extends JFrame implements ActionListener{
	JLabel idLabel, titleLabel, contentLabel;
	JTextField idTxt, titleTxt;
	JTextArea contentArea;
	JButton update, back;

	BbsDao bdao;
	BbsDto dto; 	// 수정하려는 글의 객체
	int sequenceNum;
	
	
	public UpdateView(int sequenceNum) {
		super("글 수정");
		
		this.sequenceNum = sequenceNum;
		bdao = BbsDao.getInstance();
		dto = bdao.getSelectedBbs(sequenceNum);
		
		JPanel p = new JPanel();
		p.setBounds(0, 0, 400, 480);
		p.setLayout(null);
		p.setBackground(Color.white);
		add(p);
		
		
		idLabel = new JLabel("I D");
		idLabel.setBounds(10, 20, 50, 20);
		idLabel.setFont(new Font(null, Font.BOLD,15));
		idLabel.setHorizontalAlignment(JLabel.RIGHT);
		p.add(idLabel);
		
		// 아이디텍스트필드 
		idTxt = new JTextField();
		idTxt.setBounds(80, 20, 250, 20);
		p.add(idTxt);
		
		// 아이디는 접속한 계정의 사용자로, bbs에서 가져온다.
		
		idTxt.setText(dto.getId());
		idTxt.setEditable(false);
		
		
		// title 
		// 타이틀 레이블 
		titleLabel = new JLabel("제목");
		titleLabel.setBounds(10, 50, 50, 20);
		titleLabel.setFont(new Font(null, Font.BOLD,15));
		titleLabel.setHorizontalAlignment(JLabel.RIGHT);
		p.add(titleLabel);
		
		// 타이틀 텍스트필드 
		titleTxt = new JTextField();
		titleTxt.setBounds(80, 50, 250, 20);
		
		// DB에서 텍스트 가져오기 
		titleTxt.setText(dto.getTitle());
		p.add(titleTxt);
		
		
		
		
		// contents
		// 내용 레이블 
		contentLabel = new JLabel("내용");
		contentLabel.setBounds(10, 80, 50, 20);
		contentLabel.setFont(new Font(null, Font.BOLD,15));
		contentLabel.setHorizontalAlignment(JLabel.RIGHT);
		p.add(contentLabel);
		
		
		
		// 내용 텍스트애리어 
		contentArea = new JTextArea();
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setBounds(50, 110, 300, 250);
		p.add(scrPane);
		
		// 디비에서 내용가져오기
		contentArea.setText(dto.getContents());
		
		
		
		// 버튼추가하기
		
		update = new JButton("수정");
		update.setBounds(80, 380, 100, 40);
		update.addActionListener(this);
		p.add(update);
		
		back = new JButton("뒤로가기");
		back.setBounds(210, 380, 100, 40);
		back.addActionListener(this);
		p.add(back);
		
		
		
	
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
			new ShowBbsView(sequenceNum);
		}
		if(btn == update) {
			// 수정하기
			
			int result = JOptionPane.showConfirmDialog(null,"수정하시겠습니까?", "Confirm",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				// 수정 코드
				String newTitle = titleTxt.getText().trim();
				String newContent = contentArea.getText().trim();
				if(newTitle.equals("") || newContent.equals("")) {
					JOptionPane.showMessageDialog(null, "제목과 내용을 모두 입력하세요!");
					return;                                                                                     
				}
				boolean b = bdao.update(newTitle, newContent, sequenceNum);
				if(b) {
					JOptionPane.showMessageDialog(null, "글을 수정했습니다.");
					dispose();
					new BbsListView();
				}
				else {
					JOptionPane.showMessageDialog(null, "수정에 실패했습니다!");
				}
					return;
			}else if(result == JOptionPane.CLOSED_OPTION) {
				return;
			}
		}
		
		}

}
