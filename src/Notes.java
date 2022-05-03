
public abstract class Notes implements Addable, Deletable, Editable, Printable{
    String noteName;
    //    String noteType; // 1 - books, 2 - movies, 3 - recipes
    String noteBody;
    String noteAuthor;

    @Override
    public String toString() {
        return super.toString();
    }

}