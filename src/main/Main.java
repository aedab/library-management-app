package main;
import java.util.Scanner;

import library.Book;
import library.Book.*;
import library.Library;
import library.User;


public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		int selection;
		Scanner keyboard = new Scanner(System.in);
		loading(); //SHOW THE LOADING SCREEN
		boolean inMenu = true;
		
		while(inMenu) {
			// SELECTION MENU 
			System.out.println("******e-Library******");
			System.out.println("Interactive menu - Choose an option: " + 
			"\n * 1 REGISTER A BOOK " + "\n * 2 CHECK BOOK AVAILABIITY " +
			"\n * 3 RENT A BOOK " + "\n * 4 RETURN A BOOK " + "\n * 5 LIST ALL BOOKS " +
			"\n * 6 CREATE NEW USER " + "\n * 7 LIST ALL USERS " + "\n * 8 EXIT " + " \n -----" 
			+ "\n Your option: ");
			
			
			// Read input
			selection = keyboard.nextInt();


			switch(selection) {
			case 1: option1(); break;
			case 2: option2(); break;
			case 3: option3(); break;
			case 4: option4(); break;
			case 5: option5(); break;
			case 6: option6(); break;
			case 7: option7(); break;
			case 8: inMenu = false; 
			System.out.println("*** FIN DE PROGRAMA ***"); break;
			}
			
		}
	
		
		

	}
	

	static void loading() throws InterruptedException {
		
		   int i = 0;
		   while(i < 11) {
			   System.out.print("Loading e-Library");
		       for (int j=0;j<i;j++) {
		           System.out.print(".");
		       }

		       for (int j=0;j<10-i;j++) {
		           System.out.print(" ");
		       }

		       System.out.print(" "+  i*10 + "%");
		       if(i<10) {
		           System.out.print("\r");
		           Thread.sleep(200);
		       }
		       i++;
		}
		System.out.println("\n" + "\n");
		
	}
	
	private static void option1() {
		Library lb = new Library();
		String title,author,genre;
		long isbn;
		Scanner in = new Scanner(System.in);
		System.out.println("Title: ");
		title = in.nextLine();
		System.out.println("ISBN: ");
		isbn = Long.parseLong(in.nextLine());
		System.out.println("Author: ");
		author = in.nextLine();
	    System.out.println("GENRE: " + "\n" + " NONE  ADVENTURE  CRIME  K_DRAMA  FANTASY  HORROR  MYSTERY  POETRY THRILLER  BIOGRAPHY  JOURNAL  SCIENCE  TRAVEL \n");
		genre = in.nextLine();
		
		
		Book b = new Book(title,isbn,author,Genre.valueOf(genre.toUpperCase()),null);
		if(lb.addBook(b)) {
			System.out.println("^_^ BOOK ADDED SUCCESSFULY ^_^" + "\n");
			}else System.out.println(":(  BOOK FAILED TO BE ADDED :(" + "\n");
		
	}

	private static void option2() {
		Library lb = new Library();
		Scanner k = new Scanner(System.in);
		String title;
		byte selection;
		long isbn;
		System.out.println("WHAT BOOK ARE YOU LOOKING FOR ? " + "\n 1.LOOK BY TITLE " + "\n 2.LOOK BY ISBN ");
		selection = Byte.parseByte(k.nextLine());
		
		if(selection == 1) {
			System.out.print("WRITE THE TITLE --->");
			title = k.nextLine();
			if(lb.getBookByTitle(title) != null) {
				if(lb.getBookByTitle(title).getUserDni().contains("null"))
				 System.out.println(lb.getBookByTitle(title).getTitle() + " IS AVAILABLE FOR RENT." + "\n");
				else
			     System.out.println(lb.getBookByTitle(title).getTitle() + " IS RENTED BY " +
			    		 lb.getUserByDni(lb.getBookByTitle(title).getUserDni()).getName()+ "\n");

			}else
				System.out.println("Sorry, we don't have that book in the system :( " + "\n");
		}else if(selection == 2) {
			System.out.print("WRITE THE ISBN ---> ");
			isbn = Long.parseLong(k.nextLine());
			if(lb.getBookByISBN(isbn) != null) {
				if(lb.getBookByISBN(isbn).getUserDni().contains("null"))
					 System.out.println("THE BOOK W/ ISBN NO. " + lb.getBookByISBN(isbn).getIsbn() + " IS AVAILABLE FOR RENTING." + "\n");
					else
				     System.out.println("THE BOOK W/ ISBN NO. " + lb.getBookByISBN(isbn).getIsbn() + " IS RENTED BY " + 
				    		 lb.getUserByDni(lb.getBookByISBN(isbn).getUserDni()).getName() +"\n");
				}else
					System.out.println("Sorry, we don't have that book in the system :( " + "\n");
			
			}
		}
	
	private static void option3() {
		Library lb = new Library();
		Scanner sc = new Scanner(System.in);
		String dni,title;
		System.out.println("Introduce your DNI: ");
		dni = sc.nextLine();
		System.out.println("Introduce the book title: ");
		title = sc.nextLine();
		
		try {
		if(lb.rentBook(dni, title))
			System.out.println("^_^ BOOK RENTED SUCCESSFULY ^_^" + "\n");
		else
			System.out.println("BOOK IS ALREADY RENTED BY " + lb.getUserByDni(lb.getBookByTitle(title).getUserDni()).getName()  + "\n");
		}catch(Exception NullPointerException) {
			System.out.println("THE BOOK OR DNI IS INCORRECT, PLEASE TRY AGAIN !");
		}
		
	}
	private static void option4() {
		Library lb = new Library();
		String title;
		Scanner in = new Scanner(System.in);
		System.out.println("TITLE OF THE BOOK TO BE RETURNED: ");
		title = in.nextLine();
		
		if(lb.returnBook(title))
			System.out.println(" ^_^ BOOK RETURNED SUCCESSFULLY ^_^");
		else
			System.out.println(" :(  UPS.. BOOK FAILED TO BE RETURNED :(");
	}

	private static void option5() {
		Library  lb = new Library();
	    Book[] b = lb.getBooks();
	    System.out.println("BOOK LIST:\n ");
	    
	    for(int i = 0; i < b.length; i++)
	    	System.out.println("Title: " + b[i].getTitle() +
	    			"\nAuthor: " + b[i].getAuthor() +
	    			"\nISBN: " + b[i].getIsbn() +
	    			"\nGENRE: " + b[i].getGenre() + "\n\n***********");
	    
	    System.out.println("\n" + "THERE ARE " + lb.getBooksCount() + 
	    		" BOOKS REGISTERED IN THE SYSTEM.\n ");
		
	}
	
	private static void option6() {
		String name,dni,email;
		long currentBook = -1;
		Library lb = new Library();
		Scanner in = new Scanner(System.in);
		System.out.println("NAME & SURNAME: ");
		name = in.nextLine();
		System.out.println("DNI: ");
		dni = in.nextLine();
		System.out.println("EMAIL: ");
		email = in.nextLine();		
		
		User u = new User(name,dni,email,currentBook);
		if(lb.registerUser(u)) {
			System.out.println("^_^ USER CREATED SUCCESSFULY ^_^" + "\n");
		}else System.out.println(":(  USER FAILED TO BE CREATED :(" + "\n");
		
	}
		
	private static void option7() {
		Library  lb = new Library();
	    User[] u = lb.getUsers();
	    System.out.println("USER LIST:\n ");
	    
	    for(int i = 0; i < u.length; i++)
	    	System.out.println("Name: "+ u[i].getName() +
	    			"\nEmail: " + u[i].getEmail() +
	    			"\nDNI: " + u[i].getDni() + "\n\n***********");
	    
	    System.out.println("\n" + "THERE ARE " + lb.getUserCount() + 
	    		" USERS REGISTERED IN THE SYSTEM.\n ");
	}
}


