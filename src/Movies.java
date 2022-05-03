import java.util.Scanner;

public class Movies extends Notes {
    String movieName;
    String movieDate;
    String movieGenre;
    String movieRating;

    public Movies(String movieName, String movieDate, String movieGenre, String movieRating) {
        this.movieName = movieName;
        this.movieDate = movieDate;
        this.movieGenre = movieGenre;
        this.movieRating= movieRating;
    }

    @Override
    public Notes addNote(String username, Notes note) {
        Movies movie = (Movies) note;
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter note's name: ");
        note.noteName = scr.nextLine();
        note.noteAuthor = username;
        System.out.println("Enter movie name: ");
        movie.movieName = scr.nextLine();
        System.out.println("Enter movie date: ");
        movie.movieDate = scr.nextLine();
        System.out.println("Enter genre: ");
        movie.movieGenre = scr.nextLine();
        System.out.println("Enter rating: ");
        movie.movieRating = scr.nextLine();
        System.out.println("Enter text of note: ");
        note.noteBody = scr.nextLine();
        return movie;
    }

    @Override
    public void print(Notes obj) {
        Movies note = (Movies) obj;
        System.out.printf("Note's name: %s, Author: %s.\n" +
                "Movie name: %s. Movie date: %s. Genre: %s. Rating: %s.\n" +
                "Note: %s", note.noteName, note.noteAuthor, note.movieName, note.movieDate, note.movieGenre, note.movieRating, note.noteBody);
    }

    @Override
    public String toString() {
        return noteName + noteAuthor + movieName + movieDate + movieGenre + movieRating + noteBody;
    }
}