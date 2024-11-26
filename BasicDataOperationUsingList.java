import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцiй з даними типу Float.
 * 
 * <p>Цей клас зчитує данi з файлу "list/Float.data", сортує їх та виконує пошук значення в масивi та списку.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив Float.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi Float.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi Float.</li>
 *   <li>{@link #sortList()} - Сортує список Float.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в списку Float.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мiнiмальне та максимальне значення в списку Float.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingList(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #dataFloatValueToSearch} - Значення Float для пошуку.</li>
 *   <li>{@link #dataFloatArray} - Масив Float.</li>
 *   <li>{@link #dataFloatList} - Список Float.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingList "2024-03-16T00:12:38Z"
 * }
 * </pre>
 */
public class BasicDataOperationUsingList {
    static final String PATH_TO_DATA_FILE = "list/float.data";

    Float dataFloatValueToSearch;
    Float[] dataFloatArray;
    List<Float> dataFloatList;

    public static void main(String[] args) {  
        BasicDataOperationUsingList basicDataOperationUsingList = new BasicDataOperationUsingList(args);
        basicDataOperationUsingList.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingList(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String searchValue = args[0];
        dataFloatValueToSearch = Float.parseFloat(searchValue);

        dataFloatArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        dataFloatList = new ArrayList<>(){{
            for (Float dataFloat : dataFloatArray) {
                add(dataFloat);
            }
        }};
    }


    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та список об'єктiв Float з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операцiї з масивом дати та часу
        
        searchArray();
        findMinAndMaxInArray();

        
        sortArray();
        searchArray();
        findMinAndMaxInArray();

        // операцiї з ArrayList
        searchList();
        findMinAndMaxInList();

        sortList();

        searchList();
        findMinAndMaxInList();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(dataFloatArray, PATH_TO_DATA_FILE + ".sorted");;
    }

    /**
     * Сортує масив об'єктiв Float та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();

           dataFloatArray = Arrays.stream(dataFloatArray)
                              .sorted()
                              .toArray(Float[]::new);

        Utils.printOperationDuration(startTime, "сортування масиву дійсних чисел");
    }

    /**
     * Метод для пошуку значення в масивi .
     */
    void searchArray() {
        long startTime = System.nanoTime();
        int index = dataFloatList.stream()
        .filter(dataFloat -> dataFloat.equals(dataFloatValueToSearch))
        .findFirst()
        .map(dataFloatList::indexOf)
        .orElse(-1);
        boolean found = Arrays.stream(dataFloatArray)
        .anyMatch(dataFloat -> dataFloat.equals(dataFloatValueToSearch));

        Utils.printOperationDuration(startTime, "пошук в масивi дійсних чисел");


        System.out.println("Значення " + dataFloatValueToSearch + (found ? " знайдено" : " не знайдено") + " в масиві"+ "За індексом:"+ index);

    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi дійсних чисел.
     */
    void findMinAndMaxInArray() {
        if (dataFloatArray == null || dataFloatArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Float min = Arrays.stream(dataFloatArray)
        .min(Float::compareTo)
        .orElse(null);


        Float max = Arrays.stream(dataFloatArray)
        .max(Float::compareTo)
        .orElse(null);
        

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Шукає задане значення дійсних чисел в ArrayList дійсних чисел.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = dataFloatList.stream()
                    .filter(dataFloat -> dataFloat.equals(dataFloatValueToSearch))
                    .findFirst()
                    .map(dataFloatList::indexOf)
                    .orElse(-1);

        Utils.printOperationDuration(startTime, "пошук в ArrayList дійсних чисел");        

        if (index >= 0) {
            System.out.println("Значення '" + dataFloatValueToSearch + "' знайдено в ArrayList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + dataFloatValueToSearch + "' в ArrayList не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в ArrayList дійсних чисел.
     */
    void findMinAndMaxInList() {
        if (dataFloatList == null || dataFloatList.isEmpty()) {
            System.out.println("ArrayList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Float min = dataFloatList.stream()
        .min(Float::compareTo)
        .orElse(null);


        Float max = dataFloatList.stream()
        .max(Float::compareTo)
        .orElse(null);

        Utils.printOperationDuration(startTime, "пошук мінімального та максимального дійсного числа в списку");

        System.out.println("Мiнiмальне значення в ArrayList: " + min);
        System.out.println("Максимальне значення в ArrayList: " + max);
    }

    /**
     * Сортує ArrayList об'єктiв Float та виводить початковий i вiдсортований списки.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        dataFloatList = dataFloatList.stream()
        .sorted()
        .collect(Collectors.toList());


        Utils.printOperationDuration(startTime, "сортування ArrayList дійсних чисел");
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу Float.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв Float з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв Float.
     */
    static Float[] readArrayFromFile(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            return br.lines()
                          .map(dataLine -> Float.parseFloat(dataLine))
                          .toArray(Float[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання даних з файлу: " + pathToFile, e);
        }

    }

    /**
     * Записує масив об'єктiв Float у файл.
     * 
     * @param dataFloatArray Масив об'єктiв Float.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Float[] dataFloatArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Float dataFloat : dataFloatArray) {
                writer.write(dataFloat+" ");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}