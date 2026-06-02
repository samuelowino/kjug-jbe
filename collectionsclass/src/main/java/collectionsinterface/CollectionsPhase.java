import collectionsinterface.Flavor;// IceCreame : [flavor1...flavor_n]
import collectionsinterface.FreezerCollection;
import collectionsinterface.IceCreame;
void main() {
    IceCreame[] iceCreameList = new IceCreame[]{
            new IceCreame("strawberry"), // 0
            new IceCreame("vanilla"), // 1
            new IceCreame("chocolate"), // 2
            new IceCreame("blackberry"), // 3
            new IceCreame("black current"), // 4    // 5 elements
    };
    var freezerCollection = new FreezerCollection<IceCreame>(iceCreameList);

    var iterator = freezerCollection.iterator();
    while (iterator.hasNext()) {
        var element = iterator.next();
        IO.println("Element is " + element.getName());
    }

    // ensure that it contains flavors
    var freezerIsEmpty = freezerCollection.isEmpty();
    IO.println("freezer empty: " + freezerIsEmpty);
    var amountOfIceCreame = freezerCollection.size();
    IO.println("number of flavors in freeze " + amountOfIceCreame);

    var randomFlavor = new IceCreame("vanilla");
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
        IO.println(flavorCopy.getName());
    }
}
