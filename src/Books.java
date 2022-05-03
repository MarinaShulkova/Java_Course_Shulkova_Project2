import java.awt.print.Book;
import java.util.Scanner;

public class Books extends Notes {
    String bookName;
    String bookWriter;
    String bookPublisher;

    public Books(String bookName, String bookWriter, String bookPublisher) {
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookPublisher = bookPublisher;
    }

    @Override
    public Notes addNote(String username, Notes note) {
        Books book = (Books) note;
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter note's name: ");
        note.noteName = scr.nextLine();
        note.noteAuthor = username;
        System.out.println("Enter book's name: ");
        book.bookName = scr.nextLine();
        System.out.println("Enter writer: ");
        book.bookWriter = scr.nextLine();
        System.out.println("Enter publisher: ");
        book.bookPublisher = scr.nextLine();
        System.out.println("Enter text of note: ");
        note.noteBody = scr.nextLine();
        return book;
    }

    @Override
    public void print(Notes obj) {
        Books note = (Books) obj;
        System.out.printf("Note's name: %s, Author: %s.\n" +
                "Book's name: %s. Writer: %s. Publisher: %s.\n" +
                "Note: %s", note.noteName, note.noteAuthor, note.bookName, note.bookWriter, note.bookPublisher, note.noteBody);
    }

    @Override
    public String toString() {
        return noteName + noteAuthor + bookName + bookWriter + bookPublisher + noteBody;
    }
}
