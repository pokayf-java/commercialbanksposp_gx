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
import com.poka.app.anno.base.service.impl.PopupInfoService;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.anno.enity.PopupInfo;

/**
 * 2017年10月18日
 * 弹窗获取冠字号详细信息
 * @author enma.ai
 *
 */
@Component
public class PopupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PopupInfoService popupInfoService;
	
	@Autowired
	@Qualifier("popupInfoService")
	public void setPopupInfoServiceImpl(PopupInfoService popupInfoService) {
		this.popupInfoService = popupInfoService;
	}

	public PopupServlet() {
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
		getPopupInfo(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	/**
	 * 获取弹窗详细信息
	 * 2017年10月18日
	 * @author Enma.ai
	 * @return void
	 * @param request
	 * @param response
	 */
	private void getPopupInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			//从前端获取冠字号
			String dealNo = request.getParameter("dealNo");
			//确定冠字号一定不为空
			if (dealNo != null && dealNo.trim().length() > 0) {
				dealNo = dealNo.replaceAll(" ", "");
				List<PopupInfo> popupInfoList = null;
				//根据冠字号获取详细信息
				popupInfoList = popupInfoService.findPopupInfoList(dealNo);
				//测试用
				for (PopupInfo popupInfo : popupInfoList) {
					System.out.println(popupInfo);
				}
				PrintWriter pw = response.getWriter();
				if (popupInfoList != null && popupInfoList.size() > 0) {
					Gson gson = new Gson();
					String jsonMonList = gson.toJson(popupInfoList);
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
