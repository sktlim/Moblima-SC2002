package Controllers;

import Models.*;
import java.io.*;
import java.util.*;





public class AdminManager {


    public final static String FILENAME = "Databases/admins.txt";


    // CRUD methods
    public void createAdmin(String username, String password){
        // create method
        Map<String, List<String>> mapFromFile = HashMapFromTextFile();
        List<String> l = new ArrayList<String>();
        l.add(username);
        l.add(password);
        Integer admId = mapFromFile.size()+1;
        String admIdOut = admId.toString();
        mapFromFile.put(admIdOut, l);

        File file = new File(FILENAME);
        BufferedWriter bf = null;

        try{
            bf = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, List<String>> entry: mapFromFile.entrySet()){
                bf.write(entry.getKey() + ",");
                List<String> ls = entry.getValue();
                Iterator<String> listIterator = ls.iterator();
                while(listIterator.hasNext()){
                    bf.write(listIterator.next() + ",");
                }
                bf.newLine();
            }
            bf.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                bf.close();
            }
            catch(Exception e){
            }
        }
    }
        
    public Map<String,List<String>> HashMapFromTextFile(){
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        BufferedReader br = null;

        try{
            File file = new File(FILENAME);
            br = new BufferedReader(new FileReader(FILENAME));
            String line = null;
            while ((line=br.readLine())!=null){
                String[] parts = line.split(",");
                String adminId = parts[0].trim();
                List<String> l = new ArrayList<String>();
                String username = parts[1].trim();
                String password = parts[2].trim();
                l.add(username);
                l.add(password);

                if (!username.equals("") && !password.equals("")){
                    map.put(adminId,l);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (br!=null){
                try{
                    br.close();
                } catch (Exception e){
                    }
            }
        }
        return map;
    }

    public void printAdminList(){
        Map<String, List<String>> mapFromFile = HashMapFromTextFile();
        for (Map.Entry<String,List<String>> entry: mapFromFile.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public boolean updateAdmin(String username, String password){
        //update method
        Map<String, List<String>> mapFromFile = HashMapFromTextFile();
        // true if success, false otherwise
        return true;
    }

    public void deleteAdmin(int adminId){
        // delete method
    }



 
}

