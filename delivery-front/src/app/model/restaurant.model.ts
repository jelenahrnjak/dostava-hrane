import { Address } from "./address.model";

export class Restaurant {
    constructor(
        public restaurantId : string,
        public name : string,
        public description : string, 
        public image : string,   
        public address : Address
      ) {}
      
}