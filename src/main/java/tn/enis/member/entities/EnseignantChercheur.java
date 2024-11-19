package tn.enis.member.entities;

import jakarta.persistence.DiscriminatorValue;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ens")
public class EnseignantChercheur extends Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	private String grade;

	@NonNull
	private String etablissement;

	@Builder
	public EnseignantChercheur(String cin, String nom, String prenom, Date dateNaissance, String cv, String email,
			String password, String grade, String etablissement) {

		super(cin, nom, prenom, dateNaissance, cv, email, password);
		this.grade = grade;
		this.etablissement = etablissement;

	}

}