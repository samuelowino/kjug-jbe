// Comparator interface
// Comparable interface
// they assist you with sorting collections
// [1,3,2,4] -> sorting algo -> [1,2,3,4]
void main() {

//    boolean condition = true;
    // ternary operator
//    String msg = condition ? "good" : "bad";
//    IO.println("msg " + msg);

    record Person(String name, int age) { }
    class SortPersonByAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1,Person p2) {
            return Integer.compare(p2.age(),p1.age());
        }
    }
    class SortPersonByNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p2.name().compareTo(p1.name());
        }
    }
    List<Person> people = new ArrayList<>();
    people.add( new Person("Dennis",78));
    people.add( new Person("Alex", 21));
    people.add( new Person("Ben", 11));
    var iterator = people.iterator();
    while (iterator.hasNext()) {
        var person = iterator.next();
        IO.println(person.name() + " - " + person.age());
    }

    var ageSortComparator = new SortPersonByAgeComparator();
    Collections.sort(people,ageSortComparator);


    IO.println("After sorting...");
    iterator = people.iterator();
    while (iterator.hasNext()) {
        var person = iterator.next();
        IO.println(person.name() + " - " + person.age());
    }

    var nameSortComparator = new SortPersonByNameComparator();
    Collections.sort(people, nameSortComparator);

    IO.println("After sorting by name");

    iterator = people.iterator();
    while (iterator.hasNext()) {
        var person = iterator.next();
        IO.println(person.name() + " - " + person.age());
    }
}
