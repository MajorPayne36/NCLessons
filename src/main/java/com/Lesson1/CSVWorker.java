package com.Lesson1;

import com.Lesson1.Clients.Clients;
import com.Lesson1.Contracts.ContractTypes;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.Contracts.TVPackages;
import org.apache.commons.csv.*;


import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWorker {
    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String[] FILE_HEADER = {"startDate"
            , "endDate"
            , "lastName"
            , "firstName"
            , "birthday"
            , "gender"
            , "passSeries"
            , "passNumber"
            , "contractType"
            , "contractInfo"};

    public static void writeCsvFile(String fileName) {


        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {

            //initialize FileWriter object
            fileWriter = new FileWriter(fileName);

            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            //Creating new contracts
            String[] contract1 = new String[]{"20-12-2020", "20-12-2025", "Путин", "Владимир", "07-10-1952", "male", "2019", "204031", ContractTypes.digitalTV, TVPackages.premiumPack.toString()};
            String[] contract2 = new String[]{"19-03-2007", "19-03-2009", "Двейн", "Джонсон", "02-05-1972", "male", "2018", "456987", ContractTypes.phoneContract, "900mi 10gb 900sms",};
            String[] contract3 = new String[]{"01-02-2009", "01-02-2010", "Трамп", "Дональд", "14-06-1946", "male", "2019", "124578", ContractTypes.wiredInternetContract, "100",};
            String[] contract4 = new String[]{"03-11-2010", "03-11-2011", "Ким", "Ын", "08-01-1982", "male", "2016", "326598", ContractTypes.digitalTV, TVPackages.premiumPack.toString()};
            String[] contract5 = new String[]{"30-06-2017", "30-06-2018", "Брюс", "Ли", "27-11-1940", "male", "2019", "201030", ContractTypes.wiredInternetContract, "200",};

            //Write contracts to CSV file
            String[] contract6 = new String[]{"12-12-2012", "12-12-2013", "Шварцнеггер", "Арнольд", "30-07-1947", "male", "2018", "363534", ContractTypes.phoneContract, "2000mi 2gb 100sms",};
            csvFilePrinter.printRecord(contract1);
            csvFilePrinter.printRecord(contract2);
            csvFilePrinter.printRecord(contract3);
            csvFilePrinter.printRecord(contract4);
            csvFilePrinter.printRecord(contract5);
            csvFilePrinter.printRecord(contract6);

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    public static Contracts readContractsFromCSVFile(String fileName) {

        FileReader fileReader = null;

        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);

        try {


            //Create a new list of student to be filled by CSV file data
            Contracts contracts = new Contracts(new Clients());

            //initialize FileReader object
            fileReader = new FileReader(fileName);

            //Get a list of CSV file records
            List csvRecords = csvFileParser.getRecords();

            //Read the CSV file records starting from the second record to skip the header
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord recor
                //initialize CSVParser object
                csvFileParser = new CSVParser(fileReader, csvFileFormat);
                d = (CSVRecord) csvRecords.get(i);
                contracts.addContract(new Bas);
                //Create a new student object and fill his data
                switch (record.get("contractType")){
                    case C
                }
            }

            //Print the new student list
            for (Student student : students) {
                System.out.println(student.toString());
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }


    }
}

