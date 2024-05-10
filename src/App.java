import entity.*;
import DAO.*;

public class App {
    public static void main(String[] args) throws Exception {
        User test = new User();
        test.setUsername("Test");

        new UserDAO().registerUser(test);
    }
}
