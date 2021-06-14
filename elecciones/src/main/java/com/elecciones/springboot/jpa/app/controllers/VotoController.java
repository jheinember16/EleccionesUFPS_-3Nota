package com.elecciones.springboot.jpa.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.elecciones.springboot.jpa.app.entities.Candidato;
import com.elecciones.springboot.jpa.app.entities.Eleccion;
import com.elecciones.springboot.jpa.app.entities.Estamento;
import com.elecciones.springboot.jpa.app.entities.Votante;
import com.elecciones.springboot.jpa.app.entities.Voto;
import com.elecciones.springboot.jpa.app.repository.dao.ICandidatoDao;
import com.elecciones.springboot.jpa.app.repository.dao.IEleccionDao;
import com.elecciones.springboot.jpa.app.repository.dao.IEstamentoDao;
import com.elecciones.springboot.jpa.app.repository.dao.IVotanteDao;
import com.elecciones.springboot.jpa.app.repository.dao.IVotoDao;

@Controller
@RequestMapping("/voto")
public class VotoController {
	@Autowired
	private IEleccionDao eleccionDao;
	@Autowired
	private ICandidatoDao candidatoDao;
	@Autowired
	private IEstamentoDao estamentoDao;
	@Autowired
	private IVotanteDao votanteDao;
	@Autowired
	private IVotoDao votoDao;

	@GetMapping("/urna")
	private String urna(Model model) {
		model.addAttribute("elecciones", eleccionDao.findAll());
		return "voto/urna";
	}

	@PostMapping("/form")
	private String votacion(Model model, @RequestParam Integer eleccion) {
		Eleccion e = this.eleccionDao.findById(eleccion).orElse(null);
		model.addAttribute("eleccion", e);
		model.addAttribute("candidatos", candidatoDao.findAll());
		model.addAttribute("estamentos", estamentoDao.findAll());
		model.addAttribute("votantes", votanteDao.findAll());
		return "voto/form";
	}
	
	@GetMapping({ "/listar", "", "/" })
	private String listar(Model model) {
		model.addAttribute("votos", votoDao.findAll());
		return "voto/lista";
	}
	
	@PostMapping("/guardar")
	private String guardar(Model model, @RequestParam Integer estamento, @RequestParam Integer votante,
			@RequestParam Integer candidato) {
		Estamento e = this.estamentoDao.findById(estamento).orElse(null);
		Votante v = this.votanteDao.findById(votante).orElse(null);
		Candidato c = this.candidatoDao.findById(candidato).orElse(null);
		if (e == null || v == null || c == null)
			return "redirect:/voto/urna";
		Voto voto = new Voto();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			String fecha = "" + LocalDate.now();
			Date date = formatter.parse(fecha);
			voto.setFechacreacion(date);
			voto.setFechavoto(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "redirect:/voto/urna";
		}
		voto.setEstamentoBean(e);
		voto.setVotanteBean(v);
		voto.setCandidatoBean(c);

		this.votoDao.save(voto);
		return "redirect:/voto/listar";
	}
}
