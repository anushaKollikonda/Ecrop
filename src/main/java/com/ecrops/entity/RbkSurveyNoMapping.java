package com.ecrops.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RbkSurveyNoMapping {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private String rbkuserid;
	private Integer mao_alloted_ext;
	private Integer vaa_alloted_ext;
	private Integer occupant_extent;
	public RbkSurveyNoMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RbkSurveyNoMapping(String rbkuserid, Integer mao_alloted_ext, Integer vaa_alloted_ext,
			Integer occupant_extent) {
		super();
		this.rbkuserid = rbkuserid;
		this.mao_alloted_ext = mao_alloted_ext;
		this.vaa_alloted_ext = vaa_alloted_ext;
		this.occupant_extent = occupant_extent;
	}
	public String getRbkuserid() {
		return rbkuserid;
	}
	public void setRbkuserid(String rbkuserid) {
		this.rbkuserid = rbkuserid;
	}
	public Integer getMao_alloted_ext() {
		return mao_alloted_ext;
	}
	public void setMao_alloted_ext(Integer mao_alloted_ext) {
		this.mao_alloted_ext = mao_alloted_ext;
	}
	public Integer getVaa_alloted_ext() {
		return vaa_alloted_ext;
	}
	public void setVaa_alloted_ext(Integer vaa_alloted_ext) {
		this.vaa_alloted_ext = vaa_alloted_ext;
	}
	public Integer getOccupant_extent() {
		return occupant_extent;
	}
	public void setOccupant_extent(Integer occupant_extent) {
		this.occupant_extent = occupant_extent;
	}
	@Override
	public String toString() {
		return "RbkSurveyNoMapping [rbkuserid=" + rbkuserid + ", mao_alloted_ext=" + mao_alloted_ext
				+ ", vaa_alloted_ext=" + vaa_alloted_ext + ", occupant_extent=" + occupant_extent + "]";
	}
	
	
	

}
