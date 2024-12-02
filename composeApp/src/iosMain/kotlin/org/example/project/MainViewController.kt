package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.app.ui.App

fun MainViewController() = ComposeUIViewController {

    initKoin()
    App().AppContent()

    /*

    // в java методы не могут вызываться за пределами классов

    public class Example {
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in) // создание нового объекта класса

        int day = 3; // Например, 3 — это среда

        for (int i = 1; i <= 5; i++) {          //счетчик -> условие -> действие
            System.out.println("Число: " + i);
        }

        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Некорректный день");
        }
    }
}


      int numbers = new int [5];  //пустой массив с 5 элементами

      numbers.length; // аналог size для получения размера массива

    1 версия gpt  ->  numbers.forEach(number -> System.out.println("Число: " + number));

    2 версия video ->  for( int number:numbers){

    System.out.println(number)

    };

   // Создаём двумерный массив // грубо гооворя это список в списке

        int[][] matrix = {
            {1, 2, 3},
            {4},
            {7, 8, 9, 10}
        };


    // пометка public class означает что это главный класс в файле и файл должен быть строго

    такого же названия как и public class иначе будет ошибка

    в файле может быть только один public и множество обычных class

    class Person{

    String name;

        void speak () {   // void обозначает что метод не возвращает ничего

    System.out.println("Привет");

    }

    int years () {    // возвращает число

    return 40;

    }

    }

    Person person1 = new Person();

    person1.name = "Дима";

    person1.speak();

     */

}