package malakKhodorProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class mainGUI {

	private static PatientList patientList = new PatientList();
	private static DoctorList doctorList = new DoctorList();
	private static CheckListQueue checkListQueue = new CheckListQueue();

	//
	private static int patientCount = 1;
	private static int doctorCount = 1;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Hospital Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());

		// Create panel to hold buttons
		JPanel buttonPanel = new JPanel(new GridLayout(11, 1, 5, 5));
		Font buttonFont = new Font("Arial", Font.BOLD, 16);

		// Colors for buttons
		Color addButtonColor = new Color(34, 139, 34); // Forest Green
		Color searchButtonColor = new Color(135, 206, 250); // Sky Blue
		Color viewButtonColor = new Color(0, 128, 128); // Teal
		Color dequeueButtonColor = new Color(240, 128, 128); // Light Coral
		Color scheduleCheckUpButtonColor = new Color(70, 130, 180); // Steel
																	// Blue
		Color exitButtonColor = new Color(178, 34, 34); // Firebrick Red

		JButton addPatientButton = createButton("Add a patient", addButtonColor,
				buttonFont);
		JButton addDoctorButton = createButton("Add a doctor", addButtonColor,
				buttonFont);
		JButton scheduleCheckUpButton = createButton("Schedule a check-up",
				scheduleCheckUpButtonColor, buttonFont);
		JButton viewCheckUpQueueButton = createButton("View check-up queue",
				viewButtonColor, buttonFont);
		JButton viewAllDoctorsButton = createButton("View all doctors",
				viewButtonColor, buttonFont);
		JButton viewAllPatientsButton = createButton("View all patients",
				viewButtonColor, buttonFont);
		JButton searchPatientByIdButton = createButton("Search for a patient",
				searchButtonColor, buttonFont);
		JButton searchDoctorByContactButton = createButton(
				"Search for a doctor", searchButtonColor, buttonFont);
		JButton dequeueCheckUpButton = createButton("Dequeue a check-up",
				dequeueButtonColor, buttonFont);
		JButton exitButton = createButton("Exit", exitButtonColor, buttonFont);

		JButton[] buttons = {addPatientButton, addDoctorButton,
				scheduleCheckUpButton, viewCheckUpQueueButton,
				viewAllDoctorsButton, viewAllPatientsButton,
				searchPatientByIdButton, searchDoctorByContactButton,
				dequeueCheckUpButton, exitButton};

		for (JButton button : buttons) {
			buttonPanel.add(button);
		}

		// Create empty panels for left and right padding
		JPanel leftPadding = new JPanel();
		leftPadding.setPreferredSize(new Dimension(150, 0)); // Adjust width as
																// needed

		JPanel rightPadding = new JPanel();
		rightPadding.setPreferredSize(new Dimension(150, 0)); // Adjust width as
																// needed

		// Add panels to mainPanel
		mainPanel.add(leftPadding, BorderLayout.WEST);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.add(rightPadding, BorderLayout.EAST);

		frame.add(mainPanel);

		frame.setVisible(true);

		addPatientButton.addActionListener(e -> addPatient());
		addDoctorButton.addActionListener(e -> addDoctor());
		scheduleCheckUpButton.addActionListener(e -> scheduleCheckUp());
		viewCheckUpQueueButton.addActionListener(e -> viewCheckUpQueue());
		viewAllDoctorsButton.addActionListener(e -> viewAllDoctors());
		viewAllPatientsButton.addActionListener(e -> viewAllPatients());
		searchPatientByIdButton.addActionListener(e -> searchPatient());
		searchDoctorByContactButton.addActionListener(e -> searchDoctor());
		dequeueCheckUpButton.addActionListener(e -> dequeueCheckUp());
		exitButton.addActionListener(e -> System.exit(0));
	}

	private static JButton createButton(String text, Color color, Font font) {
		JButton button = new JButton(text);
		button.setFont(font);
		button.setBackground(color);
		button.setForeground(Color.WHITE);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Margin
																			// around
																			// button
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(color.darker()); // Darker color on hover
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(color); // Original color when not hovering
			}
		});
		return button;
	}

	private static void dequeueCheckUp() {
		CheckUp dequeuedCheckUp = checkListQueue.priorityDequeue();
		if (dequeuedCheckUp != null) {
			String message = "Dequeued Check-Up:\n" + "Priority: "
					+ dequeuedCheckUp.getPriority() + "\n";
			if (dequeuedCheckUp.getDoctor() != null) {
				message += "Doctor: " + dequeuedCheckUp.getDoctor().getName()
						+ "\n";
			} else {
				message += "Doctor: N/A\n";
			}
			if (dequeuedCheckUp.getPatient() != null) {
				message += "Patient: " + dequeuedCheckUp.getPatient().getName()
						+ "\n";
			} else {
				message += "Patient: N/A\n";
			}
			message += "Recommendation: " + dequeuedCheckUp.getRecomendationm()
					+ "\n" + "Date: " + dequeuedCheckUp.getDate();
			JOptionPane.showMessageDialog(null, message);
			// Display remaining check-ups
			viewCheckUpQueue();
		} else {
			JOptionPane.showMessageDialog(null,
					"No check-up available to dequeue.");
		}
	}

	private static void scheduleCheckUp() {
		JFrame frame = new JFrame("Schedule Check-Up");
		frame.setSize(400, 500);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		Font labelFont = new Font("Arial", Font.BOLD, 14);

		gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Patient ID
		JLabel patientIdLabel = new JLabel("Patient ID:");
		patientIdLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(patientIdLabel, gbc);

		JTextField patientIdField = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(patientIdField, gbc);

		// Doctor ID
		JLabel doctorIdLabel = new JLabel("Doctor ID:");
		doctorIdLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(doctorIdLabel, gbc);

		JTextField doctorIdField = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(doctorIdField, gbc);

		// Priority
		JLabel priorityLabel = new JLabel("Priority:");
		priorityLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(priorityLabel, gbc);

		JTextField priorityField = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(priorityField, gbc);

		// Recommendation
		JLabel recommendationLabel = new JLabel("Recommendation:");
		recommendationLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(recommendationLabel, gbc);

		JTextField recommendationField = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(recommendationField, gbc);

		// Date
		JLabel dateLabel = new JLabel("Date (dd/MM/yyyy):");
		dateLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(dateLabel, gbc);

		JTextField dateField = new JTextField(20);
		dateField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(dateField, gbc);

		// Date Picker Button
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		JButton datePickerButton = new JButton("Pick Date");
		panel.add(datePickerButton, gbc);

		// Time Label
		JLabel timeLabel = new JLabel("Time (hh:mm):");
		timeLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(timeLabel, gbc);

		// Time Selection Panel
		JPanel timePanel = new JPanel(new GridLayout(0, 4)); // Grid layout with
																// 0 rows
																// (auto-adjust)
																// and 4 columns
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 2; // Span across two columns
		panel.add(timePanel, gbc);

		// Create radio buttons for time slots (8:00 AM to 1:30 PM in 30-minute
		// intervals)
		ButtonGroup timeButtonGroup = new ButtonGroup();
		String[] timeSlots = {"08:00", "08:30", "09:00", "09:30", "10:00",
				"10:30", "11:00", "11:30", "12:00", "12:30", "01:00", "01:30"};
		for (String timeSlot : timeSlots) {
			JRadioButton timeRadioButton = new JRadioButton(timeSlot);
			timeRadioButton.setActionCommand(timeSlot); // Set action command
														// for easier
														// identification
			timeButtonGroup.add(timeRadioButton);
			timePanel.add(timeRadioButton);
		}

		// Schedule Button
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		JButton scheduleButton = new JButton("Schedule");
		panel.add(scheduleButton, gbc);

		frame.add(panel);
		frame.setVisible(true);

		datePickerButton.addActionListener(e -> {
			JDialog datePickerDialog = new JDialog(frame, "Select Date", true);
			datePickerDialog.setSize(300, 150);

			datePickerDialog.setLayout(new GridLayout(4, 2));
			datePickerDialog.setLocationRelativeTo(null);

			JTextField dayField = new JTextField();
			JTextField monthField = new JTextField();
			JTextField yearField = new JTextField(String.valueOf(2024));
			yearField.setEditable(false);

			datePickerDialog.add(new JLabel("Day:"));
			datePickerDialog.add(dayField);
			datePickerDialog.add(new JLabel("Month:"));
			datePickerDialog.add(monthField);
			datePickerDialog.add(new JLabel("Year:"));
			datePickerDialog.add(yearField);

			JButton confirmDateButton = new JButton("Confirm");
			datePickerDialog.add(confirmDateButton);

			confirmDateButton.addActionListener(ev -> {

				int day;
				try {
					day = Integer.parseInt(dayField.getText());
					if (day < 1 || day > 30) {
						JOptionPane.showMessageDialog(datePickerDialog,
								"Day should be between 1 and 30.");
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(datePickerDialog,
							"Invalid day format! Please enter a number.");
					return;
				}

				int month;
				try {
					month = Integer.parseInt(monthField.getText());
					if (month < 1 || month > 12) {
						JOptionPane.showMessageDialog(datePickerDialog,
								"Month should be between 1 and 12.");
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(datePickerDialog,
							"Invalid month format! Please enter a number.");
					return;
				}

				String selectedDate = day + "/" + month + "/"
						+ yearField.getText();
				dateField.setText(selectedDate);
				datePickerDialog.dispose();
			});

			datePickerDialog.setVisible(true);
		});

		scheduleButton.addActionListener(e -> {
			try {
				String patientId = patientIdField.getText();
				String doctorId = doctorIdField.getText();
				int priority = Integer.parseInt(priorityField.getText());
				String recommendation = recommendationField.getText();
				String dateString = dateField.getText();

				// Find the selected time slot
				String selectedTime = timeButtonGroup.getSelection()
						.getActionCommand();

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"dd/MM/yyyy");
				dateFormat.setLenient(false);

				java.util.Date parsedDate = dateFormat.parse(dateString);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

				Patient patient = patientList.searchById(patientId);
				Doctor doctor = doctorList.searchById(doctorId);

				if (patient == null || doctor == null) {
					JOptionPane.showMessageDialog(frame,
							"Patient or Doctor not found!");
					return;
				}

				CheckUp existingAppointment = checkListQueue.checkForConflict(
						sqlDate, selectedTime + ":00", patientId, doctorId);

				if (existingAppointment != null) {
					// Conflict found, disable the selected time slot
					String conflictingTimeSlot = existingAppointment.getTime();

					// Get the button group's buttons
					Enumeration<AbstractButton> buttons = timeButtonGroup
							.getElements();
					while (buttons.hasMoreElements()) {
						JRadioButton button = (JRadioButton) buttons
								.nextElement();
						if (button.getActionCommand()
								.equals(conflictingTimeSlot)) {
							button.setEnabled(false);
							break;
						}
					}

					JOptionPane.showMessageDialog(frame,
							"This time slot is already booked for another patient. Please choose another time.");
					return;
				}

				CheckUp newCheckUp = new CheckUp(doctor, patient, priority,
						recommendation, sqlDate, selectedTime + ":00");
				checkListQueue.priorityEnqueue(newCheckUp, priority);

				JOptionPane.showMessageDialog(frame,
						"Check-up scheduled successfully!");
				frame.dispose();
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(frame,
						"Invalid date format! Please use dd/MM/yyyy.");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame,
						"Invalid priority! Please enter a number.");
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(frame,
						"Please select a time slot.");
			}
		});
	}

	private static void viewCheckUpQueue() {
		JFrame frame = new JFrame("Check-Up Queue");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane);

		String queueData = checkListQueue.display();
		textArea.setText(queueData);

		frame.setVisible(true);
	}

	private static void viewAllDoctors() {
		JFrame frame = new JFrame("All Doctors");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane);

		String doctorData = doctorList.AllDoctorInfo();
		textArea.setText(doctorData);

		frame.setVisible(true);
	}

	private static void viewAllPatients() {
		JFrame frame = new JFrame("All Patients");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);

		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane);

		String patientData = patientList.printAllPatient();
		textArea.setText(patientData);

		frame.setVisible(true);
	}

	private static void searchPatient() {
		JFrame frame = new JFrame("Search Patient");
		frame.setSize(400, 250);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		Font labelFont = new Font("Arial", Font.BOLD, 14);

		gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Search Criteria
		JLabel criteriaLabel = new JLabel("Search By:");
		criteriaLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(criteriaLabel, gbc);

		String[] criteriaOptions = {"ID", "Name", "Contact"};
		JComboBox<String> criteriaComboBox = new JComboBox<>(criteriaOptions);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(criteriaComboBox, gbc);

		// Input Field
		JLabel inputLabel = new JLabel("Enter Value:");
		inputLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(inputLabel, gbc);

		JTextField inputField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(inputField, gbc);

		// Search Button
		JButton searchButton = new JButton("Search");
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(searchButton, gbc);

		frame.add(panel);
		frame.setVisible(true);

		searchButton.addActionListener(e -> {
			String selectedCriteria = (String) criteriaComboBox
					.getSelectedItem();
			String value = inputField.getText();
			Patient patient = null;

			switch (selectedCriteria) {
				case "ID" :
					patient = patientList.searchById(value);
					break;
				case "Name" :
					patient = patientList.searchByName(value);
					break;
				case "Contact" :
					patient = patientList.searchByContact(value);
					break;
			}

			if (patient != null) {
				JOptionPane.showMessageDialog(frame,
						"- Patient Found - \n" + "Name: " + patient.getName()
								+ "\nId: " + patient.getId() + "\nContact: "
								+ patient.getContact());
			} else {
				JOptionPane.showMessageDialog(frame, "Patient not found!");
			}
		});
	}

	private static void searchDoctor() {
		JFrame frame = new JFrame("Search Doctor");
		frame.setSize(400, 250);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		Font labelFont = new Font("Arial", Font.BOLD, 14);

		gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Search Criteria
		JLabel criteriaLabel = new JLabel("Search By:");
		criteriaLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(criteriaLabel, gbc);

		String[] criteriaOptions = {"ID", "Name", "Contact"};
		JComboBox<String> criteriaComboBox = new JComboBox<>(criteriaOptions);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(criteriaComboBox, gbc);

		// Input Field
		JLabel inputLabel = new JLabel("Enter Value:");
		inputLabel.setFont(labelFont);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(inputLabel, gbc);

		JTextField inputField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(inputField, gbc);

		// Search Button
		JButton searchButton = new JButton("Search");
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(searchButton, gbc);

		frame.add(panel);
		frame.setVisible(true);

		searchButton.addActionListener(e -> {
			String selectedCriteria = (String) criteriaComboBox
					.getSelectedItem();
			String value = inputField.getText();
			Doctor doctor = null;

			switch (selectedCriteria) {
				case "ID" :
					doctor = doctorList.searchById(value);
					break;
				case "Name" :
					doctor = doctorList.searchByName(value);
					break;
				case "Contact" :
					doctor = doctorList.searchByContact(value);
					break;
			}

			if (doctor != null) {
				JOptionPane.showMessageDialog(frame,
						"- Doctor Found - \n" + "Name: " + doctor.getName()
								+ "\nID: " + doctor.getId() + "\nContact: "
								+ doctor.getContact() + "\nSpeciality: "
								+ doctor.getSpeciality() + "\nFees: "
								+ doctor.getFees());
			} else {
				JOptionPane.showMessageDialog(frame, "Doctor not found!");
			}
		});
	}

	private static void addPatient() {
		JFrame frame = new JFrame("Add Patient");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(4, 2));

		String id = "p" + patientCount;

		JTextField idField = new JTextField();
		idField.setText(id);
		idField.setEditable(false);

		JTextField nameField = new JTextField();
		JTextField contactField = new JTextField();

		panel.add(new JLabel("Patient ID:"));
		panel.add(idField);
		panel.add(new JLabel("Patient Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Patient Contact:"));
		panel.add(contactField);

		JButton addButton = new JButton("Add");
		panel.add(addButton);

		frame.add(panel);
		frame.setVisible(true);

		addButton.addActionListener(e -> {
			String name = nameField.getText();
			String contact = contactField.getText();

			if (name.isEmpty() || contact.isEmpty()) {
				JOptionPane.showMessageDialog(frame,
						"Please fill in all fieds.");
				return;
			}

			if (patientList.searchById(id) != null) {
				JOptionPane.showMessageDialog(frame,
						"Patient ID already exists,\n"
								+ "Please enter another ID.");
				return;
			}

			Patient newPatient = new Patient(id, name, contact);
			patientList.Insert(newPatient);
			patientCount++;

			JOptionPane.showMessageDialog(frame, "Patient added successfully!");
			frame.dispose();
		});
	}

	private static void addDoctor() {
		JFrame frame = new JFrame("Add Doctor");
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(6, 2));

		String id = "d" + doctorCount;

		JTextField idField = new JTextField();
		idField.setText(id);
		idField.setEditable(false);

		JTextField nameField = new JTextField();
		JTextField contactField = new JTextField();
		JTextField specialityField = new JTextField();
		JTextField feesField = new JTextField();

		panel.add(new JLabel("Doctor ID:"));
		panel.add(idField);
		panel.add(new JLabel("Doctor Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Doctor Contact:"));
		panel.add(contactField);
		panel.add(new JLabel("Doctor Speciality:"));
		panel.add(specialityField);
		panel.add(new JLabel("Doctor Fees:"));
		panel.add(feesField);

		JButton addButton = new JButton("Add");
		panel.add(addButton);

		frame.add(panel);
		frame.setVisible(true);

		addButton.addActionListener(e -> {
			String name = nameField.getText();
			String contact = contactField.getText();
			String speciality = specialityField.getText();

			if (name.isEmpty() || contact.isEmpty() || speciality.isEmpty()) {
				JOptionPane.showMessageDialog(frame,
						"Please fill in all fields.");
				return;
			}

			int fees = 0;
			boolean validInput = false;

			try {
				fees = Integer.parseInt(feesField.getText());
				validInput = true;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame,
						"Fees should be a number.\nPlease re-enter.");
				feesField.setText("");
				return;
			}

			if (doctorList.searchById(id) != null) {
				JOptionPane.showMessageDialog(frame,
						"Doctor ID already exists,\n"
								+ "Please enter another ID.");
				return;
			}

			Doctor newDoctor = new Doctor(id, name, contact, speciality, fees);
			doctorList.Insert(newDoctor);
			doctorCount++;
			JOptionPane.showMessageDialog(frame, "Doctor added successfully!");
			frame.dispose();
		});
	}

}
