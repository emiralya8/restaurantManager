package dev.example.restaurantManager.service;

import java.util.List;

public interface ICustomQueriesService<T> {
    List<T> getElementByContentDescription(String contentDescription);
}
