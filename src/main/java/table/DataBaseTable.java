package table;

public abstract class DataBaseTable {

    public abstract void insert(String... params);
    public abstract String select();
    public abstract void update(String oldParameter, String newParameter);
    public abstract void delete(String paramToDelete);
}
