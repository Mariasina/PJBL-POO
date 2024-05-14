import DAO.*;
import entity.*;
import java.awt.Graphics;
import views.*;

public class App {
    public static void main(String[] args) throws Exception {
        User test = new User();
        test.setUsername("teste3");

        new UserDAO().registerUser(test);

        Menu menu = new Menu("menu");

    }
}
