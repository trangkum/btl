package Module.File;

import Module.TicketImage.TicketimageModel;
import Module.Employee.EmployeeModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "file", schema = "btl", catalog = "")
public class FileModel {
    private int id;
    private byte[] data;
    private Integer length;
    private String name;
    private Integer employeeId;
    private Timestamp createTime;
    private String extension;
    private EmployeeModel employeeByEmployeeId;
    private Collection<TicketimageModel> ticketimagesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data", nullable = true)
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Basic
    @Column(name = "length", nullable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "employeeId", nullable = true)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "extension", nullable = true, length = 10)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileModel fileModel = (FileModel) o;

        if (id != fileModel.id) return false;
        if (!Arrays.equals(data, fileModel.data)) return false;
        if (length != null ? !length.equals(fileModel.length) : fileModel.length != null) return false;
        if (name != null ? !name.equals(fileModel.name) : fileModel.name != null) return false;
        if (employeeId != null ? !employeeId.equals(fileModel.employeeId) : fileModel.employeeId != null) return false;
        if (createTime != null ? !createTime.equals(fileModel.createTime) : fileModel.createTime != null) return false;
        if (extension != null ? !extension.equals(fileModel.extension) : fileModel.extension != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    public EmployeeModel getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeModel employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @OneToMany(mappedBy = "fileByFielId")
    public Collection<TicketimageModel> getTicketimagesById() {
        return ticketimagesById;
    }

    public void setTicketimagesById(Collection<TicketimageModel> ticketimagesById) {
        this.ticketimagesById = ticketimagesById;
    }
}
