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

    public int getContractsCount() {
        return ArayUtil.getArrayValuesCount(contracts);
    }

    /**
     * Use Comparable to do bubble sort by Contract Number
     */
    public void sortBubble() {
        for (int i = 0; i < contracts.length - 1; i++) {
            // внутренний цикл прохода
            for (int j = contracts.length - 1; j > i; j--) {
                if (contracts[j - 1].compareTo(contracts[j]) > 0) {
                    swap(j - 1, j);
//                    BasicContract tmp = contracts[j - 1];
//                    contracts[j - 1] = contracts[j];
//                    contracts[j] = tmp;
                }
            }
        }
    }

    /**
     * This method do Shell's sort by entered type of Comparator
     *
     * @param a it's type of sort parameter
     */
    public void sortShell(Comparator<BasicContract> a) {
        int h = 1;
        int arrLength = ArayUtil.getArrayValuesCount(contracts);
        while (h * 3 < arrLength)
            h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < arrLength; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (a.compare(contracts[j], contracts[j - h]) < 0)
                        swap(j, j - h);
                    else
                        break;
                }
            }
            h = h / 3;
        }
    }

    /**
     * Swap the elements in array
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        BasicContract tmp = contracts[i];
        contracts[i] = contracts[j];
        contracts[j] = tmp;
    }

    /**
     * This method return needed Contracts as array
     * @param p Predicate for find Contracts
     * @return return BasicContract[] array
     */
    public BasicContract[] filterContracts (Predicate<BasicContract> p){
        BasicContract[] c = new BasicContract[0];
        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts);i++){
            if(p.test(contracts[i]))  {
                c = Arrays.copyOf(c,c.length+1,c.getClass());
                c[ArayUtil.getArrayValuesCount(c)-1] = contracts[i];
            }
        }
        return c.length == 0 ? null : c;
    }

}