package reader.impl;

import exception.ReaderException;
import model.api.IClient;
import model.api.IOrder;
import model.api.IProduct;
import model.impl.ClientImpl;
import model.impl.OrderImpl;
import model.impl.ProductImpl;
import reader.api.IReader;
import java.io.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientOrderReader implements IReader<IOrder> {

    private static final String PRODUCTS_SEPARATOR = ";";
    private static final String STORES_SEPARATOR = ":";
    private static final String NAME_SEPARATOR = "_";
    private static final Logger LOGGER = Logger.getLogger(ClientOrderReader.class.getName());

    @Override
    public IOrder readFile(String filename)  {
        IOrder order = new OrderImpl();
        int countLine = 0;
        BufferedReader reader = null;
        String line = null;
        try {
            String path = "src/main/resources/" + filename + ".txt";
            reader = new BufferedReader(new FileReader(path));
            IClient client = initializeClient(filename);
            initializeOrder(filename, client, order);

            while ((line = reader.readLine()) != null && isValid(line)) {

                if(countLine == 0){
                    extractProducts(line, order);
                }else{
                    extractStorePriority(client, line);
                }
                countLine++;
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "OrderReader: readFile -> FileNotFoundException: " + e.getMessage());
        } catch (IOException | ReaderException e) {
            try {
                reader.skip(line.length());
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, "OrderReader: readFile -> IOException: " + e.getMessage());
            }
        }finally{
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "OrderReader: readFile -> IOException: " + e.getMessage());
            }
        }
        return order;
    }

    private void initializeOrder(String filename, IClient client, IOrder order) {
        String date = filename.split(NAME_SEPARATOR)[2];
        LocalDate orderDate = LocalDate.parse(date.substring(0, 10));
        order.setClient(client);
        order.setOrderDate(orderDate);
    }

    private IClient initializeClient(String filename) {
        IClient client = new ClientImpl();
        client.setName(filename.split(NAME_SEPARATOR)[1]);
        return client;
    }

    private void extractStorePriority(IClient client, String line) {
        String[] store =  line.split(STORES_SEPARATOR);
        client.addStorePriority(Integer.parseInt(store[1]), store[0]);
    }

    private void extractProducts(String line, IOrder order) {
        String[] products = line.split(PRODUCTS_SEPARATOR);
        for (String s : products) {
            IProduct product = new ProductImpl();
            product.setName(s);
            product.setPrice(0.0);
            order.addProduct(product);
        }
    }


}
