package com.example.entities;


import com.example.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    private BigDecimal cost;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User executor;
    @CreationTimestamp
    private LocalDateTime created;
    private LocalDateTime finished;

}
