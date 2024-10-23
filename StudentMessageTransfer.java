import java.util.*;
import java.io.*;

public class StudentMessageTransfer {
    public static void main(String[] args) {
        DLinkedList studentList = new DLinkedList();
        Random random = new Random();
        int names = 0;

        try (Scanner scanner = new Scanner(new File("src/names.txt"))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                names++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
            return;
        }

        for (int j = 0; j < 30; j++) {
            if (names > 0) {
                int randomLine = random.nextInt(names);
                try (Scanner scanner = new Scanner(new File("src/names.txt"))) {
                    for (int i = 0; i <= randomLine; i++) {
                        String line = scanner.nextLine();
                        if (i == randomLine) {
                            studentList.add(line);
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("File not found.");
                }
            }
        }


        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the number of hubs (max 30): ");
        int hubs = scn.nextInt();
        while (hubs > 30 || hubs <= 0) {
            System.out.println("Invalid input. There cannot be more than 30 hubs.");
            System.out.print("Try again: ");
            hubs = scn.nextInt();
        }


        studentList.assignRandomHubs(hubs);


        DNode currentStudent = studentList.getRandomNode();
        boolean directionForward = random.nextBoolean();


        scn.nextLine();
        System.out.print("Enter a message(If you enter your message Uppercase it will be better): ");
        String message = scn.nextLine();


        while (!studentList.everyoneVisited()) {
            studentList.display();
            System.out.println("Current student: " + currentStudent.name);


            currentStudent.commonLet(message);


            if (currentStudent.isHub) {
                System.out.print("Enter a new message at hub (" + currentStudent.name + "): ");
                message = scn.nextLine();
                directionForward = !directionForward;
            }


            if ((currentStudent == studentList.head && !directionForward) ||
                    (currentStudent == studentList.tail && directionForward)) {
                System.out.println("Reached the " + (currentStudent == studentList.head ? "head" : "tail") + ". Changing direction.");


                directionForward = !directionForward;


                int newStep = getRandomStep();
                System.out.println("Generated new step: " + newStep);


                if (directionForward) {
                    currentStudent = studentList.moveForward(currentStudent, newStep);
                } else {
                    currentStudent = studentList.moveBackward(currentStudent, newStep);
                }
                continue;
            }
            int steps = getRandomStep();
            System.out.println("Moving " + steps + " steps " + (directionForward ? "forward" : "backward"));
            if (directionForward) {
                currentStudent = studentList.moveForward(currentStudent, steps);
            } else {
                currentStudent = studentList.moveBackward(currentStudent, steps);
            }
        }


        System.out.println("\nAll students visited at least once.");
        studentList.display();


    }

    static int getRandomStep() {
        return new Random().nextInt(1,3) ;
    }
}

