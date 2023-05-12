package database.model;

import java.sql.Date;

public class Person {

    private final int personID;
    private String fullName;
    private String preferredName;
    private String searchName;
    private int isPermittedToLogon;
    private String logonName;
    private int isExternalLogonProvider;
    private String hashedPassword;
    private int isSystemUser;
    private int isEmployee;
    private int isSalesperson;
    private String userPreferences;
    private String phoneNumber;
    private String faxNumber;
    private String emailAddress;
    private String photo;
    private String customFields;
    private String otherLanguages;
    private int lastEditedBy;
    private Date validFrom;
    private Date validTo;

    public Person(int personID, String fullName, String preferredName, String searchName, int isPermittedToLogon,
                  String logonName, int isExternalLogonProvider, String hashedPassword, int isSystemUser, int isEmployee,
                  int isSalesperson, String userPreferences, String phoneNumber, String faxNumber, String emailAddress,
                  String photo, String customFields, String otherLanguages, int lastEditedBy, Date validFrom, Date validTo) {
        this.personID = personID;
        this.fullName = fullName;
        this.preferredName = preferredName;
        this.searchName = searchName;
        this.isPermittedToLogon = isPermittedToLogon;
        this.logonName = logonName;
        this.isExternalLogonProvider = isExternalLogonProvider;
        this.hashedPassword = hashedPassword;
        this.isSystemUser = isSystemUser;
        this.isEmployee = isEmployee;
        this.isSalesperson = isSalesperson;
        this.userPreferences = userPreferences;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.emailAddress = emailAddress;
        this.photo = photo;
        this.customFields = customFields;
        this.otherLanguages = otherLanguages;
        this.lastEditedBy = lastEditedBy;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public int getPersonID() {
        return personID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getIsPermittedToLogon() {
        return isPermittedToLogon;
    }

    public void setIsPermittedToLogon(int isPermittedToLogon) {
        this.isPermittedToLogon = isPermittedToLogon;
    }

    public String getLogonName() {
        return logonName;
    }

    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    public int getIsExternalLogonProvider() {
        return isExternalLogonProvider;
    }

    public void setIsExternalLogonProvider(int isExternalLogonProvider) {
        this.isExternalLogonProvider = isExternalLogonProvider;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getIsSystemUser() {
        return isSystemUser;
    }

    public void setIsSystemUser(int isSystemUser) {
        this.isSystemUser = isSystemUser;
    }

    public int getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(int isEmployee) {
        this.isEmployee = isEmployee;
    }

    public int getIsSalesperson() {
        return isSalesperson;
    }

    public void setIsSalesperson(int isSalesperson) {
        this.isSalesperson = isSalesperson;
    }

    public String getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(String userPreferences) {
        this.userPreferences = userPreferences;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public String getOtherLanguages() {
        return otherLanguages;
    }

    public void setOtherLanguages(String otherLanguages) {
        this.otherLanguages = otherLanguages;
    }

    public int getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(int lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return this.fullName;
    }
}
