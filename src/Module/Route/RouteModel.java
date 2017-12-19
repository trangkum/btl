package Module.Route;


import Module.Permission.PermissionModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "route", schema = "btl", catalog = "")
public class RouteModel {
    private Integer id;
    private String name;
    private String method;
    private String route;
    private Collection<PermissionModel> permissionsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "method", nullable = true, length = 45)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "route", nullable = true, length = -1)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteModel that = (RouteModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "routeByRouteId")
    public Collection<PermissionModel> getPermissionsById() {
        return permissionsById;
    }

    public void setPermissionsById(Collection<PermissionModel> permissionsById) {
        this.permissionsById = permissionsById;
    }
}

@StaticMetamodel(RouteModel.class)
class RouteModel_ {
    public static volatile SingularAttribute<RouteModel, Integer> id;
    public static volatile SingularAttribute<RouteModel, String> name;
    public static volatile SingularAttribute<RouteModel, String> method;
    public static volatile SingularAttribute<RouteModel, String> route;
}
