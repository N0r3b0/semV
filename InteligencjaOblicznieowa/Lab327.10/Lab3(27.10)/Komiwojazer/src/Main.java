import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String fileName = "berlin52.tsp";
        String tourFile = "berlin52.opt.tour";
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


        ArrayList<Integer> tourConfiguration = new ArrayList<>();
        City startCity = cities.get(0); // Startujemy od pierwszeog miasta
        City lastCity = cities.getLast();
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

            if(nextCity == null){
                tourConfiguration.add(startCity.id);
            }

        }

        totalDistance += cities.get(tourConfiguration.get(tourConfiguration.size() - 1) - 1).distanceTo(startCity);

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

        double optimalDistance = 0;
        int previousCityId = optimalTour.get(0);

        for (int i = 1; i < optimalTour.size(); i++) {
            int currentCityId = optimalTour.get(i);

            City previousCity = cities.get(previousCityId - 1);
            City currentCity = cities.get(currentCityId - 1);

            double distance = previousCity.distanceTo(currentCity);
            optimalDistance += distance;

            previousCityId = currentCityId;
        }


        optimalDistance += cities.get(previousCityId - 1).distanceTo(cities.get(optimalTour.get(0) - 1));

        double difference = optimalDistance - totalDistance;

        double improvementPercentage = (difference / optimalDistance) * 100;



        System.out.println("Identyfikatory miast (algorytm najbliższego sąsiada):");
        for (int cityId : tourConfiguration) {
            System.out.print(cityId + " ");
        }

        System.out.println();
        System.out.println("Całkowita odległość (algorytm najbliższego sąsiada): " + totalDistance+ "\n");

        System.out.println("Identyfikatory miast (berlin52.opt.tour):");
        for (int cityId : optimalTour) {
            System.out.print(cityId + " ");
        }

        System.out.println();
        System.out.println("\n"+"Całkowita odległość (berlin52.opt.tour): " + optimalDistance+ "\n");

        System.out.println("Różnica między obliczonymi odległościami: " + Math.abs(difference) + "\n");
        System.out.println("Poprawa w procentacg: " + Math.abs(improvementPercentage) + "%");
    }
}
