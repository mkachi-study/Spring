package Model;

public class User {
    private long    _id;
    private String  _name;

    public User() {}

    public User(long id, String name) {
        _id = id;
        _name = name;
    }

    public void setId(long id) { _id = id; }
    public long getId() { return _id; }

    public void setName(String name) { _name = name; }
    public String getName() { return _name; }
}
