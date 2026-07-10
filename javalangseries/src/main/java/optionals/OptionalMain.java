
// Optionals Overview
// Optionals + Nullability
// Optionals + Streams
// Abuse Optionals
// Optional : boxed type : reference type
// OptionaInt : OptionalDouble
public Config getConfigLegacy(boolean isUnix) {
    if (isUnix) return null;
    return new Config(Map.of(
            "password","323242pass",
            "ipaddrr", "127.0.0.1"
    ));
}
public Optional<Integer> getUsageCount(String os) {
    if (os.equalsIgnoreCase("unix"))
        return Optional.empty();
    return Optional.of(new Random().nextInt(0,1000));
}
public OptionalInt getUsagePrimitive(String os) {
    if (os.equalsIgnoreCase("unix"))
        return OptionalInt.empty();
    return OptionalInt.of(new Random().nextInt(0,1000));
}
public OptionalDouble getUsagePrimitiveDouble(String os) {
    if (os.equalsIgnoreCase("unix"))
        return OptionalDouble.empty();
    double rnd = new Random().nextDouble(0,1000);
    return OptionalDouble.of(rnd);
}
public Optional<String> message(int signal) {
    if (signal < 5 && signal > 0)
        return Optional.ofNullable(signal > 3 ? null : "hello you passed! sig " + signal);
    else
        return Optional.empty();
}
public Optional<String> cleanMessage(int signal) {
    if (signal < 5 && signal > 0) {
        if (signal > 3)
            return Optional.of("hello you almost passed! sig " + signal);
        else
            return Optional.of("hello you passed! sig " + signal);
    } else
        return Optional.empty();
}
record Config(Map<String,String> entries) { }
public Optional<Config> getConfig(boolean isUnix) {
    if (isUnix) return Optional.empty();
    var conf = new Config(Map.of(
            "password","323242pass",
            "ipaddrr", "127.0.0.1"
    ));
    return Optional.of(conf);
}

void main() {

}
void opt5() {
    var rndSig = new Random().nextInt(4, 10);
    Optional<String> optMsg = cleanMessage(4);
    if (optMsg.isEmpty()) {
        IO.println("invalid signal");
        return;
    }
    var msg = optMsg.get();
    IO.println(msg);
}
void opt4() {
    var os = "unix";
    var usg = getUsagePrimitiveDouble(os);
    if (usg.isEmpty()) return;
    double d = usg.getAsDouble();
    var truncated = String.format("%,.2f",d);
    IO.println(os + " usage is " + truncated);
}
void opt3() {
    var os = "Windows";
    var usg = getUsagePrimitive(os);
    if (usg.isEmpty()) return;
    int b = usg.getAsInt();
    IO.println(os + " usage is " + b);
}
void opt2() {
    var os = "Windows";
    var usg = getUsageCount(os);
    if (usg.isEmpty()) return;
    Integer cnt = usg.get();
    IO.println(os + " usage " + cnt);
}
void opt1() {
    var os = "Windows";
    var optConf = getConfig(os.toLowerCase().startsWith("win") ? false : true);
    if (optConf.isEmpty()) return;
    Config conf = optConf.get(); // safe
    conf.entries().entrySet()
            .forEach(e ->
                    IO.println( e.getKey() + ":" + e.getValue()));
}
void legacyUsage() {
    var currentOS = "Linux";
    var conf = getConfigLegacy(currentOS.contentEquals("Windows") ? false : true);
    if (conf == null) return;
    var confEntries = conf.entries().entrySet();
    confEntries.forEach( e ->
            IO.println("key " +  e.getKey() + " val "  + e.getValue()));
}