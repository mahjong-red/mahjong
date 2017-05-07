package cn.mahjong.dto;

import java.io.Serializable;

public class RestResp implements Serializable {

	private static final long serialVersionUID = -6208891418703367201L;

	private String code;
	private String msg;
	private Object data;
	
	public RestResp() {
	}

	public RestResp(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
