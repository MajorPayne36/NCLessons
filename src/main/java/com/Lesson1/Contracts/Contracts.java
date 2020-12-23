package com.Lesson1.Contracts;

import com.Lesson1.ArrayUtil.ArayUtil;
import com.Lesson1.Clients.Clients;
import com.Lesson1.Clients.SimilarClientsException;

import java.util.function.Predicate;

public class Contracts {

    private int lastContractNumber = 1;
    private final Clients clients;
    private BasicContract[] contracts = new BasicContract[2];


    public Contracts(Clients clients) {
        this.clients = clients;
    }

    public BasicContract[] getContracts() {
        return contracts;
    }

    /**
     * This method find Contract by his Number and delete him
     *
     * @param number The founded Number
     * @throws ContractException
     */
    public void deleteContractByNumber(int number) throws ContractException {
        if (!hasContractWithNumber(number)) throw new ContractException();
        int i = getContractId(number);
        do contracts[i] = contracts[++i];
        while (contracts[i] != null);

    }

    /**
     * Find Contract by his number and return his id in array
     *
     * @param number Number of Contract
     * @return Id in array
     * @throws ContractException
     */
    public int getContractId(int number) throws ContractException {
        for (int i = 0; i < getContractsCount() - 1; i++) {
            if (contracts[i].getContractNumber() == number) return i;
        }
        throw new ContractException();
    }

    public void addContract(Object contract, String contractType) throws SimilarClientsException {

        // If didn't find the same client, program add new client to others
        if (!clients.findClientWithPass((((BasicContract) contract).getClient().getPassSeries()),
                (((BasicContract) contract).getClient().getPassNumber())))
            clients.addClient(((BasicContract) contract).getClient());

        // If contracts array is full, we increase him
        if (ArayUtil.arrayIsFull(this.contracts)) contracts = (BasicContract[]) ArayUtil.increaseArray(contracts);

        ++lastContractNumber;
        contracts[ArayUtil.getArrayValuesCount(contracts)] = (BasicContract) contract;
        switch (contractType) {
            case ("DigitalTVContract"):
                contracts[ArayUtil.getArrayValuesCount(contracts)] = (DigitalTVContract) contract;
                ((DigitalTVContract) contract).setContractNumber(lastContractNumber);
                ++lastContractNumber;
                break;
            case ("PhoneContract"):
                contracts[ArayUtil.getArrayValuesCount(contracts)] = (PhoneContract) contract;
                ((PhoneContract) contract).setContractNumber(lastContractNumber);
                ++lastContractNumber;
                break;
            case ("WiredInternetContract"):
                contracts[ArayUtil.getArrayValuesCount(contracts)] = (WiredInternetContract) contract;
                ((WiredInternetContract) contract).setContractNumber(lastContractNumber);
                ++lastContractNumber;
                break;
        }
    }

    /**
     * Return true if program found contract with transferred number
     *
     * @param contractNumber The Contract Number
     * @return If Contact with similar number founded in list, return true, otherwise return false
     */
    public boolean hasContractWithNumber(int contractNumber) {
        for (BasicContract contract : contracts) {
            if (contract.getContractNumber() == contractNumber) return true;
        }
        return false;
    }

    /**
     * Return SimilarContract by number
     *
     * @param number The contract number, which we need to find
     */
    public BasicContract getContractByHisNumber(int number) {
        for (BasicContract contract : contracts) {
            if (contract.getContractNumber() == number) return contract;
        }
        return null;
    }
