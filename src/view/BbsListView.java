package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.BbsDto;
import javabean.BbsDao;
import javabean.MemberDao;

public class BbsListView extends JFrame implements MouseListener, ItemListener, ActionListener{

	private JTable jtable;
	private JScrollPane jscrPane;
	private JButton search;
	private JButton prev, next;
	private JButton logout;
	private JTextField searchTxt;
	private JComboBox<String> choice;
	boolean searchCheck;
	private int nowPage = 1;
	private String searchStr="";
	
	
	private JButton writeBtn;
	String columnNames[] = {
			"번호", "제목", "작성자"
	};
	
	Object rowData[][];
	DefaultTableModel model;	// 테이블의 넓이, 폭 등을 설정하기 위해 필요함
	List<BbsDto> list = null;	// 전체 데이터
	int choiceNum; // 어떤것을 검색할건지에 대한 인덱스
	BbsDao dao;
	MemberDao mdao;
	
	
	//TODO: 생성자1 
	public BbsListView() {
		super("게시판");
		setLayout(null);
		mdao = MemberDao.getInstance();
		String user = mdao.getLoginUser().getName();
		JLabel label = new JLabel("["+user+"] 님의 게시판");
		label.setBounds(10, 10, 120, 15);
		add(label);
		
		// dao를 통해서 list를 취득한다
		dao = BbsDao.getInstance();
		list = dao.getBbsList(nowPage);
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
		model = new DefaultTableModel(columnNames, 0) {	// (폭,높이)
			@Override
			public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
				return false;
			}
		};
		
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
		writeBtn.addActionListener(this);
		
		
		// TODO:검색결과
		// 검색 콤보
		String[] str = {"---","제목", "내용", "작성자"};
		choice = new JComboBox<String>(str);
		choice.setBounds(10, 370, 80, 20);
		choice.setSelectedIndex(0);
		System.out.println(choice.getSelectedItem());
		add(choice);
		choice.addItemListener(this);
		
		searchTxt = new JTextField();
		searchTxt.setBounds(110, 370, 400, 20);
		add(searchTxt);
		
		
		// 검색버튼 눌렀을때 
		search = new JButton("검색");
		search.setBounds(520, 370, 80, 20);
		search.addActionListener(this);
		add(search);

		// 페이지 넘기기
		prev = new JButton("<");
		prev.setBounds(280, 400,40,30);
		prev.addActionListener(this);
		prev.setFont(new Font("",Font.BOLD,9));
		add(prev);
		
		next = new JButton(">");
		next.setBounds(330, 400,40,30);
		next.setFont(new Font("",Font.BOLD,9));
		next.addActionListener(this);
		add(next);
		
		
		// 로그아웃 버튼
		logout = new JButton("로그아웃");
		logout.setBounds(10, 400, 100, 30);
		logout.addActionListener(this);
		add(logout);
		
		setBounds(100, 100, 640, 480);
		setBackground(new Color(0,0,128));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	
	// 오버라이딩
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount() == 2 ) {
		BbsDao dao = BbsDao.getInstance();
		JTable clickedTable = (JTable)e.getSource();
		// 더블클릭하면 들어가짐 
		int rowNum = clickedTable.getSelectedRow();
		System.out.println("rowNum : "+rowNum);
		BbsDto selecteDto = list.get(rowNum);
		int sequenceNum = dao.getSequenceNum(selecteDto);
		dao.readBbs(sequenceNum); // 조회수 올리기
		dispose();
		new ShowBbsView(sequenceNum);
		}
		
		
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	// 테이블 갱신을 위한 함수
	public void showSearch(List<BbsDto> sList) {
		this.list = sList;
		
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
		DefaultTableModel  model = new DefaultTableModel(columnNames, 0) {	// (폭,높이)
			@Override
			public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
				return false;
			}
		};
		
		model.setDataVector(rowData, columnNames); // (실제데이터:2차원배열, 범주)
		jtable.setModel(model);
		
	
		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(50);// 번호가 들어갈 곳의 폭
		jtable.getColumnModel().getColumn(1).setMaxWidth(500);// 제목 폭
		jtable.getColumnModel().getColumn(2).setMaxWidth(200);// 작성자 폭
		
  	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)e.getSource();
		
		// 글쓰기 눌렀을떄 
		if(btn == writeBtn) {
			dispose();
			new BbsWriteView();
		}
		// 검색하기 
		if( btn == search ) {
			searchCheck = true;
			choiceNum = choice.getSelectedIndex();
			searchStr = searchTxt.getText().trim();
			searchTxt.setText("");
			switch (choiceNum) {
			case 1:
				JOptionPane.showMessageDialog(null, "제목으로 찾기");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "내용으로 찾기");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "아이디로 찾기");
				break;

			}
			nowPage = 1;
			getSearchList(choiceNum, nowPage);
			//
				System.out.println(list.get(0).toString());
			 	this.showSearch(list);
				return;
			
		}
		// 이전 페이지 
		if(btn == prev) {
			// 검색용일때 
			if(searchCheck) {
				if(nowPage == 1) {
					JOptionPane.showMessageDialog(null, "첫 페이지 입니다!");
					return;
				}
				nowPage--;
				getSearchList(choiceNum, nowPage);
				showSearch(list);
				
			}
			// 전체보기일때
			else {
				if(nowPage == 1) {
					JOptionPane.showMessageDialog(null, "첫 페이지 입니다!");
				}
				else {
					nowPage--;
					list = dao.getBbsList(nowPage);
					showSearch(list);
				}
			
			}
		}
		// 다음 페이지 
		if(btn == next) {
			// 검색용일때 
			if(searchCheck) {
				nowPage++;
				getSearchList(choiceNum, nowPage);
				if(list.size() == 0) {
					 JOptionPane.showMessageDialog(null, "마지막 페이지 입니다!");
					 nowPage--;
					 getSearchList(choiceNum, nowPage);
				}
				showSearch(list);
			}
			// 전체보기일때
			else {
				
				nowPage++;
				list = dao.getBbsList(nowPage);
				if(list.size() == 0) {
					 JOptionPane.showMessageDialog(null, "마지막 페이지 입니다!");
					 nowPage--;
					 list = dao.getBbsList(nowPage);
				}
				showSearch(list);
			}
		}
		
		
	// 로그아웃하기
		if(btn == logout) {
			int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?");
			if(result == JOptionPane.YES_NO_OPTION) {
				mdao.setLoginId("");
				dispose();
				new LoginView();
			}
			else return;
			
		}
		
		
	}
	public void getSearchList(int choiceNum,int page){
		 if(choiceNum == 1) {
			 
				list = dao.searchBbs(1, searchStr,page);
			}
			else if(choiceNum == 2) {
				list = dao.searchBbs(2, searchStr,page);
			}
			else if(choiceNum == 3) {
				list = dao.searchBbs(3, searchStr,page);
			}
			else if(choiceNum == 0){
				list = dao.getBbsList(page);
				searchCheck  = false;
			}
	}
}
