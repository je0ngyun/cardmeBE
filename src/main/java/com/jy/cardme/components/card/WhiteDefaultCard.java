package com.jy.cardme.components.card;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;

public class WhiteDefaultCard extends Card {
    WhiteDefaultCard() throws IOException {
        final InputStream in = getClass().getResourceAsStream("/static/WhiteDefault.svg");
        super.doc = Jsoup.parse(IOUtils.toString(in, "UTF-8"));
    }
}
