/**
 * 
 */
package adg.nonpostedreport.nonpostedreporter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The interactive component of the program. Allows the user to input both files
 * for comparing. Generates a tangible excel file of nonposted requisitions. The
 * user can also filter the nonposted requisitions by "requested by".
 * 
 * @author Courtney Ripoll
 *
 */
public class NonpostedReporterGUI extends JFrame implements ActionListener {

	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "Nonposted Requisition Reporter v1.5";
	/** Labels for user input */
	private final JLabel lblFileName = new JLabel("Desired File Name: ");
	private final JLabel lblCurrentADGJobs = new JLabel("Current ADG Jobs: ");
	private final JLabel lblMyCurrentReqs = new JLabel("My Current Requisitions: ");
	private final JLabel lblReportRan = new JLabel("Report Generated!");
	/** Components for user input */
	private JFileChooser currentADGJobs;
	private JFileChooser myCurrentRequisitions;
	private JTextField fileName;
	private JTextField filterBox;
	private JTextField currView;
	private JTextField myReqView;
	/** Panels for components */
	private final JPanel pnlInstructions = new JPanel();
	private final JPanel panel = new JPanel();
	private final JPanel credit = new JPanel();
	/** Filter string */
	private String filter = null;
	/** Check box for filter option */
	private JCheckBox checkbox = new JCheckBox("Apply Requested By Filter?");

	/** Components for user input */
	private final JButton btnRun = new JButton("Run Report");
	private final JButton currReqBtn = new JButton("Select File");
	private final JButton myReqBtn = new JButton("Select File");
	private final JButton x = new JButton("Development Information");

	/**
	 * Constructions the graphical user interface and its interactive
	 * components. Sets them visible for the user to interact with and use
	 * appropriately.
	 */
	public NonpostedReporterGUI() {
		super();

		// Set up general GUI info
		setSize(705, 455);
		setLocation(505, 150);
		setTitle(APP_TITLE);
		ImageIcon img = new ImageIcon("doc/adglogo.png");
		this.setIconImage(img.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Add panels to the container
		Container container = getContentPane();
		container.add(pnlInstructions, BorderLayout.NORTH);
		container.add(panel, BorderLayout.CENTER);
		container.add(credit, BorderLayout.SOUTH);

		// Instructions panel components
		setUpButtonsAndTextFields();
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Instructions");
		pnlInstructions.setBorder(border);
		pnlInstructions.add(getInstructions());

		// Requisition File Information panel components
		TitledBorder border2 = BorderFactory.createTitledBorder(lowerEtched, "Requisition File Information");
		panel.setBorder(border2);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(lblFileName, c);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(fileName, c);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(lblCurrentADGJobs, c);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(currView, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(currReqBtn, c);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(lblMyCurrentReqs, c);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(myReqView, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(myReqBtn, c);
		checkbox.setSelected(false);
		// Check box for filter option
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(checkbox, c);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(filterBox, c);
		// Run Report button
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(btnRun, c);

		// Label for "Report Generated"
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		lblReportRan.setVisible(false);
		lblReportRan.setForeground(Color.RED);
		panel.add(lblReportRan, c);

		// Clicking the check box disables filter text field
		checkbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					filterBox.setEnabled(true);
					filterBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				} else {
					filter = null;
					filterBox.setEnabled(false);
					filterBox.setBorder(UIManager.getBorder("TextField.border"));
				}
			}
		});

		// Development Information
		x.setCursor(new Cursor(Cursor.HAND_CURSOR));
		x.setForeground(Color.decode("#75a4ef"));
		x.addActionListener(this);
		x.setBorderPainted(false);
		x.setContentAreaFilled(false);
		x.setFocusPainted(false);
		x.setOpaque(false);
		x.setPreferredSize(new Dimension(250, 17));
		credit.add(x);

