package Module.Location;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LocationModel.class)
public class LocationModel_ {
    public static volatile SingularAttribute<LocationModel, Integer> id;
    public static volatile SingularAttribute<LocationModel, String> name;
    public static volatile SingularAttribute<LocationModel, Integer> managerEmloyeeId;
}
