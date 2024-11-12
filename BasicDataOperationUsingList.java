import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцiй з даними типу float.
 * 
 * <p>Цей клас зчитує данi з файлу "list/float.data", сортує їх та виконує пошук значення в масивi та списку.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив float.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi float.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi float.</li>
 *   <li>{@link #sortList()} - Сортує список float.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в списку float.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мiнiмальне та максимальне значення в списку float.</li>
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
 *   <li>{@link #dataFloatValueToSearch} - Значення float для пошуку.</li>
 *   <li>{@link #dataFloatArray} - Масив float.</li>
 *   <li>{@link #dataFloatList} - Список float.</li>
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
    static final String PATH_TO_DATA_FILE = "list\\float.data";

    float dataFloatValueToSearch;
    float[] dataFloatArray;
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
            for (float dataFloat : dataFloatArray) {
                add(dataFloat);
            }
        }};
    }


    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та список об'єктiв float з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операцiї з масивом дати та часу
        sortArray();
        searchArray();
        findMinAndMaxInArray();

        
        
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
     * Сортує масив об'єктiв float та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(dataFloatArray);

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
     * Знаходить мiнiмальне та максимальне значення в масивi дати i часу.
     */
    void findMinAndMaxInArray() {
        if (dataFloatArray == null || dataFloatArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        float min = dataFloatArray[0];
        float max = dataFloatArray[0];

        Utils.printOperationDuration(startTime, "пошук мінімального та максимального дійсного числа в масивi");

        for (float dataFloat : dataFloatArray) {
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
     * Шукає задане значення дати i часу в ArrayList дати i часу.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = Collections.binarySearch(this.dataFloatList, dataFloatValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в ArrayList дати i часу");        

        if (index >= 0) {
            System.out.println("Значення '" + dataFloatValueToSearch + "' знайдено в ArrayList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + dataFloatValueToSearch + "' в ArrayList не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в ArrayList дати i часу.
     */
    void findMinAndMaxInList() {
        if (dataFloatList == null || dataFloatList.isEmpty()) {
            System.out.println("ArrayList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        float min = Collections.min(dataFloatList);
        float max = Collections.max(dataFloatList);

        Utils.printOperationDuration(startTime, "пошук мінімального та максимального дійсного числа в списку");

        System.out.println("Мiнiмальне значення в ArrayList: " + min);
        System.out.println("Максимальне значення в ArrayList: " + max);
    }

    /**
     * Сортує ArrayList об'єктiв float та виводить початковий i вiдсортований списки.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        Collections.sort(dataFloatList);

        Utils.printOperationDuration(startTime, "сортування ArrayList дати i часу");
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу float.
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
     * Зчитує масив об'єктiв float з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв float.
     */
    static float[] readArrayFromFile(String pathToFile) {
        float[] tempArray = new float[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                float dateFloat = Float.parseFloat(line);
                tempArray[index++] = dateFloat;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] finalArray = new float[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв float у файл.
     * 
     * @param dataFloatArray Масив об'єктiв float.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(float[] dataFloatArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (float dataFloat : dataFloatArray) {
                writer.write(dataFloat+" ");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}