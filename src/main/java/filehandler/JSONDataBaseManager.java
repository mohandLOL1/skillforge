package filehandler;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JSONDataBaseManager<T> {
    public void read() throws IOException;
    public void write() throws IOException;
    public ArrayList<T> returnAllRecords();
}
