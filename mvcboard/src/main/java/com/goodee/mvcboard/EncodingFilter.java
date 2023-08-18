package com.goodee.mvcboard;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;


@WebFilter("/*") // 모든요청에 대해서
public class EncodingFilter extends HttpFilter implements Filter {
       

    public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() { // 필터의 종료작업을 수행
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { // 필터의 주요동작은 정의
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("요청을 가로채서 UTF-8 인코딩");
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException { //필터의 초기화 작업을 수행
		// TODO Auto-generated method stub
	}

}
