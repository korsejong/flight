package com.github.korsejong.flight.controller.journal;

import com.github.korsejong.flight.service.journal.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("journal")
public class JournalController {
    @Autowired
    private JournalService journalService;
}
