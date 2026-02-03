package com.examly.service;

import com.examly.entity.Pet;

import java.util.List;

public interface PetService {
    String addPet(Pet pet);

    String updatePet(Pet pet);

    String deletePet(int petid);

    Pet getPetById(int petid);

    List<Pet> getAllPets();

    List<Pet> searchByName(String name);

    List<Pet> filterByType(String type);
}
