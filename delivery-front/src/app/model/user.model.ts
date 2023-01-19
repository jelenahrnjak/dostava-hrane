import { Address } from "./address.model";

export class User {
    constructor(
        public id : string,
        public firstName : string,
        public lastName : string,
        public email : string,
        public phoneNumber: string, 
        public username: string,
        public address: string,
        public longitude : string,
        public latitude : string,

      ) {}
      
}