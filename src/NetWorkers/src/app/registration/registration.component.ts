import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RegistrationService } from '../shared/services/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;

  constructor(private registrationService: RegistrationService, private fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      phonenumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    
  }

  submitRegistrationForm(): void {
    if (this.registrationForm.valid) {
      const userData = this.registrationForm.value;
      this.registerUser(userData);
    }
  }

  private registerUser(userData: any): void {
    this.registrationService.registerUser(userData).subscribe(
      response => {
        console.log('Registration successful:', response);
      },
      error => {
        console.error('Registration failed:', error);
      }
    );
  }
  
}
