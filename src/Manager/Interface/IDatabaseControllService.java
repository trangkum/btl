package Manager.Interface;

import Manager.Model.DatabaseModel;
import org.hibernate.cfg.Configuration;


public interface IDatabaseControllService {
    Configuration createConfiguration(DatabaseModel databaseModel);

    boolean setActive(int id);
}
