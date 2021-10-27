package com.jy.cardme.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CardServiceImpl implements CardService{
    @Override
    public String generatingCard() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/testsvg.svg");
        return IOUtils.toString(in,"UTF-8");
    }
}
