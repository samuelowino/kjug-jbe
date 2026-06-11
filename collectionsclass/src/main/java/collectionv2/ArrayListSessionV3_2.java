void main() {
    // ensureCapacity
    // size : number of actual elements
    // capacity : amount of storage available
    // by default : 10 elements
    // adding beyond initial capacity : grow : 50% growth rate

    // interface : Vehicle
    // concrete classes  : class Bus : class Truck

    // Bus myBus = new Bus();
        // myBus.drive
        // myBus.checkNextBusStop

    // Vehicle myV = new Bus();
    // myV.checkNextBusStop

    //ArrayList implement List interface
    // var
    var bigList = new ArrayList<Integer>();
    bigList.ensureCapacity(3);
    bigList.add(1);
    bigList.add(2);
    bigList.add(3);
    IO.println("big list = " + bigList);
    IO.println("size = " + bigList.size());
    bigList.add(4);
    bigList.add(5);
    IO.println("after second add");
    IO.println("big list = " + bigList);
    IO.println("size = " + bigList.size());
}
