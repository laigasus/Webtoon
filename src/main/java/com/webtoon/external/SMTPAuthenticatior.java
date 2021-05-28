package com.webtoon.external;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 
public class SMTPAuthenticatior extends Authenticator{
  //펼치면 실제 이메일 계정 아이디와 비밀번호가 나옵니다
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {    
        return new PasswordAuthentication("laigasus98@gmail.com","okjaeook98");   
        //여기 이메일의 경우 구글에서 설정을 해놔야함 Google 계정 관리 -> 보안 -> 보안 수준이 낮은  앱의 액세스->사용함으로 변경
    }
}
