import com.Lesson1.CSVWork.CSVWorker;

public class Main {
    public static void main(String[] args) {
        CSVWorker worker = new CSVWorker();
        worker.writeData("./result.csv");
        worker.readData("./result.csv");
        System.out.print(worker.getContracts().toString());
    }
}













