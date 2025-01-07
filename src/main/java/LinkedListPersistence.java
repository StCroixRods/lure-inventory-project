import java.io.*;
import java.util.LinkedList;

public class LinkedListPersistence {

    private static final String FILE_PATH = System.getProperty("user.home") + "/Desktop/LureData/linked_list_data";

    @SuppressWarnings("unchecked") // Safe because we know the type
    public static LinkedList<Lure> loadLinkedList() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new LinkedList<>(); // Return an empty LinkedList if the file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (LinkedList<Lure>) ois.readObject(); // Deserialize the LinkedList
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading LinkedList: " + e.getMessage());
            return new LinkedList<>(); // Return an empty list on error
        }
    }
    public static void saveLinkedList(LinkedList<Lure> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(list); // Serialize the LinkedList
            System.out.println("LinkedList saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving LinkedList: " + e.getMessage());
        }
    }
}
