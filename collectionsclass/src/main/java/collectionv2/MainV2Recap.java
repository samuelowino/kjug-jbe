void main(){
    // List interface : isEmpty , size contains
    // ArrayList : add element, determine capacity, size & capacity, get
    List<Integer> numbersList = new ArrayList<>(); // 10 elements
    numbersList.add(54);
    numbersList.add(1,22);

    Integer number = numbersList.getFirst();
    Integer nextNumber = numbersList.getLast();

    List<Integer> otherList = new ArrayList<>();
    otherList.add(11);
    otherList.add(1);
    otherList.add(18);

    List<Integer> combined = new ArrayList<>();
    combined.addAll(numbersList);
    combined.addAll(otherList);

    IO.println("is empty " + numbersList.isEmpty());
    IO.println("size " + numbersList.size());

    IO.println("Numbers list " + numbersList);
    IO.println("First element " + number);
    IO.println("Second element " + nextNumber);

    IO.println("Numbers List " + numbersList);
    IO.println("Other List " + otherList);
    IO.println("Combined " + combined);
}