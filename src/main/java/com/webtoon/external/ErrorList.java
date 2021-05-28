package com.webtoon.external;

public class ErrorList {
	public static String errMsg;

	public static String printError(int errorCode) {
		switch (errorCode) {
		case 400:
			errMsg = "잘못된 요청";
			break;
		case 401:
			errMsg = "접근 권한 없음";
			break;
		case 403:
			errMsg = "접근 금지";
			break;
		case 404:
			errMsg = "페이지를 찾을 수 없음";
			break;
		case 405:
			errMsg = "메소드 허용 안됨";
			break;
		case 407:
			errMsg = "프록시 인증 필요";
			break;
		case 408:
			errMsg = "요청시간 초과";
			break;
		case 409:
			errMsg = "호환되지 않는 파일";
			break;
		case 413:
			errMsg = "요청된 문서가 서버가 다룰수 있는 크기보다 큼";
			break;
		case 414:
			errMsg = "요청한 URL이 너무 김";
			break;
		case 500:
			errMsg = "내부 서버 오류";
			break;
		case 501:
			errMsg = "요구되는 기능이 서버에 없음";
			break;
		case 502:
			errMsg = "게이트웨이 상태 나쁨";
			break;
		case 503:
			errMsg = "서비스를 사용할 수 없음";
			break;
		case 505:
			errMsg = "해당 HTTP버전을 지원 안함";
			break;
		default:
			errMsg = "알 수 없는 오류 발생";
			break;
		}
		return errMsg;
	}
}
