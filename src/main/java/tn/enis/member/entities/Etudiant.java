package tn.enis.member.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("etd")
public class Etudiant extends Member {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private EnseignantChercheur encadrant;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateInscription;

	private String diplome;

	@Builder
	public Etudiant(String cin, String nom, String prenom, Date dateNaissance, String cv, String email, String password,
			Date dateInscription, String diplome, EnseignantChercheur encadrant) {
		super(cin, nom, prenom, dateNaissance, cv, email, password);
		this.dateInscription = dateInscription;
		this.diplome = diplome;
		this.encadrant = encadrant;
	}
}