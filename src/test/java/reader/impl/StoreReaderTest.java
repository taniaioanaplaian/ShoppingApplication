package reader.impl;

import model.api.IProduct;
import model.api.IPromotion;
import model.api.IStore;
import org.junit.Test;
import reader.api.IReader;
import utils.DateFormatValidator;
import utils.DateValidator;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class StoreReaderTest {

    @Test
    public void readFile() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateValidator validator = new DateFormatValidator(dateFormatter);
        IReader<IStore> reader = new StoreReader(validator);
        IStore store = reader.readFile("magazin_Lidl");
        assert store != null;
        System.out.println(store.getName() + " " + store.isOpen());

        for(IPromotion promotion: store.getPromotions()){
            System.out.println(promotion.toString());
        }

        for(IProduct promotion: store.getProducts()){
            System.out.println(promotion.toString());
        }
    }
}