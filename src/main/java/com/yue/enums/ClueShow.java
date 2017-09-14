package com.yue.enums;

public enum ClueShow {
	show(1), //显示
	hidden(2),  //隐藏
	;

	private int code;

	ClueShow(int code) {
		this.code = code;
	}

	public int getValue() {
		return code;
	}
}
