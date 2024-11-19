package tn.enis.member.service;

import tn.enis.member.entities.EnseignantChercheur;
import tn.enis.member.entities.Etudiant;
import tn.enis.member.entities.Member;

import java.util.List;
import java.util.Map;

public interface IMemberService {
	// Crud sur les membres
	public Member addMember(Member m);

	public void deleteMember(Long id);

	public Member updateMember(Member p);

	public Member findMember(Long id);

	public List<Member> findAll();

	// Filtrage par propriété
	public Member findByCin(String cin);

	public List<Member> findByNom(String nom);

	// recherche spécifique des étudiants
	public List<Etudiant> findByDiplome(String diplome);

	// recherche spécifique des enseignants
	public List<EnseignantChercheur> findByGrade(String grade);

	public List<EnseignantChercheur> findByEtablissement(String etablissement);

	public Member findMemberById(Long id);

	public List<Etudiant> findByEncadrant(EnseignantChercheur enseignantChercheur);

	public void affectEtudiantToEncadrant(Long id_etd, Long id_ens);

	public List<EnseignantChercheur> getEnseignants();

	public List<Etudiant> getEtudiants();

	public void setEncadrantToNull(EnseignantChercheur enseignantChercheur);

	public Map<String, Integer> getNumberMembersPerGrade();

	public Map<String, Integer> getNumberMembersPerDiplome();

	public Map<String, Integer> getNumberMembersPerEtablissement();
}