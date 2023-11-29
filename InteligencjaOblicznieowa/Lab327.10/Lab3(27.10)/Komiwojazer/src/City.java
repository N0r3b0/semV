
public class City {
    int id;
    double x;
    double y;
    boolean visited;

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public double distanceTo(City otherCity) {
        double dx = this.x - otherCity.x;
        double dy = this.y - otherCity.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}