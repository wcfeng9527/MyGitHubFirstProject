/**
 * 加价规则表对象
 */
package com.shijie99.model;

import java.sql.Timestamp;

/**
 * @author ThinkPad
 *
 */
public class PriceAddAmadeus implements Comparable<PriceAddAmadeus>,java.io.Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer policyid;
	private Integer pstatus;
	private String departureCode;
	private String arrivalCode;
	private String airlineCode;
	private Double segmentfee;
	private Double bizFirmAmount;
	private Double bizPercentAmount;
	private Double taxFirmAmount;
	private Double taxPercentAmount;
	private Double policyFirmAmount;
	private Double policyPercentAmountBefore;
	private Double policyPercentAmountAfter;
	private Double ticketAmount;
	private String pcurrencycode;
	private String tcurrencycode;
	private Double minlirun;
	private Double jianjia;
	private String frm;
	private String vendor;
	private String cid;
	private String startdate;
	private String enddate;
	private String cabins;
	private String flightnumbers;
	private String flighttype;
	private Integer share;
	private String cpf;
	private String cprules;
	private String remarks;
	private Integer precision;
	private Timestamp lastdate;
	private String pricetype;
	private Integer level;
	private String from;
	private String to;
	/**
	 * 
	 */
	public PriceAddAmadeus() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	public String getArrivalCode() {
		return arrivalCode;
	}

	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public Double getBizPercentAmount() {
		return bizPercentAmount;
	}

	public void setBizPercentAmount(Double bizPercentAmount) {
		this.bizPercentAmount = bizPercentAmount;
	}

	public Double getTaxPercentAmount() {
		return taxPercentAmount;
	}

	public void setTaxPercentAmount(Double taxPercentAmount) {
		this.taxPercentAmount = taxPercentAmount;
	}

	public Double getMinlirun() {
		return minlirun;
	}

	public void setMinlirun(Double minlirun) {
		this.minlirun = minlirun;
	}

	public String getFrm() {
		return frm;
	}

	public void setFrm(String frm) {
		this.frm = frm;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getCabins() {
		return cabins;
	}

	public void setCabins(String cabins) {
		this.cabins = cabins;
	}
	
	public Double getJianjia() {
		return jianjia;
	}

	public void setJianjia(Double jianjia) {
		this.jianjia = jianjia;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}	

	public String toString() {
	
		return super.toString();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCprules() {
		return cprules;
	}

	public void setCprules(String cprules) {
		this.cprules = cprules;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFlightnumbers() {
		return flightnumbers;
	}

	public void setFlightnumbers(String flightnumbers) {
		this.flightnumbers = flightnumbers;
	}

	public String getFlighttype() {
		return flighttype;
	}

	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	@Override
	public PriceAddAmadeus clone() {
		
		PriceAddAmadeus p = null;
		try {
			p = (PriceAddAmadeus)super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public Timestamp getLastdate() {
		return lastdate;
	}

	public void setLastdate(Timestamp lastdate) {
		this.lastdate = lastdate;
	}

	public Integer getPolicyid() {
		return policyid;
	}

	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}

	public Double getPolicyPercentAmountBefore() {
		return policyPercentAmountBefore;
	}
	public void setPolicyPercentAmountBefore(Double policyPercentAmountBefore) {
		this.policyPercentAmountBefore = policyPercentAmountBefore;
	}

	public Double getPolicyPercentAmountAfter() {
		return policyPercentAmountAfter;
	}

	public void setPolicyPercentAmountAfter(Double policyPercentAmountAfter) {
		this.policyPercentAmountAfter = policyPercentAmountAfter;
	}

	public String getPcurrencycode() {
		return pcurrencycode;
	}

	public void setPcurrencycode(String pcurrencycode) {
		this.pcurrencycode = pcurrencycode;
	}

	public String getTcurrencycode() {
		return tcurrencycode;
	}

	public void setTcurrencycode(String tcurrencycode) {
		this.tcurrencycode = tcurrencycode;
	}

	public Integer getPstatus() {
		return pstatus;
	}

	public void setPstatus(Integer pstatus) {
		this.pstatus = pstatus;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getPricetype() {
		return pricetype;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Double getBizFirmAmount() {
		return bizFirmAmount;
	}

	public void setBizFirmAmount(Double bizFirmAmount) {
		this.bizFirmAmount = bizFirmAmount;
	}

	public Double getTaxFirmAmount() {
		return taxFirmAmount;
	}

	public void setTaxFirmAmount(Double taxFirmAmount) {
		this.taxFirmAmount = taxFirmAmount;
	}

	public Double getPolicyFirmAmount() {
		return policyFirmAmount;
	}

	public void setPolicyFirmAmount(Double policyFirmAmount) {
		this.policyFirmAmount = policyFirmAmount;
	}

	public Double getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	
	
	public Double getSegmentfee() {
		return segmentfee;
	}

	public void setSegmentfee(Double segmentfee) {
		this.segmentfee = segmentfee;
	}

	@Override
	public int compareTo(PriceAddAmadeus pa) {
		return this.getLevel().compareTo(pa.getLevel());
	}
	
	
	
}
