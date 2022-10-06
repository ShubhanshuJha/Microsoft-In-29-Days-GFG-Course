/* Get minimum element from stack
You are given N elements and your task is to Implement a Stack in which you can get minimum element in O(1) time.

Example 1:
Input:
    push(2)
    push(3)
    pop()
    getMin()
    push(1)
    getMin()
Output: 2 1
Explanation: In the first test case for query:
        push(2)  Insert 2 into the stack. The stack will be {2}
        push(3)  Insert 3 into the stack. The stack will be {2 3}
        pop()    Remove top element from stack. Poped element will be 3 the stack will be {2}
        getMin() Return the minimum element min element will be 2 
        push(1)  Insert 1 into the stack. The stack will be {2 1}
        getMin() Return the minimum element, min element will be 1  */


// This will return the minElem from Stack in O(1) space and O(1) time, as per the question
class GfG {
    int minEle;
    Stack<Integer> s;

    /*returns min element from stack*/
    int getMin() {
        return s == null || s.isEmpty() ? -1 : minEle;
    }
    
    /*returns poped element from stack*/
    int pop() {
        if (s == null || s.isEmpty())
            return -1;
        
        int top = s.pop();
        if (top < minEle) {
            int temp = minEle;
            minEle = (temp << 1) - top;
            return temp;
        }
        return top;
    }

    /*push element x into the stack*/
    void push(int x) {
        if (s == null || s.isEmpty()) {
            if (s == null)
                s = new Stack<>();
            s.push(x);
            minEle = x;
        } else {
            if (x < minEle) {
                s.push((x << 1) - minEle);
                minEle = x;
            } else {
                s.push(x);
            }
        }
    }   
}

