import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Borrower {
    private String name;

    public Borrower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Borrower> borrowers = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Available: " + book.isAvailable());
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Borrower findBorrower(String name) {
        for (Borrower borrower : borrowers) {
            if (borrower.getName().equalsIgnoreCase(name)) {
                return borrower;
            }
        }
        return null;
    }
}

public class LMS {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Library library = new Library();

            while (true) {
                System.out.println("\nLibrary Management System Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. Display Books");
                System.out.println("3. Check Out Book");
                System.out.println("4. Return Book");
                System.out.println("5. Add Borrower");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author name: ");
                        String author = scanner.nextLine();
                        Book newBook = new Book(title, author);
                        library.addBook(newBook);
                        System.out.println("Book added successfully!");
                        break;

                    case 2:
                        library.displayBooks();
                        break;

                    case 3:
                        System.out.print("Enter the title of the book to check out: ");
                        String checkoutTitle = scanner.nextLine();
                        Book checkoutBook = library.findBook(checkoutTitle);

                        if (checkoutBook != null && checkoutBook.isAvailable()) {
                            System.out.print("Enter borrower's name: ");
                            String borrowerName = scanner.nextLine();
                            Borrower borrower = library.findBorrower(borrowerName);

                            if (borrower == null) {
                                borrower = new Borrower(borrowerName);
                                library.addBorrower(borrower);
                            }

                            checkoutBook.setAvailable(false);
                            System.out.println("Book checked out successfully to " + borrower.getName() + "!");
                        } else {
                            System.out.println("Book not available or not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter the title of the book to return: ");
                        String returnTitle = scanner.nextLine();
                        Book returnBook = library.findBook(returnTitle);

                        if (returnBook != null && !returnBook.isAvailable()) {
                            returnBook.setAvailable(true);
                            System.out.println("Book returned successfully!");
                        } else {
                            System.out.println("Book not checked out or not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter borrower's name: ");
                        String borrowerName = scanner.nextLine();
                        Borrower newBorrower = new Borrower(borrowerName);
                        library.addBorrower(newBorrower);
                        System.out.println("Borrower added successfully!");
                        break;

                    case 6:
                        System.out.println("                                           ");
                        System.out.println("Exiting Library Management System. Goodbye!");
                        System.out.println("                                           ");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }
}
