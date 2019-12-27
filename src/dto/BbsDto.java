package dto;

public class BbsDto {
	/* CREATE TABLE BBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(30) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL
);


	 */
	private int seq;
	private String id;
	
	private String title;
	private String contents;
	private String wdate;
	
	private int del;	// 삭제 여부 확인
	private int readcount;	// 조회수
	
	public BbsDto() {
		// TODO Auto-generated constructor stub
	}

	public BbsDto(int seq, String id, String title, String contents, String wdate, int del, int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
	}

	public BbsDto(String id, String title, String contents) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	
	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", contents=" + contents + ", wdate=" + wdate
		        + ", del=" + del + ", readcount=" + readcount + "]";
	}
	
	
	
}
