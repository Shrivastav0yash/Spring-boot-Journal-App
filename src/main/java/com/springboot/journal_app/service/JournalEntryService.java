package com.springboot.journal_app.service;

import com.springboot.journal_app.entity.JournalEntry;
import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.JournalEntryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setUser(user);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    @Transactional(readOnly = true)
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<JournalEntry> findById(long myId){
        return journalEntryRepo.findById(myId);
    }

    @Transactional
    public void deleteById(long Id){
        journalEntryRepo.deleteById(Id);
    }

    @Transactional
    public boolean existsByIdAndUser_UserName(long id, String userName){
        return journalEntryRepo.existsByIdAndUser_UserName(id,userName);
    }


}
