package com.example.repositories;

import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByChatId(Long chatId);

    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query("update User user set user = ?2 where user.chatId = ?1")
    int updateUser(Long chatId, User user);

}
