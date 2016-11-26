package com.poka.app.anno.servlet;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;
import com.poka.app.anno.base.service.impl.MoneyDataService;
import com.poka.app.anno.enity.MoneyDataInfo;
@Component
public class GZHQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MoneyDataService moneyDataService;
	
	@Autowired
	@Qualifier("moneyDataService")
	public void setMoneyDataService(MoneyDataService moneyDataService) {
		this.moneyDataService = moneyDataService;
	}

	public GZHQueryServlet() {
		super();
	}

	/**
	 * 在servlet中需要注入的话，必须在init()方法中实现如下语句，不然无法实现注入。
	 */
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");

		String flag = "false";
		request.setAttribute("login", flag);
		if(null!=flag&&flag.trim().equals("false")){
			if( method != null && method.trim().length()>0 ){
				if( "query".equals(method)||"toLoginJsp".equals(method) ){
					request.getRequestDispatcher("/query.jsp").forward(request, response);
				}else if( "search".equals(method) ){
					request.getRequestDispatcher("/search.jsp").forward(request, response);
				}else if( "queryResult".equals(method) ){
					queryResult(request,response);
				}else if( "searchResult".equals(method) ){
				}
			}
			
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	/***
	 * query money code information Version 2.0 
	 * @param request
	 * @param response
	 */
	private void queryResult(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String dealNo = request.getParameter("dealNo");
			if (dealNo != null && dealNo.trim().length() > 0) {
				dealNo = dealNo.replaceAll(" ", "");
				List<MoneyDataInfo> moneyDataList = null;
				if (dealNo.indexOf("*") > -1) {
					String dealNoTmp = dealNo.replace("*", "_");
					moneyDataList = moneyDataService.findMoneyDataListByLike(dealNoTmp);
				} else {
					moneyDataList = moneyDataService.findMoneyDataList(dealNo);
				}
				
				PrintWriter pw = response.getWriter();
				if (moneyDataList != null && moneyDataList.size() > 0) {
					Gson gson = new Gson();
					String jsonMonList = gson.toJson(moneyDataList);
				    pw.write(jsonMonList);
				} else {
					response.setContentType("text/html;charset=UTF-8");
					pw.write("notfound");
				}
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
