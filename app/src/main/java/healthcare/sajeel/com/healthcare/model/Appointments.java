package healthcare.sajeel.com.healthcare.model;

public class Appointments {
    private final String id;
    private final String title;
    private final String address;
    private final String timing;
    private final int picture;
    private final int logo;

    /**
     * @param id int
     * @param title String
     * @param address String
     * @param timing String
     * @param picture int
     * @param logo int
     */
    public Appointments(String id, String title, String address, String timing, int picture, int logo) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.timing = timing;
        this.picture = picture;
        this.logo = logo;
    }

    /**
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return String
     */
    public String getTiming() {
        return timing;
    }

    /**
     * @return int
     */
    public int getPicture() {
        return picture;
    }

    /**
     * @return int
     */
    public int getLogo() {
        return logo;
    }
}