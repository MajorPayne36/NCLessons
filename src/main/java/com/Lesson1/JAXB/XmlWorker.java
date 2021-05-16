package com.Lesson1.JAXB;

import com.Lesson1.Clients.Client;
import com.Lesson1.Contracts.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlWorker {
    public static Contracts fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(BasicContract.class,
                    PhoneContract.class,
                    DigitalTVContract.class,
                    WiredInternetContract.class,
                    Client.class,
                    Contracts.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Contracts) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем объект в XML файл
    public static void convertObjectToXml(Contracts contracts, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BasicContract.class,
                    PhoneContract.class,
                    DigitalTVContract.class,
                    WiredInternetContract.class,
                    Client.class,
                    Contracts.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(contracts, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
