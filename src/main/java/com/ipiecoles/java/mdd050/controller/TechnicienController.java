package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.exception.EmployeException;
import com.ipiecoles.java.mdd050.model.Commercial;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController extends EmployeController {

    @Autowired
    private TechnicienService technicienService;

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Technicien updateEmploye(@PathVariable(value = "id") Long id, @RequestBody Technicien employe) {
        try {
            return this.employeService.updateEmploye(id, employe);
        } catch (EmployeException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_CHARSET_UTF_8, produces = APPLICATION_JSON_CHARSET_UTF_8, value = "")
    public Technicien createEmploye(@RequestBody Technicien employe) {
        return this.employeService.creerEmploye(employe);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmploye(@PathVariable Long id) {
        try {
            employeService.deleteEmploye(id);
        } catch (IllegalArgumentException e){
            throw new EntityNotFoundException("L'employé d'identifiant : " + id + " n'a pas été trouvé.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idTechnicien}/manager/{matricule}/add")
    @ResponseStatus(value = HttpStatus.OK)
    public Manager addManager(@PathVariable Long idTechnicien, @PathVariable String matricule) {
        return this.technicienService.addManager(idTechnicien, matricule);
    }
}