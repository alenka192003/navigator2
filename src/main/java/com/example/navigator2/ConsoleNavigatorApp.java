package com.example.navigator2;

import java.util.*;

public class ConsoleNavigatorApp {

    private static final Navigator navigator = new NavigatorImpl();
    private static final Set<String> availablePoints = new HashSet<>();

    public static void main(String[] args) {

        populateRoutes(); // Populate sample routes
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addRoute();
                    break;
                case 2:
                    removeRoute();
                    break;
                case 3:
                    checkContains();
                    break;
                case 4:
                    displaySize();
                    break;
                case 5:
                    displayRouteById();
                    break;
                case 6:
                    chooseRoute();
                    break;
                case 7:
                    searchRoutes();
                    break;
                case 8:
                    displayAvailablePoints();
                    break;
                case 9:
                    exit();
                    break;
                case 10:
                    getFavoriteRoutesByDestination();
                    break;
                case 11:
                    getTop3Routes();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private static void printMenu() {
        System.out.println("===== Navigation System =====");
        System.out.println("1. Add Route");
        System.out.println("2. Remove Route");
        System.out.println("3. Check if Route exists");
        System.out.println("4. Display Number of Routes");
        System.out.println("5. Display Route by ID");
        System.out.println("6. Choose Route");
        System.out.println("7. Search Routes");
        System.out.println("8. Display Available Points");
        System.out.println("9. Exit");
        System.out.println("10. Get Favorite Routes by Destination");
        System.out.println("11. Get Top 3 Routes");
    }

    private static void addRoute() {
        Route route = new Route();

        // Set route ID
        System.out.print("Enter route ID: ");
        String routeId = new Scanner(System.in).nextLine();
        route.setId(routeId);

        // Display available points
        displayAvailablePoints();

        // Take input for route points
        List<String> routePoints = new ArrayList<>();
        System.out.print("Enter route points (comma-separated, e.g., Point A, Point B): ");
        String pointsInput = new Scanner(System.in).nextLine();
        String[] pointsArray = pointsInput.split(",");
        for (String point : pointsArray) {
            String trimmedPoint = point.trim();
            routePoints.add(trimmedPoint);
            availablePoints.add(trimmedPoint);
        }

        // Set distance, popularity, and favorite
        System.out.print("Enter distance: ");
        double distance = new Scanner(System.in).nextDouble();
        route.setDistance(distance);

        System.out.print("Enter popularity: ");
        int popularity = new Scanner(System.in).nextInt();
        route.setPopularity(popularity);

        System.out.print("Is it a favorite route? (true/false): ");
        boolean isFavorite = new Scanner(System.in).nextBoolean();
        route.setFavorite(isFavorite);

        route.setLocationPoints(routePoints);

        navigator.addRoute(route);
        System.out.println("Route added successfully!");
    }
    private static void removeRoute() {
        // Implement logic to remove a route
        // You can take input from the user or use a predefined route ID
        // For example:
        System.out.print("Enter the route ID to remove: ");
        String routeId = new Scanner(System.in).nextLine();

        Route removedRoute = navigator.removeRoute(routeId);
        if (removedRoute != null) {
            System.out.println("Route removed successfully: " + removedRoute.getId());
        } else {
            System.out.println("Route not found!");
        }
    }

    private static void checkContains() {
        // Implement logic to check if a route exists
        // You can take input from the user or use a predefined route
        // For example:
        Route routeToCheck = new Route();
        routeToCheck.setId("R1");

        boolean contains = navigator.contains(routeToCheck);
        System.out.println("Contains Route: " + contains);
    }

    private static void displaySize() {
        int size = navigator.size();
        System.out.println("Number of Routes: " + size);
    }

    private static void displayRouteById() {
        // Implement logic to display a route by ID
        // You can take input from the user or use a predefined route ID
        // For example:
        System.out.print("Enter the route ID to display: ");
        String routeId = new Scanner(System.in).nextLine();

        Route route = navigator.getRoute(routeId);
        if (route != null) {
            System.out.println("Route Details: " + route.getId() + ", Distance: " + route.getDistance() + ", Popularity: " + route.getPopularity());
        } else {
            System.out.println("Route not found!");
        }
    }

    private static void chooseRoute() {
        // Implement logic to choose a route
        // You can take input from the user or use a predefined route ID
        // For example:
        System.out.print("Enter the route ID to choose: ");
        String routeId = new Scanner(System.in).nextLine();

        navigator.chooseRoute(routeId);
        System.out.println("Route chosen successfully!");
    }

    private static void searchRoutes() {
        // Implement logic to search routes
        // You can take input from the user or use predefined start and end points
        // For example:
        System.out.print("Enter the starting point: ");
        String startPoint = new Scanner(System.in).nextLine();

        System.out.print("Enter the ending point: ");
        String endPoint = new Scanner(System.in).nextLine();

        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        System.out.println("Search Results:");
        for (Route route : routes) {
            System.out.println("Route: " + route.getId() + ", Distance: " + route.getDistance() + ", Popularity: " + route.getPopularity());
        }
    }

    private static void displayAvailablePoints() {
        System.out.println("Available Points: " + availablePoints);
    }

    private static void exit() {
        System.out.println("Exiting Navigation System. Goodbye!");
    }

    private static void populateRoutes() {
        Route route1 = new Route();
        route1.setId("R1");
        route1.setDistance(10.5);
        route1.setPopularity(5);
        route1.setFavorite(true);
        route1.setLocationPoints(Arrays.asList("Point A", "Point B", "Point X"));

        Route route2 = new Route();
        route2.setId("R2");
        route2.setDistance(8.0);
        route2.setPopularity(3);
        route2.setFavorite(false);
        route2.setLocationPoints(Arrays.asList("Point A", "Point C", "Point Y"));

        Route route3 = new Route();
        route3.setId("R3");
        route3.setDistance(12.0);
        route3.setPopularity(7);
        route3.setFavorite(true);
        route3.setLocationPoints(Arrays.asList("Point B", "Point C", "Point Z"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);

        // Populate available points
        availablePoints.addAll(Arrays.asList("Point A", "Point B", "Point C", "Point X", "Point Y", "Point Z"));
    }
    private static void getFavoriteRoutesByDestination() {
        System.out.print("Enter the destination point: ");
        String destinationPoint = new Scanner(System.in).nextLine();

        Iterable<Route> favoriteRoutes = navigator.getFavoriteRoutes(destinationPoint);
        displayRoutes("Favorite Routes by Destination", favoriteRoutes);
    }

    private static void getTop3Routes() {
        Iterable<Route> top3Routes = navigator.getTop3Routes();
        displayRoutes("Top 3 Routes", top3Routes);
    }

    private static void displayRoutes(String title, Iterable<Route> routes) {
        System.out.println(title + ":");
        for (Route route : routes) {
            System.out.println("Route: " + route.getId() + ", Distance: " + route.getDistance() + ", Popularity: " + route.getPopularity());
        }
    }
}

/*import javafx.application.Application;
        import javafx.collections.FXCollections;
        import javafx.geometry.Insets;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

        import java.util.*;

public class NavigatorAppFX extends Application {

    private static final Navigator navigator = new NavigatorImpl();
    private static final Set<String> availablePoints = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Navigation System");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        // Create buttons
        Button addRouteButton = new Button("Add Route");
        Button removeRouteButton = new Button("Remove Route");
        Button checkContainsButton = new Button("Check if Route exists");
        Button displaySizeButton = new Button("Display Number of Routes");
        Button displayRouteButton = new Button("Display Route by ID");
        Button chooseRouteButton = new Button("Choose Route");
        Button searchRoutesButton = new Button("Search Routes");
        Button displayPointsButton = new Button("Display Available Points");
        Button exitButton = new Button("Exit");
        Button favoriteRoutesButton = new Button("Get Favorite Routes by Destination");
        Button top3RoutesButton = new Button("Get Top 3 Routes");

        // Add button event handlers
        addRouteButton.setOnAction(e -> addRoute());
        removeRouteButton.setOnAction(e -> removeRoute());
        checkContainsButton.setOnAction(e -> checkContains());
        displaySizeButton.setOnAction(e -> displaySize());
        displayRouteButton.setOnAction(e -> displayRouteById());
        chooseRouteButton.setOnAction(e -> chooseRoute());
        searchRoutesButton.setOnAction(e -> searchRoutes());
        displayPointsButton.setOnAction(e -> displayAvailablePoints());
        exitButton.setOnAction(e -> exit());
        favoriteRoutesButton.setOnAction(e -> getFavoriteRoutesByDestination());
        top3RoutesButton.setOnAction(e -> getTop3Routes());

        // Add buttons to layout
        layout.getChildren().addAll(
                addRouteButton, removeRouteButton, checkContainsButton,
                displaySizeButton, displayRouteButton, chooseRouteButton,
                searchRoutesButton, displayPointsButton, exitButton,
                favoriteRoutesButton, top3RoutesButton
        );

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addRoute() {
        // Create a new window for adding a route
        Stage addRouteStage = new Stage();
        addRouteStage.setTitle("Add Route");

        VBox addRouteLayout = new VBox(10);
        addRouteLayout.setPadding(new Insets(10));

        // Create input fields
        TextField routeIdField = new TextField();
        TextField routePointsField = new TextField();
        TextField distanceField = new TextField();
        TextField popularityField = new TextField();
        CheckBox favoriteCheckbox = new CheckBox("Is it a favorite route?");

        // Add input fields to layout
        addRouteLayout.getChildren().addAll(
                new Label("Route ID:"), routeIdField,
                new Label("Route Points (comma-separated):"), routePointsField,
                new Label("Distance:"), distanceField,
                new Label("Popularity:"), popularityField,
                favoriteCheckbox
        );

        // Create and set add button
        Button addButton = new Button("Add Route");
        addButton.setOnAction(e -> {
            // Retrieve values from input fields
            String routeId = routeIdField.getText();
            String[] routePoints = routePointsField.getText().split(",");
            double distance = Double.parseDouble(distanceField.getText());
            int popularity = Integer.parseInt(popularityField.getText());
            boolean isFavorite = favoriteCheckbox.isSelected();

            // Create and add the route
            Route route = new Route();
            route.setId(routeId);
            route.setDistance(distance);
            route.setPopularity(popularity);
            route.setFavorite(isFavorite);
            route.setLocationPoints(Arrays.asList(routePoints));

            navigator.addRoute(route);
            System.out.println("Route added successfully!");
            addRouteStage.close(); // Close the add route window
        });

        // Add add button to layout
        addRouteLayout.getChildren().add(addButton);

        Scene addRouteScene = new Scene(addRouteLayout, 300, 300);
        addRouteStage.setScene(addRouteScene);
        addRouteStage.show();
    }*/
