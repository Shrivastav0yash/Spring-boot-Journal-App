package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {



    @GetMapping
    public List<JournalEntry> getAll(){ //localhost:8080/journal
        return null;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){ //localhost:8080/journal

        return true;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry delJournalEntryById(@PathVariable long myId){ //localhost:8080/journal
        return null;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable long myId, @RequestBody JournalEntry myEntry){ //localhost:8080/journal
        return null;
    }
}
