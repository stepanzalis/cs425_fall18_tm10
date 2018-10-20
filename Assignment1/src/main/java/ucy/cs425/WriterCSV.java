package main.java.ucy.cs425;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSV Writer to get info which is then converted from CSV to Excel file to create a graph
 *
 *
 */
public class WriterCSV {

    private static final String CSV_FILE = "./server_measurements.csv";

    public void writeToCSV(int clientId, int limit, RequestCounter counter) {

        try {
            String[] header = { "ClientID", "Count", "End count", "Sum", "Limit"};
            String[] data = {clientId + " " + counter.getCount() + " " + counter.getEndCount() + " " + counter.getSum() + System.lineSeparator()};

            CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE));

            csvWriter.writeNext(header);
            csvWriter.writeNext(data);

            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
