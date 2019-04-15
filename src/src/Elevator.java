import java.util.ArrayList;
import java.util.List;



public class Elevator {
    private List<ElevatorButton> buttons;
    private int currentLevel;
    private Status status;
    private int trips;
    private int floors;
    private List<Request> upStops = new ArrayList<>();
    private List<Request> downStops = new ArrayList<>();
    private int maxTrips = 100;
    private

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
        }
        else {
            downStops.add(r);
        }

    }

    public void handleInternalRequest(InternalRequest r) {

    }

    public void run(){

    }

    public void openGata(){

    }

    public void closeGate(){

    }

}
