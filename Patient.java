package malakKhodorProject;

public class Patient {
	private String Id;
	private String Name;
	private String Contact;
	public Patient() {
		Id = "";
		Name = "";
		Contact = "";
	}

	public Patient(String Id, String Name, String Contact) {
		this.Id = Id;
		this.Name = Name;
		this.Contact = Contact;
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
		return Contact;
	}

	public void setContact(String Contact) {
		this.Contact = Contact;
	}

	@Override
	public String toString() {
		return "Patient{" + "Id=" + Id + ", Name=" + Name + ", Contact="
				+ Contact + '}';
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Patient patient = (Patient) o;

		if (!Id.equals(patient.Id))
			return false;
		if (!Name.equals(patient.Name))
			return false;
		return Contact.equals(patient.Contact);
	}

	// Override hashCode method
	@Override
	public int hashCode() {
		int result = Id.hashCode();
		result = 31 * result + Name.hashCode();
		result = 31 * result + Contact.hashCode();
		return result;
	}
}
