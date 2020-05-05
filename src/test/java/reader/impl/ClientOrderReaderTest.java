package reader.impl;

import com.thoughtworks.qdox.model.expression.Or;
import model.api.IClient;
import model.api.IOrder;
import model.api.IProduct;
import model.impl.ClientImpl;
import model.impl.OrderImpl;
import model.impl.ProductImpl;
import org.junit.Test;
import reader.api.IReader;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ClientOrderReaderTest {

    @Test
    public void readFile() {
        IReader<IOrder> reader = new ClientOrderReader();
        IOrder order = reader.readFile("comanda_Ionescu Maria_2020-04-15");
        IOrder expected =  new OrderImpl();
        expected.setOrderDate(LocalDate.of(2020, 04, 15));

        IClient client =  new ClientImpl();
        client.setName("Ionescu Maria");
        client.addStorePriority(1, "Lidl");
        client.addStorePriority(2, "Auchan");
        expected.setClient(client);
        java.util.List<IProduct> productList = new ArrayList<>();
        productList.add(new ProductImpl("iaurt", 0.0));
        productList.add(new ProductImpl("paine", 0.0));
        productList.add(new ProductImpl("cafea", 0.0));
        productList.add(new ProductImpl("zahar", 0.0));
        productList.add(new ProductImpl("orez", 0.0));
        productList.add(new ProductImpl("apa", 0.0));
        expected.addAllProducts(productList);

    }
}