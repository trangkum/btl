package Module.Role;


import Module.Permission.PermissionModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "role", schema = "btl", catalog = "")
public class RoleModel {
    private Integer id;
    private String name;
    private String description;
    private Collection<PermissionModel> permissionsById;

    @Id
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
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleModel roleModel = (RoleModel) o;

        if (id != null ? !id.equals(roleModel.id) : roleModel.id != null) return false;
        if (name != null ? !name.equals(roleModel.name) : roleModel.name != null) return false;
        if (description != null ? !description.equals(roleModel.description) : roleModel.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "roleByRoleId")
    public Collection<PermissionModel> getPermissionsById() {
        return permissionsById;
    }

    public void setPermissionsById(Collection<PermissionModel> permissionsById) {
        this.permissionsById = permissionsById;
    }
}

@StaticMetamodel(RoleModel.class)
class RoleModel_ {
    public static volatile SingularAttribute<RoleModel, Integer> id;
    public static volatile SingularAttribute<RoleModel, String> name;
    public static volatile SingularAttribute<RoleModel, String> description;
}
