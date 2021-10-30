package com.jy.cardme.controller;

import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.commons.StatusCode;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/sign")
    public ResponseEntity signCard(@RequestBody @Valid final CardDto.SignReq cardSignReq){
        final CardDto.SignRes data = cardService.signCard(cardSignReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.SIGN_CARD_SUCCESS)
                .httpStatus(StatusCode.CREATED)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
}
