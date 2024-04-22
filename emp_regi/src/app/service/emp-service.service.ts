import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeDemo, EmployeeQualificationDemo, QualificationDemo } from '../employee';

@Injectable({
  providedIn: 'root'
})
export class EmpServiceService {

  emp_url = "http://localhost:8080/employee";
  qual_url = "http://localhost:8080/qualification/";


  http = inject(HttpClient)

  constructor() { }

  getAllEmployee() : Observable<EmployeeDemo[]> {
    return this.http.get<EmployeeDemo[]>(this.emp_url+'/all');
  }

  addEmployee(emp : any) : Observable<EmployeeDemo>{
    return this.http.post<EmployeeDemo>(this.emp_url+'/create',emp);
  }

  deleteEmployeeById(id : number) : Observable<EmployeeDemo[]> {
    return this.http.delete<EmployeeDemo[]>(this.emp_url+`/delete/${id}`);
  }

  searchEmployeeById(id : number) : Observable<EmployeeDemo> {
    return this.http.get<EmployeeDemo>(this.emp_url+`/${id}`);
  }

  deleteQualificationById(id : number) : Observable<QualificationDemo>{
    return this.http.delete<QualificationDemo>(this.qual_url+`delete/${id}`);
  }

  updateEmployeeById(id : any,dual : EmployeeQualificationDemo) : Observable<EmployeeDemo>{
    return this.http.put<EmployeeDemo>(this.emp_url+`/update/${id}`,dual);
  }
}
