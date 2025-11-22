package filehandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;


import users.User;

public class UserDataBase extends JSONDataBaseManager<User> {

    public UserDataBase(String filename){

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

        if (!file.exists()) {
            System.out.println("File does not exist, creating new one.");
            file.createNewFile();
            this.records = new ArrayList<User>();
            return;
        }

        
        if (file.length() == 0) {
            System.out.println("File is empty, initializing empty records.");
            this.records = new ArrayList<>();
            return;
        }

        this.records = mapper.readValue(file, new TypeReference<ArrayList<User>>() {
        });

    }

    @Override
    public void write() throws IOException {
        ArrayList<User> copy = this.records;
        mapper.writerWithDefaultPrettyPrinter().writeValue(getWritableJsonFile(filename), copy);
    }

    @Override

    public ArrayList<User> returnAllRecords() {
        return this.records;
    }

}
