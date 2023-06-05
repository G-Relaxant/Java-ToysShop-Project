package Main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<ArrayList> toysArLi = new ArrayList<>();
        List<ArrayList> winRaffleToy = new ArrayList<>();
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
                deletePosition(toysArLi);
            } else if (get == 7) {
                winRaffleToy = rafflePosition(toysArLi);
                System.out.println("Выиграна позиция");
                System.out.println(winRaffleToy.get(0));
            } else if (get == 8) {
                giveOutWin(winRaffleToy);
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
        String lt1 = " Зайчик     #маленький #400   #30  шт#10 %";
        String lt2 = " Зайчик     #средний   #1000  #15  шт#6 %";
        String lt3 = " Зайчик     #большой   #3000  #5   шт#2 %";
        String lt4 = " Жирафик    #средний   #1500  #20  шт#4 %";
        String lt5 = " Дельфин    #большой   #5000  #3   шт#2 %";
        String lt6 = " Бегемотик  #маленький #500   #35  шт#15 %";
        String lt7 = " Бегемотик  #средний   #1300  #10  шт#6 %";
        String lt8 = " Рыбка      #маленький #400   #30  шт#10 %";
        String lt9 = " Рыбка      #средний   #1000  #15  шт#4 %";
        String lt10 = "Птичка     #средний   #1500  #20  шт#6 %";
        String lt11 = "Дракончик  #большой   #5555  #1   шт#1 %";
        String[] ltStrArr = {lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8, lt9, lt10, lt11};

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
            compare = compare.replaceAll("\s", "");
            read = read.replaceAll("\s", "");
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

    private static void deletePosition(List<ArrayList> data) {
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
        String delStr = "";
        data.get(selPos).set(1, delStr);
        data.get(selPos).set(2, delStr);
        data.get(selPos).set(3, delStr);
        data.get(selPos).set(4, delStr);
        data.get(selPos).set(5, delStr);
        System.out.println();
        System.out.println("Позиция удалена. При добавлении новых позиций, желательно сначала заполнять удалённые ячейки, которые на данный момент пусты");
    }

    private static List rafflePosition(List<ArrayList> data) {
        SplittableRandom rand = new SplittableRandom();
//        for (int i = 0; i < 100; i++) {
//            boolean probability = random.nextInt(1, 101) <= 99;     // вероятность prob == true = 99 %
//            System.out.println(probability);
//        }
        int prob = 0;
        String buf = "";
        String[] buf2 = {};
        boolean probability = rand.nextInt(1, 101) <= prob;
        List<ArrayList> rafPosArLi = new ArrayList<>();

        while (true) {
            for (int i = 0; i < data.size(); i++) {
                probability = rand.nextInt(1, 101) <= prob;
                buf = (String) data.get(i).get(5);
                buf2 = buf.split("\\ ");
                buf = buf2[0];
                prob = Integer.parseInt(buf);
                if (probability == true) {
                    rafPosArLi.add(data.get(i));
//                    System.out.println(rafPosArLi);
                }
            }
//            System.out.println();

            if (rafPosArLi.size() == 0) {
                continue;
            } else if (rafPosArLi.size() == 1) {
                break;
            } else {
                int winPos = rand.nextInt(0, rafPosArLi.size());
                List<ArrayList> rafPosBuf = new ArrayList<>();
//                rafPosBuf = rafPosArLi.get(winPos); // тоже самое, если нужен 1 вложенный объект
                rafPosBuf.add(rafPosArLi.get(winPos));
                rafPosArLi = rafPosBuf;
                break;
            }
        }
        System.out.println();
        return rafPosArLi;
    }

    private static void giveOutWin(List<ArrayList> data) {
        Path path = Paths.get("/home/Danya/Рабочий стол/Java-ToysShop-Project/ToysShop/src/main/resources/files/winsToyForGiveOut.txt");
        try {
            String buf = (String) data.get(0).get(1);
            String[] buf2 = buf.split("\\ ");
            String winsToy = "";
            if (buf2[0].equalsIgnoreCase("")) {
                winsToy = "Выигрыш: ID позиции - " + (String) data.get(0).get(0) // Если id позиции 10 или больше, то выдаёт ошибку: Array index out of range: 1, почему не знаю
                        + ". " + buf2[1] + " " + (String) data.get(0).get(2);
            } else {
                winsToy = "Выигрыш: ID позиции - " + (String) data.get(0).get(0) // Если id позиции 10 или больше, то выдаёт ошибку: Array index out of range: 1, почему не знаю
                        + ". " + buf2[0] + " " + (String) data.get(0).get(2);
            }

            byte[] bs = winsToy.getBytes();
            Path writtenFilePath = Files.write(path, bs);
            System.out.println();
            System.out.println("Выйгрыш записан в файл: /ToysShop/src/main/resources/files/winsToyForGiveOut.txt");
            System.out.println(new String(Files.readAllBytes(writtenFilePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}