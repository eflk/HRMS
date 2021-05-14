package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@Column(name="display_name")
	private String displayName;

	private Boolean enabled;

	private String name;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="permission")
	private List<RolePermission> rolePermissions;

	public Permission() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public RolePermission addRolePermission(RolePermission rolePermission) {
		getRolePermissions().add(rolePermission);
		rolePermission.setPermission(this);

		return rolePermission;
	}

	public RolePermission removeRolePermission(RolePermission rolePermission) {
		getRolePermissions().remove(rolePermission);
		rolePermission.setPermission(null);

		return rolePermission;
	}

}