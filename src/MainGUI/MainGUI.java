package MainGUI;

import javax.swing.table.DefaultTableModel;
import java.io.*;

/**
 * @author Bilaal
 */
public class MainGUI extends javax.swing.JFrame {

    HashTable mainHashTable;
    String FORM_ADD_TITLE = "Add an employee";
    int hashTableLength = 2;

    public MainGUI() {
        this.mainHashTable = new HashTable(hashTableLength);
        initComponents();
    }

    public void updateTable() {
        employeeTable.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[8];
        for (int x = 0; x < hashTableLength; x++) {
            for (int empIndex = 0; empIndex < mainHashTable.buckets[x].size(); empIndex++) {

                rowData[0] = mainHashTable.buckets[x].get(empIndex).getEmployeeNumber();
                rowData[1] = mainHashTable.buckets[x].get(empIndex).getFirstName();
                rowData[2] = mainHashTable.buckets[x].get(empIndex).getLastName();
                switch (mainHashTable.buckets[x].get(empIndex).getSex()) {
                    case 0:
                        rowData[5] = "Male";
                        break;
                    case 1:
                        rowData[5] = "Female";
                        break;
                    default:
                        rowData[5] = "Other";
                        break;
                }
                rowData[4] = comboxWorkLocation.getModel().getElementAt(mainHashTable.buckets[x].get(empIndex).getWorkLocation());
                if (mainHashTable.buckets[x].get(empIndex) instanceof FullTimeEmployee){
                    rowData[5] = "Full Time Employee";
                }
                else if (mainHashTable.buckets[x].get(empIndex) instanceof PartTimeEmployee){
                    rowData[5] = "Part Time Employee";
                }
                rowData[6] = mainHashTable.buckets[x].get(empIndex).getDeductionsRate();
                if (mainHashTable.buckets[x].get(empIndex) instanceof FullTimeEmployee){
                    rowData[7] = ((FullTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome();
                }
                else if (mainHashTable.buckets[x].get(empIndex) instanceof PartTimeEmployee){
                    rowData[7] = ((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome();
                }
                model.addRow(rowData);
            }
        }
        employeeTable.repaint();
        employeeTable.setVisible(true);
    }

    public void saveTable() {
        try {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("employees.csv"), "UTF-8"))) {
                for (int x = 0; x < hashTableLength; x++) {
                    for (int empIndex = 0; empIndex < mainHashTable.buckets[x].size(); empIndex++) {
                        StringBuilder newLine = new StringBuilder();
                        if (mainHashTable.buckets[x].get(empIndex) instanceof FullTimeEmployee) {
                            newLine.append("FT");
                            newLine.append(",");
                            newLine.append(((FullTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getYearlySalary() < 0 ? "" : ((FullTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getYearlySalary());
                            newLine.append(",");
                            newLine.append(((FullTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome() < 0 ? "" : ((FullTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome());
                            newLine.append(",");
                        } else if (mainHashTable.buckets[x].get(empIndex) instanceof PartTimeEmployee) {
                            newLine.append("PT");
                            newLine.append(",");
                            newLine.append(((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getNumHours() < 0 ? "" : ((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getNumHours());
                            newLine.append(",");
                            newLine.append(((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getNumWeeks() < 0 ? "" : ((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getNumWeeks());
                            newLine.append(",");
                            newLine.append(((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getWage() < 0 ? "" : ((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).getWage());
                            newLine.append(",");
                            newLine.append(((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome() < 0 ? "" : ((PartTimeEmployee) mainHashTable.buckets[x].get(empIndex)).calcNetAnnualIncome());
                            newLine.append(",");
                        } else {
                            newLine.append("NT");
                            newLine.append(",");
                        }
                        newLine.append(mainHashTable.buckets[x].get(empIndex).getEmployeeNumber() < 0 ? "" : mainHashTable.buckets[x].get(empIndex).getEmployeeNumber());
                        newLine.append(",");
                        newLine.append(mainHashTable.buckets[x].get(empIndex).getFirstName() == null ? "" : mainHashTable.buckets[x].get(empIndex).getFirstName());
                        newLine.append(",");
                        newLine.append(mainHashTable.buckets[x].get(empIndex).getLastName() == null ? "" : mainHashTable.buckets[x].get(empIndex).getLastName());
                        newLine.append(",");
                        newLine.append((mainHashTable.buckets[x].get(empIndex).getSex() != 1) && (mainHashTable.buckets[x].get(empIndex).getSex() != 2) && (mainHashTable.buckets[x].get(empIndex).getSex() != 0) ? "" : mainHashTable.buckets[x].get(empIndex).getSex());
                        newLine.append(",");
                        newLine.append((mainHashTable.buckets[x].get(empIndex).getWorkLocation() != 0) && (mainHashTable.buckets[x].get(empIndex).getWorkLocation() != 1) && (mainHashTable.buckets[x].get(empIndex).getWorkLocation() != 2) ? "" : mainHashTable.buckets[x].get(empIndex).getWorkLocation());
                        newLine.append(",");
                        newLine.append((mainHashTable.buckets[x].get(empIndex).getDeductionsRate() < 0 || mainHashTable.buckets[x].get(empIndex).getDeductionsRate() > 1) ? "" : mainHashTable.buckets[x].get(empIndex).getDeductionsRate());
                        bw.write(newLine.toString());
                        bw.newLine();
                    }
                }
                bw.flush();
            }
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formAdd = new javax.swing.JDialog();
        panelLeft = new javax.swing.JPanel();
        labelFN = new javax.swing.JLabel();
        fieldFN = new javax.swing.JTextField();
        labelLN = new javax.swing.JLabel();
        fieldLN = new javax.swing.JTextField();
        labelEN = new javax.swing.JLabel();
        spinnerEN = new javax.swing.JSpinner();
        labelWorkLocation = new javax.swing.JLabel();
        comboxWorkLocation = new javax.swing.JComboBox<>();
        comboxSex = new javax.swing.JComboBox<>();
        labelSex = new javax.swing.JLabel();
        panelRight = new javax.swing.JPanel();
        btnSaveAndCreateNew = new javax.swing.JButton();
        btnSaveAndExit = new javax.swing.JButton();
        btnCancelAndExit = new javax.swing.JButton();
        panelUp = new javax.swing.JPanel();
        spinnerDeductionsRate = new javax.swing.JSpinner();
        labelRate = new javax.swing.JLabel();
        tabEType = new javax.swing.JTabbedPane();
        panelPartTime = new javax.swing.JPanel();
        labelHours = new javax.swing.JLabel();
        fieldHours = new javax.swing.JTextField();
        labelWeeks = new javax.swing.JLabel();
        fieldWeeks = new javax.swing.JTextField();
        labelWage = new javax.swing.JLabel();
        fieldWage = new javax.swing.JTextField();
        panelFullTime = new javax.swing.JPanel();
        labelAnnualSalary = new javax.swing.JLabel();
        fieldAnnualSalary = new javax.swing.JTextField();
        exitWindow = new javax.swing.JDialog();
        Cancel = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        saveWindow = new javax.swing.JDialog();
        Cancel1 = new javax.swing.JButton();
        Save1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelTable = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        botBtns = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        menubar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        labelFN.setText("First Name");

        fieldFN.setText("Enter name");
        fieldFN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFNActionPerformed(evt);
            }
        });

        labelLN.setText("Last Name");

        fieldLN.setText("Enter name");
        fieldLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldLNActionPerformed(evt);
            }
        });

        labelEN.setText("Employee Number");

        spinnerEN.setModel(new javax.swing.SpinnerNumberModel());

        labelWorkLocation.setText("Work Location");

        comboxWorkLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mississauga", "Toronto", "Chicago" }));
        comboxWorkLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxWorkLocationActionPerformed(evt);
            }
        });

        comboxSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        comboxSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxSexActionPerformed(evt);
            }
        });

        labelSex.setText("Sex");

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldFN)
                    .addComponent(fieldLN)
                    .addComponent(comboxSex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinnerEN)
                    .addComponent(comboxWorkLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFN)
                            .addComponent(labelLN)
                            .addComponent(labelSex)
                            .addComponent(labelEN)
                            .addComponent(labelWorkLocation))
                        .addGap(0, 154, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelFN, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelLN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboxSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelWorkLocation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboxWorkLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        btnSaveAndCreateNew.setText("Save + New");
        btnSaveAndCreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAndCreateNewActionPerformed(evt);
            }
        });

        btnSaveAndExit.setText("Save");
        btnSaveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAndExitActionPerformed(evt);
            }
        });

        btnCancelAndExit.setText("Cancel");
        btnCancelAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAndExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRightLayout = new javax.swing.GroupLayout(panelRight);
        panelRight.setLayout(panelRightLayout);
        panelRightLayout.setHorizontalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveAndCreateNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveAndExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelAndExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRightLayout.setVerticalGroup(
            panelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnSaveAndCreateNew, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSaveAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelAndExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        spinnerDeductionsRate.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.01d));

        labelRate.setText("Deductions Rate");

        labelHours.setText("Hours per Week");

        fieldHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldHoursActionPerformed(evt);
            }
        });

        labelWeeks.setText("Weeks per Year");

        fieldWeeks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldWeeksActionPerformed(evt);
            }
        });

        labelWage.setText("Wage");

        fieldWage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldWageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPartTimeLayout = new javax.swing.GroupLayout(panelPartTime);
        panelPartTime.setLayout(panelPartTimeLayout);
        panelPartTimeLayout.setHorizontalGroup(
            panelPartTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPartTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPartTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldHours)
                    .addComponent(fieldWeeks)
                    .addComponent(fieldWage)
                    .addGroup(panelPartTimeLayout.createSequentialGroup()
                        .addGroup(panelPartTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHours)
                            .addComponent(labelWeeks)
                            .addComponent(labelWage))
                        .addGap(0, 109, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPartTimeLayout.setVerticalGroup(
            panelPartTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPartTimeLayout.createSequentialGroup()
                .addComponent(labelHours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWeeks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldWeeks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldWage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        tabEType.addTab("Part-Time", panelPartTime);

        labelAnnualSalary.setText("Annual Salary");

        fieldAnnualSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAnnualSalaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFullTimeLayout = new javax.swing.GroupLayout(panelFullTime);
        panelFullTime.setLayout(panelFullTimeLayout);
        panelFullTimeLayout.setHorizontalGroup(
            panelFullTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFullTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFullTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFullTimeLayout.createSequentialGroup()
                        .addComponent(labelAnnualSalary)
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(fieldAnnualSalary))
                .addContainerGap())
        );
        panelFullTimeLayout.setVerticalGroup(
            panelFullTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFullTimeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelAnnualSalary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldAnnualSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabEType.addTab("Full-Time", panelFullTime);

        javax.swing.GroupLayout panelUpLayout = new javax.swing.GroupLayout(panelUp);
        panelUp.setLayout(panelUpLayout);
        panelUpLayout.setHorizontalGroup(
            panelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpLayout.createSequentialGroup()
                .addGroup(panelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelRate)
                    .addComponent(tabEType)
                    .addComponent(spinnerDeductionsRate))
                .addGap(0, 0, 0))
        );
        panelUpLayout.setVerticalGroup(
            panelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUpLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(labelRate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerDeductionsRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabEType)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout formAddLayout = new javax.swing.GroupLayout(formAdd.getContentPane());
        formAdd.getContentPane().setLayout(formAddLayout);
        formAddLayout.setHorizontalGroup(
            formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formAddLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(panelUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(panelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        formAddLayout.setVerticalGroup(
            formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formAddLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        exitWindow.setTitle("Exit");

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.setToolTipText("");
        Save.setMaximumSize(new java.awt.Dimension(85, 23));
        Save.setMinimumSize(new java.awt.Dimension(85, 23));
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Exit.setText("Don't Save");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel1.setText("Want to save your changes to this database before exiting??");

        javax.swing.GroupLayout exitWindowLayout = new javax.swing.GroupLayout(exitWindow.getContentPane());
        exitWindow.getContentPane().setLayout(exitWindowLayout);
        exitWindowLayout.setHorizontalGroup(
            exitWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(exitWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exitWindowLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(exitWindowLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Exit)
                        .addGap(18, 18, 18)
                        .addComponent(Cancel)
                        .addGap(60, 60, 60))))
        );
        exitWindowLayout.setVerticalGroup(
            exitWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(exitWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit)
                    .addComponent(Cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveWindow.setTitle("Exit");

        Cancel1.setText("Cancel");
        Cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel1ActionPerformed(evt);
            }
        });

        Save1.setText("Save");
        Save1.setToolTipText("");
        Save1.setMaximumSize(new java.awt.Dimension(85, 23));
        Save1.setMinimumSize(new java.awt.Dimension(85, 23));
        Save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Are you sure you want to save to the archive?");

        javax.swing.GroupLayout saveWindowLayout = new javax.swing.GroupLayout(saveWindow.getContentPane());
        saveWindow.getContentPane().setLayout(saveWindowLayout);
        saveWindowLayout.setHorizontalGroup(
            saveWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(saveWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(saveWindowLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cancel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveWindowLayout.setVerticalGroup(
            saveWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(saveWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Database");

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee Number", "First Name", "Last Name", "Sex", "Work Location", "Employee Type", "Deduction Rate", "Pay"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        employeeTable.setGridColor(new java.awt.Color(222, 222, 222));
        employeeTable.setShowHorizontalLines(false);
        employeeTable.setSurrendersFocusOnKeystroke(true);
        scrollPanel.setViewportView(employeeTable);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnAdd.setText("Add");
        btnAdd.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnRemove.setMaximumSize(null);
        btnRemove.setMinimumSize(null);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnOpen.setText("Open");
        btnOpen.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botBtnsLayout = new javax.swing.GroupLayout(botBtns);
        botBtns.setLayout(botBtnsLayout);
        botBtnsLayout.setHorizontalGroup(
            botBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botBtnsLayout.createSequentialGroup()
                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        botBtnsLayout.setVerticalGroup(
            botBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenu1.setText("File");
        jMenu1.setToolTipText("");
        menubar.add(jMenu1);

        jMenu2.setText("Edit");
        menubar.add(jMenu2);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botBtns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(botBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadTable() {
        String fileToParse = "employees.csv";
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(fileToParse));
            while ((line = fileReader.readLine()) != null) {
                String[] employeeAttributes = line.split(",");
                    if ("FT".equals(employeeAttributes[0])) {
                        mainHashTable.addEmployee(new FullTimeEmployee(
                                Integer.parseInt(employeeAttributes[3]),
                                employeeAttributes[4],
                                employeeAttributes[5],
                                Integer.parseInt(employeeAttributes[6]),
                                Integer.parseInt(employeeAttributes[7]),
                                Double.parseDouble(employeeAttributes[8]),
                                Double.parseDouble(employeeAttributes[1])));
                    } else if ("PT".equals(employeeAttributes[0])) {
                        mainHashTable.addEmployee(new PartTimeEmployee(
                                Integer.parseInt(employeeAttributes[5]),
                                employeeAttributes[6],
                                employeeAttributes[7],
                                Integer.parseInt(employeeAttributes[8]),
                                Integer.parseInt(employeeAttributes[9]),
                                Double.parseDouble(employeeAttributes[3]),
                                Double.parseDouble(employeeAttributes[10]),
                                Integer.parseInt(employeeAttributes[1]),
                                Integer.parseInt(employeeAttributes[2])));
                    } 
            }
        } catch (Exception e) {
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            }
        }
        mainHashTable.displayContents();
        updateTable();
    }

    private void clearAddForm() {
        fieldFN.setText("");
        fieldLN.setText("");
        comboxSex.setSelectedIndex(0);
        comboxWorkLocation.setSelectedIndex(0);
        spinnerDeductionsRate.setValue(0);
        fieldAnnualSalary.setText("");
        fieldWeeks.setText("");
        fieldHours.setText("");
        fieldWage.setText("");
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int row = employeeTable.getSelectedRow();
        if (row > -1) {
            mainHashTable.removeEmployee((int) employeeTable.getModel().getValueAt(row, 0));
        }
        updateTable();
        mainHashTable.displayContents();//debug
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        formAdd.setTitle(FORM_ADD_TITLE);
        formAdd.pack();
        fieldFN.setText("");
        fieldLN.setText("");

        formAdd.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        //sets the title of the panel (because the same panel is used for modifying and editing)
    }//GEN-LAST:event_btnAddMouseClicked

    private void fieldAnnualSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAnnualSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAnnualSalaryActionPerformed

    private void fieldWageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldWageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldWageActionPerformed

    private void fieldWeeksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldWeeksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldWeeksActionPerformed

    private void fieldHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldHoursActionPerformed

    private void btnSaveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAndExitActionPerformed
        btnSaveAndCreateNewActionPerformed(evt);
        formAdd.setVisible(false);
// updateTable();
    }//GEN-LAST:event_btnSaveAndExitActionPerformed

    private void btnSaveAndCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAndCreateNewActionPerformed
        if (tabEType.getSelectedComponent() == panelFullTime) {
            mainHashTable.addEmployee(new FullTimeEmployee(
                    (Integer) (spinnerEN.getValue()),
                    fieldFN.getText(),
                    fieldLN.getText(),
                    comboxSex.getSelectedIndex(),
                    comboxWorkLocation.getSelectedIndex(),
                    (Double) spinnerDeductionsRate.getValue(),
                    Integer.parseInt(fieldAnnualSalary.getText())));
        } else if (tabEType.getSelectedComponent() == panelPartTime) {
            mainHashTable.addEmployee(new PartTimeEmployee(
                    ((Integer) spinnerEN.getValue()),
                    fieldFN.getText(),
                    fieldLN.getText(),
                    comboxSex.getSelectedIndex(),
                    comboxWorkLocation.getSelectedIndex(),
                    Double.parseDouble(fieldWage.getText()),
                    (Double) spinnerDeductionsRate.getValue(),
                    Integer.parseInt(fieldHours.getText()),
                    Integer.parseInt(fieldWeeks.getText())));
        }
        clearAddForm();
        updateTable();
    }//GEN-LAST:event_btnSaveAndCreateNewActionPerformed

    private void comboxSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxSexActionPerformed

    private void comboxWorkLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxWorkLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxWorkLocationActionPerformed

    private void fieldLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldLNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldLNActionPerformed

    private void fieldFNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFNActionPerformed

    private void btnCancelAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAndExitActionPerformed
        formAdd.setVisible(false);
    }//GEN-LAST:event_btnCancelAndExitActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        exitWindow.setVisible(false);
    }//GEN-LAST:event_CancelActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        saveTable();
        System.exit(0);
    }//GEN-LAST:event_SaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        exitWindow.pack();
        exitWindow.setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveWindow.pack();
        saveWindow.setVisible(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel1ActionPerformed
        // TODO add your handling code here:
        saveWindow.setVisible(false);
    }//GEN-LAST:event_Cancel1ActionPerformed

    private void Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save1ActionPerformed
        // TODO add your handling code here:
        saveTable();
        saveWindow.setVisible(false);
    }//GEN-LAST:event_Save1ActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        loadTable();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            //sets look and feel
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Cancel1;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Save;
    private javax.swing.JButton Save1;
    private javax.swing.JPanel botBtns;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelAndExit;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAndCreateNew;
    private javax.swing.JButton btnSaveAndExit;
    private javax.swing.JComboBox<String> comboxSex;
    private javax.swing.JComboBox<String> comboxWorkLocation;
    private javax.swing.JTable employeeTable;
    private javax.swing.JDialog exitWindow;
    private javax.swing.JTextField fieldAnnualSalary;
    private javax.swing.JTextField fieldFN;
    private javax.swing.JTextField fieldHours;
    private javax.swing.JTextField fieldLN;
    private javax.swing.JTextField fieldWage;
    private javax.swing.JTextField fieldWeeks;
    private javax.swing.JDialog formAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel labelAnnualSalary;
    private javax.swing.JLabel labelEN;
    private javax.swing.JLabel labelFN;
    private javax.swing.JLabel labelHours;
    private javax.swing.JLabel labelLN;
    private javax.swing.JLabel labelRate;
    private javax.swing.JLabel labelSex;
    private javax.swing.JLabel labelWage;
    private javax.swing.JLabel labelWeeks;
    private javax.swing.JLabel labelWorkLocation;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JPanel panelFullTime;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelPartTime;
    private javax.swing.JPanel panelRight;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelUp;
    private javax.swing.JDialog saveWindow;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JSpinner spinnerDeductionsRate;
    private javax.swing.JSpinner spinnerEN;
    private javax.swing.JTabbedPane tabEType;
    // End of variables declaration//GEN-END:variables

}
