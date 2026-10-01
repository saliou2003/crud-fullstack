import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Personne as PersonneModel } from '../models/personne';

@Injectable({
  providedIn: 'root',
})
export class Personne {

  private url = 'http://localhost:8080/api/persons';

  constructor(private http: HttpClient) {}

  getPersons(): Observable<any[]> {

    return this.http.get<[]>(this.url);
  }

  createPersonne(personne: PersonneModel): Observable<any> {

    return this.http.post<any>(this.url,personne);
  }

  updatePersonne(id: number, personne:PersonneModel): Observable<any> {

    return this.http.put(`${this.url}/${id}`,personne);
  }

  deletePersonne(id: number): Observable<any> {

    return this.http.delete(`${this.url}/${id}`);
  }
}