		setResizable(false);
		// Set the GUI visible
		setVisible(true);
	}

	/**
	 * Starts the GUI for the Nonposted Reporter application.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		new NonpostedReporterGUI();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String currFile = null;
		String myReqFile = null;
		if (event.getSource() == currReqBtn) {
			int val = currentADGJobs.showOpenDialog(this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = currentADGJobs.getSelectedFile();
				currFile = file.getAbsolutePath();
				currView.setText(currFile);
			}
		}
		if (event.getSource() == myReqBtn) {
			int val = myCurrentRequisitions.showOpenDialog(this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = myCurrentRequisitions.getSelectedFile();
				myReqFile = file.getAbsolutePath();
				myReqView.setText(myReqFile);
			}
		}
		if (event.getSource() == btnRun) {
			try {
				if (checkbox.isSelected() && filterBox.getText().length() != 0) {
					filter = filterBox.getText();
				}
				new NonpostedReporter(fileName.getText(), currView.getText(), myReqView.getText(), filter);
				lblReportRan.setVisible(true);
				setSize(705, 475);
				// "Report Generated" will remain on screen for 8 seconds
				Thread timer = new Thread() {
					public void run() {
						try {
							Thread.sleep(8000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lblReportRan.setVisible(false);
						setSize(705, 455);
					}
				};
				timer.start();
			} catch (Exception e) {
				if (fileName.getText().equals("")) {
					JOptionPane.showMessageDialog(this,
							"<html><center>No file name given.</center><center>Please type in a file name.</center></html>",
							"No Output File Name", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"<html><center>The report was not generated.</center><center>Please check the file parameters.</center></html>",
							"Error Parsing Files", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (event.getSource() == x) {
			JOptionPane op = new JOptionPane(new JLabel(
					"<html><center>Developed by Courtney Ripoll [ctripoll@ncsu.edu]</center><center>For Automotive Development Group</center><center>Current Version: 1.5</center><center>2017.8.9</center></html>",
					JLabel.CENTER));
			op.setMessageType(JOptionPane.INFORMATION_MESSAGE);
			JDialog dialog = op.createDialog("Program Development Information");
			dialog.setLocationRelativeTo(this);
			dialog.setModal(true);
			dialog.setVisible(true);
		}
	}

	/**
	 * Sets up the buttons and text fields that go across the top of the window.
	 */
	private void setUpButtonsAndTextFields() {
		btnRun.addActionListener(this);
		currReqBtn.addActionListener(this);
		myReqBtn.addActionListener(this);

		FileNameExtensionFilter xlsx = new FileNameExtensionFilter("Excel Workbook", "xlsx");

		currView = new JTextField(20);
		currView.setEditable(false);
		currView.setBackground(Color.WHITE);
		currView.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		myReqView = new JTextField(20);
		myReqView.setEditable(false);
		myReqView.setBackground(Color.WHITE);
		myReqView.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		fileName = new JTextField(20);
		fileName.setBackground(Color.WHITE);
		fileName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		currentADGJobs = new JFileChooser();
		currentADGJobs.setFileFilter(xlsx);
		myCurrentRequisitions = new JFileChooser();
		myCurrentRequisitions.setFileFilter(xlsx);
		filterBox = new JTextField(20);
		filterBox.setEnabled(false);
		fileName.addActionListener(this);
		currentADGJobs.addActionListener(this);
		myCurrentRequisitions.addActionListener(this);
		filterBox.addActionListener(this);
		filterBox.setBackground(Color.WHITE);
	}

	/**
	 * Gets the instructions for the program.
	 * 
	 * @return the instructions for the program.
	 */
	private Component getInstructions() {
		return new JLabel(
				"<html>Log-on to Taleo. Click on the \"Requisitions\" tab. In \"Requisitions\", click on \"Current ADG Jobs\". Export these results by <br>clicking on the option \"Export Results\". Save this file as an Excel Workbook (.xlsx).<br>i.e. Save As 'desired_file_name.xlsx' OR Open the downloaded file. In Excel, click \"File\", \"Save As\" and change the <br>\"Save as type\" option to Excel Workbook.<br>Next, hover over \"More\" then click on \"My Current Requisitions (ADG)\". Follow the same procedure to export the results <br>and save them as an Excel Workbook.<br><br>1. In \"Desired File Name\", type in a desired report name (Do NOT include a file extension).<br>2. In \"Current ADG Jobs\", find and select the file saved for Current ADG Jobs from Taleo.<br>3. In \"My Current Requisitions\", find and select the file saved for My Current Requisitions (ADG) from Taleo.<br>4. (Optional) If you would like to filter results by job requestor, select the checkbox and type in the name of the<br> desired requestor. To remove the filter, uncheck the box.<br>5. Click on Run Report. Check your desktop for the report files.</html>");
	}
}
