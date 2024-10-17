package malakKhodorProject;

public class DoctorList {
	Dnode head, tail;

	public DoctorList() {
		this.head = null;
		this.tail = null;
	}
	public boolean isEmpty() {
		return (head == null || tail == null);
	}
	public int size() {
		int count = 0;
		Dnode current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public void Insert(Doctor doctor) {
		Dnode node = new Dnode(doctor);
		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			head.next = node;
			node.pre = head;
			head = node;
		}
	}
	public Doctor searchByName(String name) {
		Dnode temp = head;
		while (temp != null) {
			if (temp.doctor.getName().equals(name)) {
				return temp.doctor;
			}
			temp = temp.pre;
		}
		return null;
	}
	public Doctor searchBySpecialty(String speciality) {
		Dnode temp = head;
		while (temp != null) {
			if (temp.doctor.getSpeciality().equals(speciality)) {
				return temp.doctor;
			}
			temp = temp.pre;
		}
		return null;
	}
	public Doctor searchById(String id) {
		Dnode temp = head;
		while (temp != null) {
			if (temp.doctor.getId().equals(id)) {
				return temp.doctor;
			}
			temp = temp.pre;
		}
		return null;
	}
	public Doctor searchByContact(String contact) {
		Dnode temp = head;
		while (temp != null) {
			if (temp.doctor.getContact().equals(contact)) {
				return temp.doctor;
			}
			temp = temp.pre;
		}
		return null;
	}
	public String AllDoctorInfo() {
		Dnode current = head;
		String doctors = "";
		while (current != null) {
			doctors += "Doctor id= " + current.doctor.getId() + " speciality = "
					+ current.doctor.getSpeciality() + "\n";
			current = current.pre;
		}
		return doctors;
	}

}
