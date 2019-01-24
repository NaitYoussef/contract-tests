package com.parisjug.delivery.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Random;

public class LogMessage implements Serializable {

    private static final long serialVersionUID = -5857383701708275796L;

    private String message;

    public LogMessage() {

    }

    public LogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public static LogMessage randomMessage() {
        String randomMessage = RandomStringUtils.random(10, true, false);
        return new LogMessage(randomMessage);
    }
}