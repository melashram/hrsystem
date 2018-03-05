import { BaseEntity } from './../../shared';

export class Project implements BaseEntity {
    constructor(
        public id?: number,
        public projectName?: string,
        public description?: string,
        public projectAddress?: string,
        public projectCompany?: string,
        public projectCity?: string,
    ) {
    }
}
