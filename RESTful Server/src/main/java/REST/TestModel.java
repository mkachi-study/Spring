package REST;

public class TestModel {
    private final long _id;
    private final String _name;

    public TestModel(long id, String content) {
        this._id = id;
        this._name = content;
    }

    public long getId() { return _id; }
    public String getName() { return _name; }
}
