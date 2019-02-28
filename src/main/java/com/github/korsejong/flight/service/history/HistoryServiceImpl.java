package com.github.korsejong.flight.service.history;

import com.github.korsejong.flight.domain.history.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository repository;
}
