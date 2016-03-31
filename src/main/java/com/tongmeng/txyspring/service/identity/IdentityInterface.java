package com.tongmeng.txyspring.service.identity;

import javax.servlet.http.HttpServletRequest;

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

	private final static String EMPTY_ID = "Empty case to get the userID of SCHCODE ";

	public static IdentityInterface getSchFactory(HttpServletRequest request) {
		
		SCHCODE currentSchCode = analysisSchCode(request);
		final String EMPTY_ID_CURRENT = EMPTY_ID + currentSchCode.getValue();

		switch (currentSchCode) {
		case TONGJI:

			return new IdentityInterface(currentSchCode) {

				public String getOriId(HttpServletRequest request) {
					String ticket = request.getParameter("ticket");

					if (ticket == null) {

						throw new IllegalArgumentException(EMPTY_ID_CURRENT);

					}

					/*
					 * To
					 * http://data.tongji.edu.cn:8080/dataservice/ws/rest/getSid
					 * ?ticket=test&appId=00168&appSecret=test&key=101
					 * 
					 * 
					 */

					return "12121";
				}
			};

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
