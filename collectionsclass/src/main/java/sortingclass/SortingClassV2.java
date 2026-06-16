void main() {
    List<Integer> numbers = new ArrayList<>();
    numbers.add(5);
    numbers.add(2);
    numbers.add(7);
    IO.println(numbers);
    Collections.sort(numbers);
    IO.println("after sorting...");
    IO.println(numbers);

    record Ticket(int tickerNo,String customerName,String service) implements Comparable<Ticket> {
        @Override
        public int compareTo(Ticket otherTicket) {
//            return Integer.compare(otherTicket.tickerNo(),this.tickerNo());
            return otherTicket.customerName().compareTo(this.customerName());
        }
    }

    List<Ticket> tickets = new ArrayList<>();
    tickets.add( new Ticket(21,"John","ID number"));
    tickets.add( new Ticket(100,"Mary","nssf"));
    tickets.add( new Ticket(11,"Dennis","helb"));

    var iterator = tickets.iterator();
    while (iterator.hasNext()) {
        var ticket = iterator.next();
        IO.println(ticket.tickerNo() + "\t" +  ticket.customerName() + "\t" +  ticket.service());
    }

    Collections.sort(tickets);

    IO.println("after sorting");
    iterator = tickets.iterator();
    while (iterator.hasNext()) {
        var ticket = iterator.next();
        IO.println(ticket.tickerNo() + "\t" +  ticket.customerName() + "\t" +  ticket.service());
    }

}
