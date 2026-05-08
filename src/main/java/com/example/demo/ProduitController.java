package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    final ProduitRepository produitRepository;

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // POST /api/produits
    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitRepository.save(produit), HttpStatus.CREATED);
    }

    // GET /api/produits
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        return new ResponseEntity<>(produitRepository.findAll(), HttpStatus.OK);
    }

    // GET /api/produits/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        return new ResponseEntity<>(produit, HttpStatus.OK);
    }

    // PUT /api/produits/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produit> majProduit(@PathVariable long id, @RequestBody Produit updated) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        produit.setNom(updated.getNom());
        produit.setPrix(updated.getPrix());
        produit.setStock(updated.getStock());
        return new ResponseEntity<>(produitRepository.save(produit), HttpStatus.OK);
    }

    // DELETE /api/produits/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable long id) {
        produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        produitRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
