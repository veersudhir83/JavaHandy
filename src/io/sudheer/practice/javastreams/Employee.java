package io.sudheer.practice.javastreams;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name, double salary, boolean active) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.active = active;
    }

    private double salary;
    private boolean active;

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Name = " + this.getName() + "; Salary = " + this.getSalary();
    }
}
