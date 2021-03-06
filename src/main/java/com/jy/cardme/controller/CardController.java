package com.jy.cardme.controller;

import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.components.commons.StatusCode;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/cardme/api/v1/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("")
    public ResponseEntity<String> useCard(@ModelAttribute @Valid final CardDto.UseReq cardUseReq) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/svg+xml"));
        headers.setCacheControl(CacheControl.noCache());
        final String cardSvg = cardService.useCard(cardUseReq);
        return new ResponseEntity<>(cardSvg, headers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity createCard(@RequestBody @Valid final CardDto.CreateReq cardCreateReq) {
        final CardDto.Info data = cardService.createCard(cardCreateReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.SIGN_CARD_SUCCESS)
                .httpStatus(StatusCode.CREATED)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity deleteCard(@ModelAttribute @Valid final CardDto.DeleteReq cardDeleteReq) {
        final CardDto.Info data = cardService.deleteCard(cardDeleteReq);
        final DefaultRes res = DefaultRes.builder()
                .message(ResponseMessage.DELETE_CARD_SUCCESS)
                .httpStatus(StatusCode.OK)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(data).build();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/preview")
    public ResponseEntity previewCard(@RequestBody @Valid final CardDto.PreviewReq cardPreviewReq) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/svg+xml"));
        headers.setCacheControl(CacheControl.noCache());
        final String cardSvg = cardService.previewCard(cardPreviewReq);
        return new ResponseEntity<>(cardSvg, headers, HttpStatus.OK);
    }
}
