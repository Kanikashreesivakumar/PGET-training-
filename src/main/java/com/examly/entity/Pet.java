package com.examly.entity;

public class Pet {
    private int petid;
    private String name;
    private String type;
    private String breed;
    private int age;
    private boolean adopted;

    public Pet() {
    }

    public Pet(int petid, String name, String type, String breed, int age, boolean adopted) {
        this.petid = petid;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.adopted = adopted;
    }

    public Pet(String name, String type, String breed, int age, boolean adopted) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.adopted = adopted;
    }

    public int getPetid() {
        return petid;
    }

    public void setPetid(int petid) {
        this.petid = petid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public String toString() {
        return "ID: " + petid + "\n" +
                "Name: " + name + "\n" +
                "Type: " + type + "\n" +
                "Breed: " + breed + "\n" +
                "Age: " + age + "\n" +
                "Adopted: " + adopted;
    }
}
