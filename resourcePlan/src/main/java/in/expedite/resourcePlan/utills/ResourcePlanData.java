package in.expedite.resourcePlan.utills;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.expedite.resourcePlan.entity.ResourcePlan;

public class ResourcePlanData {

	
	private Integer totalCell;
	
	@JsonFormat(pattern="yyyy-MM-dd") 
	private Date currentDate;
	
	private Integer currentPage;
	
	private List<ResourcePlan> data = new ArrayList<>();

	public Integer getTotalCell() {
		return totalCell;
	}

	public void setTotalCell(Integer totalCell) {
		this.totalCell = totalCell;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public List<ResourcePlan> getData() {
		return data;
	}

	public void setData(List<ResourcePlan> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResourcePlanData [totalCell=" + totalCell + ", currentDate=" + currentDate + ", currentPage="
				+ currentPage + ", data=" + data + "]";
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
