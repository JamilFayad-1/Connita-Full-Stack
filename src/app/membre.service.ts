import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Membre} from './Membre'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class MembreService {
  private apiUrl = 'http://localhost:8080/getMembresAdmin';
  apiDelete = 'http://localhost:8080/deleteMembreAdmin';
  apiSearch = 'http://localhost:8080/searchForMembre'
  constructor(private http: HttpClient) { }

  getMembres(): Observable<Membre[]> {
    return this.http.get<Membre[]>(this.apiUrl);
  }
  deleteMembre(id:Number ){
    return this.http.delete<void>(`${this.apiDelete}/${id}`);
  }

  getMembresByUsername(search: string): Observable<Membre[]> {
    const params = { search: search };
    return this.http.get<Membre[]>(this.apiSearch, { params: params });
  }
  
}
