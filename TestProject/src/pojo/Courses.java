package pojo;

import java.util.ArrayList;
import java.util.List;

public class Courses {
	
	public List<WebAutomation> getWebAutomation() {
		return WebAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		WebAutomation = webAutomation;
	}
	public List<API> getAPI() {
		return API;
	}
	public void setAPI(List<API> aPI) {
		API = aPI;
	}
	public List<Mobile> getMobile() {
		return Mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		Mobile = mobile;
	}
	private List<WebAutomation> WebAutomation;
	private List<API> API;
	private List<Mobile> Mobile;
	

	
	


	

}
