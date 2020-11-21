package com.Lesson1.Contracts;

import com.Lesson1.ArayUtil;
import com.Lesson1.Clients.Clients;
import com.Lesson1.Clients.SimilarClientsException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

    public void addContract(BasicContract contract) throws SimilarClientsException {

        // If didn't find the same client, program add new client to others
        if (!clients.findClientWithPass((contract.getClient().getPassSeries()),
                (contract.getClient().getPassNumber())))
            clients.addClient(contract.getClient());

        // If contracts array is full, we increase him
        if (ArayUtil.arrayIsFull(this.contracts)) contracts = (BasicContract[]) ArayUtil.increaseArray(contracts);

        ++lastContractNumber;
        contracts[ArayUtil.getArrayValuesCount(contracts)] = contract;

        contracts[ArayUtil.getArrayValuesCount(contracts)] = contract;
        contract.setContractNumber(lastContractNumber);
        ++lastContractNumber;
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

    public int getContractsCount() {
        return ArayUtil.getArrayValuesCount(contracts);
    }


    /**
     * This method return needed Contracts as array
     *
     * @param p Predicate for find Contracts
     * @return return BasicContract[] array
     */
    public Contracts filterContracts(Predicate<BasicContract> p) throws SimilarClientsException {
        Contracts c = new Contracts(this.clients);
        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts); i++) {
            if (p.test(contracts[i])) {
                c.addContract(contracts[i]);
            }
        }
        return c;
    }

    public void sort() {
        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts) - 1; i++) {
            // внутренний цикл прохода
            for (int j = ArayUtil.getArrayValuesCount(contracts) - 1; j > i; j--) {
                if (contracts[j - 1].compareTo(contracts[j]) > 0) {
                    ArayUtil.swap(j - 1, j, contracts);
                }
            }
        }
    }

}