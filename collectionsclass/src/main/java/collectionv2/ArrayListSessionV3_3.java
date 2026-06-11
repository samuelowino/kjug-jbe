// Bulk Operations
// searching : sorting : replacing multiple : bulk removal : bulk addition
void main() {
    List<String> fruits = new ArrayList<>();
    fruits.add("apple");
    fruits.add("banana");
    fruits.add("mango");

    List<String> meat = new ArrayList<>();
    meat.add("pork");
    meat.add("chicken");
    meat.add("beef");

    List<String> groceryList = new ArrayList<>();
    groceryList.addAll(fruits);
    groceryList.addAll(meat);

    IO.println("grocery list " + groceryList);

    groceryList.removeAll(meat);

    IO.println("after removing meat " + groceryList);

    groceryList.remove("apple");
    groceryList.remove("banana");

    IO.println("after removing apple and banana " + groceryList);

    // searching lists
    List<String> colors = new ArrayList<>();
    colors.add("blue");
    colors.add("black");
    colors.add("pink");

    List<String> requiredColors = new ArrayList<>();
    requiredColors.add("blue");
    requiredColors.add("pink");

    boolean containsAllRequired = colors.containsAll(requiredColors);
    IO.println("contains all client colors " + containsAllRequired);

}
