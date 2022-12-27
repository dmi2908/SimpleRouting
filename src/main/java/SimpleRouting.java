import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleRouting {
    private static String result = "";
    private static Map<String, Integer> townCounterMap = new HashMap<>();
    public static void main(String[] args) {
        buildRoute();
        // выводим результат обработки
        System.out.println(result);
    }

    private static void buildRoute() {
        // создаем сканер для считывания данных
        Scanner sc = new Scanner(System.in);
        // считываем количество строк
        int n = sc.nextInt();
        // необходимо считать строку, т.к. после считывания int,
        // метод nextLine возвращает пустую строку
        sc.nextLine();
        // формируем двумерный массив с городами, собираем мапу с количеством вхождений каждого города
        String[][] allTowns = new String[n][2];
        for (int i = 0; i < n; i++) {
            allTowns[i] = sc.nextLine().trim().split(" ");
            for (int j = 0; j < allTowns[i].length; j++) {
                if (!townCounterMap.containsKey(allTowns[i][j])) {
                    townCounterMap.put(allTowns[i][j], 1);
                } else {
                    townCounterMap.put(allTowns[i][j],townCounterMap.get(allTowns[i][j]) + 1);
                }
            }
        }
        // закрываем сканер
        sc.close();

        String startPoint = "";
        String endPoint = "";
        // перебираем массив, ищем начало и конец маршрута, остальные города добавляем строку result
        for (int i = allTowns.length - 1; i >= 0; i--) {
            for (int j = 0; j < allTowns[i].length; j++) {
                if (!result.contains(allTowns[i][j])) {
                    // если город встречается один раз, присваиваем start и end point
                    if (townCounterMap.get(allTowns[i][j]) == 1) {
                        if (!startPoint.isEmpty()) {
                            endPoint = allTowns[i][j];
                        } else {
                            startPoint = allTowns[i][j];
                        }
                    } else {
                        result += allTowns[i][j] + " ";
                    }
                }
            }
        }
        result = startPoint + " " + result + endPoint;
    }
}
