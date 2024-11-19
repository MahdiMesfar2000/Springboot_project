package tn.enis.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.enis.member.dao.EnseignantChercheurRepository;
import tn.enis.member.dao.EtudiantRepository;
import tn.enis.member.dao.MemberRepository;
import tn.enis.member.entities.EnseignantChercheur;
import tn.enis.member.entities.Etudiant;
import tn.enis.member.entities.Member;

@Service
@AllArgsConstructor
public class MemberImpl implements IMemberService {

	MemberRepository memberRepository;
	EtudiantRepository etudiantRepository;
	EnseignantChercheurRepository enseignantChercheurRepository;

	@Override
	public Member addMember(Member m) {
		memberRepository.save(m);
		return m;
	}

	@Override
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}

	@Override
	public Member updateMember(Member m) {
		return memberRepository.saveAndFlush(m);
	}

	@Override
	public Member findMember(Long id) {
		return (Member) memberRepository.findById(id).get();
	}

	@Override
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Member findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}

	@Override
	public List<Member> findByNom(String nom) {
		return memberRepository.findByNom(nom);
	}

	@Override
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}

	@Override
	public List<EnseignantChercheur> findByGrade(String grade) {
		return enseignantChercheurRepository.findByGrade(grade);
	}

	@Override
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}

	@Override
	public List<Etudiant> findByEncadrant(EnseignantChercheur enseignantChercheur) {

		return etudiantRepository.findEtudiantsByEncadrant(enseignantChercheur);
	}

	@Override
	public void affectEtudiantToEncadrant(Long id_etd, Long id_ens) {
		Etudiant etd = etudiantRepository.findById(id_etd).get();
		EnseignantChercheur ens = enseignantChercheurRepository.findById(id_ens).get();
		etd.setEncadrant(ens);
		etudiantRepository.save(etd);
	}

	@Override
	public List<EnseignantChercheur> getEnseignants() {
		return enseignantChercheurRepository.findAll();
	}

	@Override
	public List<Etudiant> getEtudiants() {
		return etudiantRepository.findAll();
	}

	@Override
	public void setEncadrantToNull(EnseignantChercheur enseignantChercheur) {
		List<Etudiant> etudiants = etudiantRepository.findEtudiantsByEncadrant(enseignantChercheur);
		for (Etudiant etudiant : etudiants) {
			etudiant.setEncadrant(null);
		}
		etudiantRepository.saveAllAndFlush(etudiants);
	}

	@Override
	public Map<String, Integer> getNumberMembersPerGrade() {
		List<EnseignantChercheur> enseignants = enseignantChercheurRepository.findAll();

		Map<String, Integer> numberPerGrade = new HashMap<>();
		for (EnseignantChercheur ens : enseignants) {
			if (numberPerGrade.containsKey(ens.getGrade())) {
				numberPerGrade.put(ens.getGrade(), numberPerGrade.get(ens.getGrade()) + 1);
			} else {
				numberPerGrade.put(ens.getGrade(), 1);
			}
		}

		return numberPerGrade;
	}

	@Override
	public Map<String, Integer> getNumberMembersPerDiplome() {
		List<Etudiant> etudiants = etudiantRepository.findAll();

		Map<String, Integer> numberPerDiplome = new HashMap<>();
		for (Etudiant etd : etudiants) {
			if (numberPerDiplome.containsKey(etd.getDiplome())) {
				numberPerDiplome.put(etd.getDiplome(), numberPerDiplome.get(etd.getDiplome()) + 1);
			} else {
				numberPerDiplome.put(etd.getDiplome(), 1);
			}
		}

		return numberPerDiplome;
	}

	@Override
	public Map<String, Integer> getNumberMembersPerEtablissement() {
		List<EnseignantChercheur> enseignantChercheurs = enseignantChercheurRepository.findAll();

		Map<String, Integer> numberPerEtablissement = new HashMap<>();
		for (EnseignantChercheur ens : enseignantChercheurs) {
			if (numberPerEtablissement.containsKey(ens.getEtablissement())) {
				numberPerEtablissement.put(ens.getEtablissement(),
						numberPerEtablissement.get(ens.getEtablissement()) + 1);
			} else {
				numberPerEtablissement.put(ens.getEtablissement(), 1);
			}
		}

		return numberPerEtablissement;
	}

	@Override
	public Member findMemberById(Long id) {
		return memberRepository.findById(id).get();
	}

}
