package com.example.KafkaService;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ErrorLog {
    @Id
    @GeneratedValue(generator = "ErrorLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ErrorLog", sequenceName = "ERROR_LOG_ID_SEQ")
    private Long id;

    @Column(name = "DATA", nullable = false)
    private LocalDateTime date;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;


}
