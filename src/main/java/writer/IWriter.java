package writer;

import model.api.ISearchResult;

import java.io.IOException;
import java.util.List;

public interface IWriter {

    /**
     * Writes the result information for each client
     */
    void writeResult(final List<ISearchResult> results) throws IOException;
}
