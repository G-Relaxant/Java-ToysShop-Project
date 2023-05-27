package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<ArrayList> toysArLi = new ArrayList<>();

        firstFillCatalog(toysArLi);

        System.out.println();

        System.out.println("Каталог магазина игрушек Дункана ***Dunkan'sToysShop***");
        while (true) {
            System.out.println();
            System.out.println("Выберите действие:");
            System.out.println("1 - Выйти из каталога");
            System.out.println("2 - Добавить новую позицию(игрушку)");
            System.out.println("3 - Распечатать весь каталог");
            System.out.println("4 - Выбрать игрушки по параметрам");
            System.out.println("5 - Изменить существующую позицию");
            System.out.println("6 - Удалить позицию");
            System.out.println("7 - Разыграть игрушку");
            System.out.println("8 - Выдать выигранную игрушку победителю");
            System.out.println();
            System.out.print("Введите действие: ");
            String read = scan();
            int get = Integer.parseInt(read);

            if (get == 1) {
                System.out.print("Закрыть каталог (да/нет): ");
                read = scan();
                if (read.equalsIgnoreCase("д") || read.equalsIgnoreCase("да")) {
                    System.out.println("Вы вышли из каталога");
                    break;
                } else {
                    continue;
                }
            } else if (get == 2) {
                createPosition(toysArLi);
            } else if (get == 3) {
                printCatalog(toysArLi);
            } else if (get == 4) {
                selectPosition(toysArLi);
            } else if (get == 5) {
                changePosition(toysArLi);
            } else if (get == 6) {
                System.out.println("Для удаления позиции, выберите в основном меню пункт 5, выберите нужную позицию, и при вводе новых данных, нажимайте Enter, ничего не вводя");
            } else {
                System.out.println("Некорректный ввод");
            }

        }
    }



    private static void printCatalog(List<ArrayList> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }

    private static void firstFillCatalog(List<ArrayList> data) {    // StringList
        System.out.println();
        String lt1 = " Зайчик     #маленький #400   #30  шт#4 %";
        String lt2 = " Зайчик     #средний   #1000  #15  шт#3 %";
        String lt3 = " Зайчик     #большой   #3000  #5   шт#1 %";
        String lt4 = " Жирафик    #средний   #1500  #20  шт#2 %";
        String lt5 = " Дельфин    #большой   #5000  #3   шт#1 %";
        String lt6 = " Бегемотик  #маленький #500   #35  шт#5 %";
        String lt7 = " Бегемотик  #средний   #1300  #10  шт#3 %";
        String lt8 = " Рыбка      #маленький #400   #30  шт#4 %";
        String lt9 = " Рыбка      #средний   #1000  #15  шт#2 %";
        String lt10 = "Птичка     #средний   #1500  #20  шт#3 %";
        String[] ltStrArr = {lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8, lt9, lt10};

        for (int i = 0; i < ltStrArr.length; i++) {
            String buffer = Integer.toString(i + 1);
            data.add(new ArrayList<String>());
            data.get(i).add(buffer);    // добавляет id в 1ую ячейку
            buffer = ltStrArr[i];
            String[] strSplitArr = buffer.split("\\#");

            for (int j = 0; j < strSplitArr.length; j++) {
                data.get(i).add(strSplitArr[j]);
            }
        }
    }

    private static void createPosition(List<ArrayList> data) {
        List<String> newPosList = new ArrayList<>();
        int indxPos = data.size() + 1;
        newPosList.add(Integer.toString(indxPos));
        System.out.print("Введите название игрушки (недостающие символы заполнить пробелами, общее кол-во символов == 11): ");
        String read = scan();
        newPosList.add(read);
        System.out.print("Введите размер игрушки (общее кол-во символов == 10): ");
        read = scan();
        newPosList.add(read);
        System.out.print("Введите стоимость (общее кол-во символов == 6): ");
        read = scan();
        newPosList.add(read);
        System.out.print("Введите количество (общее кол-во символов == 3): ");
        read = scan();
        newPosList.add(read + " шт");
        System.out.print("Введите вероятность выигрыша: ");
        read = scan();
        newPosList.add(read + " %");

        data.add((ArrayList) newPosList);
        System.out.println();
        System.out.println("Позиция добавлена");
    }

    private static void selectPosition(List<ArrayList> data) {
        System.out.println();
        System.out.println("Выберите параметр для поиска:");
        System.out.println("1 - Название игрушки");
        System.out.println("2 - Размер");
        System.out.println("3 - Стоимость");
        System.out.println("4 - Количество");
        System.out.println("5 - Вероятность выигрыша");
        String read = scan();
        int get2 = Integer.parseInt(read);
        System.out.print("Введите желаемое значение выбранного параметра (необходимо указать полностью значение параметра, включая дополнительные слова, можно без пробелов): ");
        read = scan();
        System.out.println();
        System.out.println("Вот позиции, соответствующие вашему запросу:");

        for (int i = 0; i < data.size(); i++) {
            String compare = (String) data.get(i).get(get2);
            compare = compare.replaceAll("\\s", "");
            read = read.replaceAll("\\s", "");
            if (compare.equalsIgnoreCase(read)) {
                System.out.println(data.get(i));
            }
        }
    }

    private static void changePosition(List<ArrayList> data) {
        System.out.print("Введите номер позиции: ");
        String read = scan();
        Integer selPos = 0;
        for (int i = 0; i < data.size(); i++) {
            String compare = (String) data.get(i).get(0);
            if (compare.equalsIgnoreCase(read)) {
                selPos = i;
                break;
            }
        }
        System.out.print("Введите новое название игрушки (недостающие символы заполнить пробелами, общее кол-во символов == 11), (если изменяется позиция с 1й по 9ю, то первым символом ставится пробел, а общее кол-во символов == 12): ");
        read = scan();
        data.get(selPos).set(1, read);
        System.out.print("Введите размер (общее кол-во символов == 10): ");
        read = scan();
        data.get(selPos).set(2, read);
        System.out.print("Введите стоимость (общее кол-во символов == 6): ");
        read = scan();
        data.get(selPos).set(3, read);
        System.out.print("Введите количество (общее кол-во символов == 3): ");
        read = scan();
        data.get(selPos).set(4, read + " шт");
        System.out.print("Введите вероятность выигрыша: ");
        read = scan();
        data.get(selPos).set(5, read + " %");
        System.out.println();
        System.out.println("Изменения внесены");
    }

    private static String scan() {
        Scanner sc = new Scanner(System.in);
        String rslt = sc.nextLine();
        return rslt;
    }
}
