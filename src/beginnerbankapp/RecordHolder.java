package beginnerbankapp;

/**
 * The RecordHolder singleton class acts as the generator of customer ID upon account creation.
 */
public class RecordHolder {

    /** Single reference to the incrementing ID */
    private static int CUSTOMER_ID = 1;
    /** Singleton instance of RecordHolder class */
    private static RecordHolder INSTANCE = null;

    private RecordHolder() {}

    public static RecordHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecordHolder();
        }
        return INSTANCE;
    }

    /** Assigns current available ID number to the user */
    public int registerCustomerId() {
        return CUSTOMER_ID++;
    }

}
