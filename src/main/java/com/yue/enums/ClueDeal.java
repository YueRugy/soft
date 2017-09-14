package com.yue.enums;

public enum ClueDeal {
	deal(1), //已处理
	notDeal(2), //没有处理
	;

	private int code;

	ClueDeal(int code) {
		this.code = code;
	}

	public int getValue() {
		return code;
	}
}
