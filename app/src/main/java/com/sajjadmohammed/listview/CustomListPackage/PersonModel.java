package com.sajjadmohammed.listview.CustomListPackage;

public class PersonModel {
    private int id,age;
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PersonModel(int id, int age, String fullName) {
        this.id = id;
        this.age = age;
        this.fullName = fullName;
    }
}
