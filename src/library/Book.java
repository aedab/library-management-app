package library;

public class Book {

	private String title;
	private long isbn;
	private String author;
	private String userDni;
	private Genre genre;
	
	public enum Genre{
		NONE,ADVENTURE,CRIME,K_DRAMA,FANTASY,HORROR,MYSTERY,POETRY,THRILLER,BIOGRAPHY,JOURNAL,SCIENCE,TRAVEL
	}
	
	
	//GETTERS AND SETTERS
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getUserDni() {
		return userDni;
	}

	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}
	

	//CONSTRUCTOR
	public Book(String title, long isbn,  String author,Genre genre,  String userDni) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.genre = genre;
		this.userDni = userDni;
	}

	@Override
	public String toString() {
		return title + "," + isbn + "," + author + "," +
			genre + "," + (userDni != null ? userDni : "null");
	}

  
	@Override
	public boolean equals(Object obj) {
		
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}
	
	
	

}
