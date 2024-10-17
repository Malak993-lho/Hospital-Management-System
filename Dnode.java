package malakKhodorProject;

public class Dnode {
	Doctor doctor;
	Dnode next, pre;

	public Dnode(Doctor doctor) {
		this.doctor = doctor;
		this.next = null;
		this.pre = null;
	}
}
