import collectionsinterface.Flavor;// IceCreame : [flavor1...flavor_n]
import collectionsinterface.FreezerCollection;
void main() {
    Flavor[] iceCreame = new Flavor[]{
            new Flavor("strawberry"),
            new Flavor("vanilla"),
            new Flavor("chocolate"),
            new Flavor("blackberry"),
            new Flavor("black current"),
    };
    var freezerCollection = new FreezerCollection(iceCreame);
    // ensure that it contains flavors
    var freezerIsEmpty = freezerCollection.isEmpty();
    IO.println("freezer empty: " + freezerIsEmpty);
    var amountOfIceCreame = freezerCollection.size();
    IO.println("number of flavors in freeze " + amountOfIceCreame);

    var randomFlavor = new Flavor("bluey");
    var isVanillaInStock = freezerCollection.contains(randomFlavor);
    if (isVanillaInStock) {
        IO.println(randomFlavor + " is in stock 😅");
    } else {
        System.err.println(randomFlavor + " is out of stock 😭");
    }

    var copyOfFlavors = freezerCollection.toArray();
    IO.println("copy of flavors");
    for (Object copy : copyOfFlavors) {
        var flavorCopy = (Flavor) copy;
        IO.println(flavorCopy.name());
    }
}
