import java.util.*;
import java.io.*;
public class FinalProjectBudgetingSystem {
    // main method
    public static void main(String [] args){
        Scanner console = new Scanner(System.in);

        // prompt user for code
        System.out.println("Welcome to the Budgeting Planner!");
        System.out.println("This program will divide your income into three budgeting categories based on the 50/20/30 rule.");

        // get user input and split income into categories inside an array through method
        double income = incomeUserInput(console);
        double[] budgetCalculations = calculateBudget(console, income);

        // assign budgetCalculations to individual variables
        double essentialsBudget = budgetCalculations[0];
        double financeGoalsBudget = budgetCalculations[1];
        double personalBudget = budgetCalculations[2];

        // essential
        String[] essentialQuestions = readQuestionsFromFile("src/essentials.txt");
        boolean[] essentialQuestionAnswers = answerQuestions(console, essentialQuestions);

        // finance goals
        String[] financeGoalsQuestions = readQuestionsFromFile("src/finance_goals.txt");
        boolean[] financeGoalsQuestionAnswers = answerQuestions(console, financeGoalsQuestions);

        // personal
        String[] personalQuestions = readQuestionsFromFile("src/personal_expenses.txt");
        boolean[] personalQuestionAnswers = answerQuestions(console, personalQuestions);

        // display results
        evaluateEssentials(essentialsBudget, essentialQuestionAnswers, "Essentials Expenses");
        evaluateFinanceGoals(financeGoalsBudget, financeGoalsQuestionAnswers, "Finance Goals Expenses");
        evaluatePersonal(personalBudget, personalQuestionAnswers, "Personal Expenses");

        // ending message
        System.out.println("Thank you for using our Budgeting Planner.");
        System.out.println("Any remaining balances in each category can be factored into whatever you see fit.");
        System.out.println("Best of luck managing your finances!");
    }
    public static double incomeUserInput(Scanner console) {
        double income = 0.0;

        System.out.print("Enter your monthly income: $");
        //while loop to check if user enters a valid double for income
        while (true) {
            if (console.hasNextDouble()){
                income = console.nextDouble();
                break;
            }
            // if user doesn't input a valid double
            else{
                System.out.print("Invalid input. Please enter your monthly income: $");
                console.next();
            }
        }
        return income;
    }
    public static double[] calculateBudget(Scanner console, double salary){
        double essentials = Math.round(salary * 0.50 * 100.0) / 100.0;
        double financeGoals = Math.round(salary * 0.20 * 100.0) / 100.0;
        double personal = Math.round(salary * 0.30 * 100.0) / 100.0;
        return new double[]{essentials, financeGoals, personal};
    }
    public static String[] readQuestionsFromFile(String fileName){
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            StringBuilder questionsBuilder = new StringBuilder();

            // Read all lines and store in a single string
            while (fileScanner.hasNextLine()) {
                questionsBuilder.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();

            // Split the questions into an array
            return questionsBuilder.toString().split("\n");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + fileName + " not found.");
            return new String[0];
        }
    }
    public static boolean[] answerQuestions(Scanner console, String[] questions){
        boolean[] answers = new boolean[questions.length];

        System.out.println("Please answer the following questions with 'yes' or 'no':");
        for (int i = 0; i < questions.length; i++) {
            while (true) {
                System.out.println(questions[i]);
                String response = console.next().toLowerCase();
                if (response.equals("yes") || response.equals("no")) {
                    answers[i] = response.equals("yes");
                    break;
                } else {
                    System.out.println("Enter a valid answer. Either yes or no.");
                    console.nextLine();
                }
            }
        }

        return answers;
    }
    public static void evaluateEssentials(double budget, boolean[] answers, String categoryName){
        double remaining = budget;
        System.out.println("Essential Expenses Category: ");
        if (answers[0]){
            double rent = Math.round(budget * 0.60);
            remaining -= rent;
            System.out.println("Your recommended rent should be roughly around: " + rent);
        } if (answers[1]){
            double utilities = Math.round(budget * 0.15);
            remaining -= utilities;
            System.out.println("Your recommended utilities spending amount is: " + utilities);
        } if (answers[2]) {
            double groceries = Math.round(budget * 0.25);
            remaining -= groceries;
            System.out.println("Your recommended grocery spending amount is: " + groceries);
        }if (remaining <= 0){
            remaining = 0;
        }
        System.out.printf("Your remaining balance in essential expenses is: %.2f \n", remaining);
        System.out.println("This remaining amount can be factored into essential expenses you might have.");
        System.out.println();
    }
    public static void evaluateFinanceGoals(double budget, boolean[] answers, String categoryName) {
        double remaining = budget;
        System.out.println("Financial Goals Category: ");
        if (answers[0]){
            double retirement = Math.round(budget * 0.33);
            remaining -= retirement;
            System.out.println("Your recommended retirement fund savings should be: " + retirement);
        } if (answers[1]){
            double purchase = Math.round(budget * 0.33);
            remaining -= purchase;
            System.out.println("Your recommended big purchase savings should be: " + purchase);
        } if (answers[2]) {
            double emergency = Math.round(budget * 0.33);
            remaining -= emergency;
            System.out.println("Your recommended emergency fund savings should be: " + emergency);
        }if (remaining <= 0){
            remaining = 0;
        }
        System.out.printf("Your remaining balance in savings is: %.2f \n", remaining);
        System.out.println("This remaining amount can be factored into any thing in the savings category.");
        System.out.println();
    }
    public static void evaluatePersonal( double budget, boolean[] answers,String categoryName){
        double remaining = budget;
        System.out.println("Personal Expenses Category: ");
        if (answers[0]){
            double eat = Math.round(budget * 0.25);
            remaining -= eat;
            System.out.println("Your recommended eating out budget is: " + eat);
        } if (answers[1]){
            double entertainment = Math.round(budget * 0.25);
            remaining -= entertainment;
            System.out.println("Your recommended entertainment spending amount is: " + entertainment);
        } if (answers[2]) {
            double clothing = Math.round(budget * 0.25);
            remaining -= clothing;
            System.out.println("Your recommended clothing/accessories spending amount is: " + clothing);
        } if (answers[3]){
            double hobbies = Math.round(budget * 0.25);
            remaining -= hobbies;
            System.out.println("Your recommended hobby spending amount is: " + hobbies);
        }if (remaining <= 0){
            remaining = 0;
        }

        System.out.printf("Your remaining balance in personal expenses is: %.2f \n", remaining);
        System.out.println("This remaining amount can be factored into any thing in the personal savings category.");
        System.out.println();
    }
}

