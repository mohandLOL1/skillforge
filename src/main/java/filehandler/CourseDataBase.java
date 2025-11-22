package filehandler;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import courses.Course;
import java.io.File;

public class CourseDataBase extends JSONDataBaseManager<Course> {

    public CourseDataBase(String filename){
        super(filename);
        try {
            read();
        } catch (IOException ex) {
            System.getLogger(CourseDataBase.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
 
    }

    @Override
    public void read() throws IOException {
        File file = getWritableJsonFile(filename);
        
        if (!file.exists() || file.length() == 0) {
        System.out.println("File is empty, initializing empty records.");
        records = new ArrayList<>();  
        return;                       
    
    }
        this.records = mapper.readValue(file, new TypeReference<ArrayList<Course>>() {});

    }

    @Override
    public void write() throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(getWritableJsonFile(filename), this.records);
    }

    @Override

    public ArrayList<Course> returnAllRecords() {
        return this.records;
    }
}
