package jahv.jpahibernate.ch5;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class VacationEntryEmbeddable {

	@Column(name = "startDate", columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	private Integer daysTaken;

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the daysTaken
	 */
	public Integer getDaysTaken() {
		return daysTaken;
	}

	/**
	 * @param daysTaken the daysTaken to set
	 */
	public void setDaysTaken(final Integer daysTaken) {
		this.daysTaken = daysTaken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VacationEntryEmbeddable [startDate=" + startDate + ", daysTaken=" + daysTaken + "]";
	}

}
