package com.webtoon.external;

public class ErrorList {
	public static String errMsg;

	public static String printError(Object errorCode) {
		if (errorCode.equals(400)) {
			errMsg = "잘못된 요청";
		} else if (errorCode.equals(401)) {
			errMsg = "접근 권한 없음";
		} else if (errorCode.equals(403)) {
			errMsg = "접근 금지";
		} else if (errorCode.equals(404)) {
			errMsg = "페이지를 찾을 수 없음";
		} else if (errorCode.equals(405)) {
			errMsg = "메소드 허용 안됨";
		} else if (errorCode.equals(407)) {
			errMsg = "프록시 인증 필요";
		} else if (errorCode.equals(408)) {
			errMsg = "요청시간 초과";
		} else if (errorCode.equals(409)) {
			errMsg = "호환되지 않는 파일";
		} else if (errorCode.equals(413)) {
			errMsg = "요청된 문서가 서버가 다룰수 있는 크기보다 큼";
		} else if (errorCode.equals(414)) {
			errMsg = "요청한 URL이 너무 김";
		} else if (errorCode.equals(500)) {
			errMsg = "내부 서버 오류";
		} else if (errorCode.equals(501)) {
			errMsg = "요구되는 기능이 서버에 없음";
		} else if (errorCode.equals(502)) {
			errMsg = "게이트웨이 상태 나쁨";
		} else if (errorCode.equals(503)) {
			errMsg = "서비스를 사용할 수 없음";
		} else if (errorCode.equals(505)) {
			errMsg = "해당 HTTP버전을 지원 안함";
		} else {
			errMsg = "알 수 없는 오류 발생";
		}

		return errMsg;
	}
}
