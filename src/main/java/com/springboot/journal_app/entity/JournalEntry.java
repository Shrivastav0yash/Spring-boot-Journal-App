package com.springboot.journal_app.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries")
@Data
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime date;

}
