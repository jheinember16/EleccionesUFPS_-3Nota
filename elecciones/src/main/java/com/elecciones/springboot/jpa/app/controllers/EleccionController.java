package com.elecciones.springboot.jpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecciones.springboot.jpa.app.entities.Eleccion;
import com.elecciones.springboot.jpa.app.repository.dao.IEleccionDao;

@Controller
@RequestMapping("/eleccion")
public class EleccionController {
	@Autowired
	private IEleccionDao eleccionDao;

	@GetMapping({ "/listar", "", "/" })
	private String listar(Model model) {
		model.addAttribute("elecciones", eleccionDao.findAll());
		return "eleccion/lista";
	}

	@GetMapping("/guardar")
	private String crear(Model model) {
		Eleccion eleccion = new Eleccion();
		model.addAttribute("eleccion", eleccion);
		model.addAttribute("accion", "Registrar");
		return "eleccion/form";
	}

	@PostMapping("/guardar")
	private String guardar(Eleccion eleccion, RedirectAttributes flask, Model model) {
		flask.addFlashAttribute("success", "eleccion creado con exito");
		this.eleccionDao.save(eleccion);
		return "redirect:/eleccion/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes flask) {
		Eleccion eleccion = this.eleccionDao.findById(id).orElse(null);
		if (eleccion != null) {
			this.eleccionDao.deleteById(id);
			flask.addFlashAttribute("success", "eleccion eliminado sastifactoriamente");
		}
		return "redirect:/eleccion/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes flask) {
		Eleccion eleccion = this.eleccionDao.findById(id).orElse(null);
		if (eleccion == null) {
			flask.addFlashAttribute("error", "ha ocurrido un error inesperado el suario no existe en la bd");
			return "redirect:/candidato/listar";
		}

		model.addAttribute("eleccion", eleccion);
		model.addAttribute("accion", "Actualizar");

		return "eleccion/form";
	}
}
