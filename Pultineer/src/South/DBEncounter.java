package South;

public class DBEncounter {
    // Variables

    private String message;

    // Getters

    public String getMessage() {
        return message;
    }

    // Setters

    public void setMessage(String message) {
        this.message = message;
    }

    // Constructors

    public DBEncounter(){
        message = "Looking at the chaos ahead, you see several soldiers sprawled across the sand. Some are unconscious, \nwhile others appear to be gravely injured. Sir Branric being the last standing soldier, is the last line of \ndefense still battling the beast. The Demon Beast towers over him, its massive form casting a shadow that engulfs \nthe area. Its eyes glow with a sinister red hue, and its breath is like a scorching wind that ripples through \nthe air. Just as you arrive, Sir Branric falls, wounded and exhausted, leaving the battle to you and your group.";
    }

    public DBEncounter(String message){
        this.message = message;
    }
}
