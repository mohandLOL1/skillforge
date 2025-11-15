
package filehandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;


import users.User;

public class UserDataBase extends JSONDataBaseManager<User> {


    public UserDataBase(String filename){
        super(filename);
    }

    @Override
    public void read() throws IOException{
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("CANNOT FIND FILE");
            file.createNewFile();
            this.records = new ArrayList<>();
        }
        else{
            this.records = mapper.readValue(file, new TypeReference<ArrayList<User>>(){});
        }
    }
    @Override
    public void write() throws IOException {
        if(!new File(filename).exists()){
            System.out.println("NO FILE FOUND");
            return;
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), this.records);
    }
    
    @Override
    
    public ArrayList<User> returnAllRecords(){
        return this.records;
    }
    
}