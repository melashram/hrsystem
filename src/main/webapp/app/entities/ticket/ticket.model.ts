import { BaseEntity, User } from './../../shared';

export class Ticket implements BaseEntity {
    constructor(
        public id?: number,
        public reason?: string,
        public comment?: string,
        public description?: string,
        public creationdate?: any,
        public acceptancedate?: any,
        public request?: BaseEntity,
        public ticketStatus?: BaseEntity,
        public assignedTo?: BaseEntity,
        public user?: User,
    ) {
    }
}
