package com.Lesson1.Clients;

import com.Lesson1.ArayUtil;

import java.time.LocalDateTime;

/**
 * This class are save clients array and realise methods to work with him
 */
public class Clients {
    private Client[] clients = new Client[2];
    private Client client;


    /**
     * Add new client to array
     *
     * @param firstName  First name
     * @param secondName Second name
     * @param birthday   his Birthday
     * @param passSeries Series of passport
     * @param passNumber Number of passport
     * @param gender     Gender
     * @throws SimilarClientsException
     */
    public void addClient(
            String firstName,
            String secondName,
            LocalDateTime birthday,
            int passSeries,
            int passNumber,
            String gender) throws SimilarClientsException {

        if (findClientWithPass(passSeries, passNumber)) {
            throw new SimilarClientsException("There is a client with same passport data");
        }

        client = new Client(firstName, secondName, birthday, passSeries, passNumber, gender);

        if (arrayIsFull()) clients = (Client[]) ArayUtil.increaseArray(clients);

        clients[ArayUtil.getArrayValuesCount(clients)] = client;
    }

    /**
     * Add to client's array transferred client
     *
     * @param client The client whom we need to add in array
     * @throws SimilarClientsException
     */
    public void addClient(Client client) throws SimilarClientsException {
        if (findClientWithPass(client.getPassSeries(), client.getPassNumber())) {
            throw new SimilarClientsException("There is a client with same passport data",client.getPassSeries(), client.getPassNumber());
        }

        client.setId(ArayUtil.getArrayValuesCount(clients) + 1);
        if (arrayIsFull()) clients = (Client[]) ArayUtil.increaseArray(clients);
        clients[ArayUtil.getArrayValuesCount(clients)] = client;
    }

    /**
     * Find and return Client by Id
     *
     * @param id
     * @return
     */
    public Client getClientById(int id) {
        int clientsCount = ArayUtil.getArrayValuesCount(clients);
        for (int i = 0; i < clientsCount; i++) {
            if (clients[i].getId() == id) return clients[i];
        }
        return null;
    }

    /**
     * Find and return Client by passport data
     *
     * @param passSeries Series
     * @param passNumber Number
     * @return
     */
    public Client getClientByPassData(int passSeries, int passNumber) {
        int clientsCount = ArayUtil.getArrayValuesCount(clients);
        for (int i = 0; i < clientsCount; i++) {
            if (clients[i].getPassNumber() == passNumber && clients[i].getPassSeries() == passSeries) return clients[i];
        }
        return null;
    }

    /**
     * This method return true if transferred client found in array
     *
     * @param client
     * @return
     */
    public boolean hasClient(Client client) {
        for (Client cl : this.clients) if (cl == client) return true;
        return false;
    }

    /**
     * This method return true if found Client with similar passport data
     *
     * @param passSeries Series
     * @param passNumber Number
     * @return
     */
    public boolean findClientWithPass(int passSeries, int passNumber) {
        int clientsCount = ArayUtil.getArrayValuesCount(clients);
        if (clientsCount == 0) return false;
        for (int i = 0; i < clientsCount; i++) {
            if (clients[i].getPassNumber() == passNumber
                    && clients[i].getPassSeries() == passSeries) return true;
        }
        return false;
    }

    /**
     * Return true if this array is full
     *
     * @return
     */
    private boolean arrayIsFull() {
        return ArayUtil.getArrayValuesCount(clients) == clients.length;
    }

    public Client[] getClients() {
        return clients;
    }
}



