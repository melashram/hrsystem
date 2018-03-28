import { BaseEntity, User } from './../../shared';

export class Ticket implements BaseEntity {
    constructor(
        public id?: number,
        public reason?: string,
        public comment?: string,
        public creationdate?: any,
        public acceptanceDate?: any,
        public description?: string,
        public request?: BaseEntity,
        public ticketStatus?: BaseEntity,
        public assignedTo?: BaseEntity,
        public user?: User,
    ) {
    }
}
