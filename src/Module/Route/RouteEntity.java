package Module.Route;

import Module.Permission.PermissionEntity;
import Module.Permission.PermissionModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class RouteEntity implements Serializable {
    public int id;
    public String name;
    public String method;
    public String route;
    public List<PermissionEntity> permissionEntities;


    public RouteEntity() {
    }

    public RouteEntity(RouteModel RouteModel, Object... objects) {
        this.id = RouteModel.getId();
        this.name = RouteModel.getName();
        this.method = RouteModel.getMethod();
        this.route = RouteModel.getRoute();
        for (Object object : objects) {
            if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof PermissionModel) {
                        this.permissionEntities = ((Collection<PermissionModel>) object).parallelStream().map(PermissionEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public RouteModel toModel() {
        RouteModel RouteModel = new RouteModel();
        RouteModel.setId(id);
        RouteModel.setName(name);
        RouteModel.setRoute(route);
        RouteModel.setMethod(method);
        return RouteModel;
    }
}
