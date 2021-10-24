package com.jy.cardme.components;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String WRONG_PASSWORD = "올바르지 않은 비밀번호";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String USER_NOT_FOUND = "회원을 찾을 수 없습니다";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String BAD_REQUEST = "잘못된 요청";
    public static final String ARGUMENT_NOT_VALID = "잘못된 매서드 매개변수";
    public static final String WRONG_TYPE_TOKEN = "잘못된 타입의 토큰";
    public static final String EXPIRED_TOKEN = "만료된 토큰";
    public static final String UNSUPPORTED_TOKEN = "지원하지 않는 토큰";
    public static final String WRONG_TOKEN = "토큰값이 비었거나 잘못된 토큰";
    public static final String UNKNOWN_TOKEN_ERROR = "알수없는 토큰 오류";
    public static final String NOT_FOUND_TOKEN_HEADER = "요청 헤더에 X-AUTH-TOKEN 필드가 없습니다.";
    public static final String NOT_AUTHORIZATION = "해당 요청에 권한이 없는 사용자입니다";
}
