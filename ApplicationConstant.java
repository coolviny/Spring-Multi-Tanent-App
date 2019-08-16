package com.utility;

import java.util.Arrays;
import java.util.List;

/**
 * Application constant to be used.
 * @author vishal
 *
 */
public interface ApplicationConstant {
	
	String CONTENT_TYPE_APPLICATION_JSON 				= "application/json; charset=UTF-8";
	String CONTENT_TYPE_HTML_TEXT 						= "text/html";
	
	String ENCRYPTION_KEY 								= "XMzDdG4D03CKm2IxIWQw7g==";
	final int DEFAULT_JWT_TIME_OUT 						= 1440; //in millisecond
	final int COOKIE_TIME_OUT 							= 10800;
	String JWT_SECRET 									= "VffD76t3YBwC9k9YZkm2XC8VByAjWBfD";
	String DEFAULT_TOKEN 								= "XMzDdG4D03CKm2IxIWQw7g==";
	
	String REQUEST_JSON 								= "requestJson";
	String RESPONSE_JSON 								= "responseJson";
	String JWT_TOKEN									= "jwtToken";
	String JWT_COOKIE_KEY 								= "_qu";
	String STRING_STATUS 								= "status";
	String STRING_MSG 									= "msg";
	String JWT_ISSUER 									= "smartSense for IOT";
	//String EMPTY_STRING 								= StringUtils.EMPTY;
	String STRING_JWT_SUBJECT 							= "jwtSubject";
	String TIME_ZONE_OFFSET								= "timeZoneOffset";
	String STRING_IS_TOKEN_EXPIRED 						= "isTokenExpired";
	String REQUEST_BEAN 								= "requestBean";
	String RESPONSE_BEAN 								= "responseBean";
	String EMPTY_JSON_OBJECT 							= "{}";
	
	// Server path to display image from api end point
	String DEFAULT_USER_IMG_SERVER_PATH 				= "/userimg/**";
	
	// Authorization constant
	String AUTHORIZATION_HEADER 						= "Authorization";
	String AUTHORIZATION_BEARER 						= "Bearer ";
	String TOKEN 										= "token";
	
	final int ENABLE 									= 1;
	final int DISABLE 									= 0;
	
	final int ON 										= 1;
	final int OFF 										= 0;
	
	String STATUS_ON 									= "ON";
	String STATUS_OFF 									= "OFF";
	
	final int ACTIVE 									= ENABLE;
	final int DEACTIVE 									= DISABLE;
	
	final int STATUS_FAILD								=500;
	final int STATUS_SUCCESS							=200;
	final int STATUS_UNAUTHORIZED						=401;
	
	//String EMAIL_TITLE 									= PropertyReader.getEmailSenderName();
	
	long TIME_24_HOURS 									= (long)24*60*60*1000;
	long TIME_15_MINUTE 								= (long)15*60*1000;
	
	final int AUTH_TYPE_EMAIL 							= 1;
	final int AUTH_TYPE_CONTACTNUMBER 					= 2;
	
	final int OTP_ACTION_FORGOTPASSWORD 				= 1;
	final int OTP_ACTION_FORGOTPASSWORD_SUCCESS 		= -1;
	
	//final int MAX_OTP_RETRY 							= PropertyReader.getMaxOtpRetryLimit();
	
	final int DEVICE_ACTION_UPDATE                      = 1;
	final int DEVICE_ACTION_DELETE                      = 2;
	
	//final float MAX_FILE_UPLOAD_SIZE 					= PropertyReader.getMaxFileUploadSize();
	
	//int THUMNAIL_WIDTH									= PropertyReader.getThumnailWidth();
	//int THUMNAIL_HEIGHT									= PropertyReader.getThumnailHeight();
	
	//final List<String> validImageTypeList 				= Arrays.asList(PropertyReader.getValidImageUploadExtension());
	//String USER_ACCOUNT_VERIFY_LINK 					= PropertyReader.getUserAccountVerifyLink();
	//String DEFAULT_USER_IMG_PATH 						= PropertyReader.getUserProfileImagePath();
	//final int MAX_FAVRT_DEVICE_ALLOW                    = PropertyReader.getMaxFavrtDeviceAllowSize();
	
	final int DEFAULT_KEY_ID							= -1;
	
	int REPORT_TYPE_BY_DATE = 1;
	int REPORT_TYPE_BY_WEEK = 2;
	int REPORT_TYPE_BY_MONTH = 3;
	int REPORT_TYPE_BY_YEAR = 4;
	
	/**
	 * Enum for user profile.
	 * @author vishal	
	 *
	 */
	enum PROFILE {
		ADMIN(1,"Admin"),USER(2,"User");
		
		private final int code;
		private final String sProfile;
		
		private PROFILE(int code,String value) {
			this.code=code;
			this.sProfile = value;
		}
	
		public String getsProfile() {
			return sProfile;
		}

		public int getCode() {
			return code;
		}

		@Override
		public String toString() {
			return sProfile;
		}
	}
	
	/**
	 * Enum to display user authorization status by admin.
	 * @author vishal
	 *
	 */
	enum ADMIN_VERIFICATION {
		PENDING(1,"Pending"),ACCEPTED(2,"Accepted"),REJECTED(3,"Rejected");
		
		private final int code;
		private final String profile;
		
		private ADMIN_VERIFICATION(int code,String value) {
			this.code=code;
			this.profile = value;
		}
	
		public String getProfile() {
			return profile;
		}
		
		public int getCode() {
			return code;
		}

		@Override
		public String toString() {
			return profile;
		}
	}
	
	/**
	 * Enum to display otp process status.
	 * @author vishal
	 *
	 */
	enum OTP_STATUS {
		PENDING(1,"Pending"),PROCESSED(2,"Processed"),LIMITEXCEED(3,"Limit Exceed"),EXPIRED(4,"Expired");
		
		private final int code;
		private final String profile;
		
		private OTP_STATUS(int code,String value) {
			this.code=code;
			this.profile = value;
		}
	
		public String getProfile() {
			return profile;
		}
		
		public int getCode() {
			return code;
		}

		@Override
		public String toString() {
			return profile;
		}
	}
	
	enum ACCOUNT_STATUS{
		NEW_USER("New User"),SUSPENDED("Suspended"),APPROVED("Approved"),REJECTED("Rejected");
		
		private final String status;
		
		private ACCOUNT_STATUS(String value) {
			this.status = value;
		}

		public String getStatus() {
			return status;
		}
	}
	
	public static enum LOG_LEVEL {
		DEBUG,ERROR,INFO,TRACE,WARN,ALL;
	}
}
