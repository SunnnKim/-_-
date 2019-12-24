package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.BbsDto;
import dto.MemberDto;
import javabean.BbsDao;

public class BbsListView extends Frame implements WindowListener, MouseListener {

	private JTable jtable;
	private JScrollPane jscrPane;
	
	private JButton writeBtn;
	String columnNames[] = {
			"번호", "제목", "작성자"
	};
	
	Object rowData[][];
	DefaultTableModel model;	// 테이블의 넓이, 폭 등을 설정하기 위해 필요함
	List<BbsDto> list = null;
	
	public BbsListView() {
		super("게시판");
		setLayout(null);
		
		JLabel label = new JLabel("게시판");
		label.setBounds(10, 10, 120, 15);
		add(label);
		
		// dao를 통해서 list를 취득한다
		BbsDao dao = BbsDao.getInstance();
		list = dao.getBbsList();
		
		
		//jtable row생성
		rowData = new Object[list.size()][3];
		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			BbsDto dto = list.get(i);
			rowData[i][0] = i+1; 	// 글번호 (**시퀀스 번호 아님**)
			rowData[i][1] = dto.getTitle(); 	// 글제목
			rowData[i][2] = dto.getId(); 	// 작성자
			
		}
		
		// Table 관련 
		// 1. 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);	// (폭,높이)
		model.setDataVector(rowData, columnNames); // (실제데이터:2차원배열, 범주)
		
		// Jtable
		jtable = new JTable(model);
		jtable.addMouseListener(this);
		
		
		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(50);// 번호가 들어갈 곳의 폭
		jtable.getColumnModel().getColumn(1).setMaxWidth(500);// 제목 폭
		jtable.getColumnModel().getColumn(2).setMaxWidth(200);// 작성자 폭
		// 테이블 크기와 위치 설정
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 300);
		add(jscrPane);
		
		writeBtn = new JButton("글쓰기");
		writeBtn.setBounds(510, 10, 100, 20);
		add(writeBtn);
		
		
		setBounds(100, 100, 640, 480);
		setBackground(new Color(0,0,128));
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	// mouseListener : 테이블 클릭했을 때 글 목록보는 곳으로 감 

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
