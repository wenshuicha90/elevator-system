import com.sun.tools.doclets.internal.toolkit.util.Extern;

public class ExternalRequest extends Request{
    private Direction direction;

    ExternalRequest(int level, Direction d) {
        super(level);
        this.direction = d;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }
    
}
