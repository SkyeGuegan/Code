
/**
 *  Object that implements both the Stack interface and the Queue interface.  Your class must use generics. 
 *  In addition to the methods required by the interfaces.
 * 
 * @author (Skye Guegan and Mr. Hans) 
 * @version (4/4/2017)
 */
class LinkedList <E> implements Stack<E>,Queue<E>{
    private Node head, tail;
    private int size;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //testing stack
        Stack stack= new LinkedList<Integer>();
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.push(5);
        System.out.println(stack);
        stack.pop();
        System.out.println("POP: " +stack);
        System.out.println("Peek: "+ stack.peek());
        System.out.println("");
        
        //testing queue
        Queue queue= new LinkedList<String>();
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fifth");
        System.out.println(queue);
        queue.dequeue();
        System.out.println("dequeue: " +queue);
        System.out.println("Peek: "+ queue.peek());
        System.out.println("");
        
        //testing LinkedList
        LinkedList link= new LinkedList<Double>();
        link.addToHead(1.0);
        link.addToHead(5.0);
        link.addToHead(7.0);
        link.addToTail(6.9);
        System.out.println(link);
        link.addToHead(2.222);
        System.out.println(link);
        System.out.println("HEAD: " +link.getHead());
        System.out.println("Size: " +link.getSize());
        System.out.println("Does it contain 2.222? " +link.contains(2.222));
        System.out.println("Does it contain 69? " +link.contains(69));
        link.removeHead();
        System.out.println("Removed the head, What is the new head? " +link.getHead());
    }

    /**
     *  Adds a new object to the head
     * 
     * @param  value   the value stored in a link list
     */
    public void addToHead(E value){
        head=new Node(value, head);
        if(head.next==null) tail=head;
        size++;
    }

    /**
     * Adds a new object to the tail
     * 
     * @param  value   the value stored in a link list
     */
    public void addToTail(E value){
        Node newTail=new Node(value, null);
        if(size==0){
            head=newTail;
            tail=newTail;
            size++;
            return;
        }
        tail.next=newTail;
        tail= newTail;
        size++;
    }

    /**
     * Returns true if the passed value is to be found anywhere in the list. 
     * Note that this should return true if it encounters any object with the same value
     * 
     * @param  value   the value stored in a link list
     * @return true    if the value is in the link list
     * @return false    if the value isn't in the link list
     */
    public boolean contains(E value){
        if(value==null)return false;
        for(Node current= head; current!=null; current=current.next){
            if(value.equals(current.value)) return true;
        }
        return false;
    }

    /**
     * Returns the size of the LinkedList
     * 
     * @param  size   the size of the Link List
     */
    public int getSize(){
        return size;
    }

    /**
     *  returns the head value, without actually removing it.
     * 
     * @return  the value of the head
     */
    public E getHead(){
        return head.value;
    }

    /**
     * Removes and returns whatever object was at the head, and updates the head (and possibly the tail). 
     * Return null if the list was empty.
     * 
     * @return  the value of the head
     */
    public E removeHead(){
        if(size==0)return null;
        Node headRemoved= head;
        head= head.next;
        size--;
        if(size==0){
            tail=null;
        }
        return headRemoved.value;
    }

    /**
     * Returns a String representing this LinkedList, with each object in parentheses separated by “arrows” (->).
     * 
     * @return     the sum of x and y 
     */
    @Override
    public String toString(){
        StringBuilder builder= new StringBuilder();
        for(Node current= head; current!=null; current=current.next){
            builder.append(current.value);
            if(current!=tail) builder.append(" -> ");
        }
        return builder.toString();
    }

    private class Node{
        public E value;
        public Node next;
        //constructor
        public Node(E value, Node next){
            this.value= value;
            this.next= next;
        }
    }

    /**
     * Removes an item from the front of the queue.
     *
     * @return the value removed
     */
    @Override
    public E dequeue(){
        return removeHead();
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param value  the value to be enqueued
     */
    @Override
    public void enqueue(E value){
        addToTail(value);
    }

    /**
     * Removes an item from the top of the stack.
     *
     * @return the value removed
     */
    @Override
    public E pop(){
        return removeHead();
    }

    /**
     * Adds an item to the top of the stack.
     *
     * @param value  the value to be enqueued
     */
    @Override
    public void push(E value){
        addToHead(value);
    }

    /**
     * Checks item at the front of the queue, without altering the queue.
     *
     * @return the value checked
     */
    @Override
    public E peek(){
        return getHead();
    }
}
