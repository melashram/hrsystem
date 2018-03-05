import { BaseEntity, User } from './../../shared';

export class HumanResourceUser implements BaseEntity {
    constructor(
        public id?: number,
        public humanResourcesPosition?: string,
        public user?: User,
    ) {
    }
}
