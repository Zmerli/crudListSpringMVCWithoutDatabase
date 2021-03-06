package com.sip.ams.controllers;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.*;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {

	public static List<Etudiant> students = new ArrayList<>();

	static {

		students.add(new Etudiant("Yosri", "y@gmail.com", 24));
		students.add(new Etudiant("Haitham", "h@gmail.com", 23));
		students.add(new Etudiant("Amine", "a@gmail.com", 25));
	}

	@RequestMapping("/list")
	public String listEtudiant(Model m) {

		m.addAttribute("students", students);
		return "etudiant/listEtudiant";
	}

	@GetMapping("/add")
	public String formAdd(Model m) {
		Etudiant e = new Etudiant();
		m.addAttribute("etudiant", e);
		return "etudiant/addEtudiant";
	}

	@PostMapping("/add")
	// @ResponseBody
	// public String saveEtudiant(@RequestParam("nom") String
	// nom,@RequestParam("email") String email,@RequestParam("age") int age)
	public String saveEtudiant(Etudiant etudiant) {
		// return "etudiant/saveEtudiant";
		Etudiant e = new Etudiant(etudiant.getNom(), etudiant.getEmail(), etudiant.getAge());
		// return nom +" "+email +" "+age;
		students.add(e);
		return "redirect:list";

	}

	@GetMapping("/delete/{email}")
	// @ResponseBody
	public String deleteEtudiant(@PathVariable("email") String mail) {
		int index = 0;

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getEmail().equals(mail)) {
				index = i;
				break;
			}
		}

		students.remove(index);
		// System.out.println(students);

		return "redirect:../list";
	}

	@GetMapping("/update/{email}")
//	@ResponseBody
	public String updateEtudiant(@PathVariable("email") String mail, Model m) {
		int index = 0;

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getEmail().equals(mail)) {
				index = i;
				break;
			}
		}

		System.out.println(mail);

		Etudiant e = new Etudiant();

		e.setEmail(students.get(index).getEmail());
		e.setAge(students.get(index).getAge());
		e.setNom(students.get(index).getNom());

		m.addAttribute("etudiant", e);

		students.remove(index);

		return "etudiant/updateEtudiant";
	}

	@PostMapping("/update")
	public String saveAfterUpdate(Etudiant etudiant) {
		Etudiant e = new Etudiant(etudiant.getNom(), etudiant.getEmail(), etudiant.getAge());
		// return nom +" "+email +" "+age;
		students.add(e);
		return "redirect:list";

	}

}
