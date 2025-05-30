// registration.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private url = 'http://localhost:3333/api/registrations';

  constructor(private httpClient: HttpClient) {}

  authenticate(username: string, password: string): Observable<any> {
    // Make a request to your backend to check username and password
    const body = { username, password };
    return this.httpClient.post(`${this.url}/authenticate`, body);
  }

  registerUser(userData: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.post(this.url, userData, { headers });
  }

  checkCredentials(username: string, password: string): Observable<boolean> {
    const loginData = { email: username, password: password };

    return this.httpClient.post<boolean>(`${this.url}/login`, loginData);
  }
  // public getRecords(): Observable<Registration[]> {

  //   return this.httpClient.get<Registration[]>(this.url);
  // }
}
