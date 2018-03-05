import { BaseEntity, User } from './../../shared';

export class ItUser implements BaseEntity {
    constructor(
        public id?: number,
        public itPositon?: string,
        public user?: User,
    ) {
    }
}
