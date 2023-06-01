package gui.controller;
import gui.model.PackageModel;
import gui.model.RackModel;
import gui.model.RobotQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//at the bottom there a links of what a HashMap, List, and Map is.
public class TSP {
    private final List<Coordinate> coordinates;
    private final Map<Coordinate, Integer> counter;
    private Coordinate currentPosition;
    private int plekX;
    private int plekY;

    //prepare the TSP object for adding coordinates, tracking visit counts,
    // and starting the algorithm from the specified initial position.
    public TSP() {
        //stores the coordinates
        coordinates = new ArrayList<>();
        //stores the visit count for each coordinate
        counter = new HashMap<>();
        // sets the starting position to location robot
        currentPosition = new Coordinate(RobotController.getXpositieTSP(), RobotController.getYpositieTSP());
    }
    // allows you to add a new coordinate to the coordinates list in the TSP class
    public void addCoordinate(int x, int y) {
        //converting X numbers to pulses
        if(x==1){
            plekX=184;
        }else if(x==2){
            plekX=1500;
        }else if(x==3){
            plekX=3000;
        }else if(x==4){
            plekX=4684;
        }else if(x==5){
            plekX=6000;
        }else if(x==6){
            plekX=7684;
        }

        //converting Y numbers to pulses
        if(y==1){
            plekY=566;
        }else if(y==2){
            plekY=1652;
        }else if(y==3){
            plekY=2664;
        }else if(y==4){
            plekY=3606;
        }else if(y==5){
            plekY=4606;
        }else if(y==6){
            plekY=5606;
        }
        coordinates.add(new Coordinate(plekX, plekY));
    }

    // algorithm to visit all the coordinates stored in the coordinates list
    public void startAlgorithm() {
        //checks if the list is not empty, meaning there are still coordinates to visit.
        while (!coordinates.isEmpty()) {
            //determine the coordinate in the coordinates list that is closest to the current position.
            //assigns the closest coordinate to the closestCoordinate variable.
            Coordinate closestCoordinate = findClosestCoordinate();
            // simulates moving to that coordinate.
            moveToCoordinate(closestCoordinate);
            //increments the visit count in the
            // counter map and removes the coordinate from the list.
            int y=PulsToCoordinateY(closestCoordinate.y);
            int x=PulsToCoordinateX(closestCoordinate.x);
            RobotQueue.addQueue(RackModel.getPackageFromRack(x,y),false);
            checkCoordinate(closestCoordinate);
        }
        System.out.println("Algorithm finished. All coordinates have been visited.");
    }
    //finds the coordinate in the coordinates list that is closest to the current position
    private Coordinate findClosestCoordinate() {
        Coordinate closestCoordinate = null;
        //minDistance is set to the maximum possible value of a double, Double.MAX_VALUE,
        //to ensure that the first distance calculated will be smaller than this initial value.
        double minDistance = Double.MAX_VALUE;

        for (Coordinate coordinate : coordinates) {
            //method to calculate the Euclidean distance between the current coordinate and the currentPosition.
            // The calculated distance is stored in the distance variable.
            // Euclidean distance: https://www.cuemath.com/euclidean-distance-formula/
            double distance = calculateDistance(coordinate, currentPosition);
            if (distance < minDistance) {
                minDistance = distance;
                closestCoordinate = coordinate;
            }
        }

        return closestCoordinate;
    }
    // calculates the Euclidean distance between two coordinates
    private double calculateDistance(Coordinate coordinate1, Coordinate coordinate2) {
        int dx = coordinate1.x - coordinate2.x;
        int dy = coordinate1.y - coordinate2.y;
        // The Math.sqrt() static method returns the square root of a number. That is:
        //∀x ≥ 0 , 𝙼𝚊𝚝𝚑.𝚜𝚚𝚛𝚝 ( 𝚡 ) = x = the unique  y ≥ 0  such that  y^2 = x
        // ∀ represent the universal quantifier, which is read as "for all" or "for every." It is used to
        // express that a statement or property holds true for every element in a given set or domain.
        return Math.sqrt(dx * dx + dy * dy);
    }
    // updates the current position (currentPosition) to the specified Coordinate.
    private void moveToCoordinate(Coordinate coordinate) {
        System.out.println("Moving to coordinate: (" + coordinate.x + ", " + coordinate.y + ")");
        //updates the currentPosition to the coordinate object.
        //It assigns the coordinate to the currentPosition, effectively simulating the movement to that coordinate.

        currentPosition = coordinate;
    }
    //checks and updates the visit count of a coordinate
    private void checkCoordinate(Coordinate coordinate) {
        //It uses the coordinate as the key and retrieves the current visit count using counter.
        // getOrDefault(coordinate, 0), which returns the current count or 0
        // if the coordinate is not present.
        // It then increments the count by 1 and puts the updated count back into the map.
        counter.put(coordinate, counter.getOrDefault(coordinate, 0) + 1);
        //removes the coordinate from the coordinates list.
        coordinates.remove(coordinate);
        System.out.println("Checked coordinate: (" + coordinate.x + ", " + coordinate.y + ")");
    }
    //defines a nested record class named Coordinate inside the TSP class.
    private record Coordinate(int x, int y) {
        //A record class is a concise way of declaring classes that primarily hold immutable data.

