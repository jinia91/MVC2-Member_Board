package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// 모든 컨트롤러가 상속받는 컨트롤러 인터페이스로, 프론트 컨트롤러에서 해당 컨트롤러 객체를 불러오기 위해 부여하는 공통속성

public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
