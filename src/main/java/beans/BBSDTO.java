package beans;

import java.sql.Timestamp;

public class BBSDTO {

	private int bbsId;
	private String bbsTitle;
	private String bbsContent;
	private Timestamp bbsDate;
	private int bbsHit;
	private String id;
	
	
	public void setBbsId(int bbsId) {
		
		this.bbsId = bbsId;
		
	}
	
	public int getBbsId() {
		
		return bbsId;
		
	}
	
	public int getBbsHit() {
		return bbsHit;
	}
	
	public void setBbsHit(int bbsHit) {
		this.bbsHit = bbsHit;
	}
	
	public String getBbsTitle() {
		return bbsTitle;
	}
	
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	
	public String getBbsContent() {
		return bbsContent;
	}
	
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	
	public Timestamp getBbsDate() {
		return bbsDate;
	}
	
	public void setBbsDate(Timestamp bbsDate) {
		this.bbsDate = bbsDate;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
}
