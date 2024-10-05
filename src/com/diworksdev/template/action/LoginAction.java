package com.diworksdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.template.dao.BuyItemDAO;
import com.diworksdev.template.dao.LoginDAO;
import com.diworksdev.template.dto.BuyItemDTO;
import com.diworksdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String result;
	private Map<String, Object> session;

	public String execute() {
		LoginDAO ldao=new LoginDAO();
		LoginDTO ldto=new LoginDTO();
		BuyItemDAO bdao=new BuyItemDAO();
		
		result=ERROR;
		ldto=ldao.getLoginUserInfo(loginUserId, loginPassword);
				session.put("loginUser",ldto);
		if(((LoginDTO)session.get("loginUser")).getLoginFlg()) {
			result=SUCCESS;
			BuyItemDTO bdto=bdao.getBuyItemInfo();
			
			session.put("login_user_id",ldto.getLoginId());
			session.put("id",bdto.getId());
			session.put("buyItem_name",bdto.getItemName());
			session.put("buyItem_price",bdto.getItemPrice());
			return result;
		}
		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
