package com.tongmeng.txyspring.service.identity;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class IdentityInterface {
	
	public enum SCHCODE {
		TONGJI(10000), FUDAN(20000), ETC(0);

		private final int id;

		private SCHCODE(int id) {
			this.id = id;
		}

		public int getValue() {
			return id;
		}
	}
	
	protected SCHCODE schCode;
	
	public IdentityInterface(SCHCODE schCode) {
		this.schCode = schCode;
	}
	
	public class UserIdentity
	{
		protected final static String NOT_LOGIN = "NOT_LOGIN" ;

		private String oriId;
		private SCHCODE schCode; 
		
		public String getOriId() {
			return oriId;
		}

		public void setOriId(String oriId) {
			this.oriId = oriId;
		}

		public IdentityInterface.SCHCODE getSchCode() {
			return schCode;
		}

		public void setSchCode(IdentityInterface.SCHCODE schCode) {
			this.schCode = schCode;
		}

		public UserIdentity(String oriId,IdentityInterface.SCHCODE schCode)
		{
			this.oriId = oriId;
			this.schCode = schCode;
		}
		
		public boolean ifLogin()
		{
			if(this.oriId.equals(NOT_LOGIN))
			{
				return false;
			}
			else
			{
				return true;
			}
		}	
	}
	
	public class notLoginUserIdentity extends UserIdentity
	{
		public notLoginUserIdentity()
		{
			super(NOT_LOGIN, SCHCODE.ETC);
		}
	}
	

	public abstract UserIdentity getUserIdentity(HttpServletRequest request);


	public static IdentityInterface getSchFactory(HttpServletRequest request) {

		SCHCODE currentSchCode = analysisSchCode(request);

		switch (currentSchCode) {
		case TONGJI:

			return new TongjiIdentity();

		case FUDAN:

			return null;

		case ETC:

			return null;

		default:

			return null;
		}

	}

	private static SCHCODE analysisSchCode(HttpServletRequest request) {
		return SCHCODE.TONGJI;

		/*
		 * if(true) { return SCHCODE.TONGJI; } else { return SCHCODE.ETC; }
		 */
	}

}


class TongjiIdentity extends IdentityInterface
{
	
	private static final Logger logger = LoggerFactory.getLogger(TongjiIdentity.class);
	
	public TongjiIdentity() {
		super(SCHCODE.TONGJI);
		// TODO Auto-generated constructor stub
	}

	static class ReturnStatus {
		private String type;
		private String result;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

	}
	
	
	public boolean isAdmin(String ticket)
	{
		ArrayList<String> array = new ArrayList<String>(Arrays.asList("1","2")); 
		if(array.contains(ticket))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public UserIdentity getUserIdentity(HttpServletRequest request) {
		String ticket = request.getParameter("ticket");

		if (ticket == null) {
			return new notLoginUserIdentity();
		}
		
		if(isAdmin(ticket))
		{
			logger.warn("use test user to debug");
			return new UserIdentity(ticket, schCode);
		}
		
		/*
		 * To
		 * http://data.tongji.edu.cn:8080/dataservice/ws/rest/getSid
		 * ?ticket=test&appId=00168&appSecret=test&key=101
		 * 
		 * 
		 */

		ObjectMapper mapper = new ObjectMapper();

		String remoteUrl = "http://data.tongji.edu.cn:8080/dataservice/ws/rest/getSid?ticket=" + ticket
				+ "&appId=10160&appSecret=infopub&key=101";

		ReturnStatus obj = null;

		try {
			obj = mapper.readValue(new URL(remoteUrl), ReturnStatus.class);
		} catch (Exception e) {
			logger.warn(e.toString());
			return new notLoginUserIdentity();
		}

		/*
		 * catch (JsonParseException e) { // TODO Auto-generated
		 * catch block e.printStackTrace();
		 * 
		 * logger.warn(arg0);
		 * 
		 * } catch (JsonMappingException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch
		 * (MalformedURLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

		if (obj == null) {
			logger.warn("obj==null after skip the Exception e");
			return new notLoginUserIdentity();
		}

		if (!obj.getType().equals("success")) {
			logger.warn("obj.getType() error when accessing the data.tongji"+ obj.getResult());
			return new notLoginUserIdentity();
		}

		return new UserIdentity(ticket, schCode);
	}
	
	
}










