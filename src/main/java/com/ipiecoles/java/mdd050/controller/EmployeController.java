package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;


    @RequestMapping("/count")
    public Long employeCount() {

        return employeService.countAllEmploye();
    }

    @RequestMapping(value = "", params = "matricule")
    public Employe employeSearchByMatricule(@RequestParam("matricule") String matricule) throws EntityNotFoundException{

        return employeService.findMyMatricule(matricule);

    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Employe createEmploye() {

        Employe employe = new Employe() {
            @Override
            public Double getPrimeAnnuelle() {
                return null;
            }
        };


        employeService.creerEmploye(employe);

        return employe;
    }


    @RequestMapping(value = "", params = "page")
    public Page<Employe> employeSortableList(@RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size,
                                             @RequestParam("sortProperty") String sortProp,
                                             @RequestParam("sortDirection") String sortDir) {


        return employeService.findAllEmployes(page, size, sortProp, sortDir);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employe getEmployeById(@PathVariable("id") Long id) throws EntityNotFoundException {

        return employeService.findById(id);


    }
}
