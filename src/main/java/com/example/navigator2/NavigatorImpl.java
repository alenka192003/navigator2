package com.example.navigator2;

import java.util.*;
import java.util.stream.Collectors;

public class NavigatorImpl implements Navigator {
    private Map<String, Route> routes;
    private Map<String, Route> favoriteRoutes;

    public NavigatorImpl() {
        this.routes = new Map<>();
        this.favoriteRoutes = new Map<>();
    }

    @Override
    public void addRoute(Route route) {
        for (Route existingRoute : routes.values()) {
            if (isSameRoute(existingRoute, route)) {
                return;
            }
        }
        routes.put(route.getId(), route);
        if (route.isFavorite()) {
            favoriteRoutes.put(route.getId(), route);
        }
    }
    private boolean isSameRoute(Route route1, Route route2) {
        return Double.compare(route1.getDistance(), route2.getDistance()) == 0 &&
                route1.getLocationPoints().equals(route2.getLocationPoints());
    }
    @Override
    public Route removeRoute(String routeId) {
        Route removedRoute = routes.remove(routeId);
        if (removedRoute != null && removedRoute.isFavorite()) {
            favoriteRoutes.remove(routeId);
        }
        return removedRoute;
    }

    @Override
    public boolean contains(String routeId) {
        return routes.contains(routeId);
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return routes.get(routeId);
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = routes.get(routeId);
        if (route != null) {
            route.incrementPopularity();
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        return routes.values()
                .stream()
                .filter(route -> route.hasLogicalOrder(startPoint, endPoint))
                .sorted(new CustomRouteComparator(startPoint, endPoint))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        return favoriteRoutes.values()
                .stream()
                .filter(route -> route.isFavorite() && route.getLocationPoints().indexOf(destinationPoint) > 0)
                .sorted(new RouteComparator(destinationPoint))
                .collect(Collectors.toList());
    }


    @Override
    public Iterable<Route> getTop3Routes() {
        return routes.values()
                .stream()
                .sorted(new RouteComparatorG3())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public void setFavorite(String routeId, boolean isFavorite) {
        Route route = routes.get(routeId);
        if (route != null) {
            System.out.println("Setting favorite for Route ID: " + routeId + " to " + isFavorite);
            route.setFavorite(isFavorite);
            favoriteRoutes.put(route.getId(), route);
        } else {
            System.out.println("Route not found!");
        }
    }
}
