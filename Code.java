//Console-Based Examination Management System using OOP
import java .util.Scanner;

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
    private Question[] q=new Question[10];
    private Student s;
    private double score;

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

    public void TakeExam(){
        for(int i=0;i<q.length;i++){
            System.out.println("Question "+(i+1)+": "+q[i].getid()+":"+q[i].getquesStatement());

            String answer;

            System.out.println("Enter your answer:");

            Scanner sc=new Scanner(System.in);
            answer=sc.nextLine();

            if(answer.equalsIgnoreCase(q[i].getanswer())){
                this.score+=5;
            }

            System.out.println("Your score is "+score);
        }
    }

    public void displayStatus(){
        if (this.score==0.0){
            System.out.println("You have not taken the exam yet");
        }

        double percent=this.score/50*100;

        if (percent>=50){
            System.out.println("You have passed the exam");
        }
        else{
            System.out.println("You have failed the exam");
        }
    }
}

public class labmid{
    public static void main(String[] args) {

        Question q1=new Question(
                "one",
                "What is the capital of India?",
                "New Delhi"
        );

        Question q2=new Question(
                "two",
                "What is the capital of USA?",
                "Washington"
        );

        Question q3=new Question(
                "three",
                "when did royal rumble held this yaer",
                "2022"
        );

        Question q4=new Question(
                "four",
                "who is the CEO of google",
                "sundar p"
        );

        Question q5=new Question(
                "five",
                "price of russian cow?",
                "six thousand"
        );

        Question q6=new Question(
                "six",
                "who is cute?",
                "Wasey"
        );

        Question q7=new Question(
                "seven",
                "who is the CEO of amazon",
                "Jeff Bezos"
        );

        Question q8=new Question(
                "eight",
                "who is the CEO of facebook",
                "Mark Zuckerberg"
        );

        Question q9=new Question(
                "nine",
                "who is the CEO of tesla",
                "Elon Musk"
        );

        Question q10=new Question(
                "ten",
                "who is the CEO of apple",
                "Tim Cook"
        );

        Student s1 = new Student(
                "037",
                "oloaua",
                "Muhammad Talha",
                20
        );

        Exam e1 = new Exam(
                s1,
                new Question[] {
                        q1, q2, q3, q4, q5,
                        q6, q7, q8, q9, q10
                }
        );

        e1.TakeExam();
        e1.displayStatus();
    }
}