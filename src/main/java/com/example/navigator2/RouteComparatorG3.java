package com.example.navigator2;

import java.util.Comparator;

public class RouteComparatorG3 implements Comparator<Route> {
    @Override
    public int compare(Route route1, Route route2) {
        // Сравнение по популярности (убывающий порядок)
        int popularityComparison = Integer.compare(route2.getPopularity(), route1.getPopularity());

        // Если популярность одинакова, сравниваем по расстоянию (возрастающий порядок)
        if (popularityComparison == 0) {
            int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());

            // Если расстояние одинаково, сравниваем по количеству точек местоположения (возрастающий порядок)
            if (distanceComparison == 0) {
                return Integer.compare(route1.getLocationPoints().size(), route2.getLocationPoints().size());
            }

            return distanceComparison;
        }

        return popularityComparison;
    }
}