// List and ArrayList

// LinkedLists
// ListIterator
// enhanced for loop
// Java 8 enhanced
// Java 9 enhanced
// Sets
record Student(String name,int id) {}
void main() {
    List<Integer> list = new ArrayList<>();

    list.add(1); // next
    list.add(2); // prev -- 1
    list.add(3);

    var it = list.iterator();
    while(it.hasNext()) {
        var value = it.next();
        IO.println(value);
    }

    // iterator : hasNext and next
    // list iterator : hasNext, next and previous

//    ListIterator<Integer> myListIt = list.listIterator();
//    var firstVal = myListIt.next();
//    IO.println("first value " + firstVal);
//    while (myListIt.hasPrevious()) {
//        var prevValue = myListIt.previous();
//        IO.println("prev value " + prevValue);
//    }
    for(int idx = 0; idx < list.size(); idx++) {
        //IO.println("index " + idx + " value " + list.get(idx));
    }
    for(Integer integer : list) {
        //IO.println("value is " + integer);
    }
    list.forEach(number -> IO.println("printing from lambda " + number));

    // static initialization of list
    // unmodifiable list
    // immutable list
    var letters = List.of('a','b','c','d','e');
    var subList = letters.subList(0,2);
    subList.forEach(copy -> IO.println("copy " + copy));
    letters.forEach(letter -> IO.println("letter is " + letter));

    var students = List.of(
            new Student("Maxwel",323),
            new Student("John",22)
    );
    logsStudents(students);
}
public void logsStudents(List<Student> students) {
    students.forEach(student ->
            IO.println("student " + student.name() + " : " + student.id()));
}