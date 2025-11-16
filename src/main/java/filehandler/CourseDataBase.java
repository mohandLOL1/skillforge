package filehandler;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import courses.Course;
import java.io.File;

public class CourseDataBase extends JSONDataBaseManager<Course> {

    public CourseDataBase(String filename) {
        super(filename);
    }

    @Override
    public void read() throws IOException {
        File file = getWritableJsonFile(filename);
        
        if (!file.exists()) {
        System.out.println("File does not exist, creating new one.");
        file.createNewFile();
        this.records = new ArrayList<>();
        return;
    }

    
    if (file.length() == 0) {
        System.out.println("File is empty, initializing empty records.");
        this.records = new ArrayList<>();
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
