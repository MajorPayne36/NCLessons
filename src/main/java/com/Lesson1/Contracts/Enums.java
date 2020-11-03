package com.Lesson1.Contracts;

enum TVPackages {

    standartPack (new String[]{"NickJr", "Disney", "THT", "CTC", "HTB"}),

    premiumPack  (new String[]{"THT", "CTC", "HTB", "HDKino", "KinoFilm", "THThd", "Fox", "Nickelodeon"}),

    childPack  (new String[]{"NickJr", "Disney", "Gulli", "Nickelodeon", "JimJam"});

    private String[] tvPackage;

    TVPackages (String[] tvPackage){
        this.tvPackage = tvPackage;
    }

    public String[] getTvPackage() {
        return tvPackage;
    }
}

enum ContractsEnum {PhoneContract, DigitalTVContract, WiredInternetContract}
