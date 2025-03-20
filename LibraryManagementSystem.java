public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }
}

abstract class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public abstract void borrowBook(Book book);
    public abstract void returnBook(Book book);
}

public class Member extends User {
    public Member(String name) {
        super(name);
    }

    @Override
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrow();
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is not available.");
        }
    }

    @Override
    public void returnBook(Book book) {
        book.returnBook();
        System.out.println(name + " returned " + book.getTitle());
    }
}

public class Librarian extends User {
    public Librarian(String name) {
        super(name);
    }

    @Override
    public void borrowBook(Book book) {
        System.out.println("Librarians cannot borrow books.");
    }

    @Override
    public void returnBook(Book book) {
        book.returnBook();
        System.out.println(name + " returned " + book.getTitle());
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println(name + " added " + book.getTitle() + " to the library.");
    }
}

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void viewAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Alice");
        Member member = new Member("Bob");

        // Adding books
        Book book1 = new Book("1984", "George Orwell");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        librarian.addBook(library, book1);
        librarian.addBook(library, book2);

        // View available books
        library.viewAvailableBooks();

        // Borrowing a book
        member.borrowBook(book1);
        library.viewAvailableBooks();

        // Returning a book
        member.returnBook(book1);
        library.viewAvailableBooks();
    }
}