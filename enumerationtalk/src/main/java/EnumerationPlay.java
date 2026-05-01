public class EnumerationPlay {
    void main() {
//        var point = new Point(1,5);
//        IO.println("point: ==> " + point);


//        var livingRoomSwitch = BulbSwitch.ON;
//        var bedroomSwitch = BulbSwitch.OFF;
//        var lobbySwitch = BulbSwitch.valueOf("OFF");
//        IO.println(";" + lobbySwitch.getDescription());


        //IO.println(";" + livingRoomSwitch.getDescription());
        //IO.println(";" + bedroomSwitch.getDescription());

//        IO.println("enter color name");
//        var input = IO.readln();
//        var ball = pickBall(input);
//        if (ball != null) {
//            IO.println("ball found is " + ball);
//        }
        var sampleBall = Box.GREEN_BALL;
        var description = describeBall(sampleBall);
        IO.println("desc: " + description);
        for (Box name : Box.values()) {
            IO.println("box == > " + name);
        }
    }
    public String describeBall(Box ball){
        //reach in the box and pick color
        return switch (ball) {
            case RED_BALL -> "this is a red ball";
            case YELLOW_BALL -> "this is yellow ball";
            case ORANGE_BALL -> "this is an orange ball";
            case GREEN_BALL -> "this is a green ball";
        };
    }
}
