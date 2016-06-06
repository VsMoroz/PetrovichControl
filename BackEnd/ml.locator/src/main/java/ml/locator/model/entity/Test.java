/**
 * 
 */
package ml.locator.model.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jun 2, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@Entity
@Table(name="test")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Basic
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Test [id=" + id + ", value=" + value + "]";
	}

}
