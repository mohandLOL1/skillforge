package filehandler;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;

public abstract class JSONDataBaseManager<T> {

    protected String filename;
    protected ArrayList<T> records;
    protected ObjectMapper mapper = new ObjectMapper();

    public JSONDataBaseManager(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public File getWritableJsonFile(String filename) {
        String baseDir = System.getProperty("user.dir") + File.separator + "/resources";
        File dir = new File(baseDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return new File(dir, filename);
    }

    public abstract void read() throws IOException;

    public abstract void write() throws IOException;

    public abstract ArrayList<T> returnAllRecords();
}
