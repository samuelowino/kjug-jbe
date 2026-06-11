void main() {

    // indexOf |
    List<Double> locations = new ArrayList<>();
    locations.add(34d);
    locations.add(11d);
    locations.add(4.23);

    IO.println("locations " + locations);

    // indexOf : contains
    boolean isContained = locations.contains(4.1);
    IO.println("is 4.1 contained in locations " + isContained);
    IO.println("is 11.0 contained in locations " + locations.contains(11.0));

    int indexOf11d  = locations.indexOf(11.0);

    IO.println("index of 11.0 is " + indexOf11d);
    IO.println("index of 34.0 is " + locations.indexOf(34d));
    IO.println("index of 4.23 is " + locations.indexOf(4.23));

    IO.println("index of 4.1 is " + locations.indexOf(4.1)); // what happens
    IO.println("index of 1.1 is " + locations.indexOf(1.1)); // what happens
}
