import flavors.Flavor;
import flavors.Pineapple;
import flavors.Strawberry;
import flavors.Vanilla;
import machine.IceCreameMachine;
import machine.Machine;
import machine.MachineStatus;
public class Main {
    void main(){
        Machine status = new Machine("some date","23","m");
        MachineStatus mstatus = new MachineStatus(
                "some date",
                "23","m",
                "",
                false);
       var result = IceCreameMachine.getMachineStatus(status);
       IO.println(result.getModel());
    }
}
