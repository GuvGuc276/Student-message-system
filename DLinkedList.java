import java.util.Random;
import java.util.Random;

class DLinkedList {
    DNode head;
    DNode tail;
    int size;

    void add(String name, boolean isHub) {
        DNode newNode = new DNode(name);
        newNode.isHub = isHub;

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }


    void add(String name) {
        add(name, false);
    }


    void display() {
        DNode current = head;
        while (current != null) {
            System.out.print(current.name +
                    (current.isHub ? "*" : "") +
                    " (Points: " + current.points +
                    ", Visits: " + current.visitCount + ") <---> ");
            current = current.next;
        }
        System.out.println();
    }



    void assignRandomHubs(int hubCount) {
        if (hubCount > size) {
            System.out.println("Number of hubs cannot exceed total students.");
            return;
        }

        Random random = new Random();
        int assignedHubs = 0;

        while (assignedHubs < hubCount) {
            DNode candidate = getRandomNode();
            if (!candidate.isHub) {
                candidate.isHub = true;
                assignedHubs++;
            }
        }
    }


    boolean everyoneVisited() {
        DNode current = head;
        while (current != null) {
            if (current.visitCount == 0)
                return false;
            current = current.next;
        }
        return true;
    }


    DNode moveForward(DNode start, int steps) {
        DNode current = start;
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current;
    }


    DNode moveBackward(DNode start, int steps) {
        DNode current = start;
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current;
    }


    DNode getRandomNode() {
        if (size == 0)
            return null;

        Random random = new Random();
        int randomIndex = random.nextInt(size);
        DNode current = head;

        for (int i = 0; i < randomIndex; i++) {
            current = current.next;
        }
        return current;
    }
}
