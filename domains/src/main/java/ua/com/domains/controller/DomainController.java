package ua.com.domains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.domains.dto.DomainDTO;
import ua.com.domains.service.DomainService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping(value = "/newDomain")
    public String newDomain(Model model) {

        model.addAttribute("domains", domainService.findAll());
        model.addAttribute("domain", new DomainDTO());
        return "domainPage";
    }

    @PostMapping(value = "/saveDomain")
    public String saveDomain(@ModelAttribute DomainDTO domainDTO, @RequestParam String name) {

        domainDTO.setName(name);
        domainService.save(domainDTO);

        return "redirect:/newDomain";
    }

    @PostMapping(value = "/updateDomain")
    public String updateDomain(@Valid DomainDTO domainDTO, @RequestParam String name) {

        DomainDTO domainDTO1 = domainService.findOne(domainDTO.getId());

        domainDTO.setName(name);
        domainService.update(domainDTO);

        return "redirect:/newDomain";
    }

    @GetMapping(value = "/updateDomain/{id}")
    public String updateDomain(@PathVariable String id, Model model){
        DomainDTO domainDTO = domainService.findOne(Integer.parseInt(id));
        model.addAttribute("domain", domainDTO);
        return "updateDomain";
    }

    @GetMapping(value = "/deleteDomain/{id}")
    public String deleteProduct(@PathVariable String id) {

        domainService.delete(Integer.parseInt(id));

        return "redirect:/newDomain";
    }
}
