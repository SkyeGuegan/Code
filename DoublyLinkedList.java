
/**
 *  This is a linked list, in which every node contains both a pointer to the prior node and one to the next node. 
 *  (The prior node of the head and the next node of the tail should both be null.) 
 * 
 * @author (Skye Guegan) 
 * @version (4/9/2017)
 */
class DoublyLinkedList <E>{
    private Node head, tail;
    private int size;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //testing LinkedList
        DoublyLinkedList link= new DoublyLinkedList<Double>();
        System.out.println("Adding a 1.0 to the head");
        link.addToHead(1.0);
        System.out.println(link);
        System.out.println("Adding a 5.0 to the head");
        link.addToHead(5.0);
        System.out.println(link);
        System.out.println("Adding a 7.0 to the head");
        link.addToHead(7.0);
        System.out.println(link);
        System.out.println("Adding a 6.9 to the tail");
        link.addToTail(6.9);
        System.out.println(link);
        System.out.println("Adding a 2.0 to spot 3");
        link.add(2.0,3);
        System.out.println(link);
        System.out.println("Does it contain 2.0? " +link.contains(2.0));
        System.out.println("Does it contain 69? " +link.contains(69));
        System.out.println("Where is 2.0? " +link.find(2.0));
        System.out.println("Where is 5.0? " +link.find(5.0));
        System.out.println("Where is 10.0? " +link.find(10.0) + " (-1 means not in List)");
        System.out.println("Get the value in spot 2? " +link.get(2));
        System.out.println("Get the value in spot 4? " +link.get(4));
        System.out.println("Get the value in spot 1? " +link.get(1));
        System.out.println("Get the value in spot 5? " +link.get(5));
        System.out.println("What value is at the head? " +link.getHead());
        System.out.println("What value is at the tail? " +link.getTail());
        System.out.println("What is the size of the list? " +link.getSize());
        System.out.println("Remove Node in spot 3");
        System.out.println(link.remove(3)+ " was the value in spot 3 removed");
        System.out.println(link);
        System.out.println("Adding a 2.0 to spot 2");
        link.add(2.0,2);
        System.out.println(link);
        System.out.println("Removing the head, which has value " +link.removeHead());
        System.out.println(link);
        System.out.println("Removing the tail, which has value " +link.removeTail());
        System.out.println(link);
        System.out.println("Adding a 1.1 to spot 1");
        link.add(1.1,1);
        System.out.println(link);
        System.out.println("Adding a 5.5 to spot 5");
        link.add(5.5,5);
        System.out.println(link);
    }

    /**
     *  Adds a new object to the head
     * 
     * @param  value   the value stored in a link list
     */
    public void add(E value, int location){
        if (location> size+1) return;
        //adding to head
        if (location==1){
            head=new Node(value, head, null);
            if(head.next==null) tail=head;
            else head.next.prev=head;
            size++;
            return;
        }
        //adding to tail
        if (location==size+1){
            Node newTail=new Node(value, null,tail);
            if(size==0){
                head=newTail;
                tail=newTail;
                size++;
            }
            tail.next=newTail;
            tail= newTail;
            size++;
            return;
        }
        //adding with forward motion
        if(location<= size/2){
            int i = 2;
            Node prev=null;
            Node next=null;
            Node current =head;
            while (i<= location){
                prev = current;
                current =current.next;
                next=current.next;
                i++;
            }
            Node newNode=new Node(value, current, prev);
            prev.next=newNode;
            current.prev=newNode;
        }
        //adding going from tail
        else{
            System.out.println("tail.prev " +tail.prev.value);
            int i = size-1;
            Node prev=null;
            Node next=null;
            Node current =tail;
            while (i>= location){
                next= current;
                current =current.prev;
                prev=current.prev;
                i--;
            }
            Node newNode=new Node(value, current, prev);
            prev.next=newNode;
            current.prev=newNode;
        }
        size++;
    }

    /**
     *  Adds a new object to the head
     * 
     * @param  value   the value stored in a link list
     */
    public void addToHead(E value){
        add(value,1);
    }

    /**
     * Adds a new object to the tail
     * 
     * @param  value   the value stored in a link list
     */
    public void addToTail(E value){
        add(value,size+1);
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
        //searches list
        for(Node current= head; current!=null; current=current.next){
            if(value.equals(current.value)) return true;
        }
        return false;
    }

    /**
     *  Returns the index of the ﬁrst Node containing the given value, or -1 if it is not found
     *  
     *  @param  value   the value stored in a link list
     *  @return  returns the index of the ﬁrst Node containing the given value
     */
    public int find(E value){
        int i=1;
        //searches list
        for(Node current= head; current!=null; current=current.next){
            if(value.equals(current.value)){
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Returns the E located at the given location, without modifying the list.
     * 
     * @param   the location of the node of interest
     * @return  the E located at the given location
     */
    public E get(int location){
        //get head
        if (location==1){
            return head.value;
        }
        //get tail
        if (location==size){
            return tail.value;
        }
        //get by going from head
        if(location<= size/2){
            int i = 2;
            Node current =head;
            while (i<= location){
                current =current.next;
                i++;
            }
            return current.value;
        }
        //get going from tail
        else{
            int i = size-1;
            Node current =tail;
            while (i>= location){
                current =current.prev;
                i--;
            }
            return current.next.value;
        }
    }

    /**
     * Returns the E located at the head, without modifying the list.
     * 
     * @return  the value of the head
     */
    public E getHead(){
        return head.value;
    }

    /**
     * Returns the size of the DoublyLinkedList
     * 
     * @return  size   the size of the List
     */
    public int getSize(){
        return size;
    }

    /**
     *  Returns the E located at the tail, without modifying the list
     * 
     * @return  the value of the head
     */
    public E getTail(){
        return tail.value;
    }

    /**
     * Deletes the Node at the given location, returning the E that was contained therein
     * 
     * @param   the location of the node of interest
     * @return  the value of the node removed
     */
    public E remove(int location){
        //if list is empty returns null
        if(size==0)return null;
        Node remove;
        Node current;
        //if removing the last node makes head and tail null
        if(size==1){
            current=head;
            head=null;
            tail=null;
            return current.value;
        }
        //returns null if invalid location and adjusts tail value
        if (location> size+1) return null;
        //removes the head
        if (location==1){
            remove=head;
            head=head.next;
            head.prev=null;
            size--;
            return remove.value;
        }
        //removes the tail and adjusts tail value
        if (location==size){
            remove=tail;
            tail=tail.prev;
            tail.next=null;
            size--;
            return remove.value;
        }
        //removes by going from head
        if(location<= size/2){
            int i = 2;
            Node prev=null;
            Node next=null;
            current =head;
            while (i<= location){
                prev = current;
                current =current.next;
                next=current.next;
                i++;
            }
            prev.next=next;
            next.prev=prev;
            size--;
            return current.value;
        }
        //removes by going from tail
        else{
            int i = size-1;
            current =tail;
            Node prev=null;
            Node next=null;
            while (i>= location){
                current =current.prev;
                next=current.next;
                prev=current.prev;
                i--;
            }
                prev.next=next;
                next.prev=prev;
                size--;
                return current.value;
        }
    }

    /**
     * Deletes the Node at the head, returning the E that was contained therein.
     * 
     * @return  the value of the head
     */
    public E removeHead(){
        return remove(1);
    }
    
    /**
     * Deletes the Node at the tail, returning the E that was contained therein
     * 
     * @return  the value of the tail
     */
    public E removeTail(){
        return remove(size);
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
            builder.append("( "+current.value+" )");
            if(current!=tail) builder.append(" -> ");
        }
        return builder.toString();
    }

    private class Node{
        public E value;
        public Node next;
        public Node prev;
        //constructor
        public Node(E value, Node next, Node prev){
            this.value= value;
            this.next= next;
            this.prev=prev;
        }
    }
}
