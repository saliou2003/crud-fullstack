import { Component, OnInit, ChangeDetectorRef  } from '@angular/core';
import { Personne as PersonneService } from '../../services/personne';
import { Personne as PersonneModel } from '../../models/personne';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-personnes',
  imports: [MatTableModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, FormsModule, CommonModule],
  templateUrl: './personnes.html',
  styleUrl: './personnes.css',
})
export class Personnes implements OnInit {

  persons: PersonneModel[] = [];
  newPersonne: any = { nom: '', prenom: '', tel: '', ville: '' };
  selectedPersonne: any = null;
  errorMessage = '';
  displayedColumns = ['nom', 'prenom', 'tel', 'ville', 'actions'];

  constructor(private personService: PersonneService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(){
    this.personService.getPersons().subscribe({
      next: (data) => {
        this.persons = data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la récupération des personnes';
        console.log(err);
      }

    })
  }

  createPerson(){
    this.personService.createPersonne(this.newPersonne).subscribe({
      next: () => {
         this.ngOnInit();
         this.newPersonne = { nom: '', prenom: '', tel: '', ville: '' };
         this.cdr.detectChanges();  
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la création de la personne';
        console.log(err);
      }
    })
  }

  updatePerson(){
    this.personService.updatePersonne(this.selectedPersonne.id,this.selectedPersonne).subscribe({
      next: () => {
        this.ngOnInit();
         this.selectedPersonne = null;
         this.cdr.detectChanges();  
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la modification des données de la personne';
        console.log(err);
      }
    })
  }

  deletePerson(id: number){
    this.personService.deletePersonne(id).subscribe({
      next: () => {
        this.ngOnInit();
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la suppression des données de la personne';
        console.log(err);
      }
    })
  }

  selectPersonne(personne: any) {
    this.selectedPersonne = { ...personne };
  }
  
}
