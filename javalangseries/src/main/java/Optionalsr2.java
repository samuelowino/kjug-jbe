
record Painter(String names,int age) {}
record Painting(String name, Painter painter, int serialNo) {}
void main() throws NoPaintingFoundException {
    List<Painting> paintings = Arrays.asList(
            new Painting("Starry Night", new Painter("Van Gogh",50), 324321),
            new Painting("Irises", new Painter("Van Gogh",17), 324211),
            new Painting("Mona Lissa", new Painter("Da Vinci",27), 432422)
    );
//    String names = paintings.stream()
//            .filter(p -> p.painter().names().equalsIgnoreCase("Van Gogh"))
//            .map(Painting::name)
//            .collect(Collectors.joining(","));
    //IO.println(names);
    // get any single painting by Da Vinci
    String names = paintings.stream()
            .filter(p -> p.painter().names().equalsIgnoreCase("Gustav Klimt"))
            .findFirst()
            .map(Painting::name)
            .orElse("No Painting Found");
    //IO.println(names);


    Optional<Integer> opint = Optional.empty();
    var val = opint.orElseGet(() -> new Random().nextInt());

    //IO.println(val);


    var painting = getPainting("Lady Godiva");
    IO.println("""
            Name: %s
            Painter: %s
            Sr.No: %d
            """.formatted(painting.name(), painting.painter().names(), painting.serialNo()));

    Optional<Double> opd = Optional.of(6d);
    opd.ifPresent(e -> {
        var sq = Math.pow(2,e);
        IO.println("sq " + sq);
    });
}
Painting getPainting(String painter) throws NoPaintingFoundException {
    List<Painting> paintings = Arrays.asList(
            new Painting("Starry Night", new Painter("Van Gogh",50), 324321),
            new Painting("Irises", new Painter("Van Gogh",17), 324211),
            new Painting("Mona Lissa", new Painter("Da Vinci",27), 432422)
    );
    return paintings.stream()
            .filter(e -> e.painter().names().equalsIgnoreCase(painter))
            .findFirst()
            .orElseThrow(() -> new NoPaintingFoundException("Did not find any painting by " + painter));
}
class NoPaintingFoundException extends Throwable {
    public NoPaintingFoundException(String msg) {
        super(msg);
    }
}