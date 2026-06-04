 // Lists
 // List is an interface
 // - abstract methods
 // - default methods
 // - depends on implementation | Concrete Class
 //
 // Wrapping a dynamic array
 // Insertion order 0....
 // retrieval order is not guaranteed
 // Index based
 // zero indexed : [A.0, B.1, C.2]
 // Allow duplicate : [A.0, B.1, C.2, B.3, A.4]
 // Allows nulls : [A.0, B.1, C.2, B.3, A.4, null.5]

 void main() {
    List<Integer> listA = new ArrayList<>(); // 10 initial capacity
    IO.println("size of listA " + listA.size());


    listA.add(100);
    listA.add(55);
    listA.add(null);

    // getFirst
     // getLast

    IO.println("value at first" + listA.getFirst());
    IO.println("value at index  1 " + listA.get(1));
    IO.println("value at last " + listA.getLast());

    List<Integer> listB = new ArrayList<>(30);
    listB.add(66);
    listB.add(55);
    listB.add(88);

    IO.println("elements in listA " + listA);
    IO.println("elements in listB " + listB);

    listA.add(45); // index 0
    int index = 0;
    int value = 11;
    listA.add(index, value); // index 0

    List<Integer> listB = new ArrayList<>();
    listB.add(80);
    listB.add(99);
    listA.addAll(listB);

    // retrieval : list.get(index)
     int valueAtIndexZero = listA.get(0);
     int valueAtIndexOne = listA.get(1);

     //

    int numberOfInteger = listA.size();
    boolean isEmpty = listA.isEmpty();
    IO.println("size " + numberOfInteger);
    IO.println("is empty " + isEmpty);
    IO.println("contains 45 " + listA.contains(45));
    IO.println("contains 11 " + listA.contains(11));

    IO.println("value at index zero is " + valueAtIndexZero);
    IO.println("value at index one is " + valueAtIndexOne);
 }




 // ArrayList
 // Next class
 // LinkedList
