package com.ecrops.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecrops.entity.ActiveSeason;
import com.ecrops.entity.Cultivator;
import com.ecrops.service.CultivatorService;
import com.ecrops.service.impl.ActiveSeasonServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class CultivatorController {

	Logger logger = LoggerFactory.getLogger(CultivatorController.class);

	@Autowired
	private CultivatorService cultivatorService;

	@Autowired
	private ActiveSeasonServiceImpl activeSeasonService;

	@GetMapping("/cultivator")
	public String loadAddOrUpdateCultivator(Model model) {
		List<ActiveSeason> cropYearActiveSeasonList = activeSeasonService.listAll();
		model.addAttribute("crYearList", Arrays
				.stream(cropYearActiveSeasonList.stream().mapToInt(ActiveSeason::getCropyear).distinct().toArray())
				.boxed().collect(Collectors.toList()));
		model.addAttribute("cultivator", new Cultivator());

		return "cultivatorHomePage";
	}

	@GetMapping("/cultivator/kathaNo/")
	public String getCultivatorDetailsByKathaNo(Cultivator cultivator, Model model) {

		List<Cultivator> allCultiVatorsList = cultivatorService.getCultivatorsByKathaNo(cultivator);

		List<Cultivator> cultivatorsList = allCultiVatorsList.stream().filter(c -> c.getCultivatorType() != null)
				.collect(Collectors.toList());

		List<Cultivator> ownersList = allCultiVatorsList.stream().map(pc -> {
			Float availableExtent = 0.00f;
			if ("O".equals(pc.getOwner_tenant())) {
				availableExtent = pc.getAnubhavadarExtent() == null ? 0.00f : pc.getAnubhavadarExtent();
				if ("O".equals(pc.getCultivatorType())) {
					availableExtent = availableExtent - pc.getOccupantExtent();
				} else if ("L".equals(pc.getCultivatorType())) {
					availableExtent = availableExtent - pc.getOccupantExtent();
				}

				for (Cultivator cc : cultivatorsList) {
					if ("K".equals(cc.getCultivatorType()) && pc.getBookingId().equals(cc.getRefBookingId())) {
						availableExtent = availableExtent - cc.getOccupantExtent();
					}
				}
			}
			pc.setAvailableExtent(Float.valueOf(String.format("%1.4f", availableExtent)));
			return pc;
		}).filter(c -> "O".equalsIgnoreCase(c.getOwner_tenant())).collect(Collectors.toList());

		model.addAttribute("ownersList", ownersList);

		model.addAttribute("cultivatorsList",
				allCultiVatorsList.stream().filter(c -> c.getCultivatorType() != null).collect(Collectors.toList()));

		model.addAttribute("cultivator", new Cultivator());

		return "addupdatecultivator";
	}

	@PostMapping("/cultivator/save")
	public String saveCultivatorsData(Cultivator cultivator, Model model) {

		cultivatorService.saveCultivatorsData(cultivator);
		List<Cultivator> cultiVatorsList = cultivatorService.getCultivatorsByKathaNo(cultivator);
		model.addAttribute("ownersList", cultiVatorsList.stream().filter(c -> "O".equalsIgnoreCase(c.getOwner_tenant()))
				.collect(Collectors.toList()));

		model.addAttribute("cultivatorsList",
				cultiVatorsList.stream().filter(c -> c.getCultivatorType() != null).collect(Collectors.toList()));

		model.addAttribute("cultivator", new Cultivator());

		return "addupdatecultivator";

	}

	@PutMapping("/cultivator/owner/update")
	public String updateOwnerOrEnjoerDetails(Cultivator cultivator, RedirectAttributes redirectAttributes) {

		cultivatorService.updateOwnerOrEnjoerDetails(cultivator);

		return "addupdatecultivator";

	}

	@PutMapping("/cultivator/update")
	public String updateCultivatorDetails(Cultivator cultivator, RedirectAttributes redirectAttributes) {

		cultivatorService.updateCultivatorDetails(cultivator);

		return "addupdatecultivator";

	}

	@DeleteMapping("/cultivator/delete")
	public String deleteCultivatorDetails(Cultivator cultivator, RedirectAttributes redirectAttributes) {

		cultivatorService.deleteCultivatorDetails(cultivator);

		return "addupdatecultivator";

	}

	@GetMapping("/cultivator/extent")
	public String getAnubhavadarAndOccupantExtent(Cultivator cultivator, Model model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		Float anubhavadarExtent = cultivatorService.getAnubhavadarExtent(cultivator);

		Float totalOccupantExtent = cultivatorService.getTotalOccupantExtent(cultivator);

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		cultivator.setAnubhavadarExtent(anubhavadarExtent);
		cultivator.setOccupantExtent(totalOccupantExtent);

		String jsonMap = mapper.writeValueAsString(cultivator);

		response.setContentType("json");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(jsonMap);
			out.flush();
			return null;
		} catch (Exception e) {
		}

		return null;
	}

}
