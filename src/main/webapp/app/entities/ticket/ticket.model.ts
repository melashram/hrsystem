import { BaseEntity, User } from './../../shared';

export class Ticket implements BaseEntity {
    constructor(
        public id?: number,
        public reason?: string,
        public toWhom?: string,
        public comment?: string,
        public creationdate?: any,
        public acceptanceDate?: any,
        public ticketStatus?: string,
        public description?: string,
        public assigendTo?: BaseEntity,
        public ticketRequst?: BaseEntity,
        public user?: User,
    ) {
    }
}
