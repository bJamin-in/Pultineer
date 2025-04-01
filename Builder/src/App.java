import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            // new ProcessBuilder("cmd", "/c", "start").inheritIO().start();
            // ProcessBuilder process = new ProcessBuilder("java", "-jar", "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Builder\\lib\\Game.jar");
            // process.inheritIO().start();

            new ProcessBuilder("cmd", "/c", "start", "java", "-jar", "Game.jar").start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
