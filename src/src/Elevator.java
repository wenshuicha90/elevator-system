import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Elevator {
    private List<ElevatorButton> buttons;
    private int currentLevel;
    private Status status;
    private int trips;
    private int floors;
    private PriorityQueue<Request> upStops = new PriorityQueue<>();
    private PriorityQueue<Request> downStops = new PriorityQueue<>();
    private int maxTrips = 100;
    private int passed_floors;

    Elevator(int floors) {
        this.floors = floors;
        buttons = new ArrayList<>();
        for (int i = 1; i <= this.floors; i ++) {
            buttons.add(new ElevatorButton(i));
        }
    }

    //external request: up and down. request level lower or higher?

    public void handleExternalRequest(ExternalRequest r) {
        if (r.getDirection() == Direction.Up){
            upStops.add(r);
            if (status == Status.Idle){
                status = Status.Up;
                run();
            }
        }
        else {
            downStops.add(r);
            if (status == Status.Idle) {
                status = Status.Down;
                run();
            }
        }

    }


    // check if request is valid
    // if valid: add request to stoplist depending on the current level, the request level and status.
    public void handleInternalRequest(InternalRequest r) {
        if (this.isRequestValid(r)) {
            if (currentLevel == r.level) {
                status = Status.Idle;
            }
            else if (currentLevel < r.level) {
                upStops.add(r);
                if (status == Status.Idle) {
                    status = Status.Up;
                    run();
                }
            }
            else {
                downStops.add(r);
                if (status == Status.Idle) {
                    status = Status.Down;
                    run();
                }
            }
        }


    }

    //control the movement of the elevator based on stoplists
    //have to use opengate and closegate
    //change status to idle in the end
    //report as it moves from floor to floor
    public void run(){
        while (upStops.size() != 0 && downStops.size() != 0){
            if ( status == Status.Up) {
                while (upStops.size() != 0) {
                    Request nextRequest = upStops.poll();
                    System.out.println("The elevator moves from " + currentLevel + "floor to" + nextRequest.level + " floor.");
                    openGate();
                    currentLevel = nextRequest.level;
                    closeGate();
                }
            }
            else if (status == Status.Down) {
                while (downStops.size() != 0) {
                    Request nextRequest = downStops.poll();
                    System.out.println("The elevator moves from " + currentLevel + "floor to" + nextRequest.level + " floor.");
                    openGate();
                    currentLevel = nextRequest.level;
                    closeGate();
                }
            }
        }
        status = Status.Idle;
    }

    private void setMaxWeight(int maxWeight) {
        this.MaxWeight = maxWeight;
    }

    public void openGate(){
        System.out.println("The elevator door opens.")

    }

    public void closeGate(){
        System.out.println("The elevator door closes.")
    }
    // handle invalidreuqestion
    private boolean isRequestValid(InternalRequest r) {
        if (r.level > floors || r.level < 1) {
            System.out.println("The input is invalid: The level has to between 1 and " + floors);
            return false;
        }

        return true;
    }

}
