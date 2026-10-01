import { Component,  ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink, CommonModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  user: User = { username: '', password: '', role: 'ROLE_USER' };
  errorMessage = '';

  constructor(private authService: Auth, private router: Router, private cdr: ChangeDetectorRef) {}

  register() {
    this.authService.register(this.user).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de l\'inscription';
        console.log(err);
         this.cdr.detectChanges();
      }
    });
  }
}