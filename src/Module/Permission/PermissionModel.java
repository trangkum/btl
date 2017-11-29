package Module.Permission;


import Module.Role.RoleModel;
import Module.Route.RouteModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "permission", schema = "btl", catalog = "")
public class PermissionModel {
    private Integer id;
    private Integer roleId;
    private Integer routeId;
    private RoleModel roleByRoleId;
    private RouteModel routeByRouteId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "roleId", nullable = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "routeId", nullable = true)
    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionModel that = (PermissionModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    public RoleModel getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleModel roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeId", referencedColumnName = "id", insertable = false, updatable = false)
    public RouteModel getRouteByRouteId() {
        return routeByRouteId;
    }

    public void setRouteByRouteId(RouteModel routeByRouteId) {
        this.routeByRouteId = routeByRouteId;
    }
}

@StaticMetamodel(PermissionModel.class)
class PermissionModel_ {
    public static volatile SingularAttribute<PermissionModel, Integer> id;
    public static volatile SingularAttribute<PermissionModel, Integer> roleId;
    public static volatile SingularAttribute<PermissionModel, Integer> routeId;
}
