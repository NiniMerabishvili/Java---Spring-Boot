import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResult, UserType, Users } from './models/users';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  private isAdmin = false;
  private baseAddress = "http://localhost:3333/api/registrations";
  
  isSignedIn() {
    return this.isAuthenticated;
  }

//   login(email: string, password: string) {
//     const loginRequest = { email: email, password: password };

//     return this.httpClient.post<AuthResult>(`${this.baseAddress}/login`, loginRequest)
//       .pipe(
//         tap(
//           (authResult: AuthResult) => {
//             if (authResult.user) {
//               console.log("Login successful. User type:", authResult.user.userType);
//             } else {
//               console.error("User object is null in the response");
//             }
//           },
//           (error) => {
//             console.error("Login failed:", error);
//           }
//         )
//       );
// }

login(email: string, password: string): Observable<AuthResult> {
  const loginRequest = { email: email, password: password };

  return this.httpClient.post<AuthResult>(`${this.baseAddress}/login`, loginRequest)
    .pipe(
      catchError((error) => {
        console.error("Login failed:", error);
        return throwError(error);
      })
    );
}
  logout() {
    this.isAuthenticated = false;
    this.isAdmin = false;

    return of(true);
  }

  isAdminUser() {
    return this.isAdmin;
  }

  constructor( private httpClient: HttpClient ) { }

}