// List : ArrayList , LinkedList
// Set : Hashset
void main() {
    Set<String> uniqueNames = new HashSet<>();
    uniqueNames.add("Wayne");
    uniqueNames.add("Jayden");
    uniqueNames.remove("Wayne");
//    uniqueNames.forEach(e -> IO.println(e));

    // Discrete Math : Set Theory
    // Unions : Intersection
    Set<Integer> groupA = new HashSet<>();
    groupA.add(1);
    groupA.add(2);
    groupA.add(3);

    Set<Integer> groupB = new HashSet<>();
    groupB.add(4);
    groupB.add(2);
    groupB.add(7);

    IO.println("set A " + groupA);
    IO.println("set B " + groupB);
    // Union
    // A [1,2,3] Union B [4,2,7] = [1,2,3,4,7]
    Set<Integer> union = new HashSet<>();
    union.addAll(groupA);
    union.addAll(groupB);

    IO.println("A " + groupA + ": B " + groupB + " : union " + union);


    // Intersection
    // A [1,2,3] Intersection B [4,2,7] = [2]
    // retainAll
    Set<Integer> intersection = new HashSet<>();
    intersection.addAll(groupB);
    intersection.retainAll(groupA);
    IO.println("A " + groupA + ": B " + groupB + " : intersection " + intersection);

    // Difference
    // A [1,2,3] subtraction B [4,2,7] = [4,7]
    Set<Integer> diff = new HashSet<>();
    diff.addAll(groupA);
    diff.removeAll(groupB); // set subtraction
    IO.println("A " + groupA + ": minus B " + groupB + " : diff " + diff);

    Set<Integer> diff2 = new HashSet<>();
    diff2.addAll(groupB);
    diff2.removeAll(groupA); // set subtraction
    IO.println("B " + groupB + ": minus A " + groupA + " : diff2 " + diff2);

    // Difference

//    Set<Integer> union = new HashSet<>();
//    union.addAll(groupA);
//    union.addAll(groupB);
//
//    IO.println("A " + groupA + ": B " + groupB + " union => " + union);
//
//    // retainAll
//    Set<Integer> intersection = new HashSet<>();
//    intersection.addAll(groupA);
//    intersection.addAll(groupB);
//    intersection.retainAll(groupA);
//    intersection.retainAll(groupB);
//    IO.println("A " + groupA + ": B " + groupB + " intersection => " + intersection);
//
//    Set<Integer> diff = new HashSet<>();
//    diff.addAll(groupA);
//    diff.removeAll(groupB);
//    IO.println("A " + groupA + ": B " + groupB + " diff => " + diff);

    // set A : [1,2,3]
    // set B: [4,2,7]

    // Union : [1,2,3,4,7]
    // Intersection: [2]
    // Difference : [4,7]
}
