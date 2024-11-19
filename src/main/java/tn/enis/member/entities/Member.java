package tn.enis.member.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_mbr", discriminatorType = DiscriminatorType.STRING, length = 3)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public abstract class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;
	@NonNull
	private String cin;
	@NonNull
	private String nom;
	@NonNull
	private String prenom;
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private byte[] photo;
	@NonNull
	private String cv;

	@NonNull
	private String email;
	@NonNull
	private String password;

}