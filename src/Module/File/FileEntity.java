package Module.File;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.TicketImage.TicketImageEntity;
import Module.TicketImage.TicketimageModel;
import org.infinispan.commons.util.Base64;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class FileEntity implements Serializable {
    public int id;
    public String data;
    public Integer length;
    public String name;
    public Integer employeeId;
    public Timestamp createTime;
    public String extension;
    public EmployeeEntity employeeEntity;
    public List<TicketImageEntity> ticketImageEntities;

    public FileEntity() {
    }

    public FileEntity(int id, byte[] data, Integer length, String name, Integer employeeId, Timestamp createTime, String extension) {
        this.id = id;
        if (data != null) this.data = Base64.encodeBytes(data);
        this.length = length;
        this.name = name;
        this.employeeId = employeeId;
        this.createTime = createTime;
        this.extension = extension;
    }

    public FileEntity(FileModel FileModel, Object... objects) {
        this.id = FileModel.getId();
        if (FileModel.getData() != null) this.data = Base64.encodeBytes(FileModel.getData());
        this.length = FileModel.getLength();
        this.name = FileModel.getName();
        this.employeeId = FileModel.getEmployeeId();
        this.createTime = FileModel.getCreateTime();
        this.extension = FileModel.getExtension();
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.employeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof TicketimageModel) {
                        this.ticketImageEntities = ((Collection<TicketimageModel>) object).parallelStream().map(TicketImageEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public FileModel toModel() {
        FileModel FileModel = new FileModel();
        FileModel.setName(name);
        FileModel.setId(id);
        if (data != null) FileModel.setData(Base64.decode(data));
        FileModel.setLength(length);
        FileModel.setEmployeeId(employeeId);
        FileModel.setCreateTime(createTime);
        FileModel.setExtension(extension);
        return FileModel;
    }
}
