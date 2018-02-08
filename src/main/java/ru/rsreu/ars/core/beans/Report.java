package ru.rsreu.ars.core.beans;

/**
 * Created by medwed on 9/20/2017.
 */
public class Report {
    private String owner;
    private String group;
    private String number;
    private String listing;
    private String checkstyleResult;

    public Report(String owner, String group, String number, String checkstyleResult) {
        this.owner = owner;
        this.group = group;
        this.number = number;
        this.checkstyleResult = checkstyleResult;
    }


    public String getCheckstyleResult() {
        return checkstyleResult;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    public String getNumber() {
        return number;
    }


    public String getGroup() {
        return group;
    }


    public String getOwner() {
        return owner;
    }

}
