package com.jy.cardme.components.card;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;

public class WhiteDefaultCard extends Card{
    WhiteDefaultCard() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/testsvg.svg");
        super.doc = Jsoup.parse(IOUtils.toString(in,"UTF-8"));
    }
    @Override
    public String getSvgString() {
        return super.doc.body().child(0).toString();
    }
}
