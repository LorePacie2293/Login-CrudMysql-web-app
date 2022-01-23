package bean;

public class Book {
	public int id_book;
	public String name_book;
	public String first_name;
	public String last_name;
	public String year_released;
	public int stock_quantity;
	public int pages;
	
	public Book(String name_book, String first_name, String last_name, String year_released,
			int stock_quantity, int pages) {
		super();
		this.name_book = name_book;
		this.first_name = first_name;
		this.last_name = last_name;
		this.year_released = year_released;
		this.stock_quantity = stock_quantity;
		this.pages = pages;
	}

	public int getId_book() {
		return id_book;
	}

	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	public String getName_book() {
		return name_book;
	}

	public void setName_book(String name_book) {
		this.name_book = name_book;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getYear_released() {
		return year_released;
	}

	public void setYear_released(String year_released) {
		this.year_released = year_released;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public int getPages() {
		return pages;
	}

	public void setPage(int page) {
		this.pages = page;
	}
	
	
}
