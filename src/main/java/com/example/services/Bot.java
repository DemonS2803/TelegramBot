package com.example.services;

import com.example.config.BotConfig;
import com.example.entities.User;
import com.example.util.TelegramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    BotConfig botConfig;

    @Autowired
    UserService userService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    public void send(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null & message.hasText()) {
            switch (message.getText().split(" ")[0]) {
                case "/help": {
                    send(message, TelegramUtil.HELP);
                    break;
                }
                case "/start": {
                    startCommandReceived(message);
                    break;
                }
                case "/reg": {
                    userService.saveUser(message.getChatId(), message.getChat().getFirstName());
                    send(message, "registration success");
                    break;
                }
                case "/set": {
                    setCommandReceived(message);
                    break;
                }
                case "hi": {
                    send(message, String.format("Hi, {}", message.getChat().getFirstName()));
                    break;
                }
                case "by": {
                    send(message, "spoky noky su4ka");
                    break;
                }
                default: {
                    send(message, "i really ne vDuPlYaU");
                }
            }
        }
    }

    private void setCommandReceived(Message message) {
        try {
            String[] commands = message.getText().split("");
            User user = userService.findUserByChatId(message.getChatId());
            System.out.println(user);
            switch (commands[1]) {
                case "contacts": {
                    userService.updateUserContacts(user, commands[2]);
                    break;
                }
                case "username": {
                    userService.updateUsername(user, commands[2]);
                    break;
                }
            }
            send(message, "Your data has been updated");
        } catch (Exception e) {
            send(message, "Oooops, something went wrong");
        }
    }

    private void startCommandReceived(Message message) {
        send(message, "nu che narod, pognali naxui");
    }
}
