
package filehandler;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;


import courses.Course;
import java.io.File;

public class CourseDataBase extends JSONDataBaseManager<Course>{


  public CourseDataBase(String filename){
     super(filename);
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
  
  @Override
  
    public ArrayList<Course> returnAllRecords(){
        return this.records;
    }
}
