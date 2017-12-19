package Module.Employee;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeModel.class)
public class EmployeeModel_ {
    public static volatile SingularAttribute<EmployeeModel, Integer> id;
    public static volatile SingularAttribute<EmployeeModel, String> email;
    public static volatile SingularAttribute<EmployeeModel, String> name;
    public static volatile SingularAttribute<EmployeeModel, Integer> teamId;
    public static volatile SingularAttribute<EmployeeModel, Integer> locationId;
    public static volatile SingularAttribute<EmployeeModel, String> briefName;
}
