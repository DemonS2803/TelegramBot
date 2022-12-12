package com.example.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
public class BotConfig {

    private String botName = "smarty228_bot";
    private String botToken = "5957680371:AAFrx64E98lfdqC7RdghKc0UagaL07VcaH0";

}
