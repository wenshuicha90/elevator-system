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
        //check maitenance first:
        checkMaintenance();
        List<Elevator> idles = new ArrayList<>();
        List<Elevator> ups = new ArrayList<>();
        List<Elevator> downs = new ArrayList<>();


        //feature 7:
        //idle elevator in the current request level > occupied elevator pass the floor on the way
        // > closest idle elevator >   other idle elevator
        for (int i = 0; i < numofElevators; i ++) {
            Elevator curElevator = elevators.get(i);
            if (curElevator.getStatus() == Status.Idle()){
                //idle elevator in the current request level
                if (curElevator.getCurrentLevel() == r.level()) {
                    curElevator.handleExternalRequest(r);
                    return true;
                }
                else {
                    idles.add(curElevator);
                }
            }
        }

    }


    public void checkMaintenance() {
        List<Elevator> usable = new ArrayList<>();
        for (int i = 0; i < elevators.size(); i ++) {
            // elevator with trips > 100  can not be added to the usable list
            if (elevators.get(i).getTrips() < 101) {
                usable.add(elevators.get(i));
            }

        }
        elevators = usable;
    }
}
