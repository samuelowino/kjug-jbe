void main() {
    // lastIndexOf
    // Lists : ArrayList : they allow duplication : allowing nulls
    List<Float> speeds = new ArrayList<>();
    speeds.add(4.2f); // 0
    speeds.add(3.1f); // 1
    speeds.add(3.1f); // 2
    speeds.add(4.2f); // 3

    IO.println("speeds " + speeds);
    IO.println("last occurrence of 3.1 is " + speeds.lastIndexOf(3.1f));
    IO.println("last occurrence of 4.2 is " + speeds.lastIndexOf(4.2f));
    IO.println("last occurrence of 11.3 is " + speeds.lastIndexOf(11.3f));
}
