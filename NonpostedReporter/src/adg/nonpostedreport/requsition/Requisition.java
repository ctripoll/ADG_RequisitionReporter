/**
 * 
 */
package adg.nonpostedreport.requsition;

/**
 * Creates a requisition object for the purposes of comparing and storing each
 * requisition. Each requisition has a job code, title, location, status,
 * requested by, and requisition owners.
 * 
 * @author Courtney Ripoll
 *
 */
public class Requisition {
	/** Parameters for a requisition object */
	private String jobCode;
	private String title;
	private String location;
	private String status;
	private String requestedBy;
	private String reqOwners;

	/**
	 * Constructs a requisition object
	 * 
	 * @param jobCode
	 *            requisition job code
	 * @param title
	 *            requisition title
	 * @param location
	 *            requisition location
	 * @param status
	 *            requisition status
	 * @param requestedBy
	 *            who the requisition was requested by
	 * @param reqOwners
	 *            current requisition owners.
	 */
	public Requisition(String jobCode, String title, String location, String status, String requestedBy,
			String reqOwners) {
		setJobCode(jobCode);
		setTitle(title);
		setLocation(location);
		setStatus(status);
		setRequestedBy(requestedBy);
		setReqOwners(reqOwners);
	}

	/**
	 * Gets the job code
	 * 
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}

	/**
	 * Sets the job code
	 * 
	 * @param jobCode
	 *            the jobCode to set
	 */
	private void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * Gets the requisition title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the requisition title
	 * 
	 * @param title
	 *            the title to set
	 */
	private void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the requisition location
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the requisition location
	 * 
	 * @param location
	 *            the location to set
	 */
	private void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the requisition status
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the requisition status
	 * 
	 * @param status
	 *            the status to set
	 */
	private void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets who the requisition was requested by
	 * 
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * Sets who the requisition was requested by
	 * 
	 * @param requestedBy
	 *            the requestedBy to set
	 */
	private void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * Gets the list of requisition owners
	 * 
	 * @return the reqOwners
	 */
	public String getReqOwners() {
		return reqOwners;
	}

	/**
	 * Sets the list of requisition owners
	 * 
	 * @param reqOwners
	 *            the reqOwners to set
	 */
	private void setReqOwners(String reqOwners) {
		this.reqOwners = reqOwners;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobCode == null) ? 0 : jobCode.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((reqOwners == null) ? 0 : reqOwners.hashCode());
		result = prime * result + ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requisition other = (Requisition) obj;
		if (jobCode == null) {
			if (other.jobCode != null)
				return false;
		} else if (!jobCode.equals(other.jobCode))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (reqOwners == null) {
			if (other.reqOwners != null)
				return false;
		} else if (!reqOwners.equals(other.reqOwners))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
