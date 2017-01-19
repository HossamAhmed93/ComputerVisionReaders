package SQLLiteDB;

/**
 * Created by Hossam Gamal on 1/18/2017.
 */
public class ItemOCR {
    private int id;
    private String code;
    private String name;
    public ItemOCR() { }
    public ItemOCR(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    public ItemOCR(String code, String name) {
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