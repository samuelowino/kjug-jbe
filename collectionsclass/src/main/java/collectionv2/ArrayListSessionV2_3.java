void main() {
    // indexOf | lastIndexOf
    List<Location> locations = new ArrayList<>();
    locations.add( new Location(45.11,30.11));
    locations.add( new Location(22.01,45.1));
    locations.add( new Location(2.1,2.30));

    IO.println("locations " + locations);
}
record Location(Double latitude, Double longitude) { }