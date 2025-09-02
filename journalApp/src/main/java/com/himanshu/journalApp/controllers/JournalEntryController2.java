package com.himanshu.journalApp.controllers;

import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.service.JournalEntryService;
import com.himanshu.journalApp.service.UserRepoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JournalEntryController2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserRepoService userRepoService;

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getentry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users curruser= userRepoService.getUserByName(authentication.getName());
        List<JournalEntry> list = curruser.getListByUser();
        if (!list.isEmpty()) {
            curruser.setListByUser(list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users curruser= userRepoService.getUserByName(authentication.getName());
        List<JournalEntry> list = curruser.getListByUser().stream().filter(x -> x.getId().equals(id)).toList();
        if(!list.isEmpty()){
            Optional<JournalEntry> entry= journalEntryService.getById(id);
            return new ResponseEntity<>(entry , HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/addEntry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if(journalEntryService.saveEntry( myEntry, authentication.getName())!=null){
           return  new ResponseEntity<>(myEntry , HttpStatus.CREATED);
       }
       else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

   

    @DeleteMapping("/delte/{myid}")
    public ResponseEntity<String> deleteById(@PathVariable ObjectId myid ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(journalEntryService.deleteById(myid , authentication.getName())){
            return  new ResponseEntity<>("Entry Deleted" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Some error" , HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{myid}")
    public ResponseEntity<?> replace(@RequestBody JournalEntry newentry , @PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users curruser= userRepoService.getUserByName(authentication.getName());
        List<JournalEntry> list = curruser.getListByUser().stream().filter(x -> x.getId().equals(myid)).toList();
        if(!list.isEmpty()){
            Optional<JournalEntry> entry= journalEntryService.getById(myid);
            if (entry.isPresent()) {
                JournalEntry existing = entry.get();
                if (newentry != null && newentry.getTitle() != null && !newentry.getTitle().isEmpty()) {
                    existing.setTitle(newentry.getTitle());
                }
                if(newentry!=null && newentry.getContent()!=null && !newentry.getContent().isEmpty()){
                    existing.setContent(newentry.getContent());
                }

                JournalEntry updated = journalEntryService.saveEntryAndReturn(existing);
                return new ResponseEntity<>(updated, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
