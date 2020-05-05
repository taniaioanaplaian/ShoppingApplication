package reader.impl;
import model.api.IProduct;
import model.api.IPromotion;
import model.api.IStore;
import model.enumeration.PromotionType;
import model.impl.*;
import reader.api.IReader;
import utils.DateValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreReader implements IReader<IStore>{

    private static final String SECTION_SEPARATOR = "\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*"+"\\R";
    private static final Logger LOGGER = Logger.getLogger(StoreReader.class.getName());
    private final DateValidator validator;

    public StoreReader(DateValidator validator){
        this.validator = validator;
    }

    @Override
    public IStore readFile(String filename) {
        IStore store =  new StoreImpl();
        String path = "src/main/resources/" + filename + ".txt";
        String[] sections;
        try {
            sections = readAllFile(path).split(SECTION_SEPARATOR);
            store.setName(filename.split("_")[1]);
            store.setOpen(readSchedule(sections[0]));
            readPromotions(sections[1], store);
            readProducts(sections[2], store);

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "StoreReader -> readFile : IOException");

        }
        return store;
    }

    private boolean readSchedule(String section) {
        section = section.replaceAll("\\R", "");
        return section.equals("Deschis");
    }

    private void readPromotions(String section, IStore store){
        String[] promotions = section.split("\\n\\s\\n");
        for(String s : promotions){
            IPromotion promotion = readCurrentPromotion(s);
            store.addPromotion(promotion);
        }

    }

    private void readProducts(String section, IStore store){
        String prod = section.replaceAll("\\R", " ");
        for(String s : prod.split(" ")){
            String productName = s.split(":")[0];
            Double productPrice = Double.parseDouble(s.split(":")[1]);
            IProduct product =  new ProductImpl();
            product.setPrice(productPrice);
            product.setName(productName);
            store.addProduct(product);
        }


    }

    private IPromotion readCurrentPromotion(String promotion){

        promotion = promotion.replaceAll("\\R", " ");

        LocalDate startDate = LocalDate.parse(promotion.split(" ")[0]);
        LocalDate finishDate;

        if(validator.isValid(promotion.split(" ")[1])) {
             finishDate = LocalDate.parse(promotion.split(" ")[1]);
        }else{
            finishDate = startDate;
        }

        IPromotion iPromotion;
        if(finishDate.equals(startDate)){
            SingleDayPromotion siPromotion = (SingleDayPromotion)PromotionFactory.createPromotion(PromotionType.SINGLE_DAY);
            assert siPromotion != null;
            siPromotion.setStartDate(startDate);
            iPromotion = siPromotion;
            String[] products = promotion.split(" ");
            for(int i = 1 ; i < products.length; i ++){
                IProduct product =  new ProductImpl();
                product.setName(products[i].split(":")[0]);
                product.setPrice(Double.parseDouble(products[i].split(":")[1]));
                iPromotion.addProduct(product);
            }
        }else{
            MultipleDayPromotion miPromotion = (MultipleDayPromotion)PromotionFactory.createPromotion(PromotionType.MULTIPLE_DAY);
            assert  miPromotion != null;
            miPromotion.setPeriod(startDate, finishDate);
            iPromotion = miPromotion;
            String[] products = promotion.split(" ");
            for(int i = 2; i < products.length; i ++){
                IProduct product =  new ProductImpl();
                product.setName(products[i].split(":")[0]);
                product.setPrice(Double.parseDouble(products[i].split(":")[1]));
                iPromotion.addProduct(product);
            }
        }

        return iPromotion;
    }


    private  String readAllFile(String filePath) throws IOException
    {
        return new String (Files.readAllBytes(Paths.get(filePath)));
    }
}
