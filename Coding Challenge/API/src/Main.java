import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        getByCategory(); // 1. for getting entries by category.
        saveEntries(); // 2. for saving entries in file

        /*
            3. Question: what are the key things you would consider when creating/consuming
            an API to ensure that it is secure and reliable?
            Ans:
            To make API key secure we can use authorization while building keys, so that it
            can be only accessible when user is authorised.
         */
    }

    public static void getByCategory() throws IOException{
         /*
            1. Create an API that lists the title, description based on the category passed
            as an input parameter.

            {"count":51,"categories":["Animals","Anime","Anti-Malware","Art \u0026 Design","Authentication \u0026 Authorization","Blockchain","Books","Business","Calendar","Cloud Storage \u0026 File Sharing","Continuous Integration","Cryptocurrency","Currency Exchange","Data Validation","Development","Dictionaries","Documents \u0026 Productivity","Email","Entertainment","Environment","Events","Finance","Food \u0026 Drink","Games \u0026 Comics","Geocoding","Government","Health","Jobs","Machine Learning","Music","News","Open Data","Open Source Projects","Patent","Personality","Phone","Photography","Programming","Science \u0026 Math","Security","Shopping","Social","Sports \u0026 Fitness","Test Data","Text Analysis","Tracking","Transportation","URL Shorteners","Vehicle","Video","Weather"]}
         */

        URL url = new URL("https://api.publicapis.org/entries?category=science");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != 200) {
            throw new RuntimeException("Error code: " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = br.readLine();

        while(line != null) {
            System.out.println(line);
            line = br.readLine();
        }

        br.close();
    }

    public static void saveEntries() throws IOException{
         /*
            Create an API that would save a new entry with all the relevant properties which retrieves
            values from the endpoint GET /entries.
         */

        URL url = new URL("https://api.publicapis.org/entries");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != 200) {
            throw new RuntimeException("Error code: " + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        File file = new File("C://Users//Shubham Dandekar//Desktop///Entries.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        String line = br.readLine();

        while(line != null) {
            fw.write(line);
            line = br.readLine();
        }
        System.out.println("Entries saved.");
        fw.close();
        br.close();
    }
}