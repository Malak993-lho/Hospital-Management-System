package malakKhodorProject;

public class CheckList {

	CNode head, tail;

	public CheckList() {
		head = null;
		tail = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void insertAtFront(CheckUp cu, int priority) {
		CNode node = new CNode(cu, priority);
		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.pre = node;
			head = node;
		}
	}

	public void priorityEnqueue(CheckUp cu, int priority) {
		CNode node = new CNode(cu, priority);
		if (isEmpty()) {
			head = node;
			tail = node;
		} else if (head.priority > priority) {
			CNode current = head;
			while (current.next != null && current.next.priority > priority) {
				current = current.next;
			}
			node.next = current.next;
			if (current.next != null) {
				current.next.pre = node;
			}
			current.next = node;
			node.pre = current;
		} else {
			node.next = head;
			head.pre = node;
			head = node;
		}
	}

	public CNode deleteFromBack() {
		CNode temp = tail;
		if (!isEmpty()) {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.pre;
				tail.next = null;
			}
		}
		return temp;
	}

	public CNode deleteFromFront() {
		CNode temp = head;
		if (!isEmpty()) {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				head.pre = null;
			}
		}
		return temp;
	}

	public String display() {
		CNode current = head;
		String dis = "";
		while (current != null) {
			dis += "Priority: " + current.cu.getPriority() + "\n";
			dis += "Patient: " + current.cu.getPatient().getName() + "\n";
			dis += "Doctor: " + current.cu.getDoctor().getName() + "\n";
			dis += "Recommendation: " + current.cu.getRecomendationm() + "\n";
			dis += "Date: " + current.cu.getData() + "\n\n";
			current = current.next;
		}
		dis += "\n";
		return dis;
	}

	public CNode find(CheckUp cu) {
		CNode current = head;
		while (current != null) {
			if (current.cu.equals(cu)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public CNode findByPriority(int priority) {
		CNode current = head;
		while (current != null) {
			if (current.priority == priority) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void updatePriority(CheckUp cu, int newPriority) {
		CNode node = find(cu);
		if (node != null) {
			deleteNode(node);
			priorityEnqueue(cu, newPriority);
		}
	}

	private void deleteNode(CNode node) {
		if (node == head) {
			deleteFromFront();
		} else if (node == tail) {
			deleteFromBack();
		} else {
			node.pre.next = node.next;
			node.next.pre = node.pre;
		}
	}

	public int size() {
		int count = 0;
		CNode current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public CNode peek() {
		return head; // Head should be the highest priority
	}

	public void clear() {
		head = null;
		tail = null;
	}

}
