package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    // Дополнить метод createPet() таким образом, чтобы в ответ на запрос возвращался текст об успешном создании питомца;
    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        return "new Pet was successfully created: " + " type: " + pet.getType() + " with name: " + pet.getName() + " Age: " + pet.getAge();
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

//    {
//        "id": 1
//    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

   // Реализовать удаление и изменение питомцев используя аннотации @DeleteMapping и @PutMapping
    @DeleteMapping(value = "/deletePet/{userId}")
    public String deletePet(@PathVariable String userId) {
        int id = Integer.parseInt(userId);
        String text = petModel.getFromList(id) + " was successfully deleted!";
        petModel.deletePet(id);
        return text;

    }

    @PutMapping(value = "/update/{userId}", consumes = "application/json")
    public String updatePet(@PathVariable String userId, @RequestBody Pet pet) {
        int id = Integer.parseInt(userId);
        String text = petModel.getFromList(id) + " was successfully updated!";
        petModel.editPet(id, pet);
        return text;
    }

}
