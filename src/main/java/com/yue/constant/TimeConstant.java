package com.yue.constant;

public class TimeConstant {
	//每分钟毫秒数
	public static final int TIME_PER_MINUTE_MILLISECOND = 60000;
	//每天的毫秒数
	public static final long TIME_PER_DAY_MILLISECOND = 86400000L;
	//每小时分钟数
	public static final int TIME_PER_HOUR_MINUTE = 3600;
	//5分钟的秒数
	public static final int TIME_FIVE_MINUTE = 300;
	
	
	
	//10分钟
	public static final int TIME_EXPIRED_VERIFYCODE_MINUTE = 10;
	//验证码有效时间 --10分钟(毫秒)
	public static final int TIME_EXPIRED_VERIFYCODE_MILLISECOND = TIME_EXPIRED_VERIFYCODE_MINUTE * TIME_PER_MINUTE_MILLISECOND;
	//5分钟
	public static final int TIME_RESEND_VERIFYCODE_MINUTE = 5;
	//发送验证码频率(5分钟后可再次发送)
	public static final int TIME_RESEND_VERIFYCODE_MINUTE_MILLISECOND = TIME_RESEND_VERIFYCODE_MINUTE * TIME_PER_MINUTE_MILLISECOND;
	//订单超时时间(20分钟)
	public static final int TIME_OVERTIME_ORDER_MINUTE = 20;
	//查看案例超时记录时间(24小时)
	public static final int TIME_OVERTIME_CASEUSERCODE_HOURS = 24;
	//30分钟
	public static final int TIME_EXPIRED_CASES_MINUTE = 10;
	//申请查看案例频率(30分钟)
	public static final int TIME_EXPIRED_CASES_MILLISECOND = TIME_EXPIRED_CASES_MINUTE * TIME_PER_MINUTE_MILLISECOND;
	
	
	
	
	//cookie有效时间 --1天 
	public static final long TIME_EXPIRED_COOKIE_MILLISECOND = 24 * TIME_PER_HOUR_MINUTE;
	//cookie删除 0秒
	public static final long TIME_DELETE_COOKIE_MILLISECOND = 0;
	//PC端cookie有效时间--12小时
	public static final long TIME_EXPIRED_COOKIE_PC_MILLISECOND = 12 * TIME_PER_HOUR_MINUTE;
	//back端cookie有效时间--12小时
	public static final long TIME_EXPIRED_COOKIE_BACK_MILLISECOND = 12 * TIME_PER_HOUR_MINUTE;
	//phone端cookie有效时间--12小时
	public static final long TIME_EXPIRED_COOKIE_PHONE_MILLISECOND = 12 * TIME_PER_HOUR_MINUTE;
	
}
