import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAccessEligibilityTest {

    @Test
    public void testEligibleEmployee() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP001",
                "Arun",
                25,
                "IT",
                "Active",
                "High",
                true
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Confidential"
            );

        assertEquals("ELIGIBLE", result);
    }

    @Test
    public void testMinimumAgeBoundary() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP002",
                "Kumar",
                21,
                "HR",
                "Active",
                "High",
                true
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Normal"
            );

        assertEquals("ELIGIBLE", result);
    }

    @Test
    public void testUnderAgeEmployee() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP003",
                "Ravi",
                20,
                "IT",
                "Active",
                "High",
                true
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Normal"
            );

        assertTrue(result.contains("NOT ELIGIBLE"));
    }

    @Test
    public void testUnauthorizedDepartment() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP004",
                "Vijay",
                30,
                "Marketing",
                "Active",
                "High",
                true
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Normal"
            );

        assertTrue(result.contains("Department is not authorized"));
    }

    @Test
    public void testInactiveEmployee() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP005",
                "Suresh",
                30,
                "Finance",
                "Inactive",
                "High",
                true
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Normal"
            );

        assertTrue(result.contains("Employment status is not active"));
    }

    @Test
    public void testInvalidEmployeeId() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP006",
                "Manoj",
                30,
                "IT",
                "Active",
                "High",
                false
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Normal"
            );

        assertTrue(result.contains("Employee ID is invalid"));
    }

    @Test
    public void testMultipleFailures() {

        EmployeeAccessEligibility.Employee employee =
            new EmployeeAccessEligibility.Employee(
                "EMP007",
                "Raj",
                19,
                "Marketing",
                "Inactive",
                "None",
                false
            );

        String result =
            EmployeeAccessEligibility.checkEligibility(
                employee,
                "Confidential"
            );

        assertTrue(result.contains("Employee must be at least 21"));
        assertTrue(result.contains("Department is not authorized"));
        assertTrue(result.contains("Employment status is not active"));
        assertTrue(result.contains("Employee ID is invalid"));
        assertTrue(result.contains("Security clearance is insufficient"));
    }
}
