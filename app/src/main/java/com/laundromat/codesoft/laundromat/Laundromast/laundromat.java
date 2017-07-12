package com.laundromat.codesoft.laundromat.Laundromast;




public class laundromat {

    private String laundromatID;
    private String laundromatName;
    private String laundromatDescription;
    private int laundromatAddres;
    private float laundromatRating;
    private String laundromatLat;
    private String laundromatLog;
    private String laundromatPhone;
    private String laundromatImage;
    private String laundromatO;

    public laundromat(){}

    public laundromat(String laundromatID, String laundromatName, String laundromatDescription, int laundromatAddres, float laundromatRating, String laundromatLat, String laundromatLog, String laundromatPhone, String laundromatImage, String laundromatO) {
        this.laundromatID = laundromatID;
        this.laundromatName = laundromatName;
        this.laundromatDescription = laundromatDescription;
        this.laundromatAddres = laundromatAddres;
        this.laundromatRating = laundromatRating;
        this.laundromatLat = laundromatLat;
        this.laundromatLog = laundromatLog;
        this.laundromatPhone = laundromatPhone;
        this.laundromatImage = laundromatImage;
        this.laundromatO = laundromatO;
    }

    public String getLaundromatID() {
        return laundromatID;
    }

    public void setLaundromatID(String laundromatID) {
        this.laundromatID = laundromatID;
    }

    public String getLaundromatName() {
        return laundromatName;
    }

    public void setLaundromatName(String laundromatName) {
        this.laundromatName = laundromatName;
    }

    public String getLaundromatDescription() {
        return laundromatDescription;
    }

    public void setLaundromatDescription(String laundromatDescription) {
        this.laundromatDescription = laundromatDescription;
    }

    public int getLaundromatAddres() {
        return laundromatAddres;
    }

    public void setLaundromatAddres(int laundromatAddres) {
        this.laundromatAddres = laundromatAddres;
    }

    public float getLaundromatRating() {
        return laundromatRating;
    }

    public void setLaundromatRating(float laundromatRating) {
        this.laundromatRating = laundromatRating;
    }

    public String getLaundromatLat() {
        return laundromatLat;
    }

    public void setLaundromatLat(String laundromatLat) {
        this.laundromatLat = laundromatLat;
    }

    public String getLaundromatLog() {
        return laundromatLog;
    }

    public void setLaundromatLog(String laundromatLog) {
        this.laundromatLog = laundromatLog;
    }

    public String getLaundromatPhone() {
        return laundromatPhone;
    }

    public void setLaundromatPhone(String laundromatPhone) {
        this.laundromatPhone = laundromatPhone;
    }

    public String getLaundromatImage() {
        return laundromatImage;
    }

    public void setLaundromatImage(String laundromatImage) {
        this.laundromatImage = laundromatImage;
    }

    public String getLaundromatO() {
        return laundromatO;
    }

    public void setLaundromatO(String laundromatO) {
        this.laundromatO = laundromatO;
    }
}
