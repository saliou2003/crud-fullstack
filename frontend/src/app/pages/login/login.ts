import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../services/auth';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  token = '';
  user: User = { username: '', password: '', role: 'ROLE_USER' };
  errorMessage = '';

  constructor(private authService: Auth, private router: Router, private cdr: ChangeDetectorRef) {}

  login() {
    this.authService.login(this.user).subscribe({
      next: (data) => {
        this.token=data.token;
        localStorage.setItem('token', this.token);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.errorMessage = 'Identifiant ou mot de passe incorrect';
        console.log(err);
        this.cdr.detectChanges();
      }
    });
  }
}