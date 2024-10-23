public class DNode {
    String name;
    DNode next;
    DNode prev;
    int points;
    boolean isHub = false;
    int visitCount;

    public DNode() {

    }

    public DNode(String name) {
        this.name = name;
        next = null;
        prev = null;
        points = 0;
        visitCount = 0;
    }

    void commonLet(String message) {
        int commonLetters = 0;
        for (int i = 0; i < this.name.length(); i++) {
            char studentChar = this.name.charAt(i);
            for (int j = 0; j < message.length(); j++) {
                if (studentChar == message.charAt(j)) {
                    commonLetters++;
                }
            }
        }
        this.points += commonLetters;
        this.visitCount++;
    }



}