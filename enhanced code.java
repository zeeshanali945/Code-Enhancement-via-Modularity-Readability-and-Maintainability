//Console-Based Examination Management System using OOP
import java.util.Scanner;

class Question{
    private String id;
    private String quesStatement;
    private String answer;

    public Question(String id,String quesStatement,String answer){
        this.id=id;
        this.quesStatement=quesStatement;
        this.answer=answer;
    }

    public void setid(String id){
        this.id=id;
    }

    public void setquesStatement(String quesStatement){
        this.quesStatement=quesStatement;
    }

    public void setanswer(String answer){
        this.answer=answer;
    }

    public String getid(){
        return this.id;
    }

    public String getquesStatement(){
        return this.quesStatement;
    }

    public String getanswer(){
        return this.answer;
    }
}

class Person{
    private String name;
    private int age;

    public Person(){

    }

    public Person (String name,int age){
        this.name=name;
        this.age=age;
    }

    public void setname(String name){
        this.name=name;
    }

    public void setage(int age){
        this.age=age;
    }

    public String getname(){
        return this.name;
    }

    public int getage(){
        return this.age;
    }
}

class Student extends Person{
    private String regID;
    private String pswd;

    public Student(){

    }

    public Student(String regID,String pswd,String name,int age){
        super(name,age);
        this.pswd=pswd;
        this.regID=regID;
    }

    public void setregID(String regID){
        this.regID=regID;
    }

    public void setpswd(String pswd){
        this.pswd=pswd;
    }

    public String getredID(){
        return this.regID;
    }

    public String getpswd(){
        return this.pswd;
    }
}

class Exam{
    private Question[] q;
    private Student s;
    private double score;
    private boolean examTaken;

    public Exam(Student s,Question [] q){
        this.q=q;
        this.s=s;
    }

    public void sets(Student s){
        this.s=s;
    }

    public void setq(Question [] q){
        this.q=q;
    }

    public Student gets(){
        return s;
    }

    public Question[] getq(){
        return q;
    }

    public boolean validateExam(){
        return s != null && q != null && q.length > 0;
    }

    public void takeExam(Scanner scanner){
        if (!validateExam()){
            return;
        }

        score = 0;
        for(int i=0;i<q.length;i++){
            displayQuestion(i);
            String answer = getAnswer(scanner);

            if(isCorrectAnswer(q[i], answer)){
                score += 5;
            }
        }
        examTaken = true;
    }

    private void displayQuestion(int questionNumber){
        Question question = q[questionNumber];
        System.out.println("Question "+(questionNumber+1)+": "+question.getid()+":"
                +question.getquesStatement());
    }

    private String getAnswer(Scanner scanner){
        System.out.println("Enter your answer:");
        return scanner.nextLine();
    }

    private boolean isCorrectAnswer(Question question, String answer){
        return answer.equalsIgnoreCase(question.getanswer());
    }

    public double calculateTotalMarks(){
        return q == null ? 0 : q.length * 5;
    }

    public double calculatePercentage(){
        double totalMarks = calculateTotalMarks();
        return totalMarks == 0 ? 0 : score / totalMarks * 100;
    }

    public void displayResult(){
        System.out.println("Your score is "+score);
        displayStatus();
    }

    public void displayStatus(){
        if (!validateExam()){
            System.out.println("Exam data is not available");
            return;
        }

        if (!examTaken){
            System.out.println("You have not taken the exam yet");
            return;
        }

        if (calculatePercentage()>=50){
            System.out.println("You have passed the exam");
        }
        else{
            System.out.println("You have failed the exam");
        }
    }
}

public class labmid{
    public static void main(String[] args) {
    Question[] questions = createQuestions();
    Student student = createStudent();
    Exam exam = new Exam(student, questions);

    if (exam.validateExam()){
        Scanner scanner = new Scanner(System.in);
        exam.takeExam(scanner);
        exam.displayResult();
    }
    else{
        System.out.println("Exam data is not available");
    }
    }

    private static Question[] createQuestions(){
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

    private static Student createStudent(){
    return new Student("037", "oloaua", "Muhammad Talha", 20);
    }
}