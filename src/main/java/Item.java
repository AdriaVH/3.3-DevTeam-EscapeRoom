public abstract class Item {
    private int id;
    private int roomId;
    private String name;
    private String description;

    protected Item() {}

    protected Item(int roomId, String name, String description) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id; }
    public int getRoomId() {
        return roomId; }
    public String getName() {
        return name; }
    public String getDescription() {
        return description; }
}