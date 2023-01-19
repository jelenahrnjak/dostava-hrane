import { OrderItem } from './orderItem.model'
import { User } from './user.model'
import { Restaurant } from './restaurant.model'

export class Order {
    constructor(
        public orderId : string,
        public customerId : string,
        public customer : User,
        public delivererId : string,
        public deliverer : User,
        public restaurantId : string,
        public restaurant : Restaurant,
        public creationDate : Date,
        public totalPrice: number = 0, 
        public orderStatus: string,  
        public orderItems: OrderItem[], 
      ) {}
      
}