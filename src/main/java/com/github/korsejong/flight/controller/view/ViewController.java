package com.github.korsejong.flight.controller.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ViewController {
    @RequestMapping(method = RequestMethod.GET)
    public String indexGET(Model model) {
        return "index";
    }
}
