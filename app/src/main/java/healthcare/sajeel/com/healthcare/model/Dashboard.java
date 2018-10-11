package healthcare.sajeel.com.healthcare.model;

public class Dashboard {
    private final String name;
    private final String address;
    private final int picture;

    /**
     * @param name String
     * @param address String
     * @param picture int
     */
    public Dashboard(String name, String address, int picture) {
        this.name = name;
        this.address = address;
        this.picture = picture;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return int
     */
    public int getPicture() {
        return picture;
    }
}