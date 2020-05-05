package service.impl;
import model.api.*;
import org.junit.Test;
import reader.api.IReader;
import reader.impl.ClientOrderReader;
import reader.impl.StoreReader;
import service.api.IOrderFinder;
import utils.DateFormatValidator;
import utils.DateValidator;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;


public class IOrderFinderImplTest {

    @Test
    public void findProducts() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateValidator validator = new DateFormatValidator(dateFormatter);
        IReader<IStore> reader = new StoreReader(validator);
        java.util.List<IStore> stores =  new ArrayList<>();
        IStore lidl = reader.readFile("magazin_Lidl");
        stores.add(lidl);
        IStore auchan = reader.readFile("magazin_Auchan");
        stores.add(auchan);
        IStore megaImage = reader.readFile("magazin_Mega Image");
        stores.add(megaImage);
        IReader<IOrder> clientOrderReader = new ClientOrderReader();
        IOrder order = clientOrderReader.readFile("comanda_Ionescu Maria_2020-04-15");
        IOrderFinder finder = new IOrderFinderImpl(stores);
        ISearchResult result = finder.findProducts(order);
        System.out.println(result.toString());

    }
}