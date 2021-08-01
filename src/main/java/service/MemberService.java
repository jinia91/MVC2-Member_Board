package service;

import dao.MemberDAO;
import beans.MemberDTO;

// 회원정보에 대한 로직을 담당하는 모델
// 1. 회원가입 로직
// 2. 로그인 로직
// 3. 회원비밀번호 찾기 로직
// 4. 회원정보 전부 불러오기 로직
// 5. 회원정보 수정 로직
// 6. 회원정보 삭제 로직

public class MemberService {

	// 싱글톤 패턴 설계//
	// 무의미한 객체 생성으로 메모리 과부하가 나는걸 피하기 위해, 객체를 하나 생성하고 이를 재활용하는 설계방식
	
	private static MemberService Memberserv = new MemberService();  // static으로객체 하나만 메모리에 올려둠
		private MemberService() {}									// 위의 static 선언에 필요한 생성자
	public static MemberService getInstance() {  		// 외부에서 본 모델의 주소값이 필요할때 제공하는 메소드. 이를 통해 객체생성없이 static에서 선언된
		return Memberserv;   							// 싱글톤객체 하나를 계속 재활용할 수 있음. 내부 멤버변수에 변화나 정보가 심어지는게 없으므로 
														// 추가적인 객체생성은 의미없음
	}
	
	// 싱글톤 패턴 설계//
	
	
	public MemberDAO dao = MemberDAO.getInstance();					 // 회원정보 관리에 필요한 DAO 호출, 역시나 같은 싱글톤 패턴	
	
	//1. 회원가입 로직
	public void MemberJoin(MemberDTO member) {
		dao.memberJoin(member);						// 회원정보를 DB에 저장하는 DAO의 메소드 호출
	}
	
	//2. 로그인 로직
	public int MemberLogin(String id, String pwd) {
		int loginResult = dao.memberLogin(id,pwd);  // 1,0,-1로 로직 결과값 반환
		
		return loginResult;
	}
	
	//3. 회원 비밀번호 찾기
	public String memberSearch(String id, String email) {
		String pwd = dao.memberSearch(id, email);
		return pwd;
	}
	
	
	//4. 회원 정보 전부 불러오기
	public MemberDTO memberSearchAll(String id, String pwd) {
		MemberDTO member = dao.memberSearchAll(id, pwd);
		return member;
	}
	
	//5. 회원정보 수정하기
	public void MemberUpdate(MemberDTO member) {
		dao.memberUpdate(member);
	}
	
	//6. 회원정보 삭제
	public void MemberDelete(String id, String pwd) {
		
		dao.memberDelete(id,pwd);
		
	}
	
}
