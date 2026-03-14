package com.springboot.journal_app.scheduler;

import com.springboot.journal_app.cache.AppCache;
import com.springboot.journal_app.entity.JournalEntry;
import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepoImpl;
import com.springboot.journal_app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepoimpl;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendMail(){
        List<User> users = userRepoimpl.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            String email = user.getEmail();
            List<String> allEntries = journalEntries.stream()
                    .map(entry -> entry.getTitle() + " : " + entry.getContent())
                    .toList();
            String entry = String.join(" ", allEntries);
            emailService.sendEmail(email, " Your Journal Enties", entry);
        }
    }

   // @Scheduled(cron = "0 0/5 * 1/1 * ? *")
    public void clearAppCache(){
        appCache.init();
    }
}
