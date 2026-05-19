package machine;
public class MachineStatus extends Machine {
    private final String lastUpdate;
    private final boolean isOffline;
    public MachineStatus(
            String manfDate,
            String oilLevel,
            String model,
            String lastUpdate,
            boolean isOffline) {
        super(manfDate, oilLevel, model);
        this.lastUpdate = lastUpdate;
        this.isOffline = isOffline;
    }
}
