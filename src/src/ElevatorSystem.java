import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {


    private List<Elevator> elevators;
    private int numofElevators;
    private int floors;

    //initialize the elevator system with elevators and floors.
    ElevatorSystem(int n, int floors) {
        this.numofElevators = n;
        this.floors = floors;
        for (int i = 0; i < n; i ++) {
            elevators.add(new Elevator(floors));
        }
    }

    public void setNumofElevators(int n) {
        this.numofElevators = n;
        elevators = null;
        for (int i = 0; i < n; i ++) {
            elevators.add(new Elevator(floors));
        }
    }

    public int getNumofElevators() {
        return this.numofElevators;
    }

    public boolean handleExternalRequest(ExternalRequest r) {

    }

}
