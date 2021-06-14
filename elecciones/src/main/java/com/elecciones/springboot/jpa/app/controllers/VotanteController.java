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

import com.elecciones.springboot.jpa.app.entities.Eleccion;
import com.elecciones.springboot.jpa.app.entities.Tipodocumento;
import com.elecciones.springboot.jpa.app.entities.Votante;
import com.elecciones.springboot.jpa.app.repository.dao.IEleccionDao;
import com.elecciones.springboot.jpa.app.repository.dao.ITipodocumentoDao;
import com.elecciones.springboot.jpa.app.repository.dao.IVotanteDao;

@Controller
@RequestMapping("/votante")
public class VotanteController {
	@Autowired
	private IVotanteDao votanteDao;
	@Autowired
	private ITipodocumentoDao tipodocumentoDao;
	@Autowired
	private IEleccionDao eleccionesDao;

	@GetMapping({ "/listar", "", "/" })
	private String listar(Model model) {
		model.addAttribute("votantes", votanteDao.findAll());
		return "votante/lista";
	}

	@GetMapping("/guardar")
	private String crear(Model model) {
		Votante votante = new Votante();
		votante.setEleccionBean(new Eleccion());
		votante.setTipodocumentoBean(new Tipodocumento());
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("tipodocumentos", tipodocumentoDao.findAll());
		model.addAttribute("votante", votante);
		model.addAttribute("accion", "Registrar");
		return "votante/form";
	}

	@PostMapping("/guardar")
	private String guardar(Votante votante, RedirectAttributes flask, Model model, @RequestParam Integer eleccion, @RequestParam Integer tipodocumento) {
		flask.addFlashAttribute("success", "votante creado con exito");
		Eleccion e = this.eleccionesDao.findById(eleccion).orElse(null);
		Tipodocumento t = this.tipodocumentoDao.findById(tipodocumento).orElse(null);
		if (e == null || t == null)
			return "redirect:/votante/guardar";
		votante.setEleccionBean(e);
		votante.setTipodocumentoBean(t);
		this.votanteDao.save(votante);
		return "redirect:/votante/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes flask) {
		Votante votante = this.votanteDao.findById(id).orElse(null);
		if (votante != null) {
			this.votanteDao.deleteById(id);
			flask.addFlashAttribute("success", "votante eliminado sastifactoriamente");
		}
		return "redirect:/votante/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes flask) {
		Votante votante = this.votanteDao.findById(id).orElse(null);
		if (votante == null) {
			flask.addFlashAttribute("error", "ha ocurrido un error inesperado el usuario no existe en la bd");
			return "redirect:/votante/listar";
		}
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("tipodocumentos", tipodocumentoDao.findAll());
		model.addAttribute("votante", votante);
		model.addAttribute("accion", "Actualizar");

		return "votante/form";
	}
}
