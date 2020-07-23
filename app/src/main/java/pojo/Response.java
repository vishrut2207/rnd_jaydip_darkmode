package pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {

	@SerializedName("EmployeeDetails")
	private List<EmployeeDetailsItem> employeeDetails;

	public void setEmployeeDetails(List<EmployeeDetailsItem> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public List<EmployeeDetailsItem> getEmployeeDetails() {
		return employeeDetails;
	}

	@Override
	public String toString() {
		return
				"Response{" +
						"employeeDetails = '" + employeeDetails + '\'' +
						"}";
	}
}