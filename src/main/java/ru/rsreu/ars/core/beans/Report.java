package ru.rsreu.ars.core.beans;

import java.util.Map;

/**
 * Created by medwed on 9/20/2017.
 */
public class Report {
    private Map<String, UserInformation> userInformationMap;
    private String listing;
    private String checkstyleResult;

    public Report(Map<String, UserInformation> userInformationMap, String checkstyleResult) {
        this.userInformationMap = userInformationMap;
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

    public Map<String, UserInformation> getUserInformationMap() {
        return userInformationMap;
    }

}
