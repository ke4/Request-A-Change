package com.ebatta.gclp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebatta.gclp.service.ChangeRequestService;

@Controller
public class ChangeRequestController {

    @Autowired
    private ChangeRequestService service;

    @RequestMapping(value = "/changerequests", method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("changeRequests", service.findAll());
        return "changerequest/list";
    }

}
