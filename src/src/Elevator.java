import java.util.ArrayList;
import java.util.List;



public class Elevator {
    private List<ElevatorButton> buttons;
    private int currentLevel;
    private Status status;
    private int trips;
    private int floors;

    Elevator(int floors) {
        this.floors = floors;
        buttons = new ArrayList<>();
        for (int i = 0; i < this.floors; i ++) {
            buttons.add(new ElevatorButton(i));
        }
    }

    //internal request


}
