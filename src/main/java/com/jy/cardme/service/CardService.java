package com.jy.cardme.service;

import com.jy.cardme.dto.CardDto;

import java.io.IOException;

public interface CardService {
    /* 공통 서비스
     * 카드 사용
     * 카드 미리보기
     * 카드 생성
     * 카드 삭제
     */
    String useCard(CardDto.UseReq cardUseReq) throws IOException;

    String previewCard(CardDto.PreviewReq cardPreviewReq) throws IOException;

    CardDto.Info createCard(CardDto.CreateReq cardCreateReq);

    CardDto.Info deleteCard(CardDto.DeleteReq cardDeleteReq);
}
