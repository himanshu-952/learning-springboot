package com.himanshu.journalApp.service;

import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepo;
    @Autowired
    private UserRepoService userRepoService ;

    @Transactional
    public JournalEntry saveEntry(JournalEntry journalEntry , String user) {

        journalEntry.setTime(LocalDateTime.now());
        Users curruser= userRepoService.getUserByName(user);
        if (curruser == null) return null;
       JournalEntry entry= journalEntryRepo.save(journalEntry);
        curruser.getListByUser().add(entry);
        userRepoService.saveEntry(curruser);
        return journalEntry;
    }
    public JournalEntry saveEntry(JournalEntry journalEntry ) {

        journalEntryRepo.save(journalEntry);
        return journalEntry;
    }



    public Optional<JournalEntry> getById(ObjectId myid) {
        return journalEntryRepo.findById(myid);
    }


@Transactional
    public boolean deleteById(ObjectId myid , String user) {
        Users curruser= userRepoService.getUserByName(user);
       boolean delete= curruser.getListByUser().removeIf(x-> x.getId().equals(myid));
       if(delete){
           userRepoService.saveEntry(curruser);
           journalEntryRepo.deleteById(myid);
           return  true;
       }
       else{
           return  false;
       }



    }

    public JournalEntry saveEntryAndReturn(JournalEntry entry) {
        return journalEntryRepo.save(entry);
    }
}
