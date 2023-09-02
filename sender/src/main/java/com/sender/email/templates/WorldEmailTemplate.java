package com.sender.email.templates;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorldEmailTemplate extends EmailTemplate {

    @Override
    public String generateTemplate(String msg) {
        String today = getTodayDate();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("World LETTER MADE IN %s", today));
        sb.append("\n");
        sb.append("msg");
        sb.append("\n Copyright by NotifyKafka App");
        return sb.toString();
    }

    private String getTodayDate() {
        Date actualDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        return format.format(actualDate);
    }
}
