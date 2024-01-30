package com.example.navigator2;

import java.util.List;
import java.util.Objects;

public class Route {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }
    public boolean hasLogicalOrder(String startPoint, String endPoint) {
        if (locationPoints == null || locationPoints.size() < 2) {
            // Handle the case where the route does not have enough points
            return false;
        }

        int startIndex = locationPoints.indexOf(startPoint);
        int endIndex = locationPoints.indexOf(endPoint);

        // Check if the points are in a logical order
        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    // Method to increment the popularity of the route
    public void incrementPopularity() {
        popularity++;
    }

    // Method to get the logical order distance (placeholder logic)
    public int getLogicalOrderDistance() {
        // Placeholder logic: Return the difference between the indices of the first two points
        if (locationPoints == null || locationPoints.size() < 2) {
            // Handle the case where the route does not have enough points
            return 0;
        }

        int indexFirstPoint = locationPoints.indexOf(locationPoints.indexOf(0));
        int indexSecondPoint = locationPoints.indexOf(locationPoints.indexOf(1));

        return indexSecondPoint - indexFirstPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.distance, distance) == 0 &&
                popularity == route.popularity &&
                isFavorite == route.isFavorite &&
                Objects.equals(id, route.id) &&
                Objects.equals(locationPoints, route.locationPoints);
    }

    @Override
    public int hashCode() {
        int result = 17; // Начальное значение, не равное 0

        // Комбинируем хеш-коды полей, умножая результат на различные простые числа
        result = 31 * result + id.hashCode();
        result = 31 * result + Double.hashCode(distance);
        result = 31 * result + Integer.hashCode(popularity);
        result = 31 * result + Boolean.hashCode(isFavorite);
        result = 31 * result + Objects.hash(locationPoints);

        return result;
    }
}