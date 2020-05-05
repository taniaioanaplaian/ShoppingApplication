package app;
import model.api.IOrder;
import model.api.ISearchResult;
import model.api.IStore;
import reader.api.IReader;
import reader.impl.ClientOrderReader;
import reader.impl.StoreReader;
import service.api.IOrderFinder;
import service.impl.IOrderFinderImpl;
import utils.DateFormatValidator;
import utils.DateValidator;
import writer.IWriter;
import writer.WriterImpl;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

public class DeliverySystem {

    private static java.util.List<IOrder> clientOrder;
    private static java.util.List<IStore> store;
    private  static IReader<IStore> reader;
    private  static  IReader<IOrder> clientOrderReader;
    private static IOrderFinder finder;
    private static List<ISearchResult> results;
    private static IWriter writer ;

    public static void main (String[] args) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateValidator validator = new DateFormatValidator(dateFormatter);
        clientOrder = new ArrayList<>();
        store = new ArrayList<>();
        reader = new StoreReader(validator);
        clientOrderReader = new ClientOrderReader();
        writer = new WriterImpl() ;
        results = new ArrayList<>();
        finder = new IOrderFinderImpl(store);
        getFilesData();
        getOrders();

        //Sort descending by cost, then ascending by client name
        results.sort(Comparator.comparingDouble(ISearchResult::getFinalCost).reversed()
                .thenComparing(ISearchResult::getClientName));

        writeToFile();
    }

    private static void writeToFile() {
        try {
            writer.writeResult(results);
        } catch (IOException e) {
            System.out.println("Problem while writing to file");
            e.printStackTrace();
        }
    }

    private static void getOrders() {
        for(IOrder order: clientOrder){
            ISearchResult result = finder.findProducts(order);
            results.add(result);
        }
    }

    private static void getFilesData() {
        for (File f : getResourceFolderFiles()) {
            if (f.isFile()) {
                String filename = f.getName();
                filename = filename.replaceAll(".txt", "");
                if(filename.substring(0, 7).equals("magazin")){
                    store.add(reader.readFile(filename));
                }else if(filename.substring(0, 7).equals("comanda")){
                    clientOrder.add(clientOrderReader.readFile(filename));
                }
            }
        }
    }


    private static File[] getResourceFolderFiles() {
        File folder = new File("src/main/resources");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        return listOfFiles;
    }

}

