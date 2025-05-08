package com.serasonproject.task71p;

public class LostFoundItem {
    private int itemId;
    private String itemType;
    private String itemName;
    private String itemPhone;
    private String itemDesc;
    private String iDate;
    private String iLoc;


    public LostFoundItem(int itemId, String itemType, String itemName, String itemPhone, String itemDesc, String iDate, String iLoc) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemPhone = itemPhone;
        this.itemDesc = itemDesc;
        this.iDate = iDate;
        this.iLoc = iLoc;
    }

    public LostFoundItem(String itemType, String itemName, String itemPhone, String itemDesc, String iDate, String iLoc) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemPhone = itemPhone;
        this.itemDesc = itemDesc;
        this.iDate = iDate;
        this.iLoc = iLoc;
    }

    public LostFoundItem() {
        /* empty constructor required for getting Task to edit */
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPhone() {
        return itemPhone;
    }

    public void setItemPhone(String itemPhone) {
        this.itemPhone = itemPhone;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getiDate() {
        return iDate;
    }

    public void setiDate(String iDate) {
        this.iDate = iDate;
    }

    public String getiLoc() {
        return iLoc;
    }

    public void setiLoc(String iLoc) {
        this.iLoc = iLoc;
    }
}
