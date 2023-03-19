import java.util.EmptyStackException;

public class SuperList<E>{
	ListNode<E> root, end;
	int size;
	public SuperList(){
		size = 0;
	}

	public void add(E value){
		ListNode<E> temp = new ListNode<E>(value);
		if(root == null){
			root = temp;
			end = root;
			size++;
		}else{
			end.setNext(temp);
			temp.setPrev(end);
			end = temp;
			size++;
		}
	}
	public void add(int index, E value){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		ListNode<E> temp = new ListNode<E>(value);
		if(size == 0){
			root = temp;
			end = root;
			size++;
		}
		else if(index == 0){
			root.setPrev(temp);
			temp.setNext(root);
			root = temp;
			size++;
		}
		else if(index == size){
			end.setNext(temp);
			temp.setPrev(end);
			end = temp;
			size++;
		}
		else if(index > 0 && index <= size-1){
			ListNode<E> current = root;
			ListNode<E> prev = new ListNode<E>(value);
			ListNode<E> next = new ListNode<E>(value);
			for(int i=0; i<index; i++){
				if(i == index-1){
					prev = current;
				}

				current = current.getNext();
				if(i == index-1){
					next = current;
					size++;
				}
			}
			prev.setNext(temp);
			temp.setPrev(prev);
			next.setPrev(temp);
			temp.setNext(next);
		}
	}
	public E get(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		ListNode<E> current = root;
		for(int i=0; i<=index; i++){
			if(i == index)
				return current.getValue();
			current = current.getNext();
		}
		return null;
	}
	public E stackPeek(){
		return end.getValue();
	}
	public E queuePeek(){
		return root.getValue();
	}
	public void set(int index, E value){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		ListNode<E> temp = new ListNode<E>(value);
		if(index == 0){
			temp.setNext(root.getNext());
			if(size > 1)
				root.getNext().setPrev(temp);
			root = temp;
		}
		if(index == size-1){
			temp.setPrev(end.getPrev());
			if(size > 1)
				end.getPrev().setNext(temp);
			end = temp;
		}
		if(index > 0 && index < size-1){
			ListNode<E> current = root;
			for(int i=0; i<index; i++){
				if(i == index-1){
					temp.setPrev(current);
					temp.setNext(current.getNext().getNext());
					current.setNext(temp);
				}
				current = current.getNext();
			}
		}
	}
	public void push(E value){
		add(value);
	}
	public E pop(){
		if(size == 0)
			throw new EmptyStackException();
		return remove(size-1);
	}
	public E poll(){
		if(size == 0)
			return null;
		return remove(0);
	}
	public E remove(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		ListNode<E> display = root;
		if(size == 1){
			root = null;
			end = null;
			size = 0;
		}
		else{
			if(index == 0){
				root.getNext().setPrev(null);
				root = root.getNext();
				size--;
			}
			else if(index == size-1){
				display = end;
				end.getPrev().setNext(null);
				end = end.getPrev();
				size--;
			}
			else if(index > 0 && index < size-1){
				ListNode<E> current = root;
				for(int i=0; i<index; i++){
					if(i == index-1){
						display = current.getNext();
						current.setNext(current.getNext().getNext());
						current.getNext().setPrev(current);
					}
					current = current.getNext();
				}
				size--;
				//current = root;
			}
		}
		return display.getValue();
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return root == null;
	}
	public void clear(){
		root = null;
		end = null;
		size = 0;
	}
	public boolean contains(E value){
		ListNode<E> temp = root;
		for(int i=0; i<size; i++){
			if(temp.getValue().equals(value)){
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
	public String toString(){
		String text = "[";
		ListNode<E> current = root;
		for(int i=0; i<size; i++){
			if(i == size-1)
				text += current.getValue();
			else
				text += current.getValue()+", ";
			current = current.getNext();
		}
		text += "]";
		return text;
	}


	public static void main(String[] args){
		// SuperList<String> list = new SuperList<String>();
		// list.add("Swathi");
		// list.add(1,"Aditi");
		// System.out.println(list);
		System.out.println("Lorem".compareTo("ipsum"));
	}

	public class ListNode<E>{
		private E value;
		private ListNode<E> prev, next;
		public ListNode(E value){
			this.value = value;
		}

		public E getValue(){
			return value;
		}

		public ListNode<E> getPrev(){
			return prev;
		}

		public ListNode<E> getNext(){
			return next;
		}

		public void setPrev(ListNode<E> prev){
			this.prev = prev;
		}

		public void setNext(ListNode<E> next){
			this.next = next;
		}

		public boolean hasPrev(){
			return prev != null;
		}

		public boolean hasNext(){
			return next != null;
		}

		public String toString(){
			return value.toString();
		}
	}
}