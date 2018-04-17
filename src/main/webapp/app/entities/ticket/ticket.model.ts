import { BaseEntity, User } from './../../shared';

export class Ticket implements BaseEntity {
    constructor(
        public id?: number,
        public reason?: string,
        public comment?: string,
        public description?: string,
        public creationDate?: any,
        public acceptanceDate?: any,
        public request?: BaseEntity,
        public ticketStatus?: BaseEntity,
        public user?: User,
        public assignedUser?: User,
    ) {
    }
}
