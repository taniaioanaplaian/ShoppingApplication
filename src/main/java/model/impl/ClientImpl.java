package model.impl;
import model.api.IClient;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientImpl implements IClient {

    private String name;
    private Map<Integer, String> storePriority;

    public ClientImpl(){
        this.storePriority  = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<Integer, String> getStorePriority() {
        return storePriority;
    }

    @Override
    public void addStorePriority(Integer priority, String storePriority) {
        this.storePriority.put(priority, storePriority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientImpl client = (ClientImpl) o;
        return name.equals(client.name) &&
                storePriority.equals(client.storePriority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storePriority);
    }
}
