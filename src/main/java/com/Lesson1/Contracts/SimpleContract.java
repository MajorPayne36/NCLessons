package com.Lesson1.Contracts;


public class SimpleContract {
    private final int thisContractNumber;
    private final String thisContractType;
    private final PhoneContract phoneContract;
    private final DigitalTVContract tvContract;
    private final WiredInternetContract internetContract;


    /**
     * Save the contract to correct field
     *
     * @param contract       The saved contract
     * @param contractType   Contract type
     * @param contractNumber The contract number
     */
    public SimpleContract(Object contract, String contractType, int contractNumber) {
        thisContractType = contractType;
        thisContractNumber = contractNumber;

        //Send the contract number to the basic contract
        ((BasicContract) contract).setContractNumber(contractNumber);
        switch (contractType) {
            case ("DigitalTVContract"):
                phoneContract = null;
                tvContract = (DigitalTVContract) contract;
                internetContract = null;
                break;
            case ("PhoneContract"):
                phoneContract = (PhoneContract) contract;
                tvContract = null;
                internetContract = null;
                break;
            case ("WiredInternetContract"):
                phoneContract = null;
                tvContract = null;
                internetContract = (WiredInternetContract) contract;
                break;
            default:
                phoneContract = null;
                tvContract = null;
                internetContract = null;
        }
    }

    /**
     * Return this contract
     */
    public Object getContract() {
        if (phoneContract != null) return phoneContract;
        else if (tvContract != null) return tvContract;
        else if (internetContract != null) return internetContract;
        else return null;
    }

    /**
     * Return this contract number in int
     */
    public int getThisContractNumber() {
        return thisContractNumber;
    }

    /**
     * Return this contract type in String
     */
    public String getThisContractType() {
        return thisContractType;
    }
}