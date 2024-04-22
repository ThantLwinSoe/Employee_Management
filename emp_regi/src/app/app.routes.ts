import { Routes } from '@angular/router';
import { EmployeeListsComponent } from './employee-lists/employee-lists.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';

export const routes: Routes = [
    {
        path:"",
        component: EmployeeListsComponent
    },
    {
        path: "form",
        component: EmployeeFormComponent
    },
    {
        path: "form/:id",
        component: EmployeeFormComponent
    }
];
