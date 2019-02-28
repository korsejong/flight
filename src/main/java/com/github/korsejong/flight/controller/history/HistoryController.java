package com.github.korsejong.flight.controller.history;

import com.github.korsejong.flight.service.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
}
