import java.io.Serializable;

public class handshackerResponse implements Serializable{
    
    public char carName[] = new char[50];
    public char driverName[] = new char[50];
    public int identifier;
    public int version;
    public char trackName[] = new char[50];
    public char trackConfig[] = new char[50];
};