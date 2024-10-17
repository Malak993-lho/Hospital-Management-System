package malakKhodorProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class CheckListQueue extends CheckList {
	private PriorityQueue<CheckUp> checkUpQueue;
	private HashMap<String, PriorityQueue<CheckUp>> doctorQueues;

	public CheckListQueue() {
		checkUpQueue = new PriorityQueue<>();
		doctorQueues = new HashMap<>();
	}

	// Enqueue a CheckUp with a specified priority
	public void priorityEnqueue(CheckUp cu, int priority) {
		checkUpQueue.offer(cu);

		String doctorId = cu.getDoctor().getId();
		if (!doctorQueues.containsKey(doctorId)) {
			doctorQueues.put(doctorId, new PriorityQueue<>());
		}
		doctorQueues.get(doctorId).offer(cu);
	}

	// Dequeue the highest-priority CheckUp
	public CheckUp priorityDequeue() {
		return checkUpQueue.poll(); // Retrieve and remove the head of the queue
	}

	public List<CheckUp> dequeueAndDisplay() {
		List<CheckUp> dequeued = new ArrayList<>();
		for (String doctorId : doctorQueues.keySet()) {
			CheckUp doctorCheckUp = doctorQueues.get(doctorId).poll();
			if (doctorCheckUp != null) {
				dequeued.add(doctorCheckUp);
				checkUpQueue.remove(doctorCheckUp); // Remove from main queue as
													// well
			}
		}

		// Display dequeued schedules (you can modify this as needed)
		if (dequeued.isEmpty()) {
			System.out.println("No schedules dequeued.");
		} else {
			System.out.println("Dequeued Schedules:");
			for (CheckUp cu : dequeued) {
				System.out.println(cu.toString());
			}
		}
		return dequeued;
	}

	// Check if the queue is empty
	public boolean isEmpty() {
		return checkUpQueue.isEmpty();
	}

	// Display all elements in the queue
	public String display() {
		StringBuilder sb = new StringBuilder();
		for (CheckUp cu : checkUpQueue) {
			sb.append(cu.toString()).append("\n");
		}
		return sb.toString();
	}

	// Get the number of elements in the queue
	public int size() {
		return checkUpQueue.size();
	}

	// Remove all elements from the queue
	public void clear() {
		checkUpQueue.clear();
	}

	// Update the priority of a specific CheckUp
	public void updatePriority(CheckUp cu, int newPriority) {
		checkUpQueue.remove(cu);
		cu.setPriority(newPriority);
		checkUpQueue.offer(cu);
	}

	public CheckUp checkForConflict(java.sql.Date date, String time,
			String patientId, String doctorId) {
		for (CheckUp checkUp : checkUpQueue) {
			if (checkUp.getDate().equals(date) && checkUp.getTime().equals(time)
					&& checkUp.getDoctor().getId().equals(doctorId)
					&& checkUp.getPatient().getId().equals(patientId)) {
				return checkUp; // Conflict found
			}
		}
		return null; // No conflict found
	}
}
