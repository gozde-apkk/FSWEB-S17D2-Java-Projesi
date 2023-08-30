package com.workintech.depencyinjection.Dependency.Injection.rest;


import com.workintech.depencyinjection.Dependency.Injection.SeniorDeveloper;
import com.workintech.depencyinjection.Dependency.Injection.mapping.DeveloperResponse;
import com.workintech.depencyinjection.Dependency.Injection.model.Developer;
import com.workintech.depencyinjection.Dependency.Injection.model.DeveloperFactory;
import com.workintech.depencyinjection.Dependency.Injection.model.JuniorDeveloper;
import com.workintech.depencyinjection.Dependency.Injection.model.MidDeveloper;
import com.workintech.depencyinjection.Dependency.Injection.tax.Taxable;
import com.workintech.depencyinjection.Dependency.Injection.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/developers")
public class DeveloperController {

    private Map<Integer, Developer> developers ;

    private Taxable taxable;

    @PostConstruct
    public void init(){
        developers = new HashMap<>();
    }

    @Autowired
    public DeveloperController(@Qualifier("developerTax") Taxable taxable) {
        this.taxable = taxable;
    }


    //CRUD OPERATIONS
    @GetMapping("/")
    public List<Developer> get(){
        //turn all developers
        return developers.values().stream().toList();
    }


    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable int id){
        if(!DeveloperValidation.isIdValid(id)){
            return new DeveloperResponse(null, "Id is not valid", 400);
        }
        if(!developers.containsKey(id)){
            return new DeveloperResponse(null,
                    "Developer with given id is not exist: " + id, 400);
        }
        return new DeveloperResponse(developers.get(id), "Success", 200);
    }

    @PostMapping("/")
    private DeveloperResponse save(@RequestBody Developer developer){
        Developer saveDeveloper =  DeveloperFactory.create(developer, taxable);

        if (saveDeveloper == null){
            return new DeveloperResponse(null,
                    "Developer with given experience is not valid    ",400);
        }
        if (developers.containsKey(developer.getId())){
            return  new DeveloperResponse(null,
                    "Developer with given id already exist:"+ developer.getId(),400);
        }
        if (!DeveloperValidation.isDeveloperValid(developer)){
            return  new DeveloperResponse(null,
                    "Developer credentials are not valid",400);
        }
        developers.put(developer.getId(), saveDeveloper );
        return new DeveloperResponse(developers.get(developer.getId()), "Successfuly", 200);
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable int id, @RequestBody Developer developer) {
        if (!developers.containsKey(id)) {
            return new DeveloperResponse(null,
                    "Developer with given id already exist:" + developer.getId(), 400);
        }
        //developers.get(id).getId();
        developer.setId(id);
        Developer updatedDeveloper = DeveloperFactory.create(developer, taxable);
        if (updatedDeveloper == null) {
            return new DeveloperResponse(null,
                    "Developer with given experience is not valid    ", 400);
        }
        if (!DeveloperValidation.isDeveloperValid(developer)) {
            return new DeveloperResponse(null, "Developer credentials are not valid", 400);
        }
        developers.put(id, updatedDeveloper);
        return new DeveloperResponse(developers.get(id), "Successfuly", 200);
        // return updatedDeveloper;
        //return developers.get(id);
    }

    @DeleteMapping("/{id}")
    public  DeveloperResponse delete(@PathVariable int id){
        if(!developers.containsKey(id)){
            return new DeveloperResponse(null,
                    "Developer with given id is not exist:" +id, 400);
        }
        Developer developer = developers.get(id);
        developers.remove(id);
        return new DeveloperResponse(developer, "Success", 200);
    }
}
