package malakKhodorProject;

public class main {

	public static void main(String[] args) {

		new mainGUI();
		 TODO code application logic here
		 Scanner scanner = new Scanner(System.in);
		 PatientList patientList = new PatientList();
		 DoctorList doctorList = new DoctorList();
		 CheckListQueue checkListQueue = new CheckListQueue();
		 Patient patient = new Patient();
		 boolean exit = false;
		
		 while (!exit) {
		
		 System.out.println("Choose an option:");
		 System.out.println("1. Add a patient");
		 System.out.println("2. Add a doctor");
		 System.out.println("3. Schedule a check-up");
		 System.out.println("4. View check-up queue");
		 System.out.println("5. View all doctors");
		 System.out.println("6. View all patients");
		 System.out.println("7. Search for a patient by ID");
		 System.out.println("8. Search for a doctor by contact");
		 System.out.println("9. Dequeue a check-up");
		 System.out.println("10. Exit");
		 int choice = scanner.nextInt();
		 scanner.nextLine();
		
		 switch (choice) {
		
		 case 1 :
		
		 while (true) {
		 System.out.println("Enter patient ID:");
		 String patientId = scanner.nextLine();
		
		 System.out.println("Enter patient name:");
		 String patientName = scanner.nextLine();
		
		 System.out.println("Enter patient contact:");
		 String patientContact = scanner.nextLine();
		
		 // Create a new Patient object
		 Patient newPatient = new Patient(patientId, patientName,
		 patientContact);
		
		 // Add the new patient to the patient list
		 patientList.Insert(newPatient);
		 System.out.println("Patient added successfully!");
		
		 System.out.println(
		 "Do you want to add another patient? (yes/no)");
		 String answer = scanner.nextLine();
		 if (!answer.equalsIgnoreCase("yes"))
		 break;
		 }
		 break;
		
		 case 2 :
		
		 while (true) {
		 System.out.println("Enter doctor ID:");
		 String doctorId = scanner.nextLine();
		
		 System.out.println("Enter doctor name:");
		 String doctorName = scanner.nextLine();
		
		 System.out.println("Enter doctor contact:");
		 String doctorContact = scanner.nextLine();
		
		 System.out.println("Enter doctor speciality:");
		 String doctorSpeciality = scanner.nextLine();
		
		 System.out.println("Enter doctor fees:");
		 int doctorFees = scanner.nextInt();
		 scanner.nextLine(); // Consume newline
		
		 // Create a new Doctor object
		 Doctor newDoctor = new Doctor(doctorId, doctorName,
		 doctorContact, doctorSpeciality, doctorFees);
		
		 // Add the new doctor to the doctor list
		 doctorList.Insert(newDoctor);
		 System.out.println("Doctor added successfully!");
		
		 System.out.println(
		 "Do you want to add another doctor? (yes/no)");
		 String answer = scanner.nextLine();
		 if (!answer.equalsIgnoreCase("yes")) {
		 break; // Exit the doctor-adding loop
		 }
		 }
		 break;
		
		 case 3 :
		
		 System.out.println("Enter patient ID for the check-up:");
		 String checkupPatientId = scanner.nextLine();
		 // Search patient by ID
		 Patient patients = patientList.searchById(checkupPatientId);
		 if (patients == null) {
		 System.out.println("Patient not found.");
		 break;
		 }
		
		 System.out.println("Enter doctor ID for the check-up:");
		 String checkupDoctorId = scanner.nextLine();
		 // Search doctor by ID
		 Doctor doctor = doctorList.searchById(checkupDoctorId);
		 if (doctor == null) {
		 System.out.println("Doctor not found.");
		 break;
		 }
		
		 System.out.println("Enter priority for the check-up:");
		 int priority = Integer.parseInt(scanner.nextLine());
		
		 System.out
		 .println("Enter recommendation for the check-up:");
		 String recommendation = scanner.nextLine();
		
		 System.out.println("Enter date for the check-up:");
		 String date = scanner.nextLine();
		
		 // Create a new CheckUp object
		 CheckUp newCheckUp = new CheckUp(doctor, patient, priority,
		 recommendation, date);
		
		 // Schedule the check-up by enqueueing it
		 checkListQueue.priorityEnqueue(newCheckUp, priority);
		 System.out.println("Check-up scheduled successfully!");
		 break;
		
		 case 4 :
		 System.out.println("Check-up queue:");
		 checkListQueue.display();
		 break;
		
		 case 5 :
		 System.out.println("All Doctors:");
		 doctorList.AllDoctorInfo();
		 break;
		
		 case 6 :
		 System.out.println("All patients:");
		 patientList.printAllPatient();
		 break;
		
		 case 7 :
		 System.out.println("Enter patient ID to search:");
		 String SearchPatientId = scanner.nextLine();
		 Patient found = patientList.searchById(SearchPatientId);
		
		 if (found != null) {
		 System.out.println("Patient found: " + found);
		 } else {
		 System.out.println("Patient with ID " + SearchPatientId
		 + " not found.");
		 }
		 break;
		
		 case 8 :
		 System.out.println("Enter doctor contact to search:");
		 String searchDoctorContact = scanner.nextLine();
		 Doctor foundDoctor = doctorList
		 .searchByContact(searchDoctorContact);
		
		 if (foundDoctor != null) {
		 System.out.println("Doctor found:");
		 System.out.println("ID: " + foundDoctor.getId());
		 System.out.println("Name: " + foundDoctor.getName());
		 System.out.println(
		 "Contact: " + foundDoctor.getContact());
		 System.out.println(
		 "Speciality: " + foundDoctor.getSpeciality());
		 System.out.println("Fees: " + foundDoctor.getFees());
		 } else {
		 System.out.println("Doctor with contact "
		 + searchDoctorContact + " not found.");
		 }
		 break;
		
		 // case 9:
		 // while (true) {
		 // System.out.println(
		 // "\n--- Check-Up Management System ---");
		 // System.out.println("1. Enqueue Check-Up");
		 // System.out.println("2. Dequeue Check-Up");
		 // System.out.println("3. Display Queue");
		 // System.out.println("4. Get Queue Size");
		 // System.out.println("5. Clear Queue");
		 // System.out.println("6. Update Check-Up Priority");
		 // System.out.println("7. Exit");
		 // System.out.print("Enter your choice: ");
		 
		 // int choice2 = scanner.nextInt();
		 // scanner.nextLine();
		
		 // switch (choice2) {
		 // case 1:
		 // System.out.println(
		 // "Enter priority for the check-up:");
		 // int p = scanner.nextInt();
		
		 // System.out.println(
		 // "Enter recommendation for the check-up:");
		 // String ec = scanner.nextLine();
		
		 // System.out.println(
		 // "Enter date for the check-up:");
		 // String d = scanner.nextLine();
		
		 // // Create a new CheckUp object
		 // CheckUp newCheckUp1 = new CheckUp(doctor,
		 // patient, p, ec, d);
		 
		 // // Schedule the check-up by enqueueing it
		 // checkListQueue.priorityEnqueue(newCheckUp1, p);
		 // System.out.println(
		 // "Check-up scheduled successfully!");
		 // break;
		
		 // case 2:
		 // // Dequeue a check-up from the front of the
		 // // queue
		 // if (!checkListQueue.isEmpty()) {
		 // CheckUp dequeuedCheckUp = checkListQueue
		 // .priorityDequeue();
		 // if (dequeuedCheckUp != null) {
		 // System.out
		 // .println("Check-up dequeued:");
		 // System.out.println(
		 // "Priority: " + dequeuedCheckUp
		 // .getPriority());
		 // System.out.println(
		 // "Doctor: " + (dequeuedCheckUp
		 // .getDoctor() != null
		 // ? dequeuedCheckUp
		 // .getDoctor()
		 // .getName()
		 // : "N/A"));
		 // System.out.println(
		 // "Patient: " + (dequeuedCheckUp
		 // .getPatient() != null
		 // ? dequeuedCheckUp
		 // .getPatient()
		 // .getName()
		 // : "N/A"));
		 // System.out.println("Recommendation: "
		 // + dequeuedCheckUp
		 // .getRecomendationm());
		 // System.out.println("Date: "
		 // + dequeuedCheckUp.getData());
		 // } else {
		 // System.out.println(
		 // "No check-up available to dequeue.");
		 // }
		 // } else {
		 // System.out.println(
		 // "The check-up queue is empty.");
		 // }
		 // break;
		 // case 3:
		 // checkListQueue.display();
		 // break;
		 // case 4:
		 // System.out.println(
		 // "Number of check-ups in the queue: "
		 // + checkListQueue.size());
		 // break;
		 // case 5:
		 // checkListQueue.clear();
		 // System.out
		 // .println("Queue cleared successfully!");
		 // break;
		 // case 6:
		 // System.out.println(
		 // "Enter priority for the check-up to update:");
		 // int oldPriority = Integer
		 // .parseInt(scanner.nextLine());
		 // CheckUp checkUpToUpdate = checkListQueue.checkList
		 // .findByPriority(oldPriority).cu;
		
		 // if (checkUpToUpdate != null) {
		 // System.out.println(
		 // "Enter new priority for the check-up:");
		 // int newPriority = Integer
		 // .parseInt(scanner.nextLine());
		 // checkListQueue.updatePriority(
		 // checkUpToUpdate, newPriority);
		 // System.out.println(
		 // "Check-up priority updated successfully!");
		 // } else {
		 // System.out.println(
		 // "No check-up found with the specified priority.");
		 // }
		 // break;
		 // case 7:
		 // System.out.println("Exiting...");
		 // scanner.close();
		 // return;
		 // default:
		 // System.out.println(
		 // "Invalid choice. Please try again.");
		
		 // }
		 // }
		
		 case 10 :
		 // exit
		 exit = true;
		 break;
		
		 }
		 }
	}
}
