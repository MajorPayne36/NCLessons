package com.Lesson1.CSVWork;

import com.Lesson1.ArayUtil;
import com.Lesson1.Clients.*;
import com.Lesson1.Contracts.*;
import com.Lesson1.Interfaces.IValidator;
import com.Lesson1.di.Annotations.AutoInjectable;
import com.Lesson1.validators.ValidateClientAge;
import com.Lesson1.validators.ValidatePassNumber;
import com.Lesson1.validators.ValidatePassSeries;
import com.Lesson1.validators.ValidatorResult;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVWorker {

    private Clients clients = new Clients();
    private Contracts contracts = new Contracts(clients);
    @AutoInjectable(clazz = IValidator.class)
    private List<IValidator> validators;

    /**
     * Save CSV file headers
     */
    private final String[] HEADER = new String[]{"StartDate", "EndDate", "FirstName", "LastName", "Gender", "Birthday", "PassSeries", "PassNumber", "ContractType", "ContractInfo"};

    /**
     * Create CSV file in the transferred file path
     *
     * @param filePath
     */
    public void writeData(String filePath) {
        // сначала создаем объект файла для файла, размещенного в местоположении
        // указано filepath

        File file = new File(filePath);
        try {
            // создаем объект FileWriter с файлом в качестве параметра
            FileWriter fileWriter = new FileWriter(file);

            // создаем объект файлового объекта CSVWriter в качестве параметра
            CSVWriter writer = new CSVWriter(fileWriter);

            // создаем список, содержащий массив строк
            List<String[]> data = new ArrayList<String[]>();
            data.add(HEADER);
            data.add(new String[]{"21-10-2004", "31-12-2004", "Andranik", "Grigoryan", "Male", "29-10-2000", "2018", "102030", "DigitalTVContract", "Premium"});
            data.add(new String[]{"29-10-2000", "06-02-2001", "Harut", "Grigoryan", "Male", "06-03-2007", "2018", "102030", "PhoneContract", "500sms 12gb 1000min"});
            data.add(new String[]{"30-11-2001", "04-03-2003", "Artur", "Sargsyan", "Male", "21-10-1997", "0205", "708090", "DigitalTVContract", "Standart+"});
            data.add(new String[]{"14-06-2003", "21-01-2009", "Ashot", "Kazazyan", "Male", "12-12-1976", "0103", "104070", "PhoneContract", "1000sms 12gb 2000min"});
            data.add(new String[]{"06-03-2007", "07-11-2009", "Manvel", "Hovvanisyan", "Male", "21-08-2001", "0405", "205080", "WiredInternetContract", "200"});
            data.add(new String[]{"14-07-2010", "15-10-2011", "Tigran", "Tovmasyan", "Male", "18-05-1995", "0780", "306090", "WiredInternetContract", "100"});
            writer.writeAll(data);

            // закрываем соединение с писателем
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read csv file and create new data in Contracts
     *
     * @param filePath
     */
    public void readData(String filePath) {


        try {
            // Создать объект для чтения файлов
            // класс с CSV-файлом в качестве параметра.
            FileReader filereader = new FileReader(filePath);

            // создать передачу объекта csvReader
            // читатель файла как параметр
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            String[] nextRecord;

            // мы будем читать данные построчно
            while ((nextRecord = csvReader.readNext()) != null) {

                if (nextRecord[8].equals("DigitalTVContract")) createDigitalTVContract(nextRecord);
                if (nextRecord[8].equals("PhoneContract")) createPhoneContract(nextRecord);
                if (nextRecord[8].equals("WiredInternetContract")) createWiredInternetContract(nextRecord);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Create from data new DigitalTVContract
     *
     * @param data String[] witch have enough info for creating new contract
     * @throws SimilarClientsException
     */
    private void createDigitalTVContract(String[] data) throws SimilarClientsException {

        contracts.addContract(new DigitalTVContract(
                data[9],
                createClient(data),
                LocalDate.parse(data[0], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(),
                LocalDate.parse(data[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay()
        ));
    }

    /**
     * Create from data new PhoneContract
     *
     * @param data String[] witch have enough info for creating new contract
     * @throws SimilarClientsException
     */
    private void createPhoneContract(String[] data) throws SimilarClientsException {
//неисправно работает регулярные выражения
        Matcher matcher = Pattern.compile("\\d+gb").matcher(data[9]);
        matcher.find();
        String gb = data[9].substring(matcher.start(), matcher.end() - 2);

        matcher = Pattern.compile("\\d+sms").matcher(data[9]);
        matcher.find();
        String sms = data[9].substring(matcher.start(), matcher.end() - 3);

        matcher = Pattern.compile("\\d+min").matcher(data[9]);
        matcher.find();
        String min = data[9].substring(matcher.start(), matcher.end() - 3);

        contracts.addContract(new PhoneContract(
                Integer.parseInt(sms),
                Integer.parseInt(min),
                Integer.parseInt(gb),
                createClient(data),
                LocalDate.parse(data[0], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(),
                LocalDate.parse(data[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay()
        ));
    }

    /**
     * Create from data new WiredInternetContract
     *
     * @param data String[] witch have enough info for creating new contract
     * @throws SimilarClientsException
     */
    private void createWiredInternetContract(String[] data) throws SimilarClientsException {
        contracts.addContract(new WiredInternetContract(
                createClient(data),
                Integer.parseInt(data[9]),
                LocalDate.parse(data[0], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(),
                LocalDate.parse(data[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay()
        ));
    }

    /**
     * From Sting[] find data about client and create new Client
     *
     * @param clientData String[] witch have enough info for creating new Client
     * @return the client from repo
     */
    private Client createClient(String[] clientData) throws SimilarClientsException {
        try {
            Client client = new Client(
                    clientData[2],
                    clientData[3],
                    LocalDate.parse(clientData[5], DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay(),
                    Integer.parseInt(clientData[6]),
                    Integer.parseInt(clientData[7]),
                    clientData[4]
            );

            clients.addClient(client);

            return client;
        } catch (SimilarClientsException e) {
            System.out.println("----------------------------------");
            System.out.println(clients.getClientByPassData(e.getPassSeries(), e.getPassNumber()).toString() + " was founded on repo");
            System.out.println("----------------------------------");
        }

        return clients.getClientByPassData(Integer.parseInt(clientData[6]), Integer.parseInt(clientData[7]));
    }

    /**
     * @return contracts
     */
    public Contracts getContracts() {
        return contracts;
    }

    /**
     * validate all contracts
     *
     * @return all validation results
     */
    public ArrayList<ValidatorResult> validateAllContracts() {
        ArrayList<ValidatorResult> results = new ArrayList<>();

        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts.getContracts()); i++)
            for (IValidator validator : validators)
                results.add(validator.validate(contracts.getContracts()[i]));

        return results;
    }
}
