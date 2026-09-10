import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeAccessEligibility {

    // Employee class to store employee details
    static class Employee {
        String employeeId;
        String name;
        int age;
        String department;
        String employmentType;
        String securityClearance;
        boolean idValid;

        Employee(String employeeId, String name, int age,
                 String department, String employmentType,
                 String securityClearance, boolean idValid) {

            this.employeeId = employeeId;
            this.name = name;
            this.age = age;
            this.department = department;
            this.employmentType = employmentType;
            this.securityClearance = securityClearance;
            this.idValid = idValid;
        }
    }

    // Method to determine access eligibility
    public static String checkEligibility(Employee employee,
                                          String requestedAccess) {

        List<String> reasons = new ArrayList<>();

        // Rule 1: Age
        if (employee.age < 21) {
            reasons.add("Employee must be at least 21 years old.");
        }

        // Rule 2: Authorized department
        if (!isAuthorizedDepartment(employee.department)) {
            reasons.add("Department is not authorized.");
        }

        // Rule 3: Active employment
        if (!employee.employmentType.equalsIgnoreCase("Active")) {
            reasons.add("Employment status is not active.");
        }

        // Rule 4: Valid employee ID
        if (!employee.idValid) {
            reasons.add("Employee ID is invalid.");
        }

        // Rule 5: Security clearance
        if (requestedAccess.equalsIgnoreCase("Confidential")) {

            if (!hasRequiredClearance(employee.securityClearance)) {
                reasons.add(
                    "Security clearance is insufficient for confidential access."
                );
            }
        }

        // Classification
        if (reasons.size() == 0) {
            return "ELIGIBLE";
        }

        // Conditional eligibility
        if (reasons.size() == 1 &&
            reasons.get(0).contains("Security clearance")) {

            return "CONDITIONALLY ELIGIBLE";
        }

        return "NOT ELIGIBLE - " + String.join(" ", reasons);
    }

    // Check authorized departments
    public static boolean isAuthorizedDepartment(String department) {

        return department.equalsIgnoreCase("IT") ||
               department.equalsIgnoreCase("HR") ||
               department.equalsIgnoreCase("Finance") ||
               department.equalsIgnoreCase("Administration");
    }

    // Check security clearance
    public static boolean hasRequiredClearance(String clearance) {

        return clearance.equalsIgnoreCase("High") ||
               clearance.equalsIgnoreCase("Top Secret");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter number of employees: ");
            int n = sc.nextInt();
            sc.nextLine();

            if (n <= 0) {
                throw new IllegalArgumentException(
                    "Number of employees must be greater than zero."
                );
            }

            for (int i = 0; i < n; i++) {

                System.out.println("\n=================================");
                System.out.println("       EMPLOYEE " + (i + 1));
                System.out.println("=================================");

                System.out.print("Enter Employee ID: ");
                String employeeId = sc.nextLine();

                System.out.print("Enter Employee Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                if (age < 0) {
                    throw new IllegalArgumentException(
                        "Age cannot be negative."
                    );
                }

                System.out.print(
                    "Enter Department (IT/HR/Finance/Administration): "
                );
                String department = sc.nextLine();

                System.out.print(
                    "Enter Employment Status (Active/Inactive): "
                );
                String employmentType = sc.nextLine();

                System.out.print(
                    "Enter Security Clearance (None/Low/High/Top Secret): "
                );
                String securityClearance = sc.nextLine();

                System.out.print(
                    "Is Employee ID Valid? (yes/no): "
                );
                String idInput = sc.nextLine();

                boolean idValid;

                if (idInput.equalsIgnoreCase("yes")) {
                    idValid = true;
                } else if (idInput.equalsIgnoreCase("no")) {
                    idValid = false;
                } else {
                    throw new IllegalArgumentException(
                        "Employee ID validity must be yes or no."
                    );
                }

                System.out.print(
                    "Enter Requested Access (Normal/Confidential): "
                );
                String requestedAccess = sc.nextLine();

                Employee employee = new Employee(
                    employeeId,
                    name,
                    age,
                    department,
                    employmentType,
                    securityClearance,
                    idValid
                );

                String result = checkEligibility(
                    employee,
                    requestedAccess
                );

                System.out.println("\n---------------------------------");
                System.out.println("       ACCESS RESULT");
                System.out.println("---------------------------------");

                System.out.println(
                    "Employee ID       : " + employee.employeeId
                );
                System.out.println(
                    "Employee Name     : " + employee.name
                );
                System.out.println(
                    "Department        : " + employee.department
                );
                System.out.println(
                    "Employment Status : " + employee.employmentType
                );
                System.out.println(
                    "Security Clearance: " + employee.securityClearance
                );
                System.out.println(
                    "Requested Access  : " + requestedAccess
                );

                System.out.println(
                    "Eligibility       : " + result
                );

                System.out.println("---------------------------------");
            }

        } catch (Exception e) {

            System.out.println(
                "Invalid input: " + e.getMessage()
            );

        } finally {

            sc.close();
        }
    }
}
