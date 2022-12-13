package com.example.services;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByChatId(Long chatId) {
        return userRepository.findUserByChatId(chatId);
    }

    public boolean saveUser(Long chatId, String username) {
        try {
            User user_ = userRepository.findUserByChatId(chatId);
            if (user_ != null) {
                System.out.println("user already exists");
            } else {
                User user = User.builder()
                        .username(username)
                        .chatId(chatId)
                        .contacts("")
                        .build();
                userRepository.save(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUserContacts(Long chatId, String contacts) {
        try {
            userRepository.updateUserContacts(chatId, contacts);
            return true;
        } catch (Exception e) {
            System.out.println("smth went wrong while updating user contacts " + chatId);
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUsername(Long chatId, String username) {
        try {
            userRepository.updateUsername(chatId, username);
            return true;
        } catch (Exception e) {
            System.out.println("smth went wrong while updating user name " + chatId);
            e.printStackTrace();
            return false;
        }
    }
}
