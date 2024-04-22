import { Pipe, PipeTransform } from '@angular/core';
import { EmployeeDemo } from '../employee';

@Pipe({
  name: 'serachfilter',
  standalone: true
})
export class SerachfilterPipe implements PipeTransform {

  transform(emp : EmployeeDemo[], filterResult : String): EmployeeDemo[] {
    
    if(!emp || !filterResult) {
      return emp;
    }
    
    return emp.filter( (e) => 
      e.name.toLocaleLowerCase().includes(filterResult.toLocaleLowerCase()) ||
      e.mail.toLocaleLowerCase().includes(filterResult.toLocaleLowerCase()) ||
      e.city.toLocaleLowerCase().includes(filterResult.toLocaleLowerCase()) ||
      e.address.toLocaleLowerCase().includes(filterResult.toLocaleLowerCase()) ||
      e.skill.find( s => s.toLocaleLowerCase().includes(filterResult.toLocaleLowerCase()))
  );
  }

}
