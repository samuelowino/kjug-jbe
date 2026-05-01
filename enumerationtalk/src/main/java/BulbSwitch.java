public enum BulbSwitch {
    ON("bulb is on"),
    OFF("bulb is off");
    private String bulbDescription;
    BulbSwitch(String desc) {
        this.bulbDescription = desc;
    }
    public String getDescription(){
        return bulbDescription;
    }
//    @Override
//    public String toString() {
//        return "BulbSwitch ";
//    }
}
