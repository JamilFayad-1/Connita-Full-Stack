import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publication } from './Publication';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {
  private apiUrl = 'http://localhost:8080/getPostsAdmin';
  apiDelete = 'http://localhost:8080/deletePostAdmin'
  constructor(private http: HttpClient) { }

  getPublications(): Observable<Publication[]> {
    return this.http.get<Publication[]>(this.apiUrl);
  }
  deletePost(id:Number ){
    return this.http.delete<void>(`${this.apiDelete}/${id}`);
  }
  
}