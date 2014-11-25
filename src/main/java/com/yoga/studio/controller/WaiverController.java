/**
 * 
 */
package com.yoga.studio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yoga.studio.common.YogaConstant;
import com.yoga.studio.model.Faculty;
import com.yoga.studio.model.Waiver;
import com.yoga.studio.services.CustomerService;
import com.yoga.studio.services.FacultyService;
import com.yoga.studio.services.WaiverService;
import com.yoga.studio.web.FireAuthority;

/**
 * Faculty's Operation on Waiver
 * 
 * @author zhangle
 *
 */
@Controller
@RequestMapping("/waivers")
@SessionAttributes({ "user" })
public class WaiverController {
	@Autowired
	@Qualifier("waiverService")
	public WaiverService waiverService;
	@Autowired
	@Qualifier("facultyService")
	public FacultyService facultyService;
	@Autowired
	public CustomerService customerService;

	/**
	 * List waivers to Faculty who needs to confirm these waivers.
	 * 
	 * @param facultyID
	 * @param model
	 * @return pageName
	 */
	@FireAuthority
	@RequestMapping(value = "/list/{facultyID}", method = RequestMethod.GET)
	public String ListWaivers(@PathVariable("facultyID") Long facultyID,
			Map<String, Object> model) {
		Faculty faculty = new Faculty();
		faculty.setId(facultyID);
		List<Waiver> waiverList = waiverService.getWaiverByFaculty(faculty);
		model.put("waivers", waiverList);
		return "faculty/listWaivers";
	}

	/**
	 * Approve the particular waiver.
	 * 
	 * @param waiverID
	 * @param model
	 * @return pageName
	 */
	@FireAuthority
	@RequestMapping(value = "/status/approve/{waiverID}", method = RequestMethod.GET)
	public String approve(@PathVariable("waiverID") Long waiverID,
			Map<String, Object> model) {
		Waiver waiver = waiverService.findOne(waiverID.longValue());
		waiver.setWaiverStatus(YogaConstant.WAIVER_STATUS_APPRIVED);
		waiverService.update(waiver);
		// List<Waiver> waiverList = waiverService.findAll();
		// model.put("waivers", waiverList);
		// model.put("status", true);
		// model.put("objectid", waiverID.longValue());
		// return "faculty/listWaivers";
		return "redirect:/waivers/list/"
				+ String.valueOf(waiver.getCustomer().getFaculty().getId());
	}

	/**
	 * Reject particular waiver with the reason.
	 * 
	 * @param waiverID
	 * @param reason
	 * @param model
	 * @return pageName
	 */
	@FireAuthority
	@RequestMapping(value = "/status/reject", method = RequestMethod.POST)
	public String reject(@RequestParam(value = "rejectWaiverID") Long waiverID,
			@RequestParam(value = "reason") String reason,
			Map<String, Object> model) {
		Waiver waiver = waiverService.findOne(waiverID.longValue());
		waiver.setWaiverStatus(YogaConstant.WAIVER_STATUS_REJECTED);
		waiver.setReason(reason);
		waiverService.update(waiver);
		// List<Waiver> waiverList = waiverService.findAll();
		// model.put("waivers", waiverList);
		// model.put("status", true);
		// model.put("objectid", waiverID.longValue());
		// return "faculty/listWaivers";
		return "redirect:/waivers/list/"
				+ String.valueOf(waiver.getCustomer().getFaculty().getId());
	}
}
