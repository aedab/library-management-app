package library;

class ListBookLinked {
	
	private NodeBook first;
	private int size;

	public ListBookLinked() {
		first = null;
		size = 0;
				
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	public int size() {
		return size;
	}
	
	
	public Book recover(int i){
		 NodeBook aux = first;
		 for (int k = 0; k<i; k++)
			 aux = aux.next;
		 return aux.data;
	}
	
	public Book remove(int i) {
		 Book x;
		 if (i==0) {
			x = first.data;
			first = first.next;
		 }
		 else {
			 NodeBook aux = first;
			 for (int k = 0; k<i-1; k++)
			 aux = aux.next;
			
			 x = aux.next.data;
			 aux.next = aux.next.next;
		 }
		 size--;
		 return x; 
	}
	
	public void insert(int i, Book x) {
		 if (i==0) 
			 first = new NodeBook(x,first);
		 else {
			 NodeBook aux = first;
			 for (int k = 0; k<i-1; k++)
				 aux = aux.next;
			 aux.next = new NodeBook(x,aux.next);
		 }
		 size++; 
		
	}
	
	public void append(Book x) {
        //Make a node with data
        NodeBook append = new NodeBook(x, null);
        NodeBook aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        aux.next = append;
        size ++;
	}
	
	public Book[] asArray() {
		Book[] arr = new Book[size];
		for(int i=0; i<size; i++ )
			arr[i] = recover(i);
		
		return arr;
	}

}
