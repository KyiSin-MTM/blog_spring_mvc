package spring.blog.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.blog.bl.dto.RoleDto;

@Entity
@Table(name="roles")
@Getter
@Setter
@AllArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	public Role() {
		super();
	}
	
	public Role(RoleDto roleDto) {
		this.id = roleDto.getId();
		this.name = roleDto.getName();
	}
}