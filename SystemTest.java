import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SystemTest {
    private UserManager userManager;

    @BeforeEach
    public void setUp() throws IOException {
        // Set up a sample Admin.csv file with test data
        BufferedWriter writer = new BufferedWriter(new FileWriter("Admin.csv"));
        writer.write("1,adminUser,admin@example.com,adminPass\n");
        writer.close();

        userManager = UserManager.getInstance();
    }

    @Test
    public void testLoadAdminsFromFile() {
        userManager.loadAdminsFromFile();

        // Check if admin user is loaded
        boolean adminExists = userManager.getUsers().stream()
                .anyMatch(user -> user instanceof AdminUser && user.getUsername().equals("adminUser"));

        assertTrue(adminExists, "Admin user should be loaded from Admin.csv");
    }

    @Test
    public void testSaveAdminsToFile() throws IOException {
        AdminUser adminUser = new AdminUser("adminUser", "admin@example.com", "adminPass");
        userManager.getUsers().add(adminUser);

        userManager.saveAdminsToFile();

        // Read the file to verify it contains the admin user's data
        try (BufferedReader reader = new BufferedReader(new FileReader("Admin.csv"))) {
            String line = reader.readLine();
            assertNotNull(line, "Admin.csv should not be empty");
            assertEquals("1,adminUser,admin@example.com,adminPass", line, "Admin.csv should contain the admin user's data");
        }
    }
}
