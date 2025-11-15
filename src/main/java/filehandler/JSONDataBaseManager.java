package filehandler;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JSONDataBaseManager<T> {

    protected ObjectMapper mapper = new ObjectMapper();

    public abstract ArrayList<T> read(String filename) throws IOException;

    public abstract void write(String filename, ArrayList<T> data) throws IOException;
}
