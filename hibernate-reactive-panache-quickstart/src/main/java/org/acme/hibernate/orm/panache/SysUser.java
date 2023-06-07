package org.acme.hibernate.orm.panache;

import java.time.LocalDateTime;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SysUser extends PanacheEntityBase {

	@Id
	public Long userId;

	public String username;

	public String password;

	public LocalDateTime createTime;

}