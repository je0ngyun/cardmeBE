package com.jy.cardme.service;

import java.io.IOException;

public interface CardService {
    /* 공통 서비스
     * 카드정보 DB 저장
     * DB 기반으로 정보를 조합하여 카드 Svg 생성
     */
    String generatingCard() throws IOException;
}
