package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.JournalEntry;
import com.springboot.journal_app.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){ //localhost:8080/journal
        return journalEntryService.getAll();
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
       return journalEntryService.findById(myId).orElse(null);
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){ //localhost:8080/journal
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @DeleteMapping("id/{myId}")
    public boolean delJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable long myId, @RequestBody JournalEntry newEntry){ //localhost:8080/journal
        JournalEntry oldjournalEntry = journalEntryService.findById(myId).orElse(null);
        if(oldjournalEntry != null){
            oldjournalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldjournalEntry.getTitle());
            oldjournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldjournalEntry.getTitle());

        }
        journalEntryService.saveEntry(oldjournalEntry);
        return oldjournalEntry;
    }
}
