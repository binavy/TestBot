package com.binavy.testbot;

import com.binavy.testbot.controller.UserMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootApplication
public class TestBotApplication implements CommandLineRunner {

    public static Queue<UserMessage> queue = new ConcurrentLinkedQueue<>();
    public static TelegramBot bot = new TelegramBot("5917484949:AAFIDBCjyffm7U2ZuLMOeYnN09HIFx7JFJA");
    public static void main(String[] args) {
        SpringApplication.run(TestBotApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        bot.setUpdatesListener(updates -> {
            // ... process updates
            // return id of last processed update or confirm them all


            if(!updates.isEmpty()) {
                for (var update : updates) {
                    // Send messages
                    if(update.message() != null ) {
                        if(update.message().text().contains("/reply")) {
                            //没做错误处理
                            System.out.println(update.message().text());
                            String username = update.message().text().split("|")[1];
                            String reply = update.message().text().split("|")[2];
                            UserMessage um = new UserMessage();
                            um.setUsername("username");
                            um.getMessage();
                            queue.add(um);
                        }
                    }
                }
            }


            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
