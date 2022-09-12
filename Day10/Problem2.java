/* Add two numbers represented by Linked List
Given two numbers represented by two linked lists, write a function that returns Sum list. The sum list is linked list representation of addition of two input numbers.

Example 1:
Input:	S1 = 3, S2 = 3
		ValueS1 = {2,3,4}
		ValueS2 = {3,4,5}
Output: 5 7 9
Explanation: After adding the 2 numbers the resultant number is 5 7 9.

Example 2:
Input:	S1 = 1, S2 = 2
		ValueS1 = {9}
		ValueS2 = {8,7}
Output: 9 6
Explanation: Add 9 and 7 we get 16. 1 is carry here and is added to 8. So the answer is 9 6  */

// Here we've to modify the methods as per their usage in the main method
class GfG {

    Node cur; // Dont change the variable name, its used in main function
    int carry; // Dont change the variable name, its used in main function
    
    //This function is called after the smaller list is added to the sublist of 
    //bigger list of same size. Once the right sublist is added, the carry
    //must be added to left side of larger list to get the final result.    
    void addCarryToRemaining(Node head, LinkedList res) {
        if (head == cur) return;
        
        addCarryToRemaining(head.next, res);
        int data = head.data + carry;
        res.push(data % 10);
        carry = data / 10;
    }
    
    //Function which adds two linked lists of same size represented by head1  
    //and head2 and returns head of the resultant linked list. Carry
    //is propagated while returning from the recursion    
	void addSameSize(Node head1, Node head2, LinkedList res) { 
	    if (head1 == null) {
	        carry = 0;
	        return;
	    }
	    addSameSize(head1.next, head2.next, res);
	    int data = carry + head1.data + head2.data;
        res.push(data % 10);
        carry = data / 10;
    }
}

