package com.example.services;

import com.example.entities.Job;
import com.example.enums.Status;
import com.example.repositories.JobRepository;
import org.apache.catalina.realm.JAASRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserService userService;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public boolean save(String task, BigDecimal cost, Long userChatId) {
        try {
            Job job = Job.builder()
                    .task(task)
                    .cost(cost)
                    .status(Status.AVAILABLE)
                    .customer(userService.findUserByChatId(userChatId))
                    .build();
            jobRepository.save(job);
            return true;
        } catch (Exception e) {
            System.out.println("error during saving new job");
            e.printStackTrace();
            return false;
        }
    }
}
