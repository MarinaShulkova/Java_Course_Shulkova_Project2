import org.w3c.dom.ls.LSOutput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main<flag> {
    static Users user = new Users("", "", Roles.USER); // инициализация пользователя
    // создание массива пользователей
    static String[][] users = {{"user", "user", String.valueOf(Roles.USER)},
            {"admin", "admin", String.valueOf(Roles.ADMIN)},
            {"moderator", "moderator", String.valueOf(Roles.MODERATOR)}};
    // создание массива заметок
    static Notes[] notesArray = new Notes[5];
    static int searchedNote = 0; //Индекс найденной заметки


    static boolean login_flag = false;

    public static void main(String[] args) {
        login_flag = login();
        menu(menu_print(0));
    }

    //Процедура menu_print(int) - вывод меню, где int = 0 - основное меню, 1 - дополнительное
    public static int menu_print(int menu_type) throws MenuException {

        if (menu_type == 0) {

            System.out.println("1 Relogin \n" +
                    "2 New note \n" +
                    "3 Search note by name \n" +
                    "4 Exit \n");

            System.out.println("Choose menu item: ");
            Scanner scr = new Scanner(System.in);
            int menu_item = scr.nextInt();
            try {
                if (menu_item <= 0 || menu_item > 4) {
                    throw new MenuException(menu_item);
                }
            } catch (MenuException me) {
                System.out.println(me.getMenu() + " - Invalid value, please try again!");
                menu_print(0);
/*            } catch (InputMismatchException e) {
                    System.out.println("Invalid value, please try again!");
                    menu_print(0);
*/
            }
            return (menu_item);

        } else {
            System.out.println();
            System.out.println(
                    "1 Print note \n" +
                            "2 Rename note \n" +
                            "3 Change word in note \n" +
                            "4 Change text of note \n" +
                            "5 Print note's author \n" +
                            "6 Delete note \n" +
                            "7 Main menu \n" +
                            "8 Print array");

            System.out.println("Choose menu item: ");
            Scanner scr = new Scanner(System.in);
            int menu_item = scr.nextInt();
            try {
                if (menu_item <= 0 || menu_item > 8) {
                    throw new MenuException(menu_item);
                }
            } catch (MenuException me) {
                System.out.println(me.getMenu() + " - Invalid value, please try again!");
                menu_print(0);
/*            } catch (InputMismatchException e) {
                System.out.println("Invalid value, please try again!");
                menu_print(0);
            }
*/
            }
            return (menu_item + 4);

        }
    }


    // Процедура обработки меню menu()

    public static void menu(int menu) {
        //       Class<? extends Notes> note; //Notes note;// = new Books("","","");
        switch (menu) {
            case 1:
                login();
                menu(menu_print(0));
            case 2:
                if (login_flag && user.role != Roles.USER) {
                    switch (addNoteType()) {
                        case 1:
                            Notes book = new Books("", "", "");
                            notesArray[emptyIndex()] = book.addNote(user.username, book);
                            //                       book.print(notesArray[searchedNote]);
                            System.out.println();
                            menu(menu_print(0));
                            break;
                        case 2:
                            Notes movie = new Movies("", "", "", "");
                            notesArray[emptyIndex()] = movie.addNote(user.username, movie);
//                        movie.print(notesArray[searchedNote]);
                            System.out.println();
                            menu(menu_print(0));
                            break;
                        case 3:
                            Notes recipe = new Recipes("", "");
                            notesArray[emptyIndex()] = recipe.addNote(user.username, recipe);
                            //                      recipe.print(notesArray[searchedNote]);
                            System.out.println();
                            menu(menu_print(0));
                            break;
                        default:
                            System.out.println("Выбрано неверное значение"); //// ИСКЛЮЧЕНИЯ!!!
                    }
                } else System.out.println("Insufficient user rights for create notes");
                menu(menu_print(0));
                break;
            case 3:
                searchedNote = searchNote();
                if (searchedNote == -1) menu(menu_print(0));
                else menu(menu_print(1));
            case 4:
                System.exit(0);
            case 5:
                notesArray[searchedNote].print(notesArray[searchedNote]);
                menu(menu_print(1));
                break; // вызов процедуры вывода заметки в консоль
            case 6:
                changeName(searchedNote);
                menu(menu_print(1));
                break; // вызов процедуры изменения названия заметки
            case 7:
                changeWord(searchedNote);
                menu(menu_print(1));
                break; // вызов процедуры замены слова в заметке
            case 8:
                changeBody(searchedNote);
                menu(menu_print(1));
                break; // вызов процедуры замены тела заметки
            case 9:
                printAuthor(searchedNote);
                menu(menu_print(1));
                break; // вызов процедуры вывода автора заметки
            case 10:
                deleteNote(searchedNote);
                menu(menu_print(0));
                break; // вызов процедуры удаления заметки
            case 11:
                menu(menu_print(0));
                break; // возврат в основное меню
            case 12:
                printArray(notesArray);
                menu(menu_print(0));
        }

    }

    // Процедура авторизации текущего пользователя
    public static boolean login() {
        Scanner scr = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0:
                    System.out.println("Enter your username: ");
                    user.username = scr.nextLine();
                    break;
                case 1:
                    System.out.println("Enter your password: ");
                    user.password = scr.nextLine();
                    break;
            }
        }
        // проверка введённых данных с пользователями в массиве
        for (int i = 0; i < users.length; i++) {
            if (users[i][0].equals(user.username) && users[i][1].equals(user.password)) {
                user.role = Roles.valueOf(users[i][2]);
                login_flag = true;
                break;
            } else
                login_flag = false;
        }
        if (!login_flag) System.out.println("Login or password incorrect. Please try again.");
        return login_flag;
    }

    // Процедура инициализации типа заметки addNoteType()
    public static int addNoteType() {
        Scanner scr = new Scanner(System.in);
        System.out.println("Choose note type: \n1. Book\n2. Movie\n3. Recipe");
        int noteType = scr.nextInt();
        return noteType;
    }

    // Процедура поиска заметки по названию
    public static int searchNote() throws NameException {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter note name to search");
        String noteName = scr.nextLine();


        for (int i = 0; i < 5; i++) {
            if (notesArray[i] == null || notesArray[i].noteName == null) continue;
            else if (noteName != null && notesArray[i].noteName.equals(noteName))
                return i;
        }
        if (noteName != "")
            System.out.printf("There is no any notes with %s name", noteName);
        System.out.println();

        try {
            if (noteName == "") {
                throw new NameException();
            }
        } catch (NameException ne) {
            System.out.println("You didn't enter any note. Please, choose menu 3 and enter note name to search");
            System.out.println();

        }
        return -1;                                                      ////////// Exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    }

    // Процедура переименования заметки
    public static void changeName(int index) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter new note name: ");
        String noteName = scr.nextLine();
        notesArray[index].noteName = noteName;
        System.out.println("Note name is changed to " + noteName);
    }

    // Процедура изменения тела заметки
    public static void changeBody(int index) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter new text of note: ");
        String noteBody = scr.nextLine();
        notesArray[index].noteBody = noteBody;
        System.out.println("Note name is changed to " + noteBody);
    }

    // Процедура вывода автора заметки
    public static void printAuthor(int index) {
        System.out.println("Note's author name is " + notesArray[index].noteAuthor);
    }

    // Процедура удаления заметки
    public static void deleteNote(int index) {
        System.out.println("Note " + notesArray[index].noteName + " is deleted.");
        notesArray[index].noteName = null;

    }

    // Процедура удаления заметки
    public static void changeWord(int index) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter changed word: ");
        String wordOld = scr.next();
        System.out.println("Enter new word: ");
        String wordNew = scr.next();

        String[] array = new String[10];
        array = notesArray[index].noteBody.split(" ");
        String body = "";
        for (String s : array) {
            if (s.equals(wordOld)) s = wordNew;
            body = body + s + " ";
        }
        notesArray[index].noteBody = body;
        System.out.println("Word" + wordOld + "Changed to " + wordNew);
    }

    public static void printArray(Notes[] array) {
        for (Notes notes : array) {
            System.out.print("notesArray[]: ");
            if (notes == null) System.out.println("null");
            else System.out.println(notes.toString());
        }
    }


    public static int emptyIndex() {
        for (int i = 0; i < notesArray.length; i++) {
            if (notesArray[i] == null || notesArray[i].noteName == null) {
                searchedNote = i;
                return searchedNote;
            }
        }
        System.out.println("There is not enough space for create new note");
        menu(menu_print(0));
        return -1;
    }
}