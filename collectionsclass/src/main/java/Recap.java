// Collection interface
// List : ArrayList : LinkedList
// Set: Hashset : Set Operation: Union : Intersection and Diff
// Map:
void main() {
//     Unmodifiable list : Java 9
//     ImmutableCollections
//     View of an unmodifiable list
    List<Integer> olist = new ArrayList<>();
    olist.add(4);
    olist.add(5);
    List<Integer> viewList = Collections.unmodifiableList(olist);
    olist.add(6);
    olist.add(7);

    IO.println("olist " + olist);
    IO.println("viewList " + viewList);

    List<Integer> numbers = List.of(1,2);

    numbers.add(3); // UnsupportedOperationException
    var fn = numbers.get(0);
    IO.println("first number " + fn);
    numbers.forEach(n -> IO.println(n));
    List<Integer> nList = List.of(1,2,3); // Unmodifiable list
    nList.add(3);
    List<Integer> myList = Arrays.asList(1,2,3); // modifiable list
    // change the values but you can't change size
    myList.add(5);

    IO.println("Unmodifiable list " + nList);
    IO.println("Modifiable list " + myList);


    Set<String> fruits = new HashSet<>(); // Unique value
    fruits.add("apple");
    fruits.add("orange");

    fruits.forEach(f -> IO.println(f));
}