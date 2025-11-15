
package filehandler;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import courses.Course;
import java.io.File;

public class CourseDataBase implements JSONDataBaseManager<Course>{

  private final String filename;
  private ArrayList<Course> records;
  private final ObjectMapper mapper = new ObjectMapper();

  public CourseDataBase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }

  @Override
  public void read() throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      file.createNewFile();
      this.records = new ArrayList<>();
    } else {
      this.records = mapper.readValue(file, new TypeReference<ArrayList<Course>>() {
      });
    }
  }

  @Override
  public void write() throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), this.records);
  }
  
    public ArrayList<Course> returnAllRecords(){
        return this.records;
    }
}
