import java.util.Random;

/*

Driver class

*/

public class Program {

public static void main(String[] args) {
  
   Customer cust = null;  
  
   LinkedQueue queue = new LinkedQueue();
   
   int num = 0;
   
   int maximumQueueLength = 0;
   
   //Starts 60 minute simulation
   
   for (int minutes = 0; minutes < 60; minutes++) {
   
      //25% chance for a new customer to be added to the queue
      
      Random n = new Random();
      
      num = n.nextInt(4) + 1;
      
      if (num == 1) {
         
         queue.enqueue(new Customer());
         
         System.out.println("New customer added!  Queue length is now: " + queue.getQueueLength());
      
         if(cust == null) {
         
            cust = queue.currentCust();
         
           }
         
         //Updates maximum queue size number if current size is larger than previous maximum
         
         if (maximumQueueLength < queue.getQueueLength()) {
         
            maximumQueueLength = queue.getQueueLength();
            
            }
      
        }
      
      //Reduces servicetime if there is a customer in queue
      
      if (cust != null) {
         
         cust.decServiceTime();
         
         if (cust.getServiceTime() == 0) {
         
            //Remove from the queue if servicetime is over
            
            queue.dequeue();
            
            cust = null;
            
            System.out.println("Customer serviced and removed from the queue.  Queue length is now: " + queue.getQueueLength());
            
            //Move next customer to the current customer being serviced
            
            if(queue.getQueueLength() > 0) {
            
               cust = queue.currentCust();
   
            }
   
         }

      }

   System.out.println("---------------------------------------------------");

}

//Displays total number of customers

System.out.println("Total number of customers serviced: " + queue.getCustomerSum());

//Displays longest line length

System.out.println("Maximum line length during the simulation: " + maximumQueueLength);

}

}




/*

LinkedQueue class

*/



class LinkedQueue {

//Vars for storage of info related to total queue size, total amount of customers

int queueLength = 0;

int customerSum = 0;

private Customer first, last;

public LinkedQueue() {

   first = last = null;

}

public boolean isEmpty() {

   return first == null;

}

//Adds c to queue

public void enqueue(Customer c) {

   //If the queue is empty, set first to reference the new object
   
   if (isEmpty()) {
   
       first = c;
   
   //if the queue is not empty, change current last object reference to new object
   
   } else {
   
      last.setNext(c);
   
   }
   
   last = c;
   
   //Update total customer number
   
   customerSum++;
   
   //Update marker for total number of people in queue
   
   queueLength++;
   
   }
   
public Customer dequeue() {
   
   if (isEmpty()) {
   
      return null;
   
   }
   
   Customer temp = first;
   
   //First references current object's next reference
   
   first = first.getNext();
   
   //Check if queue is empty, if it is, set last to null
   
   if (isEmpty()) {
   
      last = null;
   
   }
      
   //Update marker for total number of people in queue
   
   queueLength--;
   
   return temp;
   
   }
   
//Getter for total current queue length count
   
public int getQueueLength() {
   
      return queueLength;
   
}
   
//Method used to update customer getting serviced
   
public Customer currentCust() {

//Check if queue is empty
   
   if (isEmpty()) {
   
      return null;
   
   }
   
   return first;
   
}
   
//Getter for sum of customers served
   
public int getCustomerSum() {
   
      return customerSum;
   
}
   
//
   
}