package com.lucifer.util;

public class Result {

	private boolean ok;
	
	private String msg;
	
	private Object data;
	
	private String code;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public <T> T  getData() {
		return  (T)data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static Result result(boolean ok, String code,String message, Object data) {
	    Result result = new Result();
	    result.setOk(ok);
		result.setCode(code);
	    result.setMsg(message);
	    result.setData(data);
	    return result;
	}
	
	public static Result ok() {
	    return Result.result(true, null,null, null);
	}
	
	public static Result ok(Object data) {
	    return Result.result(true, null,null, data);
	}
	
	public static Result ok(String code, Object data) {
	    return Result.result(true,code, null, data);
	}
	
	public static Result fail() {
	    return Result.result(false, null,null, null);
	}
	
	public static Result fail(String code) {
	    return Result.result(false, code,null, null);
	}
	
	public static Result fail(String code, Object data) {
	    return Result.result(false, code,null, data);
	}
	

	
	public static Result exception(String code){
		Result rusult= Result.result(false, code, null,null);

		return rusult;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
