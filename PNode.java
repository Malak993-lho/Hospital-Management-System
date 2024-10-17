package malakKhodorProject;

public class PNode {
	Patient patient;
	PNode next, pre;

	public PNode(Patient patient) {
		this.patient = patient;
		this.next = null;
		this.pre = null;
	}

}
