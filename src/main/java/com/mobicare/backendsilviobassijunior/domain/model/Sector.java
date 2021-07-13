package com.mobicare.backendsilviobassijunior.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "sectors")
public class Sector extends BaseEntity{

	private String description;

	@UpdateTimestamp
	private OffsetDateTime updateAt;
	
	@JsonBackReference
	@OneToMany(mappedBy = "sector")
	private List<Collaborator> collaborators;
	
}
