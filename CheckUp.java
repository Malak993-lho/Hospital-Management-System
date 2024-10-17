package malakKhodorProject;

import java.util.Date;
import java.util.Objects;

public class CheckUp implements Comparable<CheckUp> {
	private Doctor doctor;
	private Patient patient;
	private int priority;
	private String Recomendationm;
	private Date date;
	private String time;

	private static int nextScheduleNumber = 1;
	private int scheduleNumber = 1;
	Object cu;

	public CheckUp(Doctor doctor, Patient patient, int priority,
			String Recomendationm, Date date, String time) {
		this.doctor = doctor;
		this.patient = patient;
		this.priority = priority;
		this.Recomendationm = Recomendationm;
		this.date = date;
		this.time = time;
		this.scheduleNumber = nextScheduleNumber++;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if (priority < 0) {
			throw new IllegalArgumentException("Priority cannot be negative.");
		}
		this.priority = priority;
	}

	public String getRecomendationm() {
		return Recomendationm;
	}

	public void setRecomendationm(String Recomendationm) {
		if (Recomendationm == null || Recomendationm.isEmpty()) {
			throw new IllegalArgumentException(
					"Recommendation cannot be null or empty.");
		}
		this.Recomendationm = Recomendationm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int compareTo(CheckUp other) {
		// First compare by date
		int dateComparison = this.date.compareTo(other.date);
		if (dateComparison != 0) {
			return dateComparison;
		}
		// If dates are the same, compare by priority (higher priority first)
		return Integer.compare(other.priority, this.priority);
	}
	@Override
	public String toString() {
		return String.format(
				"CheckUp (" + scheduleNumber + ") Details:\n"
						+ "-----------------\n" + "Doctor:       %s\n"
						+ "Patient:      %s\n" + "Priority:     %d\n"
						+ "Recommendation: %s\n" + "Date:         %s\n"
						+ "Time:         %s\n\n",
				doctor != null ? doctor.getName() : "N/A",
				patient != null ? patient.getName() : "N/A", priority,
				Recomendationm, date, time);
	}

	public void displaySummary() {
		System.out.println("CheckUp Summary:");
		System.out.println(
				"Doctor: " + (doctor != null ? doctor.getName() : "N/A"));
		System.out.println(
				"Patient: " + (patient != null ? patient.getName() : "N/A"));
		System.out.println("Priority: " + priority);
		System.out.println("Recommendation: " + Recomendationm);
		System.out.println("Date: " + date);
		System.out.println("Time: " + time);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CheckUp checkUp = (CheckUp) o;
		return priority == checkUp.priority
				&& Objects.equals(doctor, checkUp.doctor)
				&& Objects.equals(patient, checkUp.patient)
				&& Objects.equals(Recomendationm, checkUp.Recomendationm)
				&& Objects.equals(date, checkUp.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctor, patient, priority, Recomendationm, date);
	}
}
