package library;

public class User {
	
	private String name;
	private String dni;
	private String email;
	private long currentBook = -1;
	
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(long currentBook) {
		this.currentBook = currentBook;
	}

	
	//CONSTRUCTOR
	public User(String name, String dni, String email, long currentBook) {
		this.name = name;
		this.dni = dni;
		this.email = email;
		this.currentBook = currentBook;
		
	}
	
	@Override
	public String toString() {
		return name + "," + dni + "," + email + "," +
			currentBook;

	}

	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
   



}
