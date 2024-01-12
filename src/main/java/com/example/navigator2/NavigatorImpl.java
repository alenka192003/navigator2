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
        routes.put(route.getId(), route);
        if (route.isFavorite()) {
            favoriteRoutes.put(route.getId(), route);
        }
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
                .filter(route -> !route.getLocationPoints().get(0).equals(destinationPoint))
                .sorted(new RouteComparator("", ""))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        return routes.values()
                .stream()
                .sorted(Comparator
                        .comparingInt(Route::getPopularity)
                        .reversed()
                        .thenComparingDouble(Route::getDistance)
                        .thenComparingInt(route -> route.getLocationPoints().size()))
                .limit(3)
                .collect(Collectors.toList());
    }
}
