package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeProduitController {

    final CommandeRepository commandeRepository;
    final ProduitRepository produitRepository;
    final CommandeProduitRepository commandeProduitRepository;

    public CommandeProduitController(CommandeRepository commandeRepository,
                                     ProduitRepository produitRepository,
                                     CommandeProduitRepository commandeProduitRepository) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
        this.commandeProduitRepository = commandeProduitRepository;
    }

    // POST /api/commandes/{id}/produits/{produitId}
    @PostMapping("/{id}/produits/{produitId}")
    public ResponseEntity<CommandeProduit> ajouterProduit(
            @PathVariable long id,
            @PathVariable long produitId,
            @RequestParam int quantite) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande introuvable"));

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        // Vérification stock
        if (produit.getStock() < quantite) {
            throw new RuntimeException("Stock insuffisant");
        }

        // Créer la ligne
        CommandeProduit ligne = new CommandeProduit();
        ligne.setCommande(commande);
        ligne.setProduit(produit);
        ligne.setQuantite(quantite);
        ligne.setPrixUnitaire(produit.getPrix());

        // Décrémenter le stock
        produit.setStock(produit.getStock() - quantite);
        produitRepository.save(produit);

        return new ResponseEntity<>(commandeProduitRepository.save(ligne), HttpStatus.CREATED);
    }

    // GET /api/commandes/{id}/produits
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<CommandeProduit>> listerProduits(@PathVariable long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande introuvable"));

        return new ResponseEntity<>(commande.getLignes(), HttpStatus.OK);
    }

    // GET /api/commandes/{id}/total
    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getMontantTotal(@PathVariable long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException("Commande introuvable"));

        return new ResponseEntity<>(commande.getMontantTotal(), HttpStatus.OK);
    }
}
