import { BaseEntity } from './../../shared';

export class Request implements BaseEntity {
    constructor(
        public id?: number,
        public requestType?: string,
    ) {
    }
}
