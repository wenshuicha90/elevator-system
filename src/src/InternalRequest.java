public class InternalRequest extends Request{
    InternalRequest(int level) {
        super(level);
    }
    public void setLevel(int l) {
        super.level = l;
    }
    public int getLevel() {
        return this.level;
    }
}
