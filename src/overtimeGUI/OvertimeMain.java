package overtimeGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

import java.awt.Component;

import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OvertimeMain {

	private JFrame frame;
	private JTextField textField;
	static DefaultTableModel model = new DefaultTableModel();
	static DefaultTableModel model_3 = new DefaultTableModel();
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	static Connection conn;
	ArrayList<String> employees = new ArrayList<String>();
	JComboBox comboBox_12 = new JComboBox();
	private JTable table_1;
	JComboBox comboBox_1;
	JComboBox comboBox_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OvertimeMain window = new OvertimeMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OvertimeMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 877, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		String[] days = { "", "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		String[] hoursSelect = { "", "8", "12" };
		ButtonGroup group2 = new ButtonGroup();
		String[] areas = { "", "E-extruders", "P-pelletizer", "U-utility",
				"W-warehouse/shipping" };

		JPanel panel = new JPanel();
		tabbedPane.addTab("Overtime", null, panel, null);
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]",
				"[][][][][][][][grow][][][][]"));

		JLabel lblReasonForOvertime = new JLabel("Reason for Overtime");
		panel.add(lblReasonForOvertime, "cell 0 0,alignx trailing");

		textField = new JTextField();
		panel.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		String[] shifts = { "", "A & B", "C & D" };

		JLabel lblHours = new JLabel("Hours");
		panel.add(lblHours, "cell 2 0,alignx right");

		JLabel lblDayOfThe = new JLabel("Date");
		panel.add(lblDayOfThe, "cell 0 1,alignx trailing");
		ButtonGroup group = new ButtonGroup();

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		panel.add(datePicker, "cell 1 1,growx");

		JLabel lblShifts = new JLabel("Shift Rotation");
		panel.add(lblShifts, "cell 2 1,alignx right");
		JComboBox comboBox_15 = new JComboBox(shifts);
		panel.add(comboBox_15, "cell 3 1,growx");

		JLabel lblArea_1 = new JLabel("Area");
		panel.add(lblArea_1, "cell 0 2,alignx trailing");

		JComboBox comboBox_14 = new JComboBox(areas);
		panel.add(comboBox_14, "cell 1 2,growx");

		JLabel lblCrew_2 = new JLabel("Crew");
		panel.add(lblCrew_2, "cell 0 3,alignx trailing");
		String[] rotations = { "", "AB", "CD" };
		JComboBox comboBox_13 = new JComboBox(rotations);
		panel.add(comboBox_13, "cell 1 3,growx");

		JButton btnUpdate = new JButton("Update");
		panel.add(btnUpdate, "cell 3 3");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 7 4 1,grow");

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JComboBox comboBox = new JComboBox(hoursSelect);
		panel.add(comboBox, "cell 3 0,growx");

		JRadioButton rdbtnDays = new JRadioButton("Days");
		rdbtnDays.setSelected(true);
		group.add(rdbtnDays);
		panel.add(rdbtnDays, "flowx,cell 3 2");
		JRadioButton rdbtnNights = new JRadioButton("Nights");
		group.add(rdbtnNights);
		panel.add(rdbtnNights, "cell 3 2");
		comboBox_12 = new JComboBox();
		JLabel lblAcceptEmployeeFor = new JLabel("Select Employee for Overtime");
		panel.add(lblAcceptEmployeeFor, "cell 2 8,alignx trailing");
		panel.add(comboBox_12, "cell 3 8,growx");

		JButton btnOk = new JButton("OK");
		panel.add(btnOk, "cell 3 9");

		String[] crews = { "", "A", "B", "C", "D" };
		String[] primary = { "", "E-Extruders", "P-Pelletizer", "U-Utility",
				"W-Warehouse-Shipping" };

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("View Crews", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[grow][][grow]", "[][grow]"));

		JLabel lblCrew_1 = new JLabel("Crew");
		panel_1.add(lblCrew_1, "cell 0 0,alignx trailing");

		comboBox_1 = new JComboBox(crews);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayCrew();
			}
		});
		panel_1.add(comboBox_1, "cell 1 0");

		JButton btnDeleteAll = new JButton("Delete all from Crew");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete((String) comboBox_1.getSelectedItem());
			}
		});
		panel_1.add(btnDeleteAll, "cell 2 0,alignx center");

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 1 3 1,grow");

		table_1 = new JTable(model_3);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Update Crews", null, panel_2, null);
		panel_2.setLayout(new MigLayout("",
				"[grow][grow][grow][grow][grow][grow][grow]",
				"[][][][][][][][][][][][]"));

		JLabel lblCrew = new JLabel("Crew");
		panel_2.add(lblCrew, "cell 1 0,alignx trailing");
		comboBox_2 = new JComboBox(crews);

		panel_2.add(comboBox_2, "cell 2 0");

		JLabel lblName = new JLabel("Name");
		panel_2.add(lblName, "cell 1 1");

		JLabel lblPrimaryClassification = new JLabel("Primary Classification");
		panel_2.add(lblPrimaryClassification, "cell 2 1");

		JLabel lblSeniority = new JLabel("Seniority");
		panel_2.add(lblSeniority, "cell 3 1");

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		panel_2.add(lblPhoneNumber, "cell 4 1");

		JLabel lblCrosstrainedClassifications = new JLabel(
				"Crosstrained Classifications");
		panel_2.add(lblCrosstrainedClassifications, "cell 5 1,alignx center");

		JLabel lblHours_1 = new JLabel("Hours");
		panel_2.add(lblHours_1, "cell 6 1");

		textField_2 = new JTextField();
		panel_2.add(textField_2, "cell 1 2");
		textField_2.setColumns(10);
		JComboBox comboBox_3 = new JComboBox(primary);
		panel_2.add(comboBox_3, "cell 2 2,growx");

		JSpinner spinner = new JSpinner();
		panel_2.add(spinner, "cell 3 2,growx");

		textField_11 = new JTextField();
		panel_2.add(textField_11, "cell 4 2,growx");
		textField_11.setColumns(10);
		String[] crossTrained = { "", "E", "E P", "E P U", "E P U W", "P",
				"P U", "P U W", "U", "U W", "U W E", "W", "W E", "W E P" };
		JComboBox comboBox_16 = new JComboBox(crossTrained);
		panel_2.add(comboBox_16, "cell 5 2,growx");

		JSpinner spinner_9 = new JSpinner();
		panel_2.add(spinner_9, "cell 6 2");

		textField_3 = new JTextField();
		panel_2.add(textField_3, "cell 1 3");
		textField_3.setColumns(10);

		JComboBox comboBox_4 = new JComboBox(primary);
		panel_2.add(comboBox_4, "cell 2 3,growx");

		JSpinner spinner_1 = new JSpinner();
		panel_2.add(spinner_1, "cell 3 3,growx");

		textField_12 = new JTextField();
		panel_2.add(textField_12, "cell 4 3,growx");
		textField_12.setColumns(10);

		JComboBox comboBox_17 = new JComboBox(crossTrained);
		panel_2.add(comboBox_17, "cell 5 3,growx");

		JSpinner spinner_10 = new JSpinner();
		panel_2.add(spinner_10, "cell 6 3");

		textField_4 = new JTextField();
		panel_2.add(textField_4, "cell 1 4");
		textField_4.setColumns(10);

		JComboBox comboBox_5 = new JComboBox(primary);
		panel_2.add(comboBox_5, "cell 2 4,growx");

		JSpinner spinner_2 = new JSpinner();
		panel_2.add(spinner_2, "cell 3 4,growx");

		textField_13 = new JTextField();
		panel_2.add(textField_13, "cell 4 4,growx");
		textField_13.setColumns(10);

		JComboBox comboBox_18 = new JComboBox(crossTrained);
		panel_2.add(comboBox_18, "cell 5 4,growx");

		JSpinner spinner_11 = new JSpinner();
		panel_2.add(spinner_11, "cell 6 4");

		textField_5 = new JTextField();
		panel_2.add(textField_5, "cell 1 5");
		textField_5.setColumns(10);

		JComboBox comboBox_6 = new JComboBox(primary);
		panel_2.add(comboBox_6, "cell 2 5,growx");

		JSpinner spinner_3 = new JSpinner();
		panel_2.add(spinner_3, "cell 3 5,growx");

		textField_14 = new JTextField();
		panel_2.add(textField_14, "cell 4 5,growx");
		textField_14.setColumns(10);

		JComboBox comboBox_19 = new JComboBox(crossTrained);
		panel_2.add(comboBox_19, "cell 5 5,growx");

		JSpinner spinner_12 = new JSpinner();
		panel_2.add(spinner_12, "cell 6 5");

		textField_6 = new JTextField();
		panel_2.add(textField_6, "cell 1 6");
		textField_6.setColumns(10);

		JComboBox comboBox_7 = new JComboBox(primary);
		panel_2.add(comboBox_7, "cell 2 6,growx");

		JSpinner spinner_4 = new JSpinner();
		panel_2.add(spinner_4, "cell 3 6,growx");

		textField_15 = new JTextField();
		panel_2.add(textField_15, "cell 4 6,growx");
		textField_15.setColumns(10);

		JComboBox comboBox_20 = new JComboBox(crossTrained);
		panel_2.add(comboBox_20, "cell 5 6,growx");

		JSpinner spinner_13 = new JSpinner();
		panel_2.add(spinner_13, "cell 6 6");

		textField_7 = new JTextField();
		panel_2.add(textField_7, "cell 1 7");
		textField_7.setColumns(10);

		JComboBox comboBox_8 = new JComboBox(primary);
		panel_2.add(comboBox_8, "cell 2 7,growx");

		JSpinner spinner_5 = new JSpinner();
		panel_2.add(spinner_5, "cell 3 7,growx");

		textField_16 = new JTextField();
		panel_2.add(textField_16, "cell 4 7,growx");
		textField_16.setColumns(10);

		JComboBox comboBox_21 = new JComboBox(crossTrained);
		panel_2.add(comboBox_21, "cell 5 7,growx");

		JSpinner spinner_14 = new JSpinner();
		panel_2.add(spinner_14, "cell 6 7");

		textField_8 = new JTextField();
		panel_2.add(textField_8, "cell 1 8");
		textField_8.setColumns(10);

		JComboBox comboBox_9 = new JComboBox(primary);
		panel_2.add(comboBox_9, "cell 2 8,growx");

		JSpinner spinner_6 = new JSpinner();
		panel_2.add(spinner_6, "cell 3 8,growx");

		textField_17 = new JTextField();
		panel_2.add(textField_17, "cell 4 8,growx");
		textField_17.setColumns(10);

		JComboBox comboBox_22 = new JComboBox(crossTrained);
		panel_2.add(comboBox_22, "cell 5 8,growx");

		JSpinner spinner_15 = new JSpinner();
		panel_2.add(spinner_15, "cell 6 8");

		textField_9 = new JTextField();
		panel_2.add(textField_9, "cell 1 9");
		textField_9.setColumns(10);

		JComboBox comboBox_10 = new JComboBox(primary);
		panel_2.add(comboBox_10, "cell 2 9,growx");

		JSpinner spinner_7 = new JSpinner();
		panel_2.add(spinner_7, "cell 3 9,growx");

		textField_18 = new JTextField();
		panel_2.add(textField_18, "cell 4 9,growx");
		textField_18.setColumns(10);

		JComboBox comboBox_23 = new JComboBox(crossTrained);
		panel_2.add(comboBox_23, "cell 5 9,growx");

		JSpinner spinner_16 = new JSpinner();
		panel_2.add(spinner_16, "cell 6 9");

		textField_10 = new JTextField();
		panel_2.add(textField_10, "cell 1 10");
		textField_10.setColumns(10);

		JComboBox comboBox_11 = new JComboBox(primary);
		panel_2.add(comboBox_11, "cell 2 10,growx");

		JSpinner spinner_8 = new JSpinner();
		panel_2.add(spinner_8, "cell 3 10,growx");

		textField_19 = new JTextField();
		panel_2.add(textField_19, "cell 4 10,growx");
		textField_19.setColumns(10);

		JComboBox comboBox_24 = new JComboBox(crossTrained);
		panel_2.add(comboBox_24, "cell 5 10,growx");

		JSpinner spinner_17 = new JSpinner();
		panel_2.add(spinner_17, "cell 6 10");

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		panel_2.add(btnSubmit, "cell 5 11,alignx center");
		OvertimeMain.model.addColumn("Name");
		OvertimeMain.model.addColumn("Seniority");
		OvertimeMain.model.addColumn("Qualified");
		OvertimeMain.model.addColumn("Hours");
		OvertimeMain.model.addColumn("Oncall #");

		OvertimeMain.model_3.addColumn("Name");
		OvertimeMain.model_3.addColumn("Seniority");
		OvertimeMain.model_3.addColumn("Primary Classification");
		OvertimeMain.model_3.addColumn("CrossTrained");
		OvertimeMain.model_3.addColumn("Hours");
		OvertimeMain.model_3.addColumn("Oncall #");

		init();
	}

	private void init() {
		sqlConnection();
		try {
			CallableStatement cs = null;
			cs = OvertimeMain.conn.prepareCall("{call EmployeeSelect()}");
			ResultSet rs = cs.executeQuery();
			int i = 0;
			model.setRowCount(0);
			employees.clear();
			employees.add("");
			comboBox_12.removeAllItems();
			while (rs.next()) {
				String t1 = rs.getString("name");
				Object t1a = t1;
				String t2 = rs.getString("seniority");
				Object t2a = t2;
				String t3 = rs.getString("primaryClass");
				Object t3a = t3;
				String t4 = rs.getString("hours");
				Object t4a = t4;
				String t5 = rs.getString("phoneNumber");
				Object t5a = t5;
				model.addRow(new Object[] { t1a, t2a, t3a, t4a, t5a });
				i++;
				employees.add(t1);
			}
			for (String str : employees) {
				comboBox_12.addItem(str);
			}
			cs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
		}
	}

	public static void sqlConnection() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("lib/config.properties"));
			String userName = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			String url = properties.getProperty("jdbc.url");
			Class.forName(properties.getProperty("jdbc.driver"));
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void displayCrew() {
		if (comboBox_1.getSelectedItem() == "A") {
			viewCrew("A");
		} else if (comboBox_1.getSelectedItem() == "B") {
			viewCrew("B");
		} else if (comboBox_1.getSelectedItem() == "C") {
			viewCrew("C");
		} else if (comboBox_1.getSelectedItem() == "D") {
			viewCrew("D");
		}
	}

	private void viewCrew(String localCrew) {
		sqlConnection();
		try {
			CallableStatement cs = null;
			cs = OvertimeMain.conn.prepareCall("{call SelectCrew(?)}");
			cs.setString(1, localCrew);
			ResultSet rs = cs.executeQuery();
			int i = 0;
			model_3.setRowCount(0);
			while (rs.next()) {
				String t1 = rs.getString("name");
				Object t1a = t1;
				String t2 = rs.getString("seniority");
				Object t2a = t2;
				String t3 = rs.getString("primaryClass");
				Object t3a = t3;
				String t4 = rs.getString("crossTrainedClass");
				Object t4a = t4;
				String t5 = rs.getString("hours");
				Object t5a = t5;
				String t6 = rs.getString("phoneNumber");
				Object t6a = t6;
				model_3.addRow(new Object[] { t1a, t2a, t3a, t4a, t5a, t6a });
				i++;
			}
			cs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
		}
	}

	private void update() {
		String localCrew = (String) comboBox_2.getSelectedItem();
		String name;

	}

	private void delete(String selectedItem) {
		sqlConnection();
		try {
			CallableStatement cs = null;
			cs = OvertimeMain.conn.prepareCall("{call DeleteCrew(?)}");
			cs.setString(1, (String) selectedItem);
			cs.execute();
			cs.close();
			model_3.setRowCount(0);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
		}
	}
}
