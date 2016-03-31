package com.tongmeng.txyspring.service.identity;

public abstract class IdentityInterface {
	
	private SCHCODE schCode;
	public abstract String getOriId(String HttpRequest);
	
	public IdentityInterface(SCHCODE schCode)
	{
		this.schCode = schCode;
	}
	
	public SCHCODE getSchCode()
	{
		return this.schCode;
	}
	
	public enum SCHCODE 
	{  
		TONGJI(10000), FUDAN(20000), ETC(0);
		
		private final int id;
		private SCHCODE(int id) { this.id = id; }
		public int getValue() { return id; }	 
	}
	
	
	
	public static IdentityInterface getSchFactory(String HttpRequest)
	{
		SCHCODE currentSchCode = analysisSchCode(HttpRequest);
		
		switch(currentSchCode)
		{	
			case TONGJI:
				
				return new IdentityInterface(currentSchCode){
					
					public String getOriId(String HttpRequest)
					{
						/*
						 * To http://data.tongji.edu.cn:8080/dataservice/ws/rest/getSid?ticket=test&appId=00168&appSecret=test&key=101
						 * 
						 * 
						 */		
						
						return "12120";
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
	
	
	private static SCHCODE analysisSchCode(String HttpRequest)
	{
		return SCHCODE.TONGJI;
		
		/*
		if(true)
		{
			return SCHCODE.TONGJI;
		}
		else
		{
			return SCHCODE.ETC;
		}
		*/
	}
	
	
}
