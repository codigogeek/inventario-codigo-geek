package com.inv.inventario.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.inv.inventario.Models.Propietario;
import com.inv.inventario.Services.PropietarioService;

@RestController
@RequestMapping("/propietario")
public class PropietierController {

    @Autowired 
    private PropietarioService propietarioService;

    @GetMapping("/{id}")
    public Propietario getById(@PathVariable int id ){
        Propietario  propietario = null;

        try{
            propietario = propietarioService.getById(id).get();
        }
        catch
        (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Propietario no encontrado");
        }
        return propietario;
    }

    @PostMapping(value="",consumes = "*/*", produces = "application/json")
    public Propietario create(@RequestBody Propietario propietario){

        Optional<Propietario> find = null;
        
        try{
            find = propietarioService.getById(propietario.getIdPropietario());
        }
        catch(Exception e){
            System.err.println(e);
        }

        if(find!=null && !find.isEmpty()){
            return find.get();
        }

       propietarioService.create(propietario);
  
        return propietario;
    }
}
