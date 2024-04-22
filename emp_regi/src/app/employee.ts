export interface EmployeeDemo{
    id: number,
    name: String,
    dob: Date,
    nrc: String,
    mail: String,
    address: String,
    city: String,
    gender: String,
    skill:  String[],
    qualifications: QualificationDemo[]
}

export interface QualificationDemo{
    id: number,
    courseName: String,
    year: Date,
    employee: EmployeeDemo
}

export interface EmployeeQualificationDemo{
    employee : EmployeeDemo,
    qualification : QualificationDemo[]
}