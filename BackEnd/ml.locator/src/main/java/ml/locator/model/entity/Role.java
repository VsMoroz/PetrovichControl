package ml.locator.model.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @Column(name = "roleId")
	private Integer roleId;
	
    @Basic
    @Column(name = "name")
    private String name;
    
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name="roleId", referencedColumnName="roleId")},
    inverseJoinColumns = {@JoinColumn(name="username", referencedColumnName="username")})
    private List<User> users;
    
    public Role(){}

	public Role(Integer roleId, String name, List<User> users) {
		this.roleId = roleId;
		this.name = name;
		this.users = users;
	}


	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
