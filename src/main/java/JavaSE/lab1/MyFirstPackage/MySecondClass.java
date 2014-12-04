package JavaSE.lab1.MyFirstPackage;

public class MySecondClass {
    //    •	имеет два приватных поля типа int;
//    •	методы для получения и модификации их значений;
//    •	конструктор, создающий объект и инициализирующий значения полей;
//    •	метод с возвращаемым типом int, реализующий над этими числами какое-нибудь действие (сложение, умножение и т.д.).
    private int firstVar, secondVar;

    public MySecondClass(int firstVar, int secondVar) {
        this.firstVar = firstVar;
        this.secondVar = secondVar;
    }

    public int getFirstVar() {
        return firstVar;
    }

    public void setFirstVar(int firstVar) {
        this.firstVar = firstVar;
    }

    public int getSecondVar() {
        return secondVar;
    }

    public void setSecondVar(int secondVar) {
        this.secondVar = secondVar;
    }

    public int plus() {
        return firstVar + secondVar;
    }

    public int multiply() {
        return firstVar * secondVar;
    }
}