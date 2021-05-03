package com.Lesson1.JDBC;

import com.Lesson1.Clients.Client;
import com.Lesson1.Clients.Clients;
import com.Lesson1.Clients.SimilarClientsException;
import com.Lesson1.Contracts.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DBOperations {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The query which create clients table
     */
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE IF NOT EXISTS client("
            + "CLIENT_ID INT PRIMARY KEY, "
            + "FIRST_NAME VARCHAR(255), "
            + "SECOND_NAME VARCHAR(255), "
            + "BIRTHDAY DATE, "
            + "PASS_SERIES INT "
            + "PASS_NUMBER INT "
            + "GENDER VARCHAR(255), ";

    /**
     * The query which create contracts table
     */
    private static final String CREATE_TABLE_CONTRACT = "CREATE TABLE IF NOT EXISTS contract("
            + "CONTRACT_ID INT PRIMARY KEY, "
            + "CONTRACT_START_DATE DATE, "
            + "CONTRACT_END_DATE DATE, "
            + "CONTRACT_TYPE VARCHAR(255), "
            + "CONTRACT_INFO VARCHAR(255), "
            + "CLIENT_ID INT, "
            + "foreign key (CLIENT_ID) references client(CLIENT_ID)) ";

    /**
     * The query which insert client to clients table
     */
    private static final String INSERT_PERSON = "INSERT INTO person VALUES(?, ?, ?, ?, ?, ?, ?)";

    /**
     * The query which insert contract to contracts table
     */
    private static final String INSERT_CONTRACT = "INSERT INTO contract VALUES(?, ?, ?, ?, ?, ?)";

    /**
     * The query which select rows from both tables
     */
    private static final String SELECT_REPOSITORY = "SELECT c.*, cl.* FROM contract AS c JOIN client AS cl ON c.CLIENT_ID = cl.CLIENT_ID";

    /**
     * The clients set
     */
    private final Clients clients;

    /**
     * DB driver
     */
    private String driver;

    /**
     * DB url
     */
    private String url;

    /**
     * DB user
     */
    private String user;

    /**
     * DB user password
     */
    private String password;

    /**
     * Load properties to connect to h2
     */
    public DBOperations(Clients clients) {
        loadProperties();
        this.clients = clients;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class was not found", e);
        }
    }

    /**
     * Loads database connection properties from resources
     */
    private void loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = DBOperations.class.getResourceAsStream("/connection.properties")) {
            properties.load(inputStream);

            driver = properties.getProperty(DBConnectionData.DRIVER);
            url = properties.getProperty(DBConnectionData.URL);
            user = properties.getProperty(DBConnectionData.USER);
            password = properties.getProperty(DBConnectionData.PASSWORD);
        } catch (IOException e) {
            LOGGER.error("Something went wrong while get properties", e);
        }
    }

    /**
     * Save contracts to DB
     *
     * @param contracts the repository which we save
     */
    public void saveContractsToDB(Contracts contracts) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement personStatement = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement contractStatement = connection.prepareStatement(INSERT_CONTRACT)) {

            List<Client> clients = new ArrayList<>();
            boolean add;

            for (int i = 0; i < contracts.getContractsCount(); i++) {
                add = true;
                Client currentClient = contracts.getContracts()[i].getClient();

                for (Client c : clients) {
                    if (currentClient.equals(c)) {
                        add = false;
                        break;
                    }
                }

                if (add) {
                    this.clients.addClient(currentClient);
                    personStatement.setInt(1, currentClient.getId());
                    personStatement.setString(2, currentClient.getFirstName());
                    personStatement.setString(3, currentClient.getSecondName());
                    personStatement.setDate(4, Date.valueOf(String.valueOf(currentClient.getBirthday())));
                    personStatement.setInt(5, currentClient.getPassSeries());
                    personStatement.setInt(6, currentClient.getPassNumber());
                    personStatement.setString(7, currentClient.getGender());

                    personStatement.executeUpdate();
                }
            }

            for (int i = 0; i < contracts.getContractsCount(); i++) {
                BasicContract currentContract = contracts.getContracts()[i];

                contractStatement.setInt(1, currentContract.getContractNumber());
                contractStatement.setDate(2, Date.valueOf(String.valueOf(currentContract.getStartDateTime())));
                contractStatement.setDate(3, Date.valueOf(String.valueOf(currentContract.getEndDateTime())));
                contractStatement.setString(4, currentContract.getClass().getSimpleName());
                if (currentContract instanceof DigitalTVContract) {
                    contractStatement.setString(5,
                            String.valueOf(((DigitalTVContract) currentContract).getTvPackage()));
                } else if (currentContract instanceof PhoneContract) {
                    String additionalInfo = ((PhoneContract) currentContract).getMinuteCount() + "/" +
                            ((PhoneContract) currentContract).getSmsCount() + "/" +
                            ((PhoneContract) currentContract).getInternetCount();
                    contractStatement.setString(5, additionalInfo);
                } else if (currentContract instanceof WiredInternetContract) {
                    contractStatement.setString(5, String.valueOf(((WiredInternetContract) currentContract).getMaxSpeed()));
                }
                contractStatement.setInt(6, currentContract.getClient().getId());
                contractStatement.executeUpdate();
            }
        } catch (SQLException | SimilarClientsException e) {
            LOGGER.error("Database access error or the url is null", e);
        }
    }

    /**
     * Get all contracts from DB
     *
     * @return contracts
     */
    public Contracts getContractsfromDB() {
        Contracts contracts = new Contracts(new Clients());
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_REPOSITORY);
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("SECOND_NAME"),
                        resultSet.getTimestamp("BIRTHDAY").toLocalDateTime(),
                        resultSet.getInt("PASS_SERIES"),
                        resultSet.getInt("PASS_NUMBER"),
                        resultSet.getString("GENDER")
                );
                switch (resultSet.getString("CONTRACT_TYPE")) {
                    case "DigitalTVContract": {
                        BasicContract contract = new DigitalTVContract(
                                resultSet.getString("CONTRACT_INFO"),
                                client,
                                resultSet.getTimestamp("CONTRACT_START_DATE").toLocalDateTime(),
                                resultSet.getTimestamp("CONTRACT_END_DATE").toLocalDateTime()
                        );
                        contract.setContractNumber(resultSet.getInt("CONTRACT_NUMBER"));
                        contracts.addContract(contract);
                        break;
                    }
                    case "PhoneContract": {
                        String additionalInfo = resultSet.getString("CONTRACT_INFO");
                        String minutes = additionalInfo.substring(0, additionalInfo.indexOf("/"));
                        additionalInfo = additionalInfo.substring(minutes.length() + 1);
                        String sms = additionalInfo.substring(0, additionalInfo.indexOf("/"));
                        additionalInfo = additionalInfo.substring(sms.length() + 1);
                        String internet = additionalInfo;

                        BasicContract contract = new PhoneContract(
                                Integer.parseInt(sms),
                                Integer.parseInt(minutes),
                                Integer.parseInt(internet),
                                client,
                                resultSet.getTimestamp("CONTRACT_START_DATE").toLocalDateTime(),
                                resultSet.getTimestamp("CONTRACT_END_DATE").toLocalDateTime()
                        );
                        contract.setContractNumber(resultSet.getInt("CONTRACT_NUMBER"));
                        contracts.addContract(contract);
                        break;
                    }
                    case "WiredInternetContract": {
                        BasicContract contract = new WiredInternetContract(
                                client,
                                Integer.parseInt(resultSet.getString("CONTRACT_INFO")),
                                resultSet.getTimestamp("CONTRACT_START_DATE").toLocalDateTime(),
                                resultSet.getTimestamp("CONTRACT_END_DATE").toLocalDateTime()
                        );
                        contract.setContractNumber(resultSet.getInt("CONTRACT_NUMBER"));
                        contracts.addContract(contract);
                        break;
                    }
                }
            }
        } catch (SQLException | SimilarClientsException e) {
            LOGGER.error("Anything went wrong while getting data from DB", e);
        }
        return contracts;
    }
    
    /**
     * This method create a client table if it doesn't exist
     */
    public void createClientTable() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_CLIENT);
        } catch (SQLException e) {
            LOGGER.error("Anything went wrong while creating client table", e);
        }
    }

    /**
     * This method creates a contract table if it doesn't exist
     */
    public void createContractTable() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_CONTRACT);
        } catch (SQLException e) {
            LOGGER.error("Anything went wrong while creating contract table", e);
        }
    }
}
