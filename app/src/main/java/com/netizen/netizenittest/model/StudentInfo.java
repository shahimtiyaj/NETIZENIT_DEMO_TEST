package com.netizen.netizenittest.model;

public class StudentInfo {
    int _id;
    private String SName;
    private byte[] SImage;
    private String SSession;
    private String SClass;
    private String SEmail;
    private String SBirthDate;
    private String SBloodGroup;
    private String SContactPersion;
    private String SArea;
    private String SPhone;
    private String SCity;
    private String SPinCode;
    private String SGender;
    private String SAgree;

    public StudentInfo() {

    }

    public StudentInfo(String SName, String SSession, String SClass,String SEmail, String SBirthDate, String SBloodGroup,
                       String SContactPersion, String SArea, String SPhone, String SCity, String SPinCode,
                       String SGender, String SAgree, byte[] SImage) {
        this.SName = SName;
        this.SSession = SSession;
        this.SClass = SClass;
        this.SEmail = SEmail;
        this.SBirthDate = SBirthDate;
        this.SBloodGroup = SBloodGroup;
        this.SContactPersion = SContactPersion;
        this.SArea = SArea;
        this.SPhone = SPhone;
        this.SCity = SCity;
        this.SPinCode = SPinCode;
        this.SGender = SGender;
        this.SAgree = SAgree;
        this.SImage = SImage;

    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public String getSSession() {
        return SSession;
    }

    public void setSSession(String SSession) {
        this.SSession = SSession;
    }

    public String getSClass() {
        return SClass;
    }

    public void setSClass(String SClass) {
        this.SClass = SClass;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }

    public String getSBirthDate() {
        return SBirthDate;
    }

    public void setSBirthDate(String SBirthDate) {
        this.SBirthDate = SBirthDate;
    }

    public String getSBloodGroup() {
        return SBloodGroup;
    }

    public void setSBloodGroup(String SBloodGroup) {
        this.SBloodGroup = SBloodGroup;
    }

    public String getSContactPersion() {
        return SContactPersion;
    }

    public void setSContactPersion(String SContactPersion) {
        this.SContactPersion = SContactPersion;
    }

    public String getSArea() {
        return SArea;
    }

    public void setSArea(String SArea) {
        this.SArea = SArea;
    }

    public String getSPhone() {
        return SPhone;
    }

    public void setSPhone(String SPhone) {
        this.SPhone = SPhone;
    }

    public String getSCity() {
        return SCity;
    }

    public void setSCity(String SCity) {
        this.SCity = SCity;
    }

    public String getSPinCode() {
        return SPinCode;
    }

    public void setSPinCode(String SPinCode) {
        this.SPinCode = SPinCode;
    }

    public String getSGender() {
        return SGender;
    }

    public void setSGender(String SGender) {
        this.SGender = SGender;
    }

    public String getSAgree() {
        return SAgree;
    }

    public void setSAgree(String SAgree) {
        this.SAgree = SAgree;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public byte[] getSImage() {
        return SImage;
    }

    public void setSImage(byte[] SImage) {
        this.SImage = SImage;
    }

}
