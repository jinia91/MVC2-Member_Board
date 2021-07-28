package beans;

// 데이터 운반을 수행하는 클래스 DTO. get과 set으로만 이루어져있음;
// 회원에 대한 정보 운반시에 이용할것

public class MemberDTO {
	
	private String id;
	private String pwd;
	private String email;
	private String birth;
	private String gender;
	
	
	public String getId() {return id;}
	public String getPwd() {return pwd;}
	public String getEmail() {return email;}
	public String getBirth() {return birth;}
	public String getGender() {return gender;}
	
	public void setId(String id) {this.id=id;}
	public void setPwd(String pwd) {this.pwd=pwd;}
	public void setEmail(String email) {this.email=email;}
	public void setBirth(String birth) {this.birth=birth;}
	public void setGender(String gender) {this.gender=gender;}
	
}
