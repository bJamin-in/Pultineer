package West;
public class HeraldsHills {
    private String[] messages;

    public String getMessage(int x) {
        return messages[x];
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public HeraldsHills(String[] messages) {
        this.messages = messages;
    }

}
