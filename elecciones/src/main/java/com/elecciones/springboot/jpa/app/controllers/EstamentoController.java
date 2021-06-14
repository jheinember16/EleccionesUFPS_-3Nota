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
import com.elecciones.springboot.jpa.app.entities.Estamento;
import com.elecciones.springboot.jpa.app.repository.dao.IEleccionDao;
import com.elecciones.springboot.jpa.app.repository.dao.IEstamentoDao;

@Controller
@RequestMapping("/estamento")
public class EstamentoController {
	@Autowired
	private IEstamentoDao estamentoDao;
	@Autowired
	private IEleccionDao eleccionesDao;

	@GetMapping({ "/listar", "", "/" })
	private String listar(Model model) {
		model.addAttribute("estamentos", estamentoDao.findAll());
		return "estamento/lista";
	}

	@GetMapping("/guardar")
	private String crear(Model model) {
		Estamento estamento = new Estamento();
		estamento.setEleccionBean(new Eleccion());
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("estamento", estamento);
		model.addAttribute("accion", "Registrar");
		return "estamento/form";
	}

	@PostMapping("/guardar")
	private String guardar(Estamento estamento, RedirectAttributes flask, Model model, @RequestParam Integer eleccion) {
		flask.addFlashAttribute("success", "estamento creado con exito");
		Eleccion e = this.eleccionesDao.findById(eleccion).orElse(null);
		if (e == null)
			return "redirect:/estamento/guardar";
		estamento.setEleccionBean(e);
		this.estamentoDao.save(estamento);
		return "redirect:/estamento/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes flask) {
		Estamento estamento = this.estamentoDao.findById(id).orElse(null);
		if (estamento != null) {
			this.estamentoDao.deleteById(id);
			flask.addFlashAttribute("success", "estamento eliminado sastifactoriamente");
		}
		return "redirect:/estamento/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes flask) {
		Estamento estamento = this.estamentoDao.findById(id).orElse(null);
		if (estamento == null) {
			flask.addFlashAttribute("error", "ha ocurrido un error inesperado el usuario no existe en la bd");
			return "redirect:/estamento/listar";
		}
		model.addAttribute("elecciones", eleccionesDao.findAll());
		model.addAttribute("estamento", estamento);
		model.addAttribute("accion", "Actualizar");

		return "estamento/form";
	}
}
