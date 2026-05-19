package machine;
public class IceCreameMachine {
    public static <M extends Machine> M getMachineStatus(M currentStatus){
        currentStatus.setModel("xyz");
        return currentStatus;
    }
}
