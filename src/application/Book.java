package application;

public class Book {
    private int id;
    private String title;
    private String author;
    private String status;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = "Available";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void issueBook() {
        this.status = "Issued";
    }

    public void returnBook() {
        this.status = "Available";
    }
}
