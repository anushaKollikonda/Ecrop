package com.ecrops.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecrops.entity.AllocatedSurveyNoMapping;
import com.ecrops.entity.AuthMAOvaaVroEkyc;
import com.ecrops.entity.CropBookingDetailsMaoIntf;
import com.ecrops.entity.CropwiseExtBookedRBKwise;
import com.ecrops.entity.DataSourceWiseBookingReport;
import com.ecrops.entity.FarmerBookingDetails;
import com.ecrops.entity.MaoAuthVaaVroekyc;
import com.ecrops.entity.MaoSocialAuditcorrection;
import com.ecrops.entity.NormalAreasMwiseMao;
import com.ecrops.entity.PhyAckVwise;
import com.ecrops.entity.RbkSurveyNoMapping;
import com.ecrops.entity.RbkSurveyNoMappingDrpdwn;
import com.ecrops.entity.RepLandDataDetails;
import com.ecrops.entity.Rep_DownloadedDetailsIntf;
import com.ecrops.entity.Rep_phy_ack_rbk;
import com.ecrops.entity.RofrBookedExtent;
import com.ecrops.entity.RofrBookedExtentPartitions;
import com.ecrops.entity.SeasonCropBookedExtent;
import com.ecrops.entity.SpuperChkAppr;
import com.ecrops.entity.SuperCheckRecordsAlloted;
import com.ecrops.entity.SuperChkReport;
import com.ecrops.entity.SuperChk_rejReport;
import com.ecrops.entity.Superchekupdstatus;
import com.ecrops.model.RequestModel;
import com.ecrops.partitions.AllocatedSurveyNoMappingPartition;
import com.ecrops.partitions.AuthMAOvaaVroEkycPartition;
import com.ecrops.partitions.CropBookingDetailsMaoIntfPartition;
import com.ecrops.partitions.CropwiseExtBookedRBKwisePartition;
import com.ecrops.partitions.DataSourceWiseBookingReportPartitions;
import com.ecrops.partitions.PhyAckVwisePartition;
import com.ecrops.partitions.RbkSurveyNoMappingDrpdwnPartitions;
import com.ecrops.partitions.Rep_DownloadedDetailsIntfPartition;
import com.ecrops.partitions.Rep_phy_ack_rbkPartition;
import com.ecrops.partitions.SeasonCropBookedExtentPartition;
import com.ecrops.partitions.SpuperChkApprPartition;
import com.ecrops.partitions.SuperCheckRecordsAllotedPartition;
import com.ecrops.partitions.SuperChkReportPartition;
import com.ecrops.partitions.SuperChk_rejReportPartition;
import com.ecrops.partitions.SuperchekupdstatusPartition;
import com.ecrops.projections.MasterProjections;
import com.ecrops.repo.AllocataedSurveyNoMappingRepo;
import com.ecrops.repo.FarmerBookingDetailsPartitions;
import com.ecrops.repo.FarmerBookingDetailsRepo;
import com.ecrops.repo.MaoAuthVaaVroekycPartition;
import com.ecrops.repo.MaoAuthVaaVroekycRepo;
import com.ecrops.repo.MaoSocialAuditcorrectionRepo;
import com.ecrops.repo.NormalAreasMwiseMaoRepo;
import com.ecrops.repo.RbkSurveyNoMappingPartition;
import com.ecrops.repo.RbkSurveyNoMappingRepo;
import com.ecrops.repo.RepLandDataDetailsRepo;
import com.ecrops.repo.RofrBookedExtentRepo;
import com.ecrops.repo.RofrBookedExtentRepo.EfishDetailsC;
import com.ecrops.repo.RofrBookedExtentRepo.EfishDetailsR;
import com.ecrops.repo.RofrBookedExtentRepo.FarmerDetails;
import com.ecrops.repo.SeasonCropBookedExtentRepo;
import com.ecrops.repo.SeasonCropBookedExtentRepo.BlockedEfishExtent;
import com.ecrops.repo.SeasonCropBookedExtentRepo.CropInsGrievance;
import com.ecrops.repo.SeasonCropBookedExtentRepo.DeviceRegDetails;
import com.ecrops.repo.SeasonCropBookedExtentRepo.EmployeeList;
import com.ecrops.repo.SeasonCropBookedExtentRepo.NonWebView;
import com.ecrops.repo.SeasonCropBookedExtentRepo.ObjUnobj;
import com.ecrops.repo.SeasonCropBookedExtentRepo.RepVaaDetails;
import com.ecrops.util.MasterFunctions;

@RestController

@RequestMapping("/util")
public class UtilRestController {
	@Autowired
	SeasonCropBookedExtentRepo seasonCropBookedExtentRepo;

	@Autowired
	private NormalAreasMwiseMaoRepo normalAreasMwiseMaoRepo;
	@Autowired
	private FarmerBookingDetailsRepo farmerBookingDetailsRepo;
	@Autowired
	private FarmerBookingDetailsPartitions partition;
	@Autowired
	private RofrBookedExtentPartitions rofrBookedExtentPartitions;
	@Autowired
	private MaoAuthVaaVroekycRepo maoAuthVaaVroekycRepo;
	@Autowired
	private MaoAuthVaaVroekycPartition maoAuthVaaVroekycPartition;
	@Autowired
	private RbkSurveyNoMappingRepo rbkSurveyNoMappingRepo;

	@Autowired
	private RbkSurveyNoMappingPartition rbkSurveyNoMappingPartition;

	@Autowired
	private AllocataedSurveyNoMappingRepo allocataedSurveyNoMappingRepo;

	@GetMapping("/getAllSeason")
	public List<MasterProjections> getAllSeasonn() {
		List<MasterProjections> list = normalAreasMwiseMaoRepo.getAllSeason();
		return list;
	}

	@GetMapping("/getAllCrop")
	public List<MasterProjections> getAllCrop() {
		List<MasterProjections> list = maoAuthVaaVroekycRepo.getAllCrops();
		return list;
	}

	@GetMapping("/getAllMandals")
	public List<MasterProjections> getMandals(Integer dcode) {
		List<MasterProjections> list = seasonCropBookedExtentRepo.getAllMandals(dcode);
		return list;
	}

//**************************************************//Villages//*************************************************
	@GetMapping("/getAllVillages")
	public List<MasterProjections> getVillages(Integer dcode, Integer mcode) {
		System.out.println("dcode=>" + dcode);
		System.out.println("mcode=>" + mcode);
		List<MasterProjections> list = seasonCropBookedExtentRepo.getAllVillages(dcode, mcode);
		return list;
	}
//===========================CropGroups=================================//

	@GetMapping("/getCropGroup")
	public List<MasterProjections> getCropGroup() {
		System.out.println("=======getCropGroup==========");
		List<MasterProjections> list = seasonCropBookedExtentRepo.getAllCropGrp();
		System.out.println("=======list==========" + list.size());
		return list;
	}

	// ===============getAllCrpGrp=====================//
	@GetMapping("/getCropGroupid")
	public List<MasterProjections> getCropGoupidd(String grpcode) {
		List<MasterProjections> list = seasonCropBookedExtentRepo.getAllCrpGrpid(Integer.parseInt(grpcode));
		System.out.println("=======list==========" + list.size());
		return list;
	}

	// ==================Normal Area Report====================//
	@GetMapping("/getNormarlAreaReport")
	List<NormalAreasMwiseMao> getList(String dcode, String mcode, String cropyear) {

		String[] season = cropyear.split("@");
		String seasonType = season[0];
		Integer seasonYear = Integer.parseInt(season[1]);
		System.out.println("seasonType=>" + seasonType);
		System.out.println("seasonYear=>" + seasonYear);

		Integer ddcode = Integer.parseInt(dcode);
		Integer mmcode = Integer.parseInt(mcode);

		System.out.println("dcode=>" + dcode);
		System.out.println("mcode=>" + mcode);
		System.out.println("cropyear=>" + cropyear);

		List<NormalAreasMwiseMao> listt = normalAreasMwiseMaoRepo.getListt(ddcode, mmcode, seasonType, seasonYear);
		System.out.println("list size=>" + listt.size());
		System.out.println("list =>" + listt.toString());
		return listt;
	}

// <-------------FARMER BOOKING DETAILS----------->
	@GetMapping("/getFbDetails")
	List<FarmerBookingDetails> getFarmers(String dcode, String mcode, String cropyear, HttpSession session,
			String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		Integer ddcode = Integer.parseInt(dcode);
		Integer mmcode = Integer.parseInt(mcode);
		String wbdcode = (String) session.getAttribute("wbdcode");

		List<FarmerBookingDetails> Farmerlist = partition.farmerBookingDetails(dcode, mcode, year, wbdcode);

		return Farmerlist;
	}

	@GetMapping("/getFbDetails1")
	List<FarmerBookingDetails> getFbDetails(String dcode, String mcode, String cropyear, String wbdcode,
			String userid) {
//		System.out.println("dcode===================>" + dcode);
//		System.out.println("mcode===================>" + mcode);
//		System.out.println("cropyear===================>" + cropyear);
//		System.out.println("wbdcode===================>" + wbdcode);

		List<FarmerBookingDetails> list = partition.farmerBookingDetails(dcode, mcode, cropyear, wbdcode);
		System.out.println("details===================>" + list.size());

		return list;
	}

	// <------------------ROFR BOOKED EXTENT----------------->//

	@GetMapping("/getRofr")
	List<RofrBookedExtent> getRbExt(String wbcode, String wbmcode, String cropyear, HttpSession session,
			String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		// Integer ddcode = Integer.parseInt(dcode);
		// Integer mmcode = Integer.parseInt(mcode);
		String wbdcode = (String) session.getAttribute("wbdcode");

		List<RofrBookedExtent> rofrList = rofrBookedExtentPartitions.rofrbext(wbdcode, wbmcode, year);

		return rofrList;
	}

	@GetMapping("/getRofr1")
	List<RofrBookedExtent> getRext(String wbdcode, String wbmcode, String cropyear, String userid) {
//		System.out.println("dcode===================>" + dcode);
//		System.out.println("mcode===================>" + mcode);
//		System.out.println("cropyear===================>" + cropyear);
//		System.out.println("wbdcode===================>" + wbdcode);

		List<RofrBookedExtent> rofr = rofrBookedExtentPartitions.rofrbext(wbdcode, wbmcode, cropyear);
		System.out.println("details===================>" + rofr.size());

		return rofr;
	}

	// <------------------MaoAuthVaaVroEkyc----------------->//
	@GetMapping("/getekyc")
	List<MaoAuthVaaVroekyc> getEkyc(String wbdcode, String cropyear, HttpSession session, String mcode, String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		// Integer ddcode = Integer.parseInt(dcode);
		// Integer mmcode = Integer.parseInt(mcode);
		// String wbdcode = (String) session.getAttribute("wbdcode");

//			List<MaoAuthVaaVroekyc> ekycList = maoAuthVaaVroekycPartition.vaaVroEkyc(wbdcode, year,mcode);

		return null;
	}

	@GetMapping("/getekyc1")
	List<MaoAuthVaaVroekyc> getEkycVaaVro(String wbdcode, String cropyear, String mcode, String userid, String cropid) {
		System.out.println("wbdcode===================>" + wbdcode);
		System.out.println("mcode===================>" + mcode);
		System.out.println("cropyear===================>" + cropyear);
		System.out.println("userid===================>" + userid);
		System.out.println("cropid===================>" + cropid);

		List<MaoAuthVaaVroekyc> ekyc = maoAuthVaaVroekycPartition.vaaVroEkyc(wbdcode, mcode, cropyear, cropid);
		System.out.println("details===================>" + ekyc.size());

		return ekyc;
	}

	// <------------------MaoRbkSurveyNoMapping----------------->//

	@GetMapping("/rbk")
	List<RbkSurveyNoMapping> getSurveyNo(String wbdcode, String cropyear, HttpSession session, String mcode,
			String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];

		// Integer ddcode = Integer.parseInt(dcode);
		// Integer mmcode = Integer.parseInt(mcode);
		// String wbdcode = (String) session.getAttribute("wbdcode");

		// List<MaoAuthVaaVroekyc> ekycList =
		// maoAuthVaaVroekycPartition.vaaVroEkyc(wbdcode, year,mcode);

		return null;
	}

	@GetMapping("/rbk1")
	List<RbkSurveyNoMapping> getRbkSno(String wbdcode, String cropyear, String mcode, String userid, String updatedby) {

		System.out.println("wbdcode===================>" + wbdcode);
		System.out.println("mcode===================>" + mcode);
		System.out.println("cropyear===================>" + cropyear);
		System.out.println("userid===================>" + userid);

		List<RbkSurveyNoMapping> sno = rbkSurveyNoMappingPartition.rbkSno(wbdcode, mcode, cropyear, userid, userid);
		System.out.println("details===================>" + sno.size());

		return sno;
	}

	// <------------------AllocatedSurveyNoMapping----------------->//
	@Autowired
	private AllocatedSurveyNoMappingPartition allocatedSurveyNoMappingPartition;
	@Autowired
	private RbkSurveyNoMappingDrpdwnPartitions rbkSurveyNoMappingDrpdwnPartitions;

	@GetMapping("/drpd")
	List<RbkSurveyNoMappingDrpdwn> getRbkdrpd(String mcode, String cropyear, String wbdcode, String rbkcode,
			HttpSession session, HttpServletRequest request) {
		System.out.println("mcode==========>" + mcode);
		System.out.println("cropyear==========>" + cropyear);
		System.out.println("wbdcode==========>" + wbdcode);
		System.out.println("rbkcode==========>" + rbkcode);

//		String[] season = cropyear.split("@");
//		String seasonType = season[0];
//		String year = season[1];
//		Integer seasonYear = Integer.parseInt(season[1]);
		cropyear = request.getParameter("cropyear");
		// mcode= (String) session.getAttribute("mcode");
		// wbdcode= (String) session.getAttribute("wbdcode");
		// rbkcode= (String) session.getAttribute("rbkcode");

		List<RbkSurveyNoMappingDrpdwn> aldw = rbkSurveyNoMappingDrpdwnPartitions.getRBK(mcode, cropyear, wbdcode,
				rbkcode);
		System.out.println("details------>" + aldw.size());

		return aldw;
	}

	@GetMapping("/asnom")
	List<AllocatedSurveyNoMapping> getAlcSurveyNo(String wbdcode, String cropyear, HttpSession session, String mcode,
			String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		return null;
	}

	@GetMapping("/asnom1")
	List<AllocatedSurveyNoMapping> getAllocSnoMapping(String wbdcode, String cropyear, String mcode, String userid,
			String rbkid) {

		System.out.println("/asnom1");
		System.out.println("wbdcode===================>" + wbdcode);
		System.out.println("mcode===================>" + mcode);
		System.out.println("cropyear===================>" + cropyear);
		System.out.println("userid===================>" + userid);
		System.out.println("rbkid===================>" + rbkid);

		List<AllocatedSurveyNoMapping> asno = allocatedSurveyNoMappingPartition.allocatedSnoDetails(wbdcode, cropyear,
				mcode, userid, rbkid);
		System.out.println("details===================>" + asno.size());
		return asno;
	}

	// *******// DataSourceWiseBookingExtent//*******//
	@Autowired
	DataSourceWiseBookingReportPartitions dataSourceWiseBookingReportPartitions;

	@GetMapping("/dtsrcb")
	List<DataSourceWiseBookingReport> dataSrcDet(String wbdcode, String cropyear, HttpSession session, String wbmcode,
			String userid) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		return null;
	}

	@GetMapping("/dtsrcb1")
	List<DataSourceWiseBookingReport> dataSrcDet(String wbdcode, String cropyear, String wbmcode, String userid) {

		System.out.println("wbdcode===================>" + wbdcode);
		System.out.println("cropyear===================>" + cropyear);
		System.out.println("userid===================>" + userid);

		List<DataSourceWiseBookingReport> dsb = dataSourceWiseBookingReportPartitions.dataSrcDet(wbdcode, cropyear,
				wbmcode, userid);
		System.out.println("details===================>" + dsb.get(0));
		return dsb;
	}

	// **********************efishDetails***************************
	@Autowired
	private RofrBookedExtentRepo rofrBookedExtentRepo;

	@GetMapping("/efishDetails")
	public ResponseEntity<?> efishDetails(@RequestParam("dataSrc") String dataSrc,

			@RequestParam("dcode") String dcode, @RequestParam("mcode") String mcode) {
		if (dataSrc.equalsIgnoreCase("R")) {
			List<EfishDetailsR> efishDetailsR = rofrBookedExtentRepo.efishDetailsR(dataSrc, Integer.parseInt(dcode),
					Integer.parseInt(mcode));
			return new ResponseEntity<List<EfishDetailsR>>(efishDetailsR, HttpStatus.OK);
		}
		if (dataSrc.equalsIgnoreCase("C")) {
			List<EfishDetailsC> efishDetailsC = rofrBookedExtentRepo.efishDetailsC(Integer.parseInt(dcode),
					Integer.parseInt(mcode));
			return new ResponseEntity<List<EfishDetailsC>>(efishDetailsC, HttpStatus.OK);
		}
		return null;
	}

	// **************** Farmer Details****************
	@GetMapping("/farmerdet")
	public ResponseEntity<?> farmerdet(@RequestParam("mcode") String mcode, @RequestParam("date") String date) {
		List<FarmerDetails> farmerDetails = rofrBookedExtentRepo.farmerDetails(Integer.parseInt(mcode), date);
		return new ResponseEntity<List<FarmerDetails>>(farmerDetails, HttpStatus.OK);
	}

	// **************************************************************************//
	@Autowired
	SeasonCropBookedExtentPartition seasonCropBookedExtentPartition;

	@GetMapping("/allcrp1")
	List<SeasonCropBookedExtent> getAllCrop(String wbmcode, String cropyear, String userid, String wbdcode) {

		System.out.println("wbmcode===================>" + wbmcode);
		System.out.println("cropyear===================>" + cropyear);
		System.out.println("userid===================>" + userid);

		List<SeasonCropBookedExtent> scp = seasonCropBookedExtentPartition.getAllCrops(wbmcode, cropyear, userid,
				wbdcode);
		System.out.println("details===================>" + scp.get(0));
		return scp;
	}
