package com.ebatta.gclp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
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

    @RequestMapping(value = "/changerequest/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable(value="id") int id, Model model)
            throws ChangeRequestNotFoundException {
        model.addAttribute("changeRequest", service.findById(id));
        model.addAttribute("mode", "view");
        return "changerequest/view";
    }

    @RequestMapping(value = "/changerequest/{id}/delete", method = RequestMethod.POST)
    public String deleteById(@PathVariable(value="id") int id)
            throws ChangeRequestNotFoundException {
        service.deleteById(id);
        return "redirect:/changerequests";
    }

    @ExceptionHandler({ChangeRequestNotFoundException.class, DataAccessException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView handleChangeRequestNotFoundException(
        Exception exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.setViewName("error/cr_notfound");
        return mav;
    }
}
