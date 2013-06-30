package pe.edu.ucsp.oms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
	  return true;
  }

	@Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
		String username = (String)request.getSession().getAttribute("username");
		if (username == null) {
			if (!request.getRequestURL().toString().endsWith("login.html")) {
				response.sendRedirect(request.getContextPath() + "/login.html");
			}
		}
  }

	@Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
  }

}
