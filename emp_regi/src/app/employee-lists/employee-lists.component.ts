import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { EmpServiceService } from '../service/emp-service.service';
import { EmployeeDemo } from '../employee';
import { Router, RouterLink } from '@angular/router';
import { SerachfilterPipe } from '../filter/serachfilter.pipe';
import { FormsModule } from '@angular/forms';
import { PagingConfig } from '../paging/pagingConfig';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-employee-lists',
  standalone: true,
  imports: [CommonModule,RouterLink,SerachfilterPipe,FormsModule,NgxPaginationModule],
  templateUrl: './employee-lists.component.html',
  styleUrl: './employee-lists.component.scss'
})
export class EmployeeListsComponent implements PagingConfig{

  service = inject(EmpServiceService);
  // Pagination
  currentPage: number = 1;
  itemsPerPage: number = 5;
  totalItems: number = 0;
  // PagingConfig Object
  pagingConfig : PagingConfig = {} as PagingConfig;
  // total size
  totalSize: number[] = [5,10,15,20];
  // onTableDataChange
  onTableDataChange(event: number){
    this.pagingConfig.currentPage = event;
    this.showAllEmployee();
  }
  // onTableSizeChange
  onTableSizeChange(event: any) {
    this.pagingConfig.itemsPerPage = event.target.value;
    this.pagingConfig.currentPage = 1;
    this.showAllEmployee();
  }

  allEmployee : EmployeeDemo[] = [];
  // for search
  filterResult : String = '';

  ngOnInit(): void {
    console.log("Application is start")

    this.showAllEmployee();

    this.pagingConfig = {
      currentPage : this.currentPage,
      itemsPerPage: this.itemsPerPage,
      totalItems : this.totalItems
    }
  }
  // Prepare for update
  prepareEmployee(id : number, emp : EmployeeDemo) {
    console.log("Prepare Employee", emp)
    
  }

  // CURD delete Employee
  deleteEmployee(id : number) {
    this.service.deleteEmployeeById(id).subscribe({
      next: (res) => {
        console.log("Delete id", id);
        this.allEmployee = res;
      }
    })
  }

  // CUED all Employees
  showAllEmployee() {
    this.service.getAllEmployee().subscribe({
      next:(res : EmployeeDemo[]) =>{
        this.allEmployee = res;
        console.log("DB Call",res);
        this.pagingConfig.totalItems = res.length;
      }
    })
  }

}
