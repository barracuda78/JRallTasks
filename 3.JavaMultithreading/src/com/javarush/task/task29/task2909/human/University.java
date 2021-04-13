package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

//4.1. Замена наследования делегированием.
//4.1.1. Класс University не должен наследоваться от Student.
//4.1.2. Класс University должен содержать список students. Не забудь его инициализировать.
//4.1.3. Добавь сеттер и геттер для students.
//4.1.4. Университет имеет название (name) и возраст (age). Добавь необходимые поля, сеттеры и геттеры для них.
public class University{
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
       this.name = name;
       this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    6.2. Добавление параметра. Добавить параметр с типом double в метод getStudentWithAverageGrade(), чтобы было понятно с каким средним балом нужен студент.
//Реализуй метод getStudentWithAverageGrade().

    public Student getStudentWithAverageGrade(double grade) {
        //TODO:
        for(Student student : students){
            if (student.getAverageGrade() == grade) return student;
        }
        return null;
    }

//    6.3. Удаление параметра. Убери параметр из метода getStudentWithMaxAverageGrade().
//Реализуй этот метод, он должен возвращать студента с максимальным средним балом.
    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double max = Double.MIN_VALUE;
        for(Student student: students){
            if(max <= student.getAverageGrade()){
                max = student.getAverageGrade();
            }
        }
        for(Student student: students){
            if(student.getAverageGrade() == max) return student;
        }
        return null;
    }

//    6.4. Разделение запроса и модификатора. Раздели метод getStudentWithMinAverageGradeAndExpel на Student getStudentWithMinAverageGrade() и void expel(Student student).
//    Первый метод должен возвратить студента с минимальным средним балом,
//    а второй - отчислить переданного студента (удалять из списка students).
//    public void getStudentWithMinAverageGradeAndExpel() {
//        //TODO:
//    }

    public Student getStudentWithMinAverageGrade(){
        double min = Double.MAX_VALUE;
        for(Student student: students){
            if(min >= student.getAverageGrade()){
                min = student.getAverageGrade();
            }
        }
        for(Student student: students){
            if(student.getAverageGrade() == min) return student;
        }
        return null;
    }
    public void expel(Student student){
        if(student == null) return;
        if(students.contains(student)) students.remove(student);

    }


}