import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class Auth {

   private url = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(user: User): Observable<any> {
    return this.http.post<any>(`${this.url}/login`, user);
  }

  register(user: User): Observable<any> {
    return this.http.post<any>(`${this.url}/register`, user);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

}
