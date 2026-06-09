void main() {
    // remove : remove an element
    List<IceCreameFlavor> flavors = new ArrayList<>();
    flavors.add(new IceCreameFlavor("vanilla"));
    flavors.add(new IceCreameFlavor("strawberry"));
    flavors.add(new IceCreameFlavor("mango"));

//    flavors.remove(new IceCreameFlavor("vanilla"));
//    flavors.remove(new IceCreameFlavor("strawberry"));

//    flavors.remove(0);

    flavors.removeLast();

    IO.println("flavors " + flavors);
}

record IceCreameFlavor(String name) { }