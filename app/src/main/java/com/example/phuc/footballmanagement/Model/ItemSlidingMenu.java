package com.example.phuc.footballmanagement.Model;


public class ItemSlidingMenu {
    int idImage;
    String itemName;

    public ItemSlidingMenu(int idImage, String itemName) {
        this.idImage = idImage;
        this.itemName = itemName;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}