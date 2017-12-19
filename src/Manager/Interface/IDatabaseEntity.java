package Manager.Interface;

import java.util.List;


public interface IDatabaseEntity {
    void insert() throws Exception;

    void update() throws Exception;

    void delete() throws Exception;

    List<Object> select() throws Exception;

    void insert(List<Object> data) throws Exception;

    void create() throws Exception;

    void truncate() throws Exception;

    void drop() throws Exception;

    List<Object> select(int id) throws Exception;
}
