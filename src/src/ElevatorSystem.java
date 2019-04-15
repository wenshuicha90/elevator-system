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

            else {
                if (curElevator.getDirection() == Direction.Up) {
                    ups.add(curElevator);
                }
                else {
                    downs.add(curElevator);
                }
            }
        }

        if (r.getDirection() == Direction.Up) {
            for (int i = 0; i < ups.size(); i ++) {
                if (ups.get(i).getCurrentLevel() <= r.level) {
                    ups.get(i).handleExternalRequest(r);
                    return true;
                }
            }
        }


        if (r.getDirection() == Direction.Down) {
            for (int i = 0; i < downs.size(); i ++) {
                if (ups.get(i).getCurrentLevel() <= r.level) {
                    ups.get(i).handleExternalRequest(r);
                    return true;
                }
            }
        }


        if (idles.size() > 0) {
            Integer minDist = Integer.MAX_VALUE;
            Elevator closestElevator = elevators.get(0);
            for (int i = 0; i < idles.size(); i ++) {
                Integer curDist = Math.abs(idles.get(i).getCurrentLevel() - r.level);
                if (curDist < minDist) {
                    minDist = curDist;
                    closestElevator = idles.get(i);
                }
            }
            closestElevator.handleExternalRequest(r);
            return true;
        }
        return false;
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
