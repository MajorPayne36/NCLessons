import com.Lesson1.CSVWork.CSVWorker;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.JAXB.XmlWorker;
import com.Lesson1.validators.ValidationStatus;
import com.Lesson1.validators.ValidatorResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        CSVWorker worker = new CSVWorker();
        worker.writeData("./result.csv");
        worker.readData("./result.csv");
        System.out.print(worker.getContracts().toString());

        // Creating XML
        XmlWorker.convertObjectToXml(worker.getContracts(), "./contracts.xml");

        Contracts repo = XmlWorker.fromXmlToObject("./contracts.xml");
        if (repo==null) {
            LOGGER.error("null xml data returned");
        }
        else {
            LOGGER.info("XML CONVERTING RESULTS");
            System.out.print(worker.getContracts().toString());
        }
    }
}













