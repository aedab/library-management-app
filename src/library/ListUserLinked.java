package library;

class ListUserLinked {
	
	private NodeUser first;
	private int size;


	public ListUserLinked() {
		first = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	public User recover(int i){
		 NodeUser aux = first;
		 for (int k = 0; k<i; k++)
			 aux = aux.next;
		 return aux.data;
	}
	
	public User remove(int i) {
		 User x;
		 if (i==0) {
			x = first.data;
			first = first.next;
		 }
		 else {
			 NodeUser aux = first;
			 for (int k = 0; k<i-1; k++)
			 aux = aux.next;
			
			 x = aux.next.data;
			 aux.next = aux.next.next;
		 }
		 size--;
		 return x; 
	}
	
	public void insert(int i, User x) {
		 if (i==0) 
			 first = new NodeUser(x,first);
		 else {
			 NodeUser aux = first;
			 for (int k = 0; k<i-1; k++)
				 aux = aux.next;
			 aux.next = new NodeUser(x,aux.next);
		 }
		 size++; 
		
	}
	
	public void append(User x) {
       //Make a node with data
       NodeUser append = new NodeUser(x, null);
       NodeUser aux = first;
       while (aux.next != null) {
           aux = aux.next;
       }
       aux.next = append;
       size ++;
	}

	public User[] asArray() {
		User[] arr = new User[size];
		for(int i=0; i<size; i++ )
			arr[i] = recover(i);
		
		return arr;
	}
}
