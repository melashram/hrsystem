import { BaseEntity, User } from './../../shared';

export class Employee implements BaseEntity {
    constructor(
        public id?: number,
        public personalPhoneNumber?: string,
        public workPhoneNumber?: string,
        public dOB?: any,
        public hireDate?: any,
        public title?: string,
        public socialInsuranceNumber?: string,
        public nationality?: string,
        public nationalIdNumber?: string,
        public passportNumber?: string,
        public cibAcountNumber?: string,
        public cityCountry?: string,
        public homeAddress?: string,
        public user?: User,
    ) {
    }
}
