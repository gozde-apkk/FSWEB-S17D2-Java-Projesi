package com.workintech.depencyinjection.Dependency.Injection.validation;

import com.workintech.depencyinjection.Dependency.Injection.model.Developer;

public class DeveloperValidation {

    public static boolean isValid(int id){
        return id > 0;
    }

    public static boolean isDeveloperValid(Developer developer){
        return isValid(developer.getId()) &&
                developer.getName() != null &&
                !developer.getName().isEmpty() &&
                developer.getSalary() >25000;
    }


    public static boolean isIdValid(int id) {
        return id > 0;
    }
}
