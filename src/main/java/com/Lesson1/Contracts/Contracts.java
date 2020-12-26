package com.Lesson1.Contracts;

import com.Lesson1.ArayUtil;
import com.Lesson1.Clients.Clients;
import com.Lesson1.Clients.SimilarClientsException;
import com.Lesson1.Sorters.BubbleSorter;
import com.Lesson1.Sorters.ShellSorter;
import com.Lesson1.di.Annotations.AutoInjectable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class Contracts {

    @AutoInjectable(clazz = BubbleSorter.class)
    BubbleSorter bubbleSorter;

    @AutoInjectable(clazz = ShellSorter.class)
    ShellSorter shellSorter;

    private int lastContractNumber = 1;
    private final Clients clients;
    private BasicContract[] contracts = new BasicContract[2];


    public Contracts(Clients clients) {
        this.clients = clients;
    }

    public BasicContract[] getContracts() {
        return contracts;
    }

    public void addContract(BasicContract contract) throws SimilarClientsException {

        // If didn't find the same client, program add new client to others
        if (!clients.findClientWithPass((contract.getClient().getPassSeries()),
                (contract.getClient().getPassNumber())))
            clients.addClient(contract.getClient());

        // If contracts array is full, we increase him
        if (ArayUtil.arrayIsFull(this.contracts)) contracts = (BasicContract[]) ArayUtil.increaseArray(contracts);

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ArayUtil.getArrayValuesCount(contracts);i++){
            sb.append(contracts[i].getClient().getId()).append(" ");
            sb.append(contracts[i].getClient().getFirstName()).append(" ");
            sb.append(contracts[i].getClient().getSecondName()).append(" ");
            sb.append(contracts[i].getClient().getPassSeries()).append(" ");
            sb.append(contracts[i].getClient().getPassNumber()).append(" ");
            sb.append(contracts[i].getStartDateTime()).append(" ");
            sb.append(contracts[i].getEndDateTime()).append(" ");
            String contractType = contracts[i].getClass().getSimpleName();
            sb.append(contractType).append(" ");
            switch (contractType) {
                case ("DigitalTVContract"):
                    sb.append(((DigitalTVContract)contracts[i]).getTvPackage());
                    break;
                case ("PhoneContract"):
                    sb.append(((PhoneContract)contracts[i]).getInternetCount()).append(" gb");
                    sb.append(((PhoneContract)contracts[i]).getMinuteCount()).append(" min");
                    sb.append(((PhoneContract)contracts[i]).getSmsCount()).append(" sms");
                    break;
                case ("WiredInternetContract"):
                    sb.append(((WiredInternetContract)contracts[i]).getMaxSpeed());
                    break;
                default:
            }

            sb.append("\n");

        }
        return sb.toString();
    }
}