package com.jy.cardme.components.card;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class WhiteDefaultCard extends Card{
    WhiteDefaultCard() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/testsvg.svg");
        SvgString = IOUtils.toString(in,"UTF-8");
    }
    @Override
    public String getSvgString() {
        return SvgString;
    }
}
