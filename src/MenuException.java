public class MenuException extends RuntimeException{

    private int menu;

    public MenuException (int menu) {
        this.menu =menu;
    }
/*
  public MenuException (String massage, int menu) {
        super(massage);
        this.menu = menu;
    }

*/

    public int getMenu() {
        return menu;
    }

}
