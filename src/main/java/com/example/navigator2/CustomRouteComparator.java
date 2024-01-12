package com.example.navigator2;

import java.util.Comparator;

public class CustomRouteComparator implements Comparator<Route> {
    private String startPoint;
    private String endPoint;

    public CustomRouteComparator(String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int compare(Route route1, Route route2) {
        boolean isFavorite1 = route1.isFavorite();
        boolean isFavorite2 = route2.isFavorite();

        if (isFavorite1 && !isFavorite2) {
            return -1; // Первый маршрут избранный, поэтому он должен быть первым в списке
        } else if (!isFavorite1 && isFavorite2) {
            return 1; // Второй маршрут избранный, поэтому он должен быть первым в списке
        } else {
            boolean isLogicalOrder1 = route1.hasLogicalOrder(startPoint, endPoint);
            boolean isLogicalOrder2 = route2.hasLogicalOrder(startPoint, endPoint);

            if (isLogicalOrder1 && isLogicalOrder2) {
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
            } else if (isLogicalOrder1) {
                // Первый маршрут логический, поэтому он должен быть первым в списке
                return -1;
            } else if (isLogicalOrder2) {
                // Второй маршрут логический, поэтому он должен быть первым в списке
                return 1;
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
}
