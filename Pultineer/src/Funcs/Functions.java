package Funcs;
public class Functions {
    // Delay
    public static void delay(int x) {
        // Functionality: Sleeps the thread to delay output
        try {
            Thread.sleep(x);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }// End of delay(int x)
}
