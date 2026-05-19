package machine;
public class Machine {
    private String manfDate;
    private String oilLevel;
    private String model;

    public Machine(String manfDate, String oilLevel, String model) {
        this.manfDate = manfDate;
        this.oilLevel = oilLevel;
        this.model = model;
    }
    public String getManfDate() {
        return manfDate;
    }

    public void setManfDate(String manfDate) {
        this.manfDate = manfDate;
    }

    public String getOilLevel() {
        return oilLevel;
    }

    public void setOilLevel(String oilLevel) {
        this.oilLevel = oilLevel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
