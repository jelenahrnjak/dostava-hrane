export class OrderItem {
    constructor(
        public itemName : string,
        public itemPrice : string,
        public itemCount : number = 0,
        public mealId : string,
        public count : number = 0,
      ) {}
      
}