package dev.example.restaurantManager.utilities.fakers;

import java.util.List;

public interface IFaker<T> {
    T CreateObject(T object);
    List<T> GetNObjects(int n);
}