public class ElevatorButton {
    private int level;

    ElevatorButton(int level) { this.level = level;
    }

    public InternalRequest pressInternalButton() {
        return new InternalRequest(this.level);
    }

    public ExternalRequest pressExternalButton(Direction direction) {
        return new ExternalRequest(this.level, direction);
    }
}
