package SQLLiteDB;

/**
 * Created by Hossam Gamal on 1/18/2017.
 */
public class ItemBarcode {
    private int id;
    private String code;
    private String name;
    public ItemBarcode() { }
    public ItemBarcode(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return this.id;
    }
    public String getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }
}