package ef.hrms.entities.concrete;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Data
@Entity
@Table(name="user_roles")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

}