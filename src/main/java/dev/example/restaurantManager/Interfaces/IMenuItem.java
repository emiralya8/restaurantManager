package dev.example.restaurantManager.Interfaces;

public interface IMenuItem {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    double getPrice();
    void setPrice(double price);
}
