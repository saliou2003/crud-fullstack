package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients/{id}/commandes") //Toutes les commandes d'un client.
public class CommandeController {

    final CommandeRepository commandeRepository;

    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getALLCommandsByClient(@PathVariable long id){

        return new ResponseEntity<>(commandeRepository.findByClientId(id), HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Commande> getCommandById(@PathVariable long Id){
        Optional<Commande> commande = commandeRepository.findById(Id);
        if(commande.isPresent()){
            return new ResponseEntity<>(commande.get(), HttpStatus.OK);
        }
        throw new CommandeNotFoundException("La commande n'est pas trouvée");
    }

    @PostMapping
    public ResponseEntity<Commande> CreatedCommande(@RequestBody Commande commande){

        Commande createdCommande = commandeRepository.save(commande);

        return  new ResponseEntity<>(createdCommande,HttpStatus.OK);

    }

    @PutMapping("/{Id}")
    public ResponseEntity<Commande> UpdatedCommande(@RequestBody Commande commande , @PathVariable long Id){

        Optional<Commande> commandeInfo =commandeRepository.findById(Id);

        if(commandeInfo.isPresent()){
            Commande existedCommande = commandeInfo.get();
            existedCommande.setStatut(commande.getStatut());
            Commande upCommande = commandeRepository.save(existedCommande);
            return new ResponseEntity<>(upCommande,HttpStatus.OK);
        }
        throw new CommandeNotFoundException("La commande n'est pas trouvée !");
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> DellCommande(@PathVariable long Id){

        Optional<Commande> commande = commandeRepository.findById(Id);

        if(commande.isPresent()){

                commandeRepository.delete(commande.get());
                return new ResponseEntity<>(HttpStatus.OK);

        }
        throw new CommandeNotFoundException("La commande n'est pas trouvée");

    }
}
