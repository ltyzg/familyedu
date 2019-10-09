package com.qishiyun.familyedu.enums;


/**
 * 定义本服务的业务错误码
 * 错误码为状态码为200时，返回给调用方的自定义code码
 * 服务编码：共8位，前四位为服务编码，后四位为业务编码
 * 服务编码：1007，业务编码由开发人员定义
 *
 * @author
 */

public enum ErrorCodeEnum {

	CP10000001(10000001, "小学家庭教育没有报告"),
	CP10000002(10000002, "初中家庭教育没有报告"),
	CP10000003(10000003, "该班级暂时无人完成有效报告"),





	;

	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the code
	 *
	 * @return the enum
	 */
	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
