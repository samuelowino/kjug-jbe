
// OOP
// interface Drivable
//  ...void drive()
// Bus implements Drivable
// ...override drive()
// Truck implements Drivable
// ... override drive()
// characteristic : drivable
// Functional interface : interface with one and only one abstract method
// abstract methods : default methods : static method

@FunctionalInterface
interface Drivable {
    int drive(String license);
    default void registerStop() {
        IO.println("register stop at " + System.currentTimeMillis());
    }
}
public static void globalDrive(Drivable drivable) {
    int dist = drivable.drive("drivers license: 32432k323");
    IO.println("distance: " + dist + " km");
}
void main() {
    // lambdas
    // fn as fast class citizen

    Function<Integer,Function<Integer,Function<Integer,Integer>>> fn =
            a -> b -> c -> a + b + c;

    int x = fn.apply(3).apply(5).apply(10);

    IO.println("chain add " + x);


    Drivable driveTruck = (license) -> {
        IO.println("driving truck: license no " + license);
        var dist = new Random().nextInt();
        return  dist;
    };
    globalDrive(driveTruck);

    Drivable driveBus = (license) -> {
        IO.println("driving bus: license no " + license);
        var dist = new Random().nextInt();
        return dist;
    };
    globalDrive(driveBus);

    List<Integer> ints = new ArrayList<>();
    ints.add(1);
    ints.add(2);
    ints.add(4);

    IO.println("before removal " + ints);

    Predicate<Integer> predicate = (n) -> n > 1;
    ints.removeIf(predicate);

    IO.println("after removal: " + ints);
}
