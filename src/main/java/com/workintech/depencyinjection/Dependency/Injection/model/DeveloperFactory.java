package com.workintech.depencyinjection.Dependency.Injection.model;

import com.workintech.depencyinjection.Dependency.Injection.SeniorDeveloper;
import com.workintech.depencyinjection.Dependency.Injection.tax.Taxable;

public class DeveloperFactory {

  public static  Developer create(Developer developer, Taxable taxable){
        Developer saveDeveloper;

      if (developer.getExperience().name().equalsIgnoreCase("JUNIOR")){
            saveDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - developer.getSalary() * taxable.getSimpleTaxRate(),
                    developer.getExperience());
        } else if (developer.getExperience().name().equalsIgnoreCase("mid")) {
            saveDeveloper = new MidDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary()-developer.getSalary()* taxable.getMiddleTaxRate(),
                    developer.getExperience());
        }else if (developer.getExperience().name().equalsIgnoreCase("senior")){
            saveDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary()  - developer.getSalary() * taxable.getUpperTaxRate(),
                    developer.getExperience());
        }else {
            saveDeveloper = null;
        }
        return saveDeveloper;
    }
}
