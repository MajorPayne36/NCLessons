import com.Lesson1.CSVWork.CSVWorker;
import com.Lesson1.validators.Validate;
import com.Lesson1.validators.ValidationStatus;
import com.Lesson1.validators.ValidatorResult;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CSVWorker worker = new CSVWorker();
        worker.writeData("./result.csv");
        worker.readData("./result.csv");
        System.out.print(worker.getContracts().toString());

        System.out.println("\n***************************************************" +
                "\nVALIDATION RESULTS\n");

        ArrayList<ValidatorResult> validationResults = new Validate(worker.getContracts()).validateAllContracts();
        validationResults.forEach(vr->{
            if (vr.getStatus().equals(ValidationStatus.ERROR))
                System.out.println(vr);
        });
        System.out.println("\n***************************************************");
    }
}













