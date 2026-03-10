package com.springboot.journal_app.respository;

import com.springboot.journal_app.entity.ConfigJournalAppEntity;
import com.springboot.journal_app.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigJournalAppRepo extends JpaRepository<ConfigJournalAppEntity, Long> {

}
