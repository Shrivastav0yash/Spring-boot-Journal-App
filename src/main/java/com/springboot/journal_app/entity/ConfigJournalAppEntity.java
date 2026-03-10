package com.springboot.journal_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "config_key")
    private String key;
    @Column(name = "config_value")
    private String value;
}
