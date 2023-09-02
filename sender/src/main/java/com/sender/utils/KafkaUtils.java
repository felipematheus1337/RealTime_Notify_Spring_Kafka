package com.sender.utils;


import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {


    public static final String WORLD_LETTER = "worldletter";
    public static final String FINANCIAL_LETTER = "financialletter";
    public static final String CRYPTO_LETTER = "cryptoletter";
    public static final String SPORTS_LETTER = "sportsletter";

    public static final String CUSTOM_TOPIC = "customLetter";

    public static final String STORAGE_TOPIC = "storage";
}
