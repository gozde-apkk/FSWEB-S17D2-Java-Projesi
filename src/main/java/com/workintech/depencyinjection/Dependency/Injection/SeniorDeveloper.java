package com.workintech.depencyinjection.Dependency.Injection;

import com.workintech.depencyinjection.Dependency.Injection.model.Developer;
import com.workintech.depencyinjection.Dependency.Injection.model.Experience;

public class SeniorDeveloper extends Developer {
    public SeniorDeveloper(int id, String name, Double salary, Experience expreience) {
        super(id, name, salary, expreience);
    }
}