//=============================BLOCKED e-fish EXTENT============================//

	@GetMapping("/blockedext")
	public ResponseEntity<?> blockedext(@RequestParam("dcode") String dcode, @RequestParam("mcode") String mcode,
			@RequestParam("vcode") String wbvcode) {
		System.out.println("blockedext");
		System.out.println("dcode=>" + dcode);
		System.out.println("mcode=>" + mcode);
		System.out.println("wbvcode=>" + wbvcode);

		Integer parsedDcode = Integer.parseInt(dcode);
		Integer parsedMcode = Integer.parseInt(mcode);
		Integer parsedWbvcode = Integer.parseInt(wbvcode);

		@SuppressWarnings("unchecked")
		List<BlockedEfishExtent> blockedExtent = seasonCropBookedExtentRepo.findByExtent(parsedDcode, parsedMcode,
				parsedWbvcode);

		return new ResponseEntity<>(blockedExtent, HttpStatus.OK);
	}

//===========OBJ-UNOBJ//

	@GetMapping("/objunobj1")
	public ResponseEntity<?> getObj() {
		List<ObjUnobj> Objunobj = seasonCropBookedExtentRepo.getObjObj();

		return new ResponseEntity<List<ObjUnobj>>(Objunobj, HttpStatus.OK);
	}

///======================EMP LIST=======================///
	@GetMapping("/emplist")
	public ResponseEntity<?> getEmp(@RequestParam("dcode") String dcode, @RequestParam("mcode") String mcode) {
		List<EmployeeList> emplist = seasonCropBookedExtentRepo.getEmpList(Integer.parseInt(dcode),
				Integer.parseInt(mcode));
		return new ResponseEntity<List<EmployeeList>>(emplist, HttpStatus.OK);
	}

	@GetMapping("/vaadet")
	public ResponseEntity<?> getVAADet(@RequestParam("mcode") String mcode, String userid) {
		List<RepVaaDetails> vaadet = seasonCropBookedExtentRepo.getVaaDet(mcode);
		return new ResponseEntity<List<RepVaaDetails>>(vaadet, HttpStatus.OK);
	}

	@GetMapping("/devregdet1")
	List<DeviceRegDetails> getCropyear(HttpSession session, String cropyear) {
		String[] season = cropyear.split("@");
		String seasonType = season[0];
		String year = season[1];
		Integer seasonYear = Integer.parseInt(season[1]);

		return null;
	}

