package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.JournalEntry;
import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.service.JournalEntryService;
import com.springboot.journal_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){ //localhost:8080/journal
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
        Optional<JournalEntry> jE = journalEntryService.findById(myId);
        if(jE.isPresent()){
            return new ResponseEntity<>(jE.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){ //localhost:8080/journal
        try{
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> delJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?>  updateJournalEntryById(
            @PathVariable long myId,
            @PathVariable String userName,
            @RequestBody JournalEntry newEntry
    ){
        JournalEntry oldjournalEntry = journalEntryService.findById(myId).orElse(null);
        if(oldjournalEntry != null){
            oldjournalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldjournalEntry.getTitle());
            oldjournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldjournalEntry.getTitle());
            journalEntryService.saveEntry(oldjournalEntry);
            return new ResponseEntity<>(oldjournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
