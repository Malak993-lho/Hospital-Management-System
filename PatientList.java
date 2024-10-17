package malakKhodorProject;

public class PatientList {
	PNode head, tail;

	public PatientList() {
		this.head = null;
		this.tail = null;
	}
	public boolean isEmpty() {
		return (head == null || tail == null);
	}
	public void Insert(Patient patient) {
		PNode node = new PNode(patient);
		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.pre = tail;
			tail = node;
		}
	}

	public Patient searchById(String id) {
		PNode temp = head;
		while (temp != null) {
			if (temp.patient.getId().equals(id)) {
				return temp.patient;
			}
			temp = temp.next; // Iterating in the forward direction
		}
		return null;
	}

	public Patient searchByName(String name) {
		PNode temp = head;
		while (temp != null) {
			if (temp.patient.getName().equalsIgnoreCase(name)) {
				return temp.patient;
			}
			temp = temp.next; // Iterating in the forward direction
		}
		return null; // Name not found
	}

	public Patient searchByContact(String contact) {
		PNode temp = head;
		while (temp != null) {
			if (temp.patient.getContact().equals(contact)) {
				return temp.patient;
			}
			temp = temp.next; // Iterating in the forward direction
		}
		return null; // Contact not found
	}

	public int size() {
		PNode temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next; // Iterating in the forward direction
		}
		return count;
	}

	public void removeById(String id) {
		PNode temp = head;
		while (temp != null) {
			if (temp.patient.getId().equals(id)) {
				if (temp == head) {
					head = head.next;
					if (head != null) {
						head.pre = null;
					}
				} else if (temp == tail) {
					tail = tail.pre;
					if (tail != null) {
						tail.next = null;
					}
				} else {
					temp.pre.next = temp.next;
					temp.next.pre = temp.pre;
				}
				return;
			}
			temp = temp.next;
		}
	}

	public void updatePatient(String id, Patient updatedPatient) {
		PNode temp = head;
		while (temp != null) {
			if (temp.patient.getId().equals(id)) {
				temp.patient = updatedPatient;
				return;
			}
			temp = temp.next;
		}
	}

	public String printAllPatient() {
		PNode current = head;
		String patients = "";
		while (current != null) {
			patients += "ID: " + current.patient.getId() + "\n" + "Name: "
					+ current.patient.getName() + "\n" + "Contact: "
					+ current.patient.getContact() + "\n" + "\n";
			current = current.next;
		}
		return patients;
	}

	public void printAllPatientsReverse() {
		PNode current = tail;
		while (current != null) {
			System.out.println("ID: " + current.patient.getId());
			System.out.println("Name: " + current.patient.getName());
			System.out.println("Contact: " + current.patient.getContact());
			System.out.println();
			current = current.pre;
		}
	}

}
