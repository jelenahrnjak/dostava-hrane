import { Address } from "./address.model";
import { OrderItem } from "./orderItem.model";

export class Restaurant {
    constructor(
        public restautantId : string,
        public restaurantId : string,
        public name : string,
        public description : string, 
        public image : string,   
        public address : Address,
        public meals : OrderItem
      ) {}
      
}