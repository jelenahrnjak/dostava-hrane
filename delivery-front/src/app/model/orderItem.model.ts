export class OrderItem {
    constructor(
        public itemName : string,
        public itemPrice : number,
        public itemCount : number = 0,
        public mealId : string,
        public mealType : string, 
      ) {}
      
}