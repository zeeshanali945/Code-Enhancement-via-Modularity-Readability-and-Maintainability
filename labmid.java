import java.util.Scanner;

// Represents a single examination question.
class Question {
    private String id;
    private String questionStatement;
    private String answer;

    public Question(String id, String questionStatement, String answer) {
        this.id = id;
        this.questionStatement = questionStatement;
        this.answer = answer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public String getAnswer() {
        return answer;
    }
}

// Stores information shared by people in the system.
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Represents a student participating in an examination.
class Student extends Person {
    private String registrationId;
    private String password;

    public Student() {
    }

    public Student(String registrationId, String password, String name, int age) {
        super(name, age);
        this.password = password;
        this.registrationId = registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getPassword() {
        return password;
    }
}

// Handles examination validation, execution, scoring, and result display.
class Exam {
    private static final double MARKS_PER_QUESTION = 5.0;
    private static final double PASS_PERCENTAGE = 50.0;

    private Question[] questions;
    private Student student;
    private double score;
    private boolean examTaken;

    public Exam(Student student, Question[] questions) {
        this.questions = questions;
        this.student = student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Student getStudent() {
        return student;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public boolean validateExam() {
        if (student == null) {
            System.out.println("A student is required before the exam can begin.");
            return false;
        }

        if (questions == null || questions.length == 0) {
            System.out.println("No questions are available.");
            return false;
        }

        for (Question question : questions) {
            if (question == null) {
                System.out.println("The exam contains an invalid question.");
                return false;
            }
        }

        return true;
    }

    public void takeExam(Scanner scanner) {
        if (!validateExam()) {
            return;
        }

        if (examTaken) {
            System.out.println("The exam has already been taken.");
            return;
        }

        score = 0;
        for (int questionIndex = 0;
             questionIndex < questions.length;
             questionIndex++) {
            displayQuestion(questionIndex);
            String userAnswer = getAnswer(scanner);

            if (isCorrectAnswer(questions[questionIndex], userAnswer)) {
                score += MARKS_PER_QUESTION;
            }
        }
        examTaken = true;
    }

    private void displayQuestion(int questionIndex) {
        Question question = questions[questionIndex];
        int questionNumber = questionIndex + 1;

        System.out.println(
                "Question " + questionNumber + ": "
                        + question.getId() + ": "
                        + question.getQuestionStatement()
        );
    }

    private String getAnswer(Scanner scanner) {
        while (true) {
            System.out.println("Enter Your Answer:");
            String answer = scanner.nextLine().trim();

            if (!answer.isEmpty()) {
                return answer;
            }

            System.out.println("Answer cannot be empty.");
        }
    }

    private boolean isCorrectAnswer(Question question, String answer) {
        return question.getAnswer() != null
            && answer != null
            && answer.equalsIgnoreCase(question.getAnswer().trim());
    }

    public double calculateTotalMarks() {
        if (questions == null) {
            return 0;
        }

        // The total changes automatically when the question collection changes.
        return questions.length * MARKS_PER_QUESTION;
    }

    public double calculatePercentage() {
        double totalMarks = calculateTotalMarks();

        if (totalMarks == 0) {
            return 0;
        }

        return (score / totalMarks) * 100;
    }

    public void displayResult() {
        double totalMarks = calculateTotalMarks();
        double percentage = calculatePercentage();

        System.out.println("Your Score: " + score + " out of " + totalMarks);
        System.out.println("Your Percentage: " + percentage + "%");
        displayStatus();
    }

    public void displayStatus() {
        if (!validateExam()) {
            return;
        }

        if (!examTaken) {
            System.out.println("You have not taken the exam yet.");
            return;
        }

        double percentage = calculatePercentage();

        if (percentage >= PASS_PERCENTAGE) {
            System.out.println("You have passed the exam.");
        } else {
            System.out.println("You have failed the exam.");
        }
    }
}

// Coordinates the examination application.
public class labmid {
    private static final int MINIMUM_AGE = 5;
    private static final int MAXIMUM_AGE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Question[] questions = createQuestions();
        Student student = createStudent(scanner);
        Exam exam = new Exam(student, questions);

        if (exam.validateExam()) {
            exam.takeExam(scanner);
            exam.displayResult();
        }
    }

    private static Question[] createQuestions() {
        return new Question[] {
                new Question("one", "What is the capital of India?", "New Delhi"),
                new Question("two", "What is the capital of USA?", "Washington"),
                new Question("three", "when did royal rumble held this yaer", "2022"),
                new Question("four", "who is the CEO of google", "sundar p"),
                new Question("five", "price of russian cow?", "six thousand"),
                new Question("six", "who is cute?", "Wasey"),
                new Question("seven", "who is the CEO of amazon", "Jeff Bezos"),
                new Question("eight", "who is the CEO of facebook", "Mark Zuckerberg"),
                new Question("nine", "who is the CEO of tesla", "Elon Musk"),
                new Question("ten", "who is the CEO of apple", "Tim Cook")
        };
    }

    private static Student createStudent(Scanner scanner) {
        String registrationId = readRequiredInput(
                scanner,
            "Enter Registration ID:",
                "Registration ID cannot be empty."
        );
        String password = readRequiredInput(
                scanner,
            "Enter Password:",
                "Password cannot be empty."
        );
        String name = readRequiredInput(
                scanner,
            "Enter Student Name:",
                "Student name cannot be empty."
        );
        int age = readAge(scanner);

        return new Student(registrationId, password, name, age);
    }

    private static String readRequiredInput(
            Scanner scanner,
            String prompt,
            String errorMessage
    ) {
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(errorMessage);
        }
    }

    private static int readAge(Scanner scanner) {
        while (true) {
            System.out.println("Enter Age:");
            String ageInput = scanner.nextLine().trim();

            try {
                int age = Integer.parseInt(ageInput);

                if (age >= MINIMUM_AGE && age <= MAXIMUM_AGE) {
                    return age;
                }

                System.out.println(
                        "Age must be between " + MINIMUM_AGE
                                + " and " + MAXIMUM_AGE + "."
                );
            } catch (NumberFormatException exception) {
                System.out.println("Invalid age. Please enter a valid number.");
            }
        }
    }
}