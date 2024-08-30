import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

class Main {
    public static void main(String[] noargs){
        // Person obj = new Person("tim", "timmy", "24521");
        // System.out.print(obj);
        //part 2 ----------------------------------------------------
        LinkedList<Person> personList = new LinkedList<>();
        String dataFile = "datafile.txt"; 
        try (Scanner input = new Scanner(new FileInputStream(dataFile))) {
            MyMain.store(input, personList);
        } catch (FileNotFoundException e) {
            System.out.println("FILE DONT EXIST DOG: " + e.getMessage());
        }
        MyMain.display(System.out, personList);
        int index = MyMain.find("003", personList);
        if (index != -1) {
            System.out.println("index of person: " + index);
        } else {
            System.out.println("Person not file with that id.");
        }
        //part 3---------------------------------------------------
        System.out.println("part 3, PersonList output: ");
        PersonList pl = new PersonList();
        try (Scanner input = new Scanner(new FileInputStream(dataFile))) {
            pl.store(input);
        } catch (FileNotFoundException e) {
            System.out.println("FILE DONT EXIST DOG: " + e.getMessage());
        }
        pl.display(System.out);
        index = pl.find("003");
        if (index != -1) {
            System.out.println("index of person: " + index);
        } else {
            System.out.println("Person not file with that id.");
        }
    }

    public static class MyMain {
        
        public static void store(Scanner input, LinkedList<Person> list) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String id = parts[2].trim();
                    list.add(new Person(firstName, lastName, id));
                }
            }
        }

        public static void display(PrintStream output, LinkedList<Person> list) {
            for (Person person : list) {
                output.println(person);
            }
        }

        public static int find(String sid, LinkedList<Person> list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(sid)) {
                    return i;
                }
            }
            return -1;
        }
    }
}

class Person {
    //part 1 -------------------------------------------------
    private String firstName;
    private String lastname;
    private String id;

    Person(String firstName, String lastname, String id){
        this.firstName = firstName;
        this.lastname = lastname;
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastname;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString(){
        return firstName + " " + lastname + " " + id;
    }
}

class PersonList {
    private LinkedList<Person> persons;

    public PersonList() {
        persons = new LinkedList<>();
    }

    public void store(Scanner input) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String id = parts[2].trim();
                persons.add(new Person(firstName, lastName, id));
            }
        }
    }

    public void display(PrintStream output) {
        for (Person person : persons) {
            output.println(person);
        }
    }

    public int find(String sid) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId().equals(sid)) {
                return i;
            }
        }
        return -1;
    }
}
