package com.goodee.mvcboard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//리스너는 특정 이벤트가 발생했을 때 웹 애플리케이션에서 미리 정의한 작업을 수행
@WebListener
public class BootListener implements ServletContextListener {


    public BootListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }


    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("톰캣실행시 실행되는 코드");
    }
	
}
