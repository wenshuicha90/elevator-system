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
    private int maxWeight = 5000;
    private int totalWeights;
    private List<Person> passengers;
    private int maxTrips = 100;
    private int passed_floors;

    Elevator(int floors) {
        this.floors = floors;
        buttons = new ArrayList<>();
        for (int i = 1; i <= this.floors; i ++) {
            buttons.add(new ElevatorButton(i));
        }
    }

    //external request: up and down. request level: lower or higher?

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
        while (upStops.size() != 0 || downStops.size() != 0){
            if ( status == Status.Up) {
                while (upStops.size() != 0) {
                    Request nextRequest = upStops.poll();
                    passed_floors += Math.abs(currentLevel - nextRequest.level);
                    System.out.println("The elevator moves from " + currentLevel + "floor to" + nextRequest.level + " floor.");
                    openGate();
                    currentLevel = nextRequest.level;
                    closeGate();
                }
                trips += 1;
            }
            if (status == Status.Down) {
                while (downStops.size() != 0) {
                    Request nextRequest = downStops.poll();
                    passed_floors += Math.abs(currentLevel - nextRequest.level);
                    System.out.println("The elevator moves from " + currentLevel + "floor to" + nextRequest.level + " floor.");
                    openGate();
                    currentLevel = nextRequest.level;
                    closeGate();
                }
                trips += 1;
            }
        }
        status = Status.Idle;
    }

    private void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void openGate(List<Person> people){
        System.out.println("The elevator door opens.");
        for (Person p: people) {
            if (totalWeights + p.weight <= totalWeights) {
                totalWeights += p.weight;
                passengers.add(p);
            }
            else {
                System.out.println("Too many people and stop coming in.");
                 break;
            }
        }

    }

    public void closeGate(List<Person> people){

        for (Person p: people) {
            if (passengers.contains(p)) {
                passengers.remove(p);
                totalWeights -= p.weight;
            }
            else {
                System.out.println("This person is not in the elevator.");
                break;
            }
        }
        System.out.println("The elevator door closes.");

    }

    public float getCurrentWeight() {
        return totalWeights;
    }

    // handle invalidreuqestion
    private boolean isRequestValid(InternalRequest r) {
        if (r.level > floors || r.level < 1) {
            System.out.println("The input is invalid: The level has to between 1 and " + floors);
            return false;
        }
        return true;
    }

    public Status getStatus(){
        return this.status;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public Direction getDirection() {
        return status == Status.Up? Direction.Up: Direction.Down;
    }
    public int getTrips() {
        return trips;
    }

    public void resetPassed_floors() {
        passed_floors = 0;
    }

    public int getPassed_floor(){
        return passed_floors;
    }

}
