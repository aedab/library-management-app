package library;

class NodeBook {
	
	Book data;
	NodeBook next;

	NodeBook(Book data) {
		this.data = data;
	}
	NodeBook(Book data, NodeBook next){
		this.data = data;
		this.next = next;
	}
	

}
