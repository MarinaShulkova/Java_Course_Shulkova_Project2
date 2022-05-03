
import java.util.Scanner;

public class Recipes extends Notes  {
    String recipeName;
    String recipeCalories;

    public Recipes(String recipeName, String recipeCalories) {
        this.recipeName = recipeName;
        this.recipeCalories = recipeCalories;
    }

    @Override
    public Notes addNote(String username, Notes note) {
        Recipes recipe = (Recipes) note;
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter note's name: ");
        note.noteName = scr.nextLine();
        note.noteAuthor = username;
        System.out.println("Enter recipe name");
        recipe.recipeName = scr.nextLine();
        System.out.println("Enter calories: ");
        recipe.recipeCalories = scr.nextLine();
        System.out.println("Enter text of note: ");
        note.noteBody = scr.nextLine();
        return recipe;
    }

    @Override
    public void print(Notes obj) {
        Recipes note = (Recipes) obj;
        System.out.printf("Note's name: %s, Author: %s.\n" +
                "Recipe name: %s. Calories: %s.\n" +
                "Note: %s", note.noteName, note.noteAuthor, note.recipeName, note.recipeCalories, note.noteBody);
    }

    @Override
    public String toString() {
        return noteName + noteAuthor + recipeName + recipeCalories + noteBody;
    }
}