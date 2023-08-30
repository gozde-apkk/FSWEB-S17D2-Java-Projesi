package com.workintech.depencyinjection.Dependency.Injection.model;

import java.util.Objects;

public class Developer {

    private int id;
    private String name;
    private Double salary ;
    private  Experience experience;

    public Developer(int id, String name, Double salary, Experience experience) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience( Experience experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer developer)) return false;
        return getId() == developer.getId() && Objects.equals(getName(), developer.getName()) && Objects.equals(getSalary(), developer.getSalary()) && getExperience() == developer.getExperience();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSalary(), getExperience());
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", experience=" +experience +
                '}';
    }
}
