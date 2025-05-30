import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserType } from '../shared/models/users';
import { AuthService } from '../shared/auth.service';
import { RegistrationService } from '../shared/services/registration.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  loginFailed = false;

  form = new FormGroup ({
    user: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  onLogin() {
    if (this.form.valid) {
      this.loginFailed = false;
      this.authService.login(this.form.value.user!, this.form.value.password!).subscribe((success) => {
        if (success) {
          if (this.authService.isAdminUser()) {
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/customer']);
          }
        } else {
          this.loginFailed = true;
        }
      });
    }
  }

  // onLogin() {
  //   if (this.form.valid) {
  //     this.authService.login(this.form.value.user!, this.form.value.password!).subscribe(
  //       (authResult) => {
  //         const user = authResult.user;
  
  //         if (user) {
  //           console.log("User type:", user.userType);
  
  //           if (user.userType === UserType.Admin) {
  //             console.log("Navigating to admin dashboard");
  //             this.router.navigate(['/admin']);
  //           } else if (user.userType === UserType.Customer) {
  //             console.log("Navigating to customer dashboard");
  //             this.router.navigate(['/customer']);
  //           } else {
  //             console.error("Unknown user type:", user.userType);
  //             this.loginFailed = true;
  //           }
  //         } else {
  //           console.error("User object is null in the response");
  //           this.loginFailed = true;
  //         }
  //       },
  //       (error) => {
  //         console.error("Error during login", error);
  //         this.loginFailed = true;
  //       }
  //     );
  //   }
  // }
  
  
  

// onLogin() {
//   if (this.form.valid) {
//     this.authService.login(this.form.value.user!, this.form.value.password!).subscribe(
//       (success) => {
//         if (UserType.Admin) {
//           this.router.navigate(['/admin']);
//         } else if (UserType.Customer) {
//           this.router.navigate(['/customer']);
//         } else {
//           // This part may need to be adjusted based on the actual response from the AuthService
//           this.loginFailed = true;
//           console.log("Register First");
//         }
//       },
//       (error) => {
//         this.loginFailed = true;
//         console.error("Error during login", error);
//       }
//     );
//   }
// }

  onLogout() {
    this.authService.logout().subscribe(() => {
      this.router.navigate(['/']); 
    });
  }

  // loadRecords(){
  //   this.registrationService.().subscribe(d => {
  //     this.students = d;
  //   });
  // }

  constructor(private authService: AuthService, private registrationService: RegistrationService, private router: Router) {}

  ngOnInit(): void {}
}