package com.github.korsejong.flight.service.journal;

import com.github.korsejong.flight.domain.journal.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository repository;
}
