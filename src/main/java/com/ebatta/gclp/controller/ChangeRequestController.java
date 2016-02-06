package com.ebatta.gclp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ebatta.gclp.exception.ChangeRequestNotFoundException;
import com.ebatta.gclp.persistence.dto.ChangeRequestDTO;
import com.ebatta.gclp.persistence.model.ChangeRequest;
import com.ebatta.gclp.persistence.model.RequestStateEnum;
import com.ebatta.gclp.persistence.model.RequestStatePropertyEditor;
import com.ebatta.gclp.persistence.model.RiskEnum;
import com.ebatta.gclp.service.ChangeRequestService;

@Controller
public class ChangeRequestController {

    private static final Logger logger = LoggerFactory.getLogger(ChangeRequestController.class);

    @Autowired
    private ChangeRequestService service;

    @Autowired
    ChangeRequestFormValidator changeRequestFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(changeRequestFormValidator);
        binder.registerCustomEditor(RequestStateEnum.class, new RequestStatePropertyEditor());
    }

    @RequestMapping(value = "/changerequests", method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("changeRequests", createDTOs(service.findAll()));
        return "changerequest/list";
    }

    @RequestMapping(value = "/changerequest/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable(value="id") Integer id, Model model)
            throws ChangeRequestNotFoundException {
        model.addAttribute("changeRequest", createDTO(service.findById(id)));
        model.addAttribute("riskItems", RiskEnum.values());
        model.addAttribute("mode", "view");
        return "changerequest/view";
    }

    @RequestMapping(value = "/changerequest/{id}/delete", method = RequestMethod.POST)
    public String deleteById(@PathVariable(value="id") Integer id)
            throws ChangeRequestNotFoundException {
        service.deleteById(id);
        return "redirect:/changerequests";
    }

    @RequestMapping(value="/changerequest/add", method = RequestMethod.GET)
    public String showAddChangeRequest(Model model) {
        model.addAttribute("changeRequest", new ChangeRequestDTO());
        model.addAttribute("riskItems", RiskEnum.values());
        model.addAttribute("stateItems", RequestStateEnum.values());
        model.addAttribute("mode", "add");
        return "changerequest/view";
    }

    @RequestMapping(value="/changerequest/add", method = RequestMethod.POST)
    public String add(@Validated @ModelAttribute("changeRequest") ChangeRequestDTO changeRequest,
            BindingResult result, Model model) throws ChangeRequestNotFoundException {
        if (result.hasErrors()) {
            model.addAttribute("mode", "add");
            model.addAttribute("riskItems", RiskEnum.values());
            model.addAttribute("stateItems", RequestStateEnum.values());
            return "changerequest/view";
        }

        logger.debug("======= ChangeRequest: " + changeRequest);
        if (changeRequest.getId() == null) {
            service.create(changeRequest);
        } else {
            service.update(changeRequest);
        }

        return "redirect:/changerequests";
    }

    @RequestMapping(value = "/changerequest/{id}/update", method = RequestMethod.GET)
    public String updateById(@PathVariable(value="id") Integer id, Model model)
            throws ChangeRequestNotFoundException {
        ChangeRequestDTO crToUpdate = createDTO(service.findById(id));
        logger.debug("======= ChangeRequest from UPDATE: " + crToUpdate);
        model.addAttribute("changeRequest", crToUpdate);
        model.addAttribute("riskItems", RiskEnum.values());
        model.addAttribute("stateItems", RequestStateEnum.values());
        model.addAttribute("mode", "update");

        return "changerequest/view";
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

    private List<ChangeRequestDTO> createDTOs(List<ChangeRequest> models) {
        List<ChangeRequestDTO> dtos = new ArrayList<>();

        for (ChangeRequest model : models) {
            dtos.add(createDTO(model));
        }

        return dtos;
    }

    private ChangeRequestDTO createDTO(ChangeRequest model) {
        ChangeRequestDTO dto = new ChangeRequestDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setSummary(model.getSummary());
        dto.setDetail(model.getDetail());
        dto.setControl(model.getControl());
        dto.setCustomer(model.getCustomer());
        dto.setRisk(model.getRisk());
        dto.setState(model.getState());

        return dto;
    }
}
