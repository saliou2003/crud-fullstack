package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersons(){
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personne> CreatePerson(@RequestBody Personne personne){
        Personne personCreated = personRepository.save(personne);
        if(personCreated.getNom().length() <= 2){
            throw new IllegalArgumentException("Taille du nom est très petite !");
        }
        return new ResponseEntity<>(personCreated,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonById(@PathVariable long id){
        Optional<Personne> personne = personRepository.findById(id);
        return personne.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseThrow(() -> new PersonNotFoundExcepttion("personne not found"));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> UpdatedPerson(@PathVariable long id , @RequestBody Personne personInfo){

        Optional<Personne> personne = personRepository.findById(id);
        if(personne.isPresent()){
            Personne existedPerson = personne.get();
            existedPerson.setVille(personInfo.getVille());
            existedPerson.setTel(personInfo.getTel());

            Personne updtedPerson = personRepository.save(existedPerson);
            return new ResponseEntity<>(existedPerson, HttpStatus.OK);

        }
       throw new PersonNotFoundExcepttion("personne not found");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletePerson(@PathVariable long id) {

        Optional<Personne> personne = personRepository.findById(id);

        if(personne.isPresent()){
            personRepository.delete(personne.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new PersonNotFoundExcepttion("personne not found");

    }

}
