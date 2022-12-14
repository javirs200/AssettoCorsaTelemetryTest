import java.io.Serializable;

public class handsaker implements Serializable{
    
    public int identifier;
    public int version;
    public int operationId;

    public handsaker(int i, int j, int k) {
        identifier = i;
        version = j;
        operationId = k;
    }
}
