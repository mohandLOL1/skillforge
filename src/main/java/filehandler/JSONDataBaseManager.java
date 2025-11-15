package filehandler;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JSONDataBaseManager<T>  {
    protected String filename;
    protected ArrayList<T> records;
    
    protected ObjectMapper mapper = new ObjectMapper();
    public abstract ArrayList<T> read() throws IOException;
    public abstract void write() throws IOException;
    
    public abstract ArrayList<T> returnAllRecords();
}
