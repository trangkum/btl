package Module.File;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.QueryParam;
import java.sql.Timestamp;
import java.util.List;

public class SearchFileModel {
    @QueryParam("id")
    public Integer id;
    @QueryParam("length")
    public Integer length;
    @QueryParam("name")
    public String name;
    @QueryParam("employeeId")
    public Integer employeeId;
    @QueryParam("createTime")
    public Timestamp createTime;
    @QueryParam("extension")
    public String extension;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<FileModel> root) {
        if (id != null) {
            predicates.add(builder.equal(root.get(FileModel_.id), id));
        }
        if (length != null) {
            predicates.add(builder.equal(root.get(FileModel_.length), length));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(FileModel_.name)), "%" + name.toLowerCase() + "%"));
        }
        if (employeeId != null) {
            predicates.add(builder.equal(root.get(FileModel_.employeeId), employeeId));
        }
        if (createTime != null) {
            predicates.add(builder.equal(root.get(FileModel_.createTime), createTime));
        }
        if (extension != null && !extension.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(FileModel_.extension)), "%" + name.toLowerCase() + "%"));
        }
        return predicates;
    }
}
