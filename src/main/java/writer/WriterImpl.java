package writer;
import model.api.IProduct;
import model.api.ISearchResult;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriterImpl implements IWriter {

    @Override
    public void writeResult(List<ISearchResult> results) throws IOException {

        List<String> res = new ArrayList<>();
        StringBuilder string ;

        for(ISearchResult result : results){

            string = new StringBuilder();
            string.append("\n").append(result.getClientName()).append("\n");
            for(int i = 0 ; i < result.getOrderResult().size(); i ++){
                string.append(result.getOrderResult().get(i)).append(" ").append(result.getStores().get(i).getName()).append("\n");
            }
            string.append(String.format("Final cost: %.2f\n", result.getFinalCost()));

            if(!result.getNotFound().isEmpty()){
                string.append("Not found: ");
                for(IProduct product : result.getNotFound()){
                    string.append(product.getName()).append("\n");
                }
            }
            res.add(String.valueOf(string));
        }
        Files.write(Paths.get("src/main/resources/comenzi.txt"), res.toString().getBytes());

    }
}
