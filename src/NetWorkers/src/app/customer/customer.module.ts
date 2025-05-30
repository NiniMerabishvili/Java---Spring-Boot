import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { MyBookingsComponent } from './my-bookings/my-bookings.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    MyBookingsComponent,
    MyOrdersComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    ReactiveFormsModule
  ]
})
export class CustomerModule { }