/*
Main method:
    display message to user of what this program is for:
    this method will divide a user's income into a budgeting system to
    give them an idea of what they can afford based on their income

    prompt user with messages regarding the budgeting system
    set array = run 50/20/30 budget method (will return an array)
    set divisions into their own doubles
    double for 50%/essential
    double for 30%/financeGoals
    double for 20%/personal

    type array = run question method for essentials
    type array = run question method for finance goals
    type array = run question method for personal expenses
    (methods will return arrays)

    run essential expenses method taking in two parameters: scanner and array dedicated for essentials question answers, and double for essentials
    run finance goals method taking in two parameters: scanner and array dedicated for finance goals question answers, and double for finance goals
    run personal expenses method taking in two parameters: scanner and array dedicated for personal expense question answers, and double for personal

calculate 50/20/30 budget method (parameter will be scanner, user :
    ask user for monthly income

    calculate income by dividing it into 3 sectors
    create array for these divisions
    create loop to append division into array
        50% for essentials and set to a double
        20% for finance goals and set to a double
        30% for personal expenses and set to double
    (all these values will be truncated to 2 decimal places).


questionsEssentials method (parameters will include scanner, array for essentials):
    scanner will read off of file for essential expense questions
    create loop for scanner to read off each line in file:
        file will have questions that will be displayed on screen using SOP
        user will input yes or no for each question displayed
        each answer to the questions will be stored in an array


questionsFinanceGoals method (parameters will be scanner, array for finance goals):
    scanner will read off of file for finance goals expense questions
    create loop for scanner to read off each line in file:
        file will have questions that will be displayed on screen using SOP
        user will input yes or no for each question displayed
        each answer to the questions will be stored in an array


questionsPersonalExpenses method (parameters will be scanner, array for personal expenses):
    scanner will read off of file for personal expense questions
    create loop for scanner to read off each line in file:
        file will have questions that will be displayed on screen using SOP
        user will input yes or no for each question displayed
        each answer to the questions will be stored in an array

all the following methods will be calculated using the 50/20/30 rule for budgeting

essentialExpenses Method (parameters will be an array for questions regarding essentials, double of divided income essentials):
    create if statements for each array index and display messages if the array index allows it
    in each if statement display message regarding the questions

financeGoals Method:
    create if statements for each array index and display messages if the array index allows it
    in each if statement display message regarding the questions

personalExpenses Method:
    create if statements for each array index and display messages if the array index allows it
    in each if statement display message regarding the questions

 */