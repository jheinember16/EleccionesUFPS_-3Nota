package com.elecciones.springboot.jpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecciones.springboot.jpa.app.entities.Candidato;
import com.elecciones.springboot.jpa.app.entities.Eleccion;
import com.elecciones.springboot.jpa.app.repository.dao.ICandidatoDao;
import com.elecciones.springboot.jpa.app.repository.dao.IEleccionDao;

@Controller
@RequestMapping("/candidato")
public class CandidatoController {
	@Autowired
	private ICandidatoDao candidatoDao;
	@Autowired
	private IEleccionDao eleccionesDao;

	@GetMapping({ "/listar", "", "/" })
	private String listar(Model model) {
		model.addAttribute("candidatos", candidatoDao.findAll());
		return "candidato/lista";
	}

	@GetMapping("/guardar")
	private String crear(Model model) {
		Candidato candidato = new Candidato();
		candidato.setEleccionBean(new Eleccion());
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("candidato", candidato);
		model.addAttribute("accion", "Registrar");
		return "candidato/form";
	}

	@PostMapping("/guardar")
	private String guardar(Candidato candidato, RedirectAttributes flask, Model model, @RequestParam Integer eleccion) {
		flask.addFlashAttribute("success", "candidato creado con exito");
		Eleccion e = this.eleccionesDao.findById(eleccion).orElse(null);
		if (e == null)
			return "redirect:/candidato/guardar";
		candidato.setEleccionBean(e);
		this.candidatoDao.save(candidato);
		return "redirect:/candidato/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes flask) {
		Candidato candidato = this.candidatoDao.findById(id).orElse(null);
		if (candidato != null) {
			this.candidatoDao.deleteById(id);
			flask.addFlashAttribute("success", "candidato eliminado sastifactoriamente");
		}
		return "redirect:/candidato/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes flask) {
		Candidato candidato = this.candidatoDao.findById(id).orElse(null);
		if (candidato == null) {
			flask.addFlashAttribute("error", "ha ocurrido un error inesperado el usuario no existe en la bd");
			return "redirect:/candidato/listar";
		}
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("candidato", candidato);
		model.addAttribute("accion", "Actualizar");

		return "candidato/form";
	}
}
