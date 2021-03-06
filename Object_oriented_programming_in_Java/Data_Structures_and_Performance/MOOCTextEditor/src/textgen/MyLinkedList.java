package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode();
		tail = new LLNode();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) { 
			throw new NullPointerException("Cannot add null object");
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		
		return true;
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index > size - 1 || index < 0){
			throw new IndexOutOfBoundsException("");
		}
		LLNode node = head;
		for (int i = -1; i < index; i++) {
			node = node.next;
		}
		return (E) node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) { 
			throw new NullPointerException("Cannot add null object");
		}
		if ((index != 0 && index > size - 1) || index < 0){
			throw new IndexOutOfBoundsException("");
		}
		LLNode node = head;
		LLNode new_node = new LLNode(element);
		
		for (int i = -1; i < index; i++) {
			node = node.next;
		}
		
		new_node.next = node;
		new_node.prev = node.prev;
		node.prev.next = new_node;
		node.prev = new_node;
		size ++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index > size - 1 || index < 0){
			throw new IndexOutOfBoundsException("");
		}
		if (size == 0){
			throw new IndexOutOfBoundsException("The list is empty"); 
		}
		LLNode node = head;
		
		for (int i = -1; i < index; i++) {
			node = node.next;
		}
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		size--;
		
		return (E) node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) { 
			throw new NullPointerException("Cannot add null object");
		}
		if (index > size - 1 || index < 0){
			throw new IndexOutOfBoundsException("");
		}
		
		LLNode node = head;
		LLNode new_node = new LLNode(element);
		
		for (int i = -1; i < index; i++) {
			node = node.next;
		}
		
		new_node.prev = node.prev;
		new_node.next = node.next;
		node.prev.next = new_node;
		node.next.prev = new_node;
		
		
		return (E) node.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode() {
		data = null;
		prev = null;
		next = null;
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode<E> getPrev() {
		return prev;
	}
	
	public LLNode<E> getNext() {
		return next;
	}

}
