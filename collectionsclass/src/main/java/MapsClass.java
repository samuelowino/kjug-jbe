// User accounts : Login screen
// User: id, name,email, pass
record User(long id,String name, String email,String pass) {
    public User() {
        this(-1,"","","");
    }
}
public User getUser(String email, Map<String,User> database) {
    return database.getOrDefault(email, new User());
}
public boolean isEmailAlreadyTaken(String email,Map<String,User> map) {
    return map.containsKey(email);
}
void main() {
    List<User> users = List.of(
            // ....
            // 1 million other users
            new User(1,"Alex","alex@gmail.com","pass123"),
            new User(2,"Kennedy","ken@gmail.com","pass343"),
            new User(3,"Joe","joe@gmail.com","123435")
    );
    Map<String,User> usersmap = new HashMap<>();
    usersmap.put("alex@gmail.com",
            new User(1,"Alex","alex@gmail.com","pass123"));
    usersmap.put("ken@gmail.com",
            new User(2,"Ken","ken@gmail.com","3243324"));
    usersmap.put("joe@gmail.com",
            new User(3,"Joe","joe@gmail.com","dfsfds3242"));

//    for (Map.Entry<String,User> entry : usersmap.entrySet()) {
//        var email = entry.getKey();
//        var user = entry.getValue();
//        IO.println("email " + email);
//        IO.println("user " + user);
//    }
//    var iterator = usersmap.entrySet().iterator();
//    while (iterator.hasNext()) {
//        Map.Entry<String,User> entry = iterator.next();
//        IO.println(entry.getKey() +" " + entry.getValue());
//    }
    //usersmap.forEach((key,value) -> IO.println(key + " " + value));

    // Collection : size : contains(k) : isEmpty
//    IO.println("map size " + usersmap.size());
//    IO.println("map isEmpty " + usersmap.isEmpty());
    var isTaken =isEmailAlreadyTaken("ken@gmail.com",usersmap);
    var isPresent = usersmap.
            containsValue(new User(
                    32,"Mark",
                    "mark@gmail.com",
                    "pass123"));
    usersmap.forEach((k,_) -> IO.println(k));
    usersmap.remove("joe@gmail.com");
    IO.println("after removal");
    usersmap.forEach((k,_) -> IO.println(k));

    Map<Integer,String> fn = Map.ofEntries(
            Map.entry(1,"apple"),
            Map.entry(2,"orange"),
            Map.entry(3,"mango")
    );
    Map<Integer,String> um = Collections.unmodifiableMap(fn);
    //fn.forEach((k,v) -> IO.println(k + " " + v));


    Map<Integer,String> fruits = Map.of(
            1,"apple",
            2,"orange",
            3,"mangoes"
            // 4, "grapes"
    );
   // fruits.forEach((k,v) -> IO.println(k + " " + v));

    // computeIfAbsent
    // computIfPresent

    Map<Integer, String> mf = new HashMap<>();
    mf.put(1,"apple");
    mf.put(2,"orange");
    mf.put(3,"mango");
    mf.computeIfAbsent(4,_ -> "grapes");
    mf.computeIfAbsent(1,_ -> "pares");
    mf.put(1,"pares");
    //mf.forEach((k,v) -> IO.println(k + " " + v));


    // list.set
    mf.computeIfPresent(2,(_,_)-> "banana");
    mf.forEach((k,v) -> IO.println(k + " " + v));

}