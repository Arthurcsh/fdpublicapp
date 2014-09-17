package com.wondersgroup.fdpublicapp.common.protocol;

/**
 * @author chengshaohua
 *
 */
public class FDParseException extends Exception {

	/**
	 * 返回数据解析异常
	 */
	private static final long serialVersionUID = -2896278298547345122L;
	private String exMessageCode;

	public FDParseException(String exMessage)	{
		super(exMessage);
	}
	
	/**
	 * 异常信息、错误码构造
	 * @param exMessage
	 * @param code
	 */
	public FDParseException(String exMessage, String code) {
		this(exMessage);
		this.exMessageCode = code;
	}
	/**
	 * 返回错误码
	 * @return
	 */
	public String getExMessageCode() {
		return exMessageCode;
	}
}
