import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class GradeCalculator {
    private static ArrayList<Double> grades = new ArrayList<>();
    private static DecimalFormat df = new DecimalFormat("#.##");
    
    public static void main(String[] args) {
        showWelcomeMessage();
        
        int choice = 0;

        while (choice != 4)
        {
            choice = showMenu();
            processChoice(choice);
        }
        
        showGoodbyeMessage();
    }
    
    /**
     * Shows welcome message to user
     */
    private static void showWelcomeMessage() {
        JOptionPane.showMessageDialog(null,
            "Welcome to the Grade Calculator System!\n\n" +
            "This program will help you track your grades\n" +
            "and calculate your current average.",
            "Grade Calculator",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Displays main menu and gets user choice
     * @return user's menu choice
     */
    private static int showMenu() {
        String[] options = {
            "1. Add a Grade",
            "2. View Current Average", 
            "3. View Letter Grade",
            "4. Exit"
        };
        
        String menu = "Grade Calculator Menu\n" +
                     "=====================\n\n";
        for (String option : options) {
            menu += option + "\n";
        }
        menu += "\nPlease enter your choice (1-4):";
        
        int choice = 0;
        boolean validChoice = false;
        
        while (validChoice == false) {
            try {
                String input = JOptionPane.showInputDialog(null, menu,
                    "Grade Calculator Menu", JOptionPane.QUESTION_MESSAGE);
                
                if (input == null) {
                    choice = 4; // Treat cancel as exit
                    validChoice = true;
                } else {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 4) {
                        validChoice = true;

                    } else {
                        validChoice = false;
                        JOptionPane.showMessageDialog(null,
                            "Please enter a number between 1 and 4.",
                            "Invalid Choice", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                    "Please enter a valid number.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } 
        }
        return choice;
    }
    
    /**
     * Processes user's menu choice
     * @param choice the selected menu option
     */
    private static void processChoice(int choice) {
        switch (choice) {
            case 1:
                addGrade();
                break;
            case 2:
                viewAverage();
                break;
            case 3:
                viewLetterGrade();
                break;
            case 4:
                // Exit - handled in main loop
                break;
            default:
                JOptionPane.showMessageDialog(null,
                    "Invalid choice. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Adds a new grade to the collection
     */
    private static void addGrade() 
    {
        Boolean valid = false;
        String grade = null;

        while (valid != true)
        {
        grade = JOptionPane.showInputDialog(null, 
            "Enter a grade: ", "Add Grade", JOptionPane.QUESTION_MESSAGE);

            
            if (grade.matches("[0-9]+") && Double.parseDouble(grade) >= 0 && Double.parseDouble(grade) <= 100)
            {
                valid = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid entry. Please enter a number from 0-100", 
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        grades.add(Double.parseDouble(grade)); 
    }
    
    /**
     * Calculates and displays current average
     */
    private static void viewAverage() {
        
        Double average = calculateAverage();
        JOptionPane.showMessageDialog(null, "Average Grade: " + Math.round(average * 100.0)/100.0, 
        "Average Grade", JOptionPane.INFORMATION_MESSAGE);

    }
    
    /**
     * Determines and displays letter grade based on average
     */
    private static void viewLetterGrade() {

        Double average = calculateAverage();
        String grade = getLetterGrade();
        
        JOptionPane.showMessageDialog(null, "Final Grade: " + Math.round(average * 100.0)/100.0 + 
        "\nLetter Grade: " + grade, "Final Grade", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Shows goodbye message
     */
    private static void showGoodbyeMessage() {
        String message = "Thank you for using Grade Calculator!\n\n";
        if (!grades.isEmpty()) {
            double average = calculateAverage();
            message += "Final Statistics:\n" +
                      "Total Grades: " + grades.size() + "\n" +
                      "Final Average: " + df.format(average) + "%\n" +
                      "Letter Grade: " + getLetterGrade();
        }
        
        JOptionPane.showMessageDialog(null, message,
            "Goodbye", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static double calculateAverage() {

        Double average = 0.0;

        if (grades.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "No grades entered", 
            "Grade Average", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            for (int i = 0; i < grades.size(); i++)
            {
                average += grades.get(i);
            }

            average = average / grades.size();
        }
        return average;
    }
    
    private static String getLetterGrade() {
        
        String finalGrade = "";
        Double average = calculateAverage();

        if (average >= 90)
        {
            finalGrade = "A";
        }
        else if (average >= 80)
        {
            finalGrade = "B";
        }
        else if (average >= 70)
        {
            finalGrade = "C";
        }
        else if (average >= 60)
        {
            finalGrade = "D";
        }
        else
        {
            finalGrade = "F";
        }

        return finalGrade;
    }
}