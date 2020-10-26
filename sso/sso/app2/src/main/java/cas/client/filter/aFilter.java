package cas.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cas.client.SessionMap;

/**
 * Servlet Filter implementation class SingleSignOutFilter
 */
@WebFilter(filterName="F1",urlPatterns="/*")
public class aFilter implements Filter {
	private String CAS_LOGOUT_URL="http://localhost:8080/cas/logout.do";
    /**
     * Default constructor. 
     */
    public aFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		//HttpSession session=httpRequest.getSession();
		String logout=httpRequest.getParameter("logout");
		String redirect=httpRequest.getParameter("redirect");
		if(redirect!=null) {
			httpResponse.sendRedirect(CAS_LOGOUT_URL);
			return;
		}
		if(logout!=null)
		{
			String sessionId=httpRequest.getParameter("sessionId");
			SessionMap.invalidate(sessionId);
		}else{
			chain.doFilter(request, response);
		}
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
