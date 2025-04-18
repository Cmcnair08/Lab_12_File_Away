import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("\n--- Enter New Record ---");

            String firstName = SafeInput.getNonZeroLenString(in, "First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Last Name");
            String email = SafeInput.getNonZeroLenString(in, "Email");
            int yearOfBirth = SafeInput.getRangedInt(in, "Year of Birth", 1900, 2025);

            String id = String.format("%06d", idCounter++);

            String record = String.join(", ", firstName, lastName, id, email, String.valueOf(yearOfBirth));
            records.add(record);

            System.out.print("Add another? (Y/N): ");
            String response = in.nextLine();
            if (!response.equalsIgnoreCase("Y")) {
                keepGoing = false;
            }
        }

        System.out.print("\nEnter filename to save (with .csv): ");
        String fileName = in.nextLine();
        String filePath = "src/" + fileName;

        try (FileWriter writer = new FileWriter(filePath)) {
            for (String rec : records) {
                writer.write(rec + "\n");
            }
            System.out.println("CSV file saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
