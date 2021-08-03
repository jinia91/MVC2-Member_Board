package service;

import dao.BBSDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.BBSDTO;

// 게시판 관련 로직을 담당하는 모델
// 1. 전체 게시물 표시
// 2. 게시물 작성
// 3. 글 조회시 조회수 +1
// 4. 글 조회시 글내용 출력


public class BBSService {

	// 싱글톤 패턴 설계//
		// 무의미한 객체 생성으로 메모리 과부하가 나는걸 피하기 위해, 객체를 하나 생성하고 이를 재활용하는 설계방식
		
		private static BBSService BBSserv = new BBSService();  // static으로객체 하나만 메모리에 올려둠
			private BBSService() {}									// 위의 static 선언에 필요한 생성자
		public static BBSService getInstance() {  		// 외부에서 본 모델의 주소값이 필요할때 제공하는 메소드. 이를 통해 객체생성없이 static에서 선언된
			return BBSserv;   							// 싱글톤객체 하나를 계속 재활용할 수 있음. 내부 멤버변수에 변화나 정보가 심어지는게 없으므로 
															// 추가적인 객체생성은 의미없음
		}
		
		// 싱글톤 패턴 설계//
		
		
		public BBSDAO dao = BBSDAO.getInstance();					 // 	
		
		
		
		// 전체 게시물 불러오기
		public ArrayList<BBSDTO> BBSList(){
			
			ArrayList<BBSDTO> list = null;
			
			try {
			 list = dao.BBSallList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		
		
		// 게시물 작성
		public void BBSWrite(BBSDTO dto) {
			
		 dao.BBSWrite(dto);	
			
		}
		
		
		// 게시물 조회시 조회수 +1
		
		
		
		// 게시물 보기
		public BBSDTO BBSView(String bbsId) {
			return dao.BBSView(bbsId);
		}
		
		// 게시물 삭제
		public void BBSDelete() {
			
		}

	
}


