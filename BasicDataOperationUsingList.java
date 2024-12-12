import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    LinkedList<Float> dataFloatList;

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
        dataFloatList = new LinkedList<>(){{
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
        // операцiї з масивом 
        
        searchArray();
        findMinAndMaxInArray();

        
        sortArray();
        searchArray();
        findMinAndMaxInArray();

        // операцiї з LinkedList
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

        Arrays.sort(dataFloatArray , Collections.reverseOrder());

        Utils.printOperationDuration(startTime, "сортування масиву дійсних чисел");
    }

    /**
     * Метод для пошуку значення в масивi .
     */
    void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.dataFloatArray, dataFloatValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масивi ");

        if (index >= 0) {
            System.out.println("Значення '" + dataFloatValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + dataFloatValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi Float.
     */
    void findMinAndMaxInArray() {
        if (dataFloatArray == null || dataFloatArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Float min = dataFloatArray[0];
        Float max = dataFloatArray[0];

        Utils.printOperationDuration(startTime, "пошук мінімального та максимального дійсного числа в масивi");

        for (Float dataFloat : dataFloatArray) {
            if (min > dataFloat){
                min = dataFloat;
            }
            if (max < dataFloat){
                max = dataFloat;
            }
        }

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Шукає задане значення Float в LinkedList Float.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = Collections.binarySearch(this.dataFloatList, dataFloatValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в LinkedList дійсних чисел");        

        if (index >= 0) {
            System.out.println("Значення '" + dataFloatValueToSearch + "' знайдено в LinkedList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + dataFloatValueToSearch + "' в LinkedList не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в LinkedList Float.
     */
    void findMinAndMaxInList() {
        if (dataFloatList == null || dataFloatList.isEmpty()) {
            System.out.println("LinkedList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        Float min = Collections.min(dataFloatList);
        Float max = Collections.max(dataFloatList);

        Utils.printOperationDuration(startTime, "пошук мінімального та максимального дійсного числа в списку");

        System.out.println("Мiнiмальне значення в LinkedList: " + min);
        System.out.println("Максимальне значення в LinkedList: " + max);
    }

    /**
     * Сортує LinkedList об'єктiв Float та виводить початковий i вiдсортований списки.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        Collections.sort(dataFloatList, Collections.reverseOrder());

        Utils.printOperationDuration(startTime, "сортування LinkedList дійсних чисел");
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
        Float[] tempArray = new Float[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Float dateFloat = Float.parseFloat(line);
                tempArray[index++] = dateFloat;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Float[] finalArray = new Float[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
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