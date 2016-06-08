package com.tongmeng.txyspring.service.identity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class IdentityInterface {
	
	private SCHCODE schCode;

	public abstract String getOriId(HttpServletRequest request);

	public IdentityInterface(SCHCODE schCode) {
		this.schCode = schCode;
	}

	public SCHCODE getSchCode() {
		return this.schCode;
	}

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

	public static IdentityInterface getSchFactory(HttpServletRequest request) {

		SCHCODE currentSchCode = analysisSchCode(request);

		switch (currentSchCode) {
		case TONGJI:

			return new TongjiIdentity(currentSchCode);

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
	
	public TongjiIdentity(SCHCODE schCode) {
		super(schCode);
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

	@Override
	public String getOriId(HttpServletRequest request) {
		String ticket = request.getParameter("ticket");

		if (ticket == null) {
			throw new IllegalArgumentException("Empty case to get the user ticket in Tongji");
		}
		
		if(ticket.equals("1"))
		{
			logger.warn("use test user to debug");
			return ticket;
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
			return "";
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
			return "";
		}

		if (obj.getType().equals("success")) {
			logger.warn("obj.getType() error when accessing the data.tongji");
			return "";
		}

		return obj.getResult();
	}
	
	
}










