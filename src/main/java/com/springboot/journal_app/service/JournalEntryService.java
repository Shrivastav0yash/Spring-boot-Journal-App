package com.springboot.journal_app.service;

import com.springboot.journal_app.entity.JournalEntry;
import com.springboot.journal_app.respository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(long myId){
        return journalEntryRepo.findById(myId);
    }

    public void deleteById(long Id){
        journalEntryRepo.deleteById(Id);
    }

}
