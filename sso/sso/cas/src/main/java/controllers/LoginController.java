package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cas.server.Constants;
import cas.server.JWTUtils;
import database.DB;

import domains.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
@WebServlet(value="/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constants.TOKEN)) {
			
					String TOKEN = cookie.getValue();
					System.out.println("CAS_ST:"+TOKEN);
					Claims claims = JWTUtils.parseJWT(TOKEN);
					User user =DB.getUser(claims.getSubject()) ;
					if(user!=null) {
						try {
							TOKEN = JWTUtils.createJWT(JWTUtils.getUUID(),user.getId() , 10);//1
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cookie = new Cookie(Constants.TOKEN,TOKEN );
						cookie.setMaxAge(-1);
						response.addCookie(cookie);
					
					
					
						response.sendRedirect(LOCAL_SERVICE + "?"
								+ Constants.TOKEN + "=" + TOKEN + "&"
								+ Constants.LOCAL_SERVICE + "=" + LOCAL_SERVICE);
						return;
					}
					
				}
			}
		}
		
		request.setAttribute(Constants.LOCAL_SERVICE, LOCAL_SERVICE);
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
		User user =DB.findUser(id,pwd);
		HttpSession session=request.getSession();
		if (user != null) {
			session.setAttribute("user", user);
			
			String TOKEN="";
			try {
				TOKEN = JWTUtils.createJWT(JWTUtils.getUUID(),user.getId() , 10);//1
				System.out.println("CAS_ST:"+TOKEN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			Cookie cookie = new Cookie(Constants.TOKEN,TOKEN );
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			
			
			
			if (LOCAL_SERVICE != null && !LOCAL_SERVICE.equals("")) {
				response.sendRedirect(LOCAL_SERVICE + "?"
						+ Constants.TOKEN + "=" + TOKEN + "&"
						+ Constants.LOCAL_SERVICE + "=" + LOCAL_SERVICE);
			} else
				response.sendRedirect(request.getContextPath()+"/main.do");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		}
	}

}