package library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import library.Book.Genre;


public class Library {
	
    static ListUserLinked users;
    static ListBookLinked books;
    
    public Library() {
    	readBooks();
    	readUsers();	
    }


	 ListBookLinked readBooks() {
		ListBookLinked books = new ListBookLinked();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/books.txt"))) {
		    String line;
		    while((line = bufferedReader.readLine()) != null){
		        String[] strArray  = line.split(","); 
		        Book b = new Book(strArray[0],Long.parseLong(strArray[1]) ,strArray[2],Genre.valueOf(strArray[3]),strArray[4]);
		        if(books.isEmpty()) {
		        	books.insert(0, b);
		        }else books.append(b);
		    }
		  
		    
		} catch (FileNotFoundException e) {
		    // Exception handling
		} catch (IOException ex) {
		    // Exception handling
		}
		return books;
	}

	 ListUserLinked readUsers() {
		ListUserLinked users = new ListUserLinked();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/users.txt"))) {
		    String lineUser;
		    while((lineUser = bufferedReader.readLine()) != null){
		        String[] strArray  = lineUser.split(","); 
		        User u = new User(strArray[0],strArray[1],strArray[2],Long.parseLong(strArray[3]));
		        if(users.isEmpty()) {
		        	users.insert(0, u);
		        }else users.append(u);
		    }
		  
		    
		} catch (FileNotFoundException e) {
		    // Exception handling
		} catch (IOException ex) {
		    // Exception handling
		}
		return users;
	}

	public Book[] getBooks() {
		return this.readBooks().asArray();
	}
	
	public int getBooksCount() {
		return this.readBooks().size();
	}
	
	 public User[] getUsers() {
		 return this.readUsers().asArray();
	 }
	 
	 public int getUserCount() {
		 return this.readUsers().size();
	 }
	
	 public boolean addBook(Book book) {
		boolean bool = false;
		for(int i = 0; i < getBooks().length; i++) {
			if(book.getIsbn() == getBooks()[i].getIsbn()) {
				System.out.println("THE BOOK WITH THIS ISBN NO. IS ALREADY REGISTERED IN THE SYSTEM");
				return bool;
				}
		}
			
		if(!bool) {
			try {
				BufferedWriter bw = new BufferedWriter( new FileWriter("./src/books.txt",true));
				bw.append("\n" + book.toString());
				bw.close();
				bool = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bool;
		
	}
	 
	 public boolean registerUser(User user) {
		boolean bool = false;
		if(getUserByDni(user.getDni()) != null) {
			System.out.println("THE USER WITH THIS DNI IS ALREADY REGISTERED IN THE SYSTEM");
			return bool;
			}
		
		if(!bool) {
			try {
				BufferedWriter bw = new BufferedWriter( new FileWriter("./src/users.txt",true));
				bw.append("\n" + user.toString());
				bw.close();
				bool = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bool;
	}
	
	public Book getBookByTitle(String title) {	
		
		for(int i = 0; i < getBooks().length; i++) {
			if(title.equals(getBooks()[i].getTitle()) )
				return getBooks()[i];
		}	
		return null;
	}
	public Book getBookByISBN(long isbn) {
		for(int i = 0; i < getBooks().length; i++) {
			if(isbn == getBooks()[i].getIsbn())
				return getBooks()[i];
		}	

		return null;
	}

	public User getUserByDni(String dni) {
		
		for(int i = 0; i < getUsers().length; i++) {
			if(dni.equals(getUsers()[i].getDni()))
				return getUsers()[i];
		}
		return null;
	}
	
	//I tested something and I realized that it might generate nullpointerexception in case
	//something is not entered correctly so I added "throws NullPointerException"
	public boolean rentBook(String dni, String title) throws NullPointerException {
		boolean rentingStatus = false;
		boolean exit = false;
		
		Book[] b = getBooks();
		User[] u = getUsers();
		
		if(getBookByTitle(title)!= null && getUserByDni(dni) != null) {
			if(getUserByDni(dni).getCurrentBook() != -1 && !getBookByTitle(title).getUserDni().contains("null"))
				return rentingStatus;
			else {
				for(int i = 0; i < b.length && !exit; i++) {
					if(b[i].getTitle().contains(title)) {
						b[i].setUserDni(dni);
						for(int j = 0; j < u.length && !exit; j++) {
							if(u[j].getDni().contains(dni)) {
								u[j].setCurrentBook(b[i].getIsbn());
								exit = true;
							}
							
						}
						
					}
				}
				writeAllBooks(b);
				writeAllUsers(u);
				return rentingStatus = true;
			
			}
		}else return rentingStatus;
			
		
	}

	public boolean returnBook(String title) {
		boolean bool = false;
		boolean exit = false;
		Book[] b = getBooks();
		User[] u = getUsers();
		
		if(getBookByTitle(title) != null && getBookByTitle(title).getUserDni().contains("null")) {
			return bool;
		}else {
			for(int i = 0; i < b.length && !exit; i++) {
				if(b[i].getTitle().contains(title)) {
					for(int j = 0; j < u.length && !exit; j++ ) {
						if(u[j].getDni().contains(b[i].getUserDni()))
							u[j].setCurrentBook(-1);
					}
						b[i].setUserDni("null");
						exit = true;
				}
				
			}
			
			writeAllBooks(b);
			writeAllUsers(u);
			return bool = true;
		}
	}

	public void writeAllBooks(Book[] books) {
		
		
			try {
				BufferedWriter bww = new BufferedWriter(new FileWriter("./src/books.txt"));
				bww.write(books[0].toString());
				for(int i = 1; i < books.length; i++) {
					bww.write("\n" + books[i].toString());				
					}
				bww.close();
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		
		
			
		
	}
	public void writeAllUsers(User[]users) {
		
			try {
				BufferedWriter bwww = new BufferedWriter(new FileWriter("./src/users.txt"));
				bwww.write(users[0].toString());
				for(int i = 1; i < users.length; i++) {
					bwww.write("\n" + users[i].toString());
					}
				bwww.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		

	}
}

