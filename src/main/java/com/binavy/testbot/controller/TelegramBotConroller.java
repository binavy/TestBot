package com.binavy.testbot.controller;

import com.binavy.testbot.TestBotApplication;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramBotConroller {
    private  static Logger logger = LoggerFactory.getLogger(TelegramBotConroller.class);
    private final long ChatId = -712128410l;
    @PostMapping("/postmsg")
    @CrossOrigin("*")
    public String postMessage(@RequestBody MessageReq req) {
        logger.info(String.format("%s",  req.getMessage()));
        SendResponse response = TestBotApplication.bot.execute(new SendMessage(ChatId, "|username| " + req.getMessage()));
        return "OK";
    }

    @PostMapping("/getmsg")
    @CrossOrigin("*")
    public String getMessage() {
        //用户判断
        return TestBotApplication.queue.remove().getMessage();
    }
}
