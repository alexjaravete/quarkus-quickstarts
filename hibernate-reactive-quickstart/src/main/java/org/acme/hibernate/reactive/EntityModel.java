package org.acme.hibernate.reactive;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EntityModel {

	private EntityModel() {
	}

	@Entity(name = "Organization")
	@Table(name = "Organization")
	public static class Organization {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String name;

		@ManyToOne(fetch = FetchType.LAZY)
		private Person createdBy;

		@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
		private Set<Person> members = new HashSet<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Person getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Person createdBy) {
			this.createdBy = createdBy;
		}

		public Set<Person> getMembers() {
			return members;
		}

		public void setMembers(Set<Person> members) {
			this.members = members;
		}

		public void addMember(Person person) {
			members.add( person );
			person.setOrganization( this );
		}

		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Organization that = (Organization) o;
			return Objects.equals( name, that.name );
		}

		@Override
		public int hashCode() {
			return Objects.hash( name );
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}
	}

	@Entity(name = "Person")
	@Table(name = "Person")
	public static class Person {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String name;

		@JsonIgnore
		@ManyToOne(fetch = FetchType.LAZY)
		private Organization organization;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Organization getOrganization() {
			return organization;
		}

		public void setOrganization(Organization organization) {
			this.organization = organization;
		}

		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			Person person = (Person) o;
			return Objects.equals( name, person.name );
		}

		@Override
		public int hashCode() {
			return Objects.hash( name );
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}
	}
}