//--------------------------------- Device Reg Details-----------------------------------------------//
	@PostMapping("/devregdet")
	public ResponseEntity<?> getDevRegDetails(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		List<DeviceRegDetails> devregdet;
		try {
			String[] season = requestModel.getCropyear().split("@");
			// System.out.println("season========="+season);
			String seasonType = season[0];
			// System.out.println("seasonType========="+seasonType);
			Integer seasonYear = Integer.parseInt(season[1]);
			// System.out.println("seasonYear========="+seasonYear);

			devregdet = seasonCropBookedExtentRepo.getDevRegDet(requestModel.getMcode(), seasonYear, seasonType);
			System.out.println("devregdet size=>" + devregdet.size());
			return new ResponseEntity<List<DeviceRegDetails>>(devregdet, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("getStackTrace =>" + e.getStackTrace());
		}
		return null;

	}

//---------------------------------- Crop Insurence Grievance---------------------------------------------//
	@PostMapping("/crpinsgri")
	public ResponseEntity<?> getCropInsGri(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		try {
			String[] season = requestModel.getCropyear().split("@");
			System.out.println("season=========" + season);
			String cseason = season[0];
			System.out.println("seasonType=========" + cseason);
			Integer Year = Integer.parseInt(season[1]);
			System.out.println("seasonYear=========" + Year);

			List<CropInsGrievance> crpins = seasonCropBookedExtentRepo.getCropIns(
					Integer.parseInt(requestModel.getDcode()), Integer.parseInt(requestModel.getMcode()), Year,
					cseason);
			System.out.println("crpins size=>" + crpins.size());
			return new ResponseEntity<List<CropInsGrievance>>(crpins, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("getStackTrace =>" + e.getStackTrace());
			return null;
		}
	}

//================================ SperCheck Allotment Records==========================================//
	@Autowired
	private SuperCheckRecordsAllotedPartition superCheckRecordsAllotedPartition;

	@PostMapping("/supchkalltrecrds")
	List<SuperCheckRecordsAlloted> getSupChkR(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		List<SuperCheckRecordsAlloted> spckr = superCheckRecordsAllotedPartition.getSupchkRds(requestModel.getWbdcode(),
				requestModel.getWbmcode(), requestModel.getUserid(), requestModel.getCropyear());
		System.out.println("details===================>" + spckr.size());
		return spckr;
	}

//================SpuperCheck APPR Report==================//
	@Autowired
	SpuperChkApprPartition spuperChkApprPartition;

	@PostMapping("/supchapprintf")
	List<SpuperChkAppr> getSupChkAppr(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		List<SpuperChkAppr> spcapr = spuperChkApprPartition.getSupchkAppr(requestModel.getWbdcode(),
				requestModel.getWbmcode(), requestModel.getUserid(), requestModel.getCropyear());
		System.out.println("details===================>" + spcapr.size());
		return spcapr;
	}
//=========================SuperCheck Report===============================//

	@Autowired
	SuperChkReportPartition superChkReportPartition;

	@PostMapping("/supcheckReport")
	List<SuperChkReport> getSupChk(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		List<SuperChkReport> supkr = superChkReportPartition.getSupchkRep(requestModel.getWbdcode(),
				requestModel.getWbmcode(), requestModel.getUserid(), requestModel.getCropyear());
		System.out.println("details===================>" + supkr.size());
		return supkr;
	}
//========================== SuperCheck Rejected===============================//

	@Autowired
	SuperChk_rejReportPartition superChk_rejReportPartition;

//@Autowired MasterFunctions masterFunctions;

	@PostMapping("/supchkrej")
	List<SuperChk_rejReport> getSupChkRejrReport(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		// masterFunctions.getCropImageMao(null, null, null, null, null, null)

		List<SuperChk_rejReport> supkrej = superChk_rejReportPartition.getSupchkRej(requestModel.getWbdcode(),
				requestModel.getWbmcode(),
				// requestModel.getUserid(),
				requestModel.getCropyear());
		System.out.println("details===================>" + supkrej.size());
		return supkrej;
	}
	// ================CropBookingDetailsMaoIntf====================//

	@Autowired
	CropBookingDetailsMaoIntfPartition cropBookingDetailsMaoIntfPartition;

	@Autowired
	MasterFunctions masterFunctions;

	@PostMapping("/crpmao")
	List<CropBookingDetailsMaoIntf> getCropdetMao(@RequestBody RequestModel requestModel) throws SQLException {
		System.out.println("requestModel=>" + requestModel.toString());

		List<CropBookingDetailsMaoIntf> crdbmao = cropBookingDetailsMaoIntfPartition.getCropDetailsMao(
				requestModel.getWbdcode(), requestModel.getDcode(), requestModel.getWbmcode(), requestModel.getVcode(),
				requestModel.getCropyear(), requestModel.getCrop());
		System.out.println("details===================>" + crdbmao.size());
		return crdbmao;
	}

	// ================PhyAckVwise====================//
	@Autowired
	PhyAckVwisePartition phyAckVwisePartition;

	@PostMapping("/phyack")
	List<PhyAckVwise> getPhyAckVwise(@RequestBody RequestModel requestModel) throws SQLException {
		System.out.println("requestModel=>" + requestModel.toString());

		List<PhyAckVwise> phyack = phyAckVwisePartition.getPhyAck(requestModel.getWbdcode(), requestModel.getWbmcode(),
				requestModel.getCropyear(), requestModel.getUserid());
		return phyack;
	}

	// ================ Rep_phy_ack_rbk====================//
	@Autowired
	Rep_phy_ack_rbkPartition rep_phy_ack_rbkPartition;

	@PostMapping("/phyrbk")
	List<Rep_phy_ack_rbk> getPhyAckRBK(@RequestBody RequestModel requestModel) throws SQLException {
		System.out.println("requestModel=>" + requestModel.toString());

		List<Rep_phy_ack_rbk> phyrbk = rep_phy_ack_rbkPartition.getPhyRbk(requestModel.getWbdcode(),
				requestModel.getWbmcode(), requestModel.getCropyear(), requestModel.getUserid());
		System.out.println("details===================>" + phyrbk.size());
		return phyrbk;
	}

//==========================Rep_DownloadedDetailsIntf===========================//
	@Autowired
	Rep_DownloadedDetailsIntfPartition rep_DownloadedDetailsIntfPartition;

	@PostMapping("/dwnlddet")
	List<Rep_DownloadedDetailsIntf> getDwnloadedDet(@RequestBody RequestModel requestModel, HttpSession httpSession)
			throws SQLException {
		System.out.println("requestModel=>" + requestModel.toString());
		System.out.println(httpSession.getAttribute("wbdname"));

		List<Rep_DownloadedDetailsIntf> dwnlddet = rep_DownloadedDetailsIntfPartition.getDwnLdDet(
				requestModel.getWbdcode(), requestModel.getWbmcode(), requestModel.getCropyear(),
				requestModel.getUserid());
		System.out.println("details===================>" + dwnlddet.size());
		return dwnlddet;
	}

//=====================RepLandDataDetails=========================//
	@Autowired
	RepLandDataDetailsRepo repLandDataDetailsRepo;

	@PostMapping("/landatam")
	List<RepLandDataDetails> getDwnloadedDet(@RequestBody RequestModel requestModel) throws SQLException {
		System.out.println("requestModel=>" + requestModel.toString());

		List<RepLandDataDetails> landata = repLandDataDetailsRepo.getLandDet(Integer.parseInt(requestModel.getDcode()),
				Integer.parseInt(requestModel.getMcode()));
		System.out.println("details===================>" + landata.size());
		return landata;
	}

	// ----------------------------------
	// NonWebView---------------------------------------//
	@PostMapping("/nonwebv")
	public ResponseEntity<?> getNonWebV(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		try {
			String[] season = requestModel.getCropyear().split("@");
			System.out.println("season=========" + season);
			String cseason = season[0];
			System.out.println("seasonType=========" + cseason);
			Integer Year = Integer.parseInt(season[1]);
			System.out.println("seasonYear=========" + Year);

			List<NonWebView> nonwebv = seasonCropBookedExtentRepo.getNonwebView(
					Integer.parseInt(requestModel.getDcode()), Integer.parseInt(requestModel.getMcode()), cseason,
					Year);
			System.out.println("crpins size=>" + nonwebv.size());
			return new ResponseEntity<List<NonWebView>>(nonwebv, HttpStatus.OK);
		} catch (Exception e) {

			System.out.println("getStackTrace =>" + e.getStackTrace());
			return null;
		}
	}

	// =====================CropwiseExtBookedRBKwise=======================//
	@Autowired
	CropwiseExtBookedRBKwisePartition cropwiseExtBookedRBKwisePartition;

	@PostMapping("/rbkext")
	public ResponseEntity<?> getRbkWise(@RequestBody RequestModel requestModel) {
		System.out.println("requestModel=>" + requestModel.toString());

		try {

			List<CropwiseExtBookedRBKwise> rbkext = cropwiseExtBookedRBKwisePartition.getBkExtRbk(
					requestModel.getDcode(), requestModel.getMcode(), requestModel.getCrop(),
					requestModel.getCropyear());
			System.out.println("crpins size=>" + rbkext.size());
			return new ResponseEntity<List<CropwiseExtBookedRBKwise>>(rbkext, HttpStatus.OK);
		} catch (Exception e) {

			System.out.println("getStackTrace =>" + e.getStackTrace());
			return null;
		}
	}
	
	
	// =====================AuthMAOvaaVroEkyc=======================//
		@Autowired
		AuthMAOvaaVroEkycPartition authMAOvaaVroEkycPartition;

		@PostMapping("/authMaoVaaVroEkyc")
		public ResponseEntity<?> getAuthEkyc(@RequestBody RequestModel requestModel) {
			System.out.println("requestModel=>" + requestModel.toString());

			try {

				List<AuthMAOvaaVroEkyc> maoauth = authMAOvaaVroEkycPartition.getAuthMaoVaaVroEkyc(
						 requestModel.getMcode(),
						requestModel.getCropyear());
				System.out.println("crpins size=>" + maoauth.size());
				return new ResponseEntity<List<AuthMAOvaaVroEkyc>>(maoauth, HttpStatus.OK);
			} catch (Exception e) {

				System.out.println("getStackTrace =>" + e.getStackTrace());
				return null;
			}
		}
		// =====================Superchekupdstatus=======================//
				@Autowired
				SuperchekupdstatusPartition superchekupdstatusPartition;

				@PostMapping("/supchkupdstatus")
				public ResponseEntity<?> getSupChkUpdSts(@RequestBody RequestModel requestModel) {
					System.out.println("requestModel=>" + requestModel.toString());

					try {
System.out.println("ASASDSADFSAFSD");
						List<Superchekupdstatus> supupds = superchekupdstatusPartition.getSupChkUpdSts(
								 requestModel.getWbdcode(),
								 requestModel.getWbmcode(),
								requestModel.getCropyear());
						System.out.println("crpins size=>" + supupds.size());
						return new ResponseEntity<List<Superchekupdstatus>>(supupds, HttpStatus.OK);
					} catch (Exception e) {

						System.out.println("getStackTrace =>" + e.getStackTrace());
						return null;
					}
				}
				
//===========================//MaoSocialAuditcorrectionRepo//================//
				@Autowired private MaoSocialAuditcorrectionRepo maoSocialAuditcorrectionRepo;
				
				@PostMapping("/maoSocialAudit")
				List<MaoSocialAuditcorrection> getSocialAuditCoorection(@RequestBody RequestModel requestModel) {
					System.out.println("requestModel=>" + requestModel.toString());
					
					String[] season = requestModel.getCropyear().split("@");
					String seasonType = season[0];
					Integer seasonYear = Integer.parseInt(season[1]);
					System.out.println("seasonType=>" + seasonType);
					System.out.println("seasonYear=>" + seasonYear);
					List<MaoSocialAuditcorrection> socialaudit = maoSocialAuditcorrectionRepo.getSocialAudit(
							Integer.parseInt(requestModel.getDcode()),
							Integer.parseInt( requestModel.getMcode()),
							seasonYear,
							seasonType);
					
					System.out.println("list size=>" + socialaudit.size());
					System.out.println("list =>" + socialaudit.toString());
					return socialaudit;
				}
}