        //defines a record class named Coordinate inside the TSP class.
        // This record class encapsulates the x-coordinate and y-coordinate of a point
        // and automatically generates useful methods for accessing and manipulating coordinate objects.
    }
    public int PulsToCoordinateY(int pulsY){
        if(pulsY==566){
            return 1;
        }else if(pulsY==1652){
            return 2;
        }else if(pulsY==2664){
            return 3;
        }else if(pulsY==3606){
            return 4;
        }else if(pulsY==4606){
            return 5;
        }else if(pulsY==5606){
            return 6;
        }
        return 0;
    }
    public int PulsToCoordinateX(int pulsX){
        if(pulsX==184){
            return 1;
        }else if(pulsX==1500){
            return 2;
        }else if(pulsX==3000){
            return 3;
        }else if(pulsX==4684){
            return 4;
        }else if(pulsX==6000){
            return 5;
        }else if(pulsX==7684){
            return 6;
        }
        return 0;
    }



}
// https://www.digitalocean.com/community/tutorials/java-list (explanation list)
/**
 List<Coordinate> coordinates is used to store a collection of Coordinate objects.
 It serves as a container for holding the coordinates that need to be visited in the
 traveling salesman problem (TSP) algorithm.
 */
// https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/ (explanation hashmap)
/**
 * HashMap<Coordinate, Integer> counter is used as the specific implementation of the Map
 * interface to store and retrieve the visit count for each coordinate visited during the TSP
 * algorithm. It provides efficient access to visit counts and enables accurate tracking of visits
 * for each coordinate, including handling duplicate coordinates.
 */

// https://www.geeksforgeeks.org/map-interface-java-examples/ (explanation map)
/**
 Map<Coordinate, Integer> counter in the code is to maintain a record of the visit count for
 each coordinate visited during the TSP algorithm. This information can be useful for analyzing
 the algorithm's behavior, tracking the frequency of visits to different coordinates,
 or evaluating the efficiency of the algorithm.
 */

//difference between HashMap and map
/**
 Map is an interface that defines a general contract for key-value mappings, while HashMap is one of the implementations of that interface. Here are the differences between Map and HashMap:

 Interface vs. Implementation:
 Map: Map is an interface that defines the operations and behavior of a key-value mapping. It provides a contract for implementing classes to adhere to.
 HashMap: HashMap is a specific implementation of the Map interface. It provides a concrete implementation of the key-value mapping using a hash table data structure.

 Extending Map Interface:
 HashMap implements the Map interface, meaning it provides all the methods and behavior specified by the Map interface.
 Other classes, such as TreeMap or LinkedHashMap, can also implement the Map interface, providing different implementations and behaviors.

 Ordering:
 HashMap: The entries in a HashMap are not ordered. The iteration order of the entries may vary and is not guaranteed to be consistent.
 Other implementations, such as LinkedHashMap, preserve the order of the entries as they were inserted, while TreeMap provides a sorted order based on the natural ordering of the keys or a specified comparator.

 Performance Characteristics:
 HashMap: It offers constant-time performance for basic operations like get and put. It achieves this by using a hash table data structure, which provides fast key-value lookups.
 TreeMap: It provides guaranteed logarithmic-time performance for basic operations. It maintains the entries in a red-black tree structure, which allows for efficient searching, insertion, and deletion based on the keys' natural order or a specified comparator.

 Thread Safety:
 HashMap: It is not thread-safe. Concurrent modifications to a HashMap from multiple threads may lead to unexpected results or exceptions. To achieve thread safety, you need to use concurrent alternatives like ConcurrentHashMap.
 Map: The Map interface itself does not specify thread safety. It depends on the specific implementation you choose.

 both HashMap and Map are needed in the code to provide the functionality of a map data structure for
 storing the visit counts. The Map interface is used for the variable declaration and type
 specification to allow flexibility in choosing different map implementations, while HashMap is
 used as the specific implementation chosen for its efficiency and suitability for the TSP algorithm.
 */
