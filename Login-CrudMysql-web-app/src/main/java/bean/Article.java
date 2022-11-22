package bean;

public class Article {
	public int idArticle;
	public String nameArticle;
	public String color;
	public String size;
	public int last_worn;
	
	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getNameArticle() {
		return nameArticle;
	}

	public void setNameArticle(String nameArticle) {
		this.nameArticle = nameArticle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getLast_worn() {
		return last_worn;
	}

	public void setLast_worn(int last_worn) {
		this.last_worn = last_worn;
	}

	public Article(String nameArticle, String color, String size, Integer last_worn) {
		super();
	
		this.nameArticle = nameArticle;
		this.color = color;
		this.size = size;
		this.last_worn = last_worn;
	}
}
