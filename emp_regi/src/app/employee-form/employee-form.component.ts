import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule,Router, ActivatedRoute } from '@angular/router';
import { EmpServiceService } from '../service/emp-service.service';
import { EmployeeDemo, QualificationDemo } from '../employee';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,RouterModule,FormsModule],
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.scss'
})
export class EmployeeFormComponent {

  fb = inject(FormBuilder);
  service = inject(EmpServiceService);
  router = inject(Router);
  activeRoute = inject(ActivatedRoute);
  id : number = 0;

  public showSkills : String [] = ['Java','C++','Python','Javascript','Ruby','Linux'];
  public citys : String[] = ['Yangon','Mandalay','Mawlamyine','Dawei','Myeik','Kawthaung'];
  // for prepare employee
  public employee !: EmployeeDemo;
  // for radio box
  public genderCheck : String = '';
  // for checkbox
  public checkBoxRequired = false;
  // for checkbox 
  public sk : string [] = [];
  // for dynamic show qualification 
  public tempQualification : QualificationDemo [] = [];
  // for del qualification 
  public delQualification : QualificationDemo [] = [];
  // cancel button
  cancel(): void {
    this.myForm.reset();
  }

  

  public myForm = this.fb.group({
    name: new FormControl('',[Validators.required]),
    dob : new FormControl<Date | null >(null,[Validators.required]),
    nrc: new FormControl('',[Validators.required]),
    mail: new FormControl('',[Validators.required,Validators.email]),
    address: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    gender: new FormControl('',[Validators.required]),
    skill: new FormArray<any>([],[Validators.required]),
    qualifications: new FormArray([])
  })
  // update prepare
  prepareEmployee() {
    this.id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    if(this.id){
      this.service.searchEmployeeById(this.id)
      .subscribe({
        next:(res) => {
          
          this.employee = res;
 //         console.log('Update Employee',this.employee);
          // review the employee
          if(this.employee) {          

            this.myForm.patchValue({
              name: this.employee.name.toString(),
              dob: this.employee.dob,
              mail: this.employee.mail.toString(),
              address: this.employee.address.toString(),
              nrc: this.employee.nrc.toString(),
              gender: this.employee.gender.toString(),
              city: this.employee.city.toString(),
            })

            this.sk = this.employee.skill as string []; // temp store original skill

            this.tempQualification = this.employee.qualifications;
            this.delQualification = this.employee.qualifications;
              
            if(this.employee.qualifications) {
 //             console.log('Update Qualification', this.tempQualification);
              this.tempQualification.forEach( q => {
                this.qualification.push(
                  new FormGroup({
                    courseName: new FormControl(q.courseName),
                    year: new FormControl(q.year)
                  })
                )
              })
            }
          }

        } 
      });

      
      
    } else {
      console.log("Add Employee ready")
    }
  }
  // init 
  ngOnInit(): void {
    
    this.prepareEmployee();

  }
  ngOnChanges(): void {
    console.log('Changes is working');
  }

  // prepare checkbox for update
  skillMatch(value : any) : boolean{ // ['Java','C++','Python','Javascript','Ruby','Linux']   
    if(this.sk) {
      for(let i = 0; i < this.sk.length ; i ++ ) {
        let result = this.sk[i] === value;
        if(result){
          return result;
        }
      }
    }
    return false
  }

  // update employee by id 
  updateEmployee(myValue : any){

      let id = Number(this.activeRoute.snapshot.paramMap.get('id'));
      // console.log('Update id',id);
      // console.log('Old Qualification',this.tempQualification);
      let qualification = this.tempQualification; // old qualification
  
      let q = myValue.value.qualifications; // old and new qualification without id
    //  console.log("Changes Value",q)
  
      if(qualification.length >= 1){
     //   console.log("Old one exist")
        // swap value 
        for(let i = 0; i < qualification.length ; i ++) {
          qualification[i].courseName = q[i].courseName;
          qualification[i].year = q[i].year;
        }

        // add new qualification without id 
        for(let i = 0 ; i < q.length ; i ++){
          if(qualification.length <= i) {
            qualification.push(q[i]);
          }
        }
      } else {
        qualification = q;
      }
  

       let ssk = myValue.get('skill').value;
       console.log('For skills',ssk); // update value without original value
       console.log('Original Skills',this.sk);
       for( let item of ssk) {
        this.sk.push(item);  // update value append to original value 
        console.log('Pushing skill',this.sk);
    }

      // employee object
      let employee = {
        id: id,
        name: myValue.get('name').value,
        dob: myValue.get('dob').value,
        nrc: myValue.get('nrc').value,
        mail: myValue.get('mail').value,
        address: myValue.get('address').value,
        city: myValue.get('city').value,
        gender: myValue.get('gender').value,
        skill:  this.sk,
        qualifications: []
      }

       console.log('Update for ',employee);
      // console.log('Update for Qualification',qualification);

    this.service.updateEmployeeById(id,{employee,qualification}).subscribe({
      next:(res) => {
        console.log('Final Update', res);
        this.router.navigate(['']);
      }
    });
    
  }

  // Save Employee
  saveEmployee(myData : any) {

    if(myData.valid){
      let employee = {
        name: myData.get('name').value,
        dob: myData.get('dob').value,
        nrc: myData.get('nrc').value,
        mail: myData.get('mail').value,
        address: myData.get('address').value,
        city: myData.get('city').value,
        gender: myData.get('gender').value,
        skill:  this.skills.value,
      }
      console.log(employee);
      let qualification = this.qualification.value;
      console.log(qualification)

      this.service.addEmployee({employee,qualification}).subscribe({
        next:(res) => {
          console.log('Save Employee',res)
          this.router.navigate(['']);
        }
      })

    }else{
      this.myForm.markAllAsTouched;
      
      console.log("Invalid Form");
    }
  }

  // for add qualification
  get qualification() : FormArray{
    return this.myForm.get('qualifications') as FormArray;
  }
  // for add qualification
  addQualification() {
    this.qualification.push(
      new FormGroup({
        courseName: new FormControl(''),
        year: new FormControl('')
      })
    )
  }
  // for remove qualification 
  removeQualification(index : number) {
    if(this.delQualification.at(index)?.id){
      let qual = this.delQualification.at(index);
      console.log(this.delQualification.at(index));
      this.service.deleteQualificationById(qual?.id as number)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.qualification.removeAt(index);
        }
      })
    }else{
      this.qualification.removeAt(index);
    }
  }

  // for checkbox skills 
  get skills() : FormArray{
    return this.myForm.get('skill') as FormArray;
  }
  // for checkbox skills
  checkCheckBox(event : Event) {
    return (event.target as HTMLInputElement).checked;
  }
  // for checkbox skills
  updateCheckboxValidity() {
    this.checkBoxRequired = this.skills.length < 1; 
  }
  // for checkbox skills
  addRemoveSkill(event : Event, item : String) {
    const check = this.checkCheckBox(event);
    let index = this.skills.value.indexOf(item);
    if(check) {
      this.skills.push(this.fb.control(item))
      console.log("update from switch",this.skills.value);
    }else{
      console.log('original skills',this.sk);
      
      let original_length = this.sk.length;
      console.log('original length',original_length);
      this.sk = this.sk.filter( s => s !== item);
      let modify_length = this.sk.length;
      console.log('modify length',modify_length);
      console.log('remain skills',this.sk);
     if(original_length === modify_length) {
        this.skills.removeAt(index);
     }
     
    }
    this.updateCheckboxValidity();
  }

  // for radio 
  genderMale(value : any) {
    this.genderCheck = value;
  }

}
