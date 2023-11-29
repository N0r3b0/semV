import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TSP {
    public static void main(String[] args) {
        String fileName = "berlin52.tsp";
        String tourFile = "berlin52.opt.tour";
        ArrayList<City> cities = readCitiesFromFile(fileName);

        ArrayList<Integer> tourConfiguration = findTourConfiguration(cities);
        double totalDistance = calculateTotalDistance(cities, tourConfiguration);

        ArrayList<Integer> optimalTour = readOptimalTourFromFile(tourFile);
        double optimalDistance = calculateTotalDistance(cities, optimalTour);

        double difference = optimalDistance - totalDistance;
        double improvementPercentage = (difference / optimalDistance) * 100;

        printResults("Identyfikatory miast (algorytm najbliższego sąsiada):", tourConfiguration, totalDistance);
        printResults("Identyfikatory miast (berlin52.opt.tour):", optimalTour, optimalDistance);
        printDifferenceAndImprovement(difference, improvementPercentage);
    }

    private static ArrayList<City> readCitiesFromFile(String fileName) {
        ArrayList<City> cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean readCoordinates = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("NODE_COORD_SECTION")) {
                    readCoordinates = true;
                    continue;
                }

                if (readCoordinates) {
                    if (line.equals("EOF")) {
                        break;
                    }

                    String[] tokens = line.split(" ");
                    if (tokens.length < 3) {
                        System.err.println("Nieprawidłowa linia danych: " + line);
                        continue;
                    }
                    int id = Integer.parseInt(tokens[0]);
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    cities.add(new City(id, x, y));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }

    private static ArrayList<Integer> findTourConfiguration(ArrayList<City> cities) {
        ArrayList<Integer> tourConfiguration = new ArrayList<>();
        City startCity = cities.get(0);
        City lastCity = cities.get(cities.size() - 1);
        tourConfiguration.add(startCity.id);
        startCity.visited = true;
        double totalDistance = 0;

        for (int i = 0; i < cities.size() - 1; i++) {
            City currentCity = cities.get(tourConfiguration.get(i) - 1);
            double minDistance = Double.MAX_VALUE;
            City nextCity = null;

            for (City city : cities) {
                if (!city.visited) {
                    double distance = currentCity.distanceTo(city);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextCity = city;
                    }
                }
            }

            if (nextCity != null) {
                tourConfiguration.add(nextCity.id);
                nextCity.visited = true;
                totalDistance += minDistance;
            }

            if (nextCity == null) {
                tourConfiguration.add(startCity.id);
            }
        }

        totalDistance += cities.get(tourConfiguration.get(tourConfiguration.size() - 1) - 1).distanceTo(startCity);

        return tourConfiguration;
    }

    private static double calculateTotalDistance(ArrayList<City> cities, ArrayList<Integer> tour) {
        double totalDistance = 0;

        for (int i = 0; i < tour.size() - 1; i++) {
            int currentCityId = tour.get(i);
            int nextCityId = tour.get(i + 1);

            City currentCity = cities.get(currentCityId - 1);
            City nextCity = cities.get(nextCityId - 1);

            double distance = currentCity.distanceTo(nextCity);
            totalDistance += distance;
        }

        totalDistance += cities.get(tour.get(tour.size() - 1) - 1).distanceTo(cities.get(tour.get(0) - 1));

        return totalDistance;
    }

    private static ArrayList<Integer> readOptimalTourFromFile(String tourFile) {
        ArrayList<Integer> optimalTour = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(tourFile))) {
            String line;
            boolean readTour = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("TOUR_SECTION")) {
                    readTour = true;
                    continue;
                }

                if (readTour && !line.equals("-1") && !line.equals("EOF")) {
                    int cityId = Integer.parseInt(line);
                    optimalTour.add(cityId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimalTour;
    }

    private static void printResults(String header, ArrayList<Integer> tour, double totalDistance) {
        System.out.println(header);
        for (int cityId : tour) {
            System.out.print(cityId + " ");
        }

        System.out.println();
        System.out.println("Całkowita odległość: " + totalDistance + "\n");
    }

    private static void printDifferenceAndImprovement(double difference, double improvementPercentage) {
        System.out.println("Różnica między obliczonymi odległościami: " + Math.abs(difference) + "\n");
        System.out.println("Poprawa w procentacg: " + Math.abs(improvementPercentage) + "%");
    }
}
