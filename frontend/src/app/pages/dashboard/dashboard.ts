import { Component,OnInit, ChangeDetectorRef } from '@angular/core';
import { Personne } from '../../services/personne'; 
import { Router } from '@angular/router';
import { Auth } from '../../services/auth'; 
import { RouterLink } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-dashboard',
  imports: [MatToolbarModule, MatCardModule, MatButtonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit{

  persons: any[]= [];
  commands: any[]= [];
  produits: any[]= [];

  errorMessage = '';

  constructor(private personService: Personne,private authService: Auth, private router: Router, private cdr: ChangeDetectorRef) {}

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

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
