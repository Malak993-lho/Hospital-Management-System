package malakKhodorProject;

public class CNode {
	CheckUp cu;
	int priority;
	CNode next, pre;

	public CNode(CheckUp cu, int priority) {
		this.cu = cu;
		this.priority = priority;
		this.next = null;
		this.pre = null;
	}

}
