package com.yue.enums;

public enum ClueStatus {
	waitDistribution(1), //等待分配
	alreadyDistribution(2), //已分配
	;

	private int code;

	ClueStatus(int code) {
		this.code = code;
	}

	public int getValue() {
		return code;
	}
}
