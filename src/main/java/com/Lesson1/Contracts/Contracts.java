package com.Lesson1.Contracts;

import com.Lesson1.ArayUtil;
import com.Lesson1.Clients.Clients;
import com.Lesson1.Clients.SimilarClientsException;

public class Contracts {

    private int lastContractNumber = 1;
    private final Clients clients;
    private SimpleContract[] contracts = new SimpleContract[2];


    public Contracts(Clients clients) {
        this.clients = clients;
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
            if (contracts[i].getThisContractNumber() == number) return i;
        }
        throw new ContractException();
    }

    public void addContract(Object contract, String contractType) throws SimilarClientsException {

        // If didn't find the same client, program add new client to others
        if (!clients.findClientWithPass((((BasicContract) contract).getClient().getPassSeries()),
                (((BasicContract) contract).getClient().getPassNumber())))
            clients.addClient(((BasicContract) contract).getClient());

        // If contracts array is full, we increase him
        if (ArayUtil.arrayIsFull(this.contracts)) contracts = (SimpleContract[]) ArayUtil.increaseArray(contracts);

        ++lastContractNumber;
        contracts[ArayUtil.getArrayValuesCount(contracts)] = new SimpleContract(contract, contractType, lastContractNumber);
    }

    /**
     * Return true if program found contract with transferred number
     *
     * @param contractNumber The Contract Number
     * @return If Contact with similar number founded in list, return true, otherwise return false
     */
    public boolean hasContractWithNumber(int contractNumber) {
        for (SimpleContract contract : contracts) {
            if (contract.getThisContractNumber() == contractNumber) return true;
        }
        return false;
    }

    /**
     * Return SimilarContract by number
     *
     * @param number The contract number, which we need to find
     */
    public SimpleContract getContractByHisNumber(int number) {
        for (SimpleContract contract : contracts) {
            if (contract.getThisContractNumber() == number) return contract;
        }
        return null;
    }

    public int getContractsCount() {
        return ArayUtil.getArrayValuesCount(contracts);
    }

}