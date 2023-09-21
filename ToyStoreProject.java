import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyStoreProject {
    private String[] toyIds;
    private String[] toyNames;
    private int[] toyWeights;

    public ToyStoreProject(String[] ids, String[] names, int[] weights) {
        this.toyIds = ids;
        this.toyNames = names;
        this.toyWeights = weights;
    }

    public void run() {
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>(); // Используем PriorityQueue для организации очереди

        // Заполняем очередь игрушками, исходя из веса их выпадения
        for (int i = 0; i < toyIds.length; i++) {
            for (int j = 0; j < toyWeights[i]; j++) {
                toyQueue.add(new Toy(toyIds[i], toyNames[i]));
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("Победитель.txt");

            // Вызываем метод Get 10 раз и записываем результат в файл
            for (int i = 0; i < 10; i++) {
                Toy toy = toyQueue.poll();
                String result = toy != null ? toy.getId() + " " + toy.getName() : "No more toys!";
                fileWriter.write(result + "\n");
                System.out.println(result);
            }

            fileWriter.close();
            System.out.println("Результат записан в файл Победитель.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[] toyIds = {"1", "2", "3"};
        String[] toyNames = {"Игрушка 1", "Игрушка 2", "Игрушка 3"};
        int[] toyWeights = {2, 3, 5};

        ToyStoreProject project = new ToyStoreProject(toyIds, toyNames, toyWeights);
        project.run();
    }
}

class Toy implements Comparable<Toy> {
    private String id;
    private String name;

    public Toy(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Toy other) {
        return this.id.compareTo(other.id);
    }
}
