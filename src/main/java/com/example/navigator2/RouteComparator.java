package com.example.navigator2;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    private String destinationPoint;

    public RouteComparator(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    @Override
    public int compare(Route route1, Route route2) {
        int indexOfDestination1 = route1.getLocationPoints().indexOf(destinationPoint);
        int indexOfDestination2 = route2.getLocationPoints().indexOf(destinationPoint);

        if (indexOfDestination1 > 0 && indexOfDestination2 > 0) {
            // Если указанная точка не является начальной для обоих маршрутов
            int logicalOrderDistance1 = route1.getLogicalOrderDistance();
            int logicalOrderDistance2 = route2.getLogicalOrderDistance();

            if (logicalOrderDistance1 != logicalOrderDistance2) {
                // Сначала по логическому расстоянию в порядке возрастания
                return Integer.compare(logicalOrderDistance1, logicalOrderDistance2);
            } else {
                // Если логические расстояния равны, то по популярности в порядке убывания
                int popularity1 = route1.getPopularity();
                int popularity2 = route2.getPopularity();
                return Integer.compare(popularity2, popularity1);
            }
        } else {
            // Если маршруты не имеют логического порядка, то сравниваем по расстоянию и популярности как обычно
            double distance1 = route1.getDistance();
            double distance2 = route2.getDistance();

            if (distance1 != distance2) {
                // Сначала по расстоянию в порядке возрастания
                return Double.compare(distance1, distance2);
            } else {
                // Если расстояния равны, то по популярности в порядке убывания
                int popularity1 = route1.getPopularity();
                int popularity2 = route2.getPopularity();
                return Integer.compare(popularity2, popularity1);
            }
        }
    }
}