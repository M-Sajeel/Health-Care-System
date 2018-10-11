package healthcare.sajeel.com.healthcare.model;

public class BookingListing {
    private final String name;
    private final String designation;
    private final String address;
    private final int picture;

    /**
     * @param name String
     * @param designation String
     * @param address String
     * @param picture int
     */
    public BookingListing(String name, String designation, String address, int picture) {
        this.name = name;
        this.designation = designation;
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
    public String getDesignation() {
        return designation;
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
