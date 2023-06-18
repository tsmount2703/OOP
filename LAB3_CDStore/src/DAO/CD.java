/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Dell
 */
public class CD {

    private String ID;
    private String collectionName;
    private String type;
    private String title;
    private int unitPrice;
    private int publishingYear;

    public CD() {
    }

    public CD(String ID, String collectionName, String type, String title, int unitPrice, int publishingYear) {
        this.ID = ID;
        this.collectionName = collectionName;
        this.type = type;
        this.title = title;
        this.unitPrice = unitPrice;
        this.publishingYear = publishingYear;
    }

    public CD cloneCD(CD cd) {
        return new CD(cd.getID(), cd.getCollectionName(), cd.getType(), cd.getTitle(), cd.getUnitPrice(), cd.getPublishingYear());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    @Override
    public String toString() {
        return ID + "," + collectionName + "," + type + "," + title + "," + unitPrice + "," + publishingYear + "\n";
    }

}
