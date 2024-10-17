package malakKhodorProject;

public class Doctor {
	private String Id, Name, contact, speciality;
	private int fees;

	public Doctor(String Id, String Name, String contact, String speciality,
			int fees) {
		this.Id = Id;
		this.Name = Name;
		this.contact = contact;
		this.speciality = speciality;
		this.fees = fees;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Doctor doctor = (Doctor) o;

		if (fees != doctor.fees)
			return false;
		if (!Id.equals(doctor.Id))
			return false;
		if (!Name.equals(doctor.Name))
			return false;
		if (!contact.equals(doctor.contact))
			return false;
		return speciality.equals(doctor.speciality);
	}

	@Override
	public int hashCode() {
		int result = Id.hashCode();
		result = 31 * result + Name.hashCode();
		result = 31 * result + contact.hashCode();
		result = 31 * result + speciality.hashCode();
		result = 31 * result + fees;
		return result;
	}

}
