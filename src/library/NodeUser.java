package library;

class NodeUser {
	
	User data;
	NodeUser next;
	
	NodeUser(User data) {
		this.data = data;
	}
	
	 NodeUser(User data, NodeUser next){
		 this.data = data;
		 this.next = next;
	 }

}
