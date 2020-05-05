package model.api;

import java.util.Map;

public interface IClient {
    /**
     * Returns client name
     * @return name
     * */
    String getName();

    /**
     * Set client name
     * @param name representing client's name
     */

    void setName(String name);

    /**
     * Returns each store the client wants to buy from
     * @return storePriority where key is the priority, and value is the store's name
     *
     * */
    Map<Integer, String> getStorePriority();

    /**
     * Adds a new store to client's options
     * @param priority integer number representing the priority
     * @param store store's Name
     * */

    void addStorePriority(Integer priority, String store);

}
