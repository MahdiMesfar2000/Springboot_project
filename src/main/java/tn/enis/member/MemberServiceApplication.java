package tn.enis.member;

import java.util.Date;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import tn.enis.member.dao.EnseignantChercheurRepository;
import tn.enis.member.dao.EtudiantRepository;
import tn.enis.member.dao.MemberRepository;
import tn.enis.member.entities.EnseignantChercheur;
import tn.enis.member.entities.Etudiant;
import tn.enis.member.entities.Member;
import tn.enis.member.service.IMemberService;

@SpringBootApplication
@AllArgsConstructor
public class MemberServiceApplication implements CommandLineRunner {

	MemberRepository memberRepository;
	EtudiantRepository etudiantRepository;
	EnseignantChercheurRepository enseignantChercheurRepository;
	IMemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Créer et enregistrer deux étudiants
		Etudiant student1 = Etudiant.builder().cin("CIN001").nom("John").prenom("Doe").dateNaissance(new Date())
				.cv("CV content").email("john.doe@example.com").password("password123").dateInscription(new Date())
				.diplome("Bachelor").encadrant(null).build();

		Etudiant student2 = Etudiant.builder().cin("CIN002").nom("Jane").prenom("Smith").dateNaissance(new Date())
				.cv("CV content").email("jane.smith@example.com").password("password123").dateInscription(new Date())
				.diplome("Master").encadrant(null).build();

		etudiantRepository.save(student1);
		etudiantRepository.save(student2);

		// Créer et enregistrer deux enseignants chercheurs
		EnseignantChercheur enseignant1 = EnseignantChercheur.builder().cin("CIN003").nom("Alice").prenom("Brown")
				.dateNaissance(new Date()).cv("CV content").email("alice.brown@example.com").password("password123")
				.grade("Professor").etablissement("University A").build();

		EnseignantChercheur enseignant2 = EnseignantChercheur.builder().cin("CIN004").nom("Bob").prenom("White")
				.dateNaissance(new Date()).cv("CV content").email("bob.white@example.com").password("password123")
				.grade("Associate Professor").etablissement("University B").build();

		enseignantChercheurRepository.save(enseignant1);
		enseignantChercheurRepository.save(enseignant2);

		// Chercher et afficher les membres dans le labo
		System.out.println("Members in the lab:");
		memberRepository.findAll()
				.forEach(member -> System.out.println("Nom: " + member.getNom() + ", Prenom: " + member.getPrenom()));

		// Chercher et afficher les membres par CIN
		String memberCin = "CIN001"; // Example CIN
		Member member = findMemberByCin(memberCin);
		if (member != null) {
			System.out.println("Found member: Nom: " + member.getNom() + ", Prenom: " + member.getPrenom());
		} else {
			System.out.println("Member not found with CIN: " + memberCin);
		}

		// Mettre à jour et afficher un membre
		Long memberId = 1L; // Example ID
		Member updatedMember = updateMember(memberId, "Mesfar", "Mahdi");
		if (updatedMember != null) {
			System.out.println(
					"Updated member: Nom: " + updatedMember.getNom() + ", Prenom: " + updatedMember.getPrenom());
		} else {
			System.out.println("Member not found with ID: " + memberId);
		}

		// Supprimer et afficher un membre
		Long memberIdToDelete = 1L; // Example ID
		Member deletedMember = deleteMember(memberIdToDelete);
		if (deletedMember != null) {
			System.out.println(
					"Deleted member: Nom: " + deletedMember.getNom() + ", Prenom: " + deletedMember.getPrenom());
		} else {
			System.out.println("Member not found with ID: " + memberIdToDelete);
		}
		
		//update a Member
		Member m = memberService.findMember(2L);
		m.setCv("cv1.pdf");
		memberService.updateMember(m);
		// Delete a Member
		memberService.deleteMember(3L);
		// afftecter un etudiant a un encadrant
		memberService.affectEtudiantToEncadrant(2L, 4L);
		
		// Pour un enseingnant donné, afficher la liste de ses étudiants
		System.out.println("Liste des étudiants d'un enseignant donné:");
		EnseignantChercheur ens = enseignantChercheurRepository.findById(4L).get();
		memberService.findByEncadrant(ens).forEach(e -> System.out.println(e.getNom() + " " + e.getPrenom()));
	}

	public Member findMemberByCin(String cin) {
		return memberRepository.findByCin(cin);
	}

	public Member updateMember(Long id, String newNom, String newPrenom) {
		Member member = memberRepository.findById(id).orElse(null);
		if (member != null) {
			member.setNom(newNom);
			member.setPrenom(newPrenom);
			memberRepository.save(member);
		}
		return member;
	}

	public Member deleteMember(Long id) {
		Member member = memberRepository.findById(id).orElse(null);
		if (member != null) {
			memberRepository.delete(member);
		}
		return member;
	}

}
