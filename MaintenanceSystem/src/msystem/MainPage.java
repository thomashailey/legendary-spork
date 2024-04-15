/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package msystem;

/**
 *
 * @author thoma
 */
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.lang.String;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import msystem.Employee;
import msystem.Equipment;

public class MainPage extends javax.swing.JFrame {

    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    Employee emp = new Employee();
    static boolean editEmployee = false;
    String sql = null;
    static String pullThisUserIdForEdit = null;
    static String selectingThisUserID = null;
    static String selectingThisUsername = null;
    static String selectingThisPassword = null;
    static String selectingThisRole = null;
    static String selectingThisEndorsement = null;
    Equipment equip = new Equipment();
    static boolean NewLog = false;
    static ArrayList<String> nullArray = new ArrayList<>();
    private int fileCounter = 1; // Counter for generating unique file names
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
    String currentDate = simpleDate.format(new java.util.Date());


    // Assuming reportDetailsField is a JTextArea
    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
//        try {
//            LimitAccess();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
        AccessReportInfo();
        AccessEmployeeInfo();
        AccessInventoryInfo();
        AccessInventoryRepportInfo();
        AccessMaintInfo();
        
        String role;
        try {
            role = LimitAccess();
            switch (role) {
                case "Technician":

                    // 0: Equipment
                    // 1: Inventory
                    // 2: Maintenance
                    // 3: Report
                    // 4: Employee
                    
                    // Technician gets access to:
                    // Equipment, Inventory
                    tabPanePanel.setEnabledAt(0, true);
                    tabPanePanel.setEnabledAt(1, true);
                    tabPanePanel.setEnabledAt(2, false);
                    tabPanePanel.setEnabledAt(3, false);
                    tabPanePanel.setEnabledAt(4, false);
                    break;
                case "Maintenance":
                    // Maintenance gets access to:
                    // Equipment, Inventory, Maintenance, Report
                    tabPanePanel.setEnabledAt(0, true);
                    tabPanePanel.setEnabledAt(1, true);
                    tabPanePanel.setEnabledAt(2, true);
                    tabPanePanel.setEnabledAt(3, true);
                    tabPanePanel.setEnabledAt(4, false);
                    break;
                case "Warehouse":
                    // Warehouse gets access to:
                    // Equipment, Inventory, Report
                    tabPanePanel.setEnabledAt(0, true);
                    tabPanePanel.setEnabledAt(1, true);
                    tabPanePanel.setEnabledAt(2, false);
                    tabPanePanel.setEnabledAt(3, true);
                    tabPanePanel.setEnabledAt(4, false);
                    break;
                case "Management":
                    // Management gets access to:
                    // Equipment, Inventory, Maintenance, Report, Employee
                    tabPanePanel.setEnabledAt(0, true);
                    tabPanePanel.setEnabledAt(1, true);
                    tabPanePanel.setEnabledAt(2, true);
                    tabPanePanel.setEnabledAt(3, true);
                    tabPanePanel.setEnabledAt(4, true);
                    break;
                case "Admin":
                    // Admin gets access to:
                    // Equipment, Inventory, Maintenance, Report, Employee
                    tabPanePanel.setEnabledAt(0, true);
                    tabPanePanel.setEnabledAt(1, true);
                    tabPanePanel.setEnabledAt(2, true);
                    tabPanePanel.setEnabledAt(3, true);
                    tabPanePanel.setEnabledAt(4, true);
                    break;
                default:
                    break;
        }
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tabPanePanel = new javax.swing.JTabbedPane();
        equipmentTab = new javax.swing.JPanel();
        lblToolname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTools = new javax.swing.JList<>();
        lblTooldescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaTooldescription = new javax.swing.JTextArea();
        btnCheckavailable = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        btnCheckin = new javax.swing.JButton();
        btnReportloss = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        equipmentPullAllBtn = new javax.swing.JButton();
        inventoryTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtInvSearch = new javax.swing.JTextField();
        btnInventorySearch = new javax.swing.JButton();
        btnInventoryAdd = new javax.swing.JButton();
        btnInventoryRequest = new javax.swing.JButton();
        lblInventoryRecord = new javax.swing.JLabel();
        btnInventoryRecordSearch = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        invMainList = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        mainPageInvRequestsLst = new javax.swing.JList<>();
        inventoryPullOnlyUnfulfilledBtn = new javax.swing.JButton();
        inventoryPullAllReportsBtn = new javax.swing.JButton();
        maintenanceTab = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        maintSearchEmpBtn = new javax.swing.JButton();
        maintPullAll = new javax.swing.JButton();
        maintSearchDateBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        maintLogBtn = new javax.swing.JButton();
        reportTab = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        reportList = new javax.swing.JList<>();
        reportListLabel = new javax.swing.JLabel();
        reportSearchField = new javax.swing.JTextField();
        reportSearchBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        reportDetailsField = new javax.swing.JTextArea();
        reportPrint = new javax.swing.JButton();
        reportLoadAllBtn = new javax.swing.JButton();
        reportAddButton = new javax.swing.JButton();
        Employee = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        empList = new javax.swing.JList<>();
        empListLabel = new javax.swing.JLabel();
        empFirstNameSearchField = new javax.swing.JTextField();
        empSearchBtn = new javax.swing.JButton();
        empAddBtn = new javax.swing.JButton();
        empRemoveBtn = new javax.swing.JButton();
        empEditBtn = new javax.swing.JButton();
        empLoadAllBtn = new javax.swing.JButton();
        empLastInitSearchField = new javax.swing.JTextField();
        mainMenuBar = new javax.swing.JMenuBar();
        logOutBtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblToolname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblToolname.setText("Tool Name:");

        lstTools.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        lstTools.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstTools.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstToolsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstTools);

        lblTooldescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTooldescription.setText("Tool Description:");

        txaTooldescription.setColumns(30);
        txaTooldescription.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        txaTooldescription.setRows(5);
        jScrollPane2.setViewportView(txaTooldescription);

        btnCheckavailable.setText("Check");

        btnCheckout.setText("Check Out");

        btnCheckin.setText("Check In");

        btnReportloss.setBackground(new java.awt.Color(255, 51, 51));
        btnReportloss.setText("REPORT LOST");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tool Summary:");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        equipmentPullAllBtn.setText("Pull All");
        equipmentPullAllBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipmentPullAllBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout equipmentTabLayout = new javax.swing.GroupLayout(equipmentTab);
        equipmentTab.setLayout(equipmentTabLayout);
        equipmentTabLayout.setHorizontalGroup(
            equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, equipmentTabLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblToolname)
                .addGap(125, 125, 125)
                .addComponent(lblTooldescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(145, 145, 145))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, equipmentTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(equipmentTabLayout.createSequentialGroup()
                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(equipmentTabLayout.createSequentialGroup()
                        .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(equipmentTabLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnCheckavailable))
                            .addGroup(equipmentTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnReportloss)))
                        .addGap(115, 115, 115)
                        .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCheckout)
                            .addComponent(btnCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(equipmentTabLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(equipmentPullAllBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        equipmentTabLayout.setVerticalGroup(
            equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentTabLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblToolname)
                    .addComponent(lblTooldescription)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckavailable)
                    .addComponent(btnCheckout))
                .addGap(18, 18, 18)
                .addGroup(equipmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckin)
                    .addComponent(btnReportloss))
                .addGap(18, 18, 18)
                .addComponent(equipmentPullAllBtn)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        tabPanePanel.addTab("Epuipment", equipmentTab);

        jLabel2.setText("Inventory:");

        btnInventorySearch.setText("Search");
        btnInventorySearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventorySearchMouseClicked(evt);
            }
        });

        btnInventoryAdd.setText("Add");
        btnInventoryAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventoryAddMouseClicked(evt);
            }
        });

        btnInventoryRequest.setText("Request");
        btnInventoryRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventoryRequestMouseClicked(evt);
            }
        });

        lblInventoryRecord.setText("Inventory Record");

        btnInventoryRecordSearch.setText("Open Record");
        btnInventoryRecordSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventoryRecordSearchMouseClicked(evt);
            }
        });

        invMainList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(invMainList);

        mainPageInvRequestsLst.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        mainPageInvRequestsLst.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                mainPageInvRequestsLstValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(mainPageInvRequestsLst);

        inventoryPullOnlyUnfulfilledBtn.setText("Pull only Unfulfilled Reports");
        inventoryPullOnlyUnfulfilledBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryPullOnlyUnfulfilledBtnMouseClicked(evt);
            }
        });

        inventoryPullAllReportsBtn.setText("Pull All Reports");
        inventoryPullAllReportsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryPullAllReportsBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventoryTabLayout.createSequentialGroup()
                        .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inventoryTabLayout.createSequentialGroup()
                                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(inventoryTabLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryTabLayout.createSequentialGroup()
                                        .addComponent(txtInvSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnInventorySearch)))
                                .addGap(24, 24, 24)
                                .addComponent(btnInventoryAdd))
                            .addComponent(jScrollPane11))
                        .addGap(18, 18, 18)
                        .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(inventoryTabLayout.createSequentialGroup()
                                    .addComponent(btnInventoryRequest)
                                    .addGap(243, 243, 243)
                                    .addComponent(btnInventoryRecordSearch))
                                .addComponent(jScrollPane9))
                            .addComponent(lblInventoryRecord))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventoryPullAllReportsBtn)
                            .addComponent(inventoryPullOnlyUnfulfilledBtn))
                        .addGap(84, 84, 84))))
        );
        inventoryTabLayout.setVerticalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblInventoryRecord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addGap(20, 20, 20)
                .addGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventorySearch)
                    .addComponent(btnInventoryRequest)
                    .addComponent(btnInventoryRecordSearch)
                    .addComponent(btnInventoryAdd)
                    .addComponent(inventoryPullAllReportsBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryPullOnlyUnfulfilledBtn)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tabPanePanel.addTab("Inventory", inventoryTab);

        maintSearchEmpBtn.setText("Search by Employee ID");
        maintSearchEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintSearchEmpBtnActionPerformed(evt);
            }
        });

        maintPullAll.setText("Pull All Logs");
        maintPullAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintPullAllActionPerformed(evt);
            }
        });

        maintSearchDateBtn.setText("Search by Date");
        maintSearchDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintSearchDateBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Maintenance Logs:");

        jList1.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        jScrollPane7.setViewportView(jList1);

        maintLogBtn.setText("New Log");
        maintLogBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maintLogBtnMouseClicked(evt);
            }
        });
        maintLogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintLogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maintenanceTabLayout = new javax.swing.GroupLayout(maintenanceTab);
        maintenanceTab.setLayout(maintenanceTabLayout);
        maintenanceTabLayout.setHorizontalGroup(
            maintenanceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(maintenanceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maintenanceTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(maintenanceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maintSearchEmpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(maintPullAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maintSearchDateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maintLogBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(maintenanceTabLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        maintenanceTabLayout.setVerticalGroup(
            maintenanceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(maintenanceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(maintenanceTabLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(maintSearchEmpBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maintPullAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maintSearchDateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maintLogBtn))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tabPanePanel.addTab("Maintenance", maintenanceTab);

        reportList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                reportListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(reportList);

        reportListLabel.setText("Report List:");

        reportSearchField.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        reportSearchField.setText("User ID");
        reportSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                reportSearchFieldFocusGained(evt);
            }
        });

        reportSearchBtn.setText("Search");
        reportSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportSearchBtnMouseClicked(evt);
            }
        });

        reportDetailsField.setColumns(20);
        reportDetailsField.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        reportDetailsField.setLineWrap(true);
        reportDetailsField.setRows(5);
        jScrollPane5.setViewportView(reportDetailsField);

        reportPrint.setText("Print");
        reportPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPrintMouseClicked(evt);
            }
        });

        reportLoadAllBtn.setText("Load All");
        reportLoadAllBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportLoadAllBtnMouseClicked(evt);
            }
        });

        reportAddButton.setText("Add");
        reportAddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportAddButtonMouseClicked(evt);
            }
        });
        reportAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportAddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportTabLayout = new javax.swing.GroupLayout(reportTab);
        reportTab.setLayout(reportTabLayout);
        reportTabLayout.setHorizontalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportTabLayout.createSequentialGroup()
                        .addComponent(reportListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(reportTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reportTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(reportTabLayout.createSequentialGroup()
                                        .addComponent(reportSearchField)
                                        .addGap(18, 18, 18)
                                        .addComponent(reportSearchBtn))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)))
                            .addGroup(reportTabLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reportAddButton)
                                .addGap(18, 18, 18)
                                .addComponent(reportLoadAllBtn)
                                .addGap(18, 18, 18)
                                .addComponent(reportPrint)))))
                .addContainerGap())
        );
        reportTabLayout.setVerticalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(reportListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportTabLayout.createSequentialGroup()
                        .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reportSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reportSearchBtn))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18)
                .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportPrint)
                    .addComponent(reportLoadAllBtn)
                    .addComponent(reportAddButton))
                .addGap(15, 15, 15))
        );

        tabPanePanel.addTab("Report", reportTab);

        empList.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        empList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        empList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                empListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(empList);

        empListLabel.setText("Employees List:");

        empFirstNameSearchField.setText("First Name");
        empFirstNameSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                empFirstNameSearchFieldFocusGained(evt);
            }
        });

        empSearchBtn.setText("Search");
        empSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empSearchBtnMouseClicked(evt);
            }
        });

        empAddBtn.setText("Add");
        empAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empAddBtnMouseClicked(evt);
            }
        });

        empRemoveBtn.setText("Remove");
        empRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empRemoveBtnMouseClicked(evt);
            }
        });

        empEditBtn.setText("Edit");
        empEditBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empEditBtnMouseClicked(evt);
            }
        });

        empLoadAllBtn.setText("Load All");
        empLoadAllBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empLoadAllBtnMouseClicked(evt);
            }
        });

        empLastInitSearchField.setText("Last Initial");
        empLastInitSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                empLastInitSearchFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout EmployeeLayout = new javax.swing.GroupLayout(Employee);
        Employee.setLayout(EmployeeLayout);
        EmployeeLayout.setHorizontalGroup(
            EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addComponent(empListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addComponent(empFirstNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(empLastInitSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(empSearchBtn)
                        .addGap(18, 18, 18)
                        .addComponent(empLoadAllBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                        .addComponent(empAddBtn)
                        .addGap(18, 18, 18)
                        .addComponent(empEditBtn)
                        .addGap(18, 18, 18)
                        .addComponent(empRemoveBtn)))
                .addContainerGap())
        );
        EmployeeLayout.setVerticalGroup(
            EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empFirstNameSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empSearchBtn)
                    .addComponent(empAddBtn)
                    .addComponent(empRemoveBtn)
                    .addComponent(empEditBtn)
                    .addComponent(empLoadAllBtn)
                    .addComponent(empLastInitSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        tabPanePanel.addTab("Employee", Employee);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanePanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanePanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        logOutBtn.setText("Log Out");
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutBtnMouseClicked(evt);
            }
        });
        mainMenuBar.add(logOutBtn);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutBtnMouseClicked
        // TODO add your handling code here:
        try {
            FileWriter myWriter = new FileWriter("currentuser.txt");
            myWriter.write("");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.setVisible(false);
        new UserAuth().setVisible(true);
    }//GEN-LAST:event_logOutBtnMouseClicked

    private void empLoadAllBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empLoadAllBtnMouseClicked
        // TODO add your handling code here:
        AccessEmployeeInfo();
    }//GEN-LAST:event_empLoadAllBtnMouseClicked

    private void reportLoadAllBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportLoadAllBtnMouseClicked
        // TODO add your handling code here:
        AccessReportInfo();
    }//GEN-LAST:event_reportLoadAllBtnMouseClicked

    private void reportSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportSearchBtnMouseClicked
        // TODO add your handling code here:
        ArrayList<String> list = new ArrayList<String>();
        try {
            int input = Integer.parseInt(reportSearchField.getText());
            try {
                list = emp.SearchReports(input);
                DefaultListModel model = new DefaultListModel();
                model.addAll(list);
                reportList.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid User ID");
        }
    }//GEN-LAST:event_reportSearchBtnMouseClicked

    private void reportPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPrintMouseClicked
        // TODO add info from details box into a new form that will be saved as a file to the user's device.
        if(reportList.getSelectedValue() != null){
            try{
                saveToFile("report" + reportList.getSelectedValue() + "_" + currentDate + ".txt");
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println("MainPage.reportPrintMouseClicked");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a report to save.");
        }
    }//GEN-LAST:event_reportPrintMouseClicked

//     private String generateFileName() {
//       
//        return "report_" + reportList.getSelectedValue() + "_" + currentDate + ".txt";
//    }
    
    private void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String reportContent = emp.PrintReportLogs(reportList.getSelectedValue());
            writer.write(reportContent);
            JOptionPane.showMessageDialog(null, "Report saved to file: " + fileName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving report to file: " + fileName);
            ex.printStackTrace();
        }
    }



    private void reportListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_reportListValueChanged
        try {
            // TODO add your handling code here:\
            String selectedReportID = reportList.getSelectedValue();
            System.out.println(selectedReportID);
            String list = emp.ReportDetails(selectedReportID);
            reportDetailsField.setText(list);
            //ViewReportDetails();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        String list = "Nothing";
        try {
            list = emp.ReportDetails(reportList.getSelectedValue());
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportDetailsField.setText(list);*/
    }//GEN-LAST:event_reportListValueChanged

    private void btnInventorySearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventorySearchMouseClicked
        // TODO add your handling code here:
        if (txtInvSearch.getText().equals("")) {
            System.out.println("pulling all inventory successful");
            AccessInventoryInfo();

        } else {
            System.out.println("Search successful");
            SearchInventory();
        }
    }//GEN-LAST:event_btnInventorySearchMouseClicked

    private void empAddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empAddBtnMouseClicked
        // TODO add your handling code here:
        new EditEmployee().setVisible(true);
        EditEmployee.passwordEdit = true;
    }//GEN-LAST:event_empAddBtnMouseClicked

    private void empRemoveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empRemoveBtnMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showInputDialog("Are you sure you want to delete this user? Please enter DELETE to confirm action (case specific)").equals("DELETE")) {
            emp.RemoveEmpInfo(pullThisUserIdForEdit);
            //sql = String.format("DELETE FROM user_authoization WHERE UserID = %s", pullThisUserIdForEdit);
            //System.out.println(sql);
            //JOptionPane.showMessageDialog(null, sql);
        } else {
            JOptionPane.showMessageDialog(null, "Data not removed, confirmation entered incorrectly");
        }
    }//GEN-LAST:event_empRemoveBtnMouseClicked

    private void empListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_empListValueChanged
        // TODO add your handling code here:
        //adding all code of selecting item here in order to not run it multiple times via the edit and delete button
        String test = empList.getSelectedValue();
        //trying to split this string into a list using .spit(",")
        if (test != null) {
            var Test = new ArrayList<String>(Arrays.asList(test.split(",")));
            pullThisUserIdForEdit = Test.get(0).trim();
            System.out.println(pullThisUserIdForEdit);
        }
    }//GEN-LAST:event_empListValueChanged

    private void empSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empSearchBtnMouseClicked
        // TODO add your handling code here:
        String searchFirstNameText = empFirstNameSearchField.getText().trim();
        String searchLastInitText = empLastInitSearchField.getText().trim();
        String searchText = null;
        boolean fName = false;
        boolean lName = false;

        if (!searchFirstNameText.equals("") && !searchFirstNameText.equals("First Name")) {
            //System.out.println(searchFirstNameText);
            fName = true;
            searchText = searchFirstNameText;
        }
        if (!searchLastInitText.equals("") && !searchLastInitText.equals("Last Initial")) {
            //System.out.println(searchLastInitText);
            lName = true;
            searchText = String.format("%s%s", searchText, searchLastInitText);
        }
        System.out.println(searchText);
        if (!fName && !lName) {
            return;
        }
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.SearchEmployees(fName, lName, searchFirstNameText, searchLastInitText);
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            empList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_empSearchBtnMouseClicked

    private void empFirstNameSearchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_empFirstNameSearchFieldFocusGained
        // TODO add your handling code here:
        if (!empFirstNameSearchField.getText().equals("")) {
            empFirstNameSearchField.setText("");
        }
    }//GEN-LAST:event_empFirstNameSearchFieldFocusGained

    private void empLastInitSearchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_empLastInitSearchFieldFocusGained
        // TODO add your handling code here:
        if (!empLastInitSearchField.getText().equals("")) {
            empLastInitSearchField.setText("");
        }
    }//GEN-LAST:event_empLastInitSearchFieldFocusGained

    private void empEditBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empEditBtnMouseClicked
        // TODO add your handling code here:
        //TODO add your handling code here:
        //this.setVisible(false);
        editEmployee = true;
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.PullEmpInfo(pullThisUserIdForEdit);
            selectingThisUserID = pullThisUserIdForEdit;
            selectingThisUsername = list.get(1);
            selectingThisPassword = null;
            selectingThisRole = list.get(2);
            selectingThisEndorsement = list.get(3);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        new EditEmployee().setVisible(true);
    }//GEN-LAST:event_empEditBtnMouseClicked

    private void reportAddButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportAddButtonMouseClicked
        // TODO add your handling code here:
        new AddReport().setVisible(true);
    }//GEN-LAST:event_reportAddButtonMouseClicked

    private void reportSearchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reportSearchFieldFocusGained
        // TODO add your handling code here:
        reportSearchField.setText("");
    }//GEN-LAST:event_reportSearchFieldFocusGained

    private void maintSearchEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintSearchEmpBtnActionPerformed
        // TODO add your handling code here:
        ArrayList<String> list = new ArrayList<String>();
        try {
            String input = String.format(jTextField1.getText());
            try {
                list = emp.PullMaintenanceActivities(jTextField1.getText());
                DefaultListModel model = new DefaultListModel();
                model.addAll(list);
                jList1.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid User ID");
        }
    }//GEN-LAST:event_maintSearchEmpBtnActionPerformed

    private void maintPullAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintPullAllActionPerformed
        // TODO add your handling code here:
        // do we even have a category for these? we could change this to a pull all button >>>>Love it
        AccessMaintInfo();
    }//GEN-LAST:event_maintPullAllActionPerformed

    private void maintSearchDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintSearchDateBtnActionPerformed
        // TODO add your handling code here:
        ArrayList<String> list = new ArrayList<String>();
        try {

            String input = String.format(jTextField1.getText());
            try {
                String pattern = "yyyy-MM-dd";
                list = emp.PullMaintenanceActivitiesDate(jTextField1.getText());
                DefaultListModel model = new DefaultListModel();
                model.addAll(list);
                jList1.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Date");
        }
    }//GEN-LAST:event_maintSearchDateBtnActionPerformed

    private void maintLogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintLogBtnActionPerformed
        // TODO add your handling code here:
        NewLog = true;
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.PullEmpInfo(pullThisUserIdForEdit);
            selectingThisUserID = pullThisUserIdForEdit;
            selectingThisUsername = list.get(1);
            selectingThisPassword = null;
            selectingThisRole = list.get(2);
            selectingThisEndorsement = list.get(3);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }

        new NewLog().setVisible(true);
    }//GEN-LAST:event_maintLogBtnActionPerformed

    private void reportAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportAddButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportAddButtonActionPerformed

    private void maintLogBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maintLogBtnMouseClicked
        // TODO add your handling code here:

        new NewLog().setVisible(true);

    }//GEN-LAST:event_maintLogBtnMouseClicked

    private void equipmentPullAllBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipmentPullAllBtnMouseClicked
        // TODO add your handling code here:
        System.out.println("Equipment Pull All Btn");
        sql = "SELECT * FROM equipment";
        ArrayList<String> elements = new ArrayList<>();
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            if (result != null) {
                System.out.println("Successfully accessed database to pull all equipment.");
            }
            while (result.next()) {
                elements.add(String.format("%5s-%4s %s", result.getString("EquipmentIDChar"), result.getString("EquipmentIDNum"), result.getString("EquipmentName")));
            }
            con.close();
            System.out.println("Database closed.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("MainPage.equipmentPullAllBtnMouseClicked");
        }
        DefaultListModel model = new DefaultListModel();
        model.addAll(elements);
        lstTools.setModel(model);
    }//GEN-LAST:event_equipmentPullAllBtnMouseClicked

    private void lstToolsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstToolsValueChanged
        // TODO add your handling code here:
        String equipPullThisCharacterID = null;
        String equipPullThisCharacterNum = null;
        String equipListSelection = lstTools.getSelectedValue().substring(0, 10).trim();
        System.out.println(equipListSelection);
        if (equipListSelection != null) {
            var equipmentListSelectionToList = new ArrayList<String>(Arrays.asList(equipListSelection.split("-")));
            System.out.println(equipmentListSelectionToList);
            equipPullThisCharacterID = equipmentListSelectionToList.get(0).trim();
            equipPullThisCharacterNum = equipmentListSelectionToList.get(1).trim();

        }
        System.out.println(equipPullThisCharacterID);
        System.out.println(equipPullThisCharacterNum);
        sql = String.format("SELECT * FROM equipment WHERE EquipmentIDChar = '%s' AND EquipmentIDNum = %s", equipPullThisCharacterID, equipPullThisCharacterNum);
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            if (result != null) {
                System.out.println("Successfully accessed database to pull equipment ID and characger number.");
            }
            while (result.next()) {
                String elements;
                elements = String.format("%5s-%4s %s", result.getString("EquipmentIDChar"), result.getString("EquipmentIDNum"), result.getString("EquipmentName"));
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("MainPage.equipmentIDChar");
        }

    }//GEN-LAST:event_lstToolsValueChanged

    private void btnInventoryAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryAddMouseClicked
        // TODO add your handling code here:

        new EditInventory(true, false, nullArray).setVisible(true);
    }//GEN-LAST:event_btnInventoryAddMouseClicked

    private void btnInventoryRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryRequestMouseClicked
        // TODO add your handling code here:
        if (invMainList.getSelectedValue() != null) {
            ArrayList<String> requestedInv = new ArrayList<String>(Arrays.asList(invMainList.getSelectedValue().split(" -- ")));
            ArrayList<String> list = new ArrayList<>();
            System.out.println(requestedInv);
            try {
                list = equip.searchInventoryForEdit(requestedInv.get(0), requestedInv.get(1));
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("MainPage.btnInventoryRequestMouseClicked");
            }
            new EditInventory(false, false, list).setVisible(true);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you requesting a new inventory item?") == 0) {
                new EditInventory(true, true, nullArray).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please sselect an item in the inventory and then hit request");
            }
        }
    }//GEN-LAST:event_btnInventoryRequestMouseClicked

    private void mainPageInvRequestsLstValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_mainPageInvRequestsLstValueChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_mainPageInvRequestsLstValueChanged

    private void btnInventoryRecordSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryRecordSearchMouseClicked
        // TODO add your handling code here:
        if (mainPageInvRequestsLst.getSelectedValue() != null) {
            //pulling record
            var recordPulled = new ArrayList<String>(Arrays.asList(mainPageInvRequestsLst.getSelectedValue().split("--")));
            sql = String.format("SELECT * FROM inventory_request WHERE InvRequestID = %s", recordPulled.get(0));
            String elements = null;
            try {
                con = db.OpenConnection();
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery();
                if (result != null) {
                    System.out.println("Successfully accessed inventory request database");
                    while (result.next()) {
                        elements = String.format("Request ID - %s\nItem ID - %s%s\nUser Requested%s\nItem Name - %s\nItem Description - %s\nQuantity needed - %s\nDestination - %s\nFulfilled - %s",
                                result.getString("InvRequestID"), result.getString("ItemIDChar"),
                                result.getString("ItemIDNum"), result.getString("UserIDRequested"),
                                result.getString("ItemName"), result.getString("Description"),
                                result.getString("Quantity"), result.getString("Destination"),
                                result.getString("Fulfilled"));
                    }
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("MainPage.btnInventoryRecordSearchMouseClicked");
            }
            JOptionPane.showMessageDialog(null, elements);
        }
    }//GEN-LAST:event_btnInventoryRecordSearchMouseClicked

    private void inventoryPullOnlyUnfulfilledBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryPullOnlyUnfulfilledBtnMouseClicked
        // TODO add your handling code here:
        
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = equip.ViewInventoryRequests(true);
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            mainPageInvRequestsLst.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("MainPage.inventoryPullOnlyUnfulfilledBtnMouseClicked");
        }     
    }//GEN-LAST:event_inventoryPullOnlyUnfulfilledBtnMouseClicked

    private void inventoryPullAllReportsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryPullAllReportsBtnMouseClicked
        // TODO add your handling code here:
        AccessInventoryRepportInfo();
    }//GEN-LAST:event_inventoryPullAllReportsBtnMouseClicked

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }
    
    public String LimitAccess() throws SQLException, ClassNotFoundException {
//        TODO: pull userID from UserAuth in order to add it here
//        then call this method when the jframe instance starts
        
//        UserAuth auth = new UserAuth();
        
        String userID = ReturnUserID();
        System.out.println("I have made it this far and my ID is: " + userID);
        ArrayList<String> elements = new ArrayList<>();
        String role;
        
        con = db.OpenConnection();
        
        sql = String.format("SELECT Role FROM user_authoization WHERE UserID = \'%s\'", userID);
        stmt = con.prepareStatement(sql);

        result = stmt.executeQuery();
        
        while (result.next()) {
            elements.add(result.getString("Role"));
        }
        
        role = elements.get(0);
        
//        switch (role) {
//            case "Technician":
//                equipmentTab.setVisible(true);
//                inventoryTab.setVisible(true);
//                maintenanceTab.setVisible(false);
//                reportTab.setVisible(false);
//                Employee.setVisible(false);
//                break;
//            case "Maintenance":
//                equipmentTab.setVisible(true);
//                inventoryTab.setVisible(true);
//                maintenanceTab.setVisible(true);
//                reportTab.setVisible(true);
//                Employee.setVisible(false);
//                break;
//            case "Warehouse":
//                equipmentTab.setVisible(true);
//                inventoryTab.setVisible(true);
//                maintenanceTab.setVisible(false);
//                reportTab.setVisible(true);
//                Employee.setVisible(false);
//                break;
//            case "Management":
//                equipmentTab.setVisible(true);
//                inventoryTab.setVisible(true);
//                maintenanceTab.setVisible(true);
//                reportTab.setVisible(true);
//                Employee.setVisible(true);
//                break;
//            case "Admin":
//                equipmentTab.setVisible(true);
//                inventoryTab.setVisible(true);
//                maintenanceTab.setVisible(true);
//                reportTab.setVisible(true);
//                Employee.setVisible(true);
//                break;
//            default:
//                break;
//        }
        return role;
    }
    
    public String ReturnUserID() {
        String userID = null;
        try {
            File userIDFile = new File("currentuser.txt");
            Scanner myReader = new Scanner(userIDFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              userID = data;
              System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return userID;
    }

    public void AccessEquipmentInfo() {

    }
    
    public void AccessMaintInfo(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.PullAllMaintenanceActivities();
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            jList1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AccessInventoryInfo() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = equip.ViewInventory();
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            invMainList.setModel(model);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AccessInventoryRepportInfo(){
        ArrayList<String> list = new ArrayList<String>();
        try{
            DefaultListModel model = new DefaultListModel();
            list = equip.ViewInventoryRequests(false);
            model.addAll(list);
            mainPageInvRequestsLst.setModel(model);
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("MainPage.AccessInventoryRepportInfo");
        }
    }

    public void SearchInventory() {
        ArrayList<String> list = new ArrayList<String>();

        try {
            list = equip.SearchInventory(txtInvSearch.getText());
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            invMainList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AccessEmployeeInfo() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.PullEmployees();
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            empList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AccessReportInfo() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = emp.PullReports();
            DefaultListModel model = new DefaultListModel();
            model.addAll(list);
            reportList.setModel(model);
            //reportSearchField.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void ViewReportDetails() throws ClassNotFoundException {
        String list = "Nothing";
        try {
            list = emp.ReportDetails(reportList.getSelectedValue());
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportDetailsField.setText(list);
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Employee;
    private javax.swing.JButton btnCheckavailable;
    private javax.swing.JButton btnCheckin;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnInventoryAdd;
    private javax.swing.JButton btnInventoryRecordSearch;
    private javax.swing.JButton btnInventoryRequest;
    private javax.swing.JButton btnInventorySearch;
    private javax.swing.JButton btnReportloss;
    private javax.swing.JButton empAddBtn;
    private javax.swing.JButton empEditBtn;
    private javax.swing.JTextField empFirstNameSearchField;
    private javax.swing.JTextField empLastInitSearchField;
    private javax.swing.JList<String> empList;
    private javax.swing.JLabel empListLabel;
    private javax.swing.JButton empLoadAllBtn;
    private javax.swing.JButton empRemoveBtn;
    private javax.swing.JButton empSearchBtn;
    private javax.swing.JButton equipmentPullAllBtn;
    private javax.swing.JPanel equipmentTab;
    private javax.swing.JList<String> invMainList;
    private javax.swing.JButton inventoryPullAllReportsBtn;
    private javax.swing.JButton inventoryPullOnlyUnfulfilledBtn;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblInventoryRecord;
    private javax.swing.JLabel lblTooldescription;
    private javax.swing.JLabel lblToolname;
    private javax.swing.JMenu logOutBtn;
    private javax.swing.JList<String> lstTools;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JList<String> mainPageInvRequestsLst;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton maintLogBtn;
    private javax.swing.JButton maintPullAll;
    private javax.swing.JButton maintSearchDateBtn;
    private javax.swing.JButton maintSearchEmpBtn;
    private javax.swing.JPanel maintenanceTab;
    private javax.swing.JButton reportAddButton;
    private javax.swing.JTextArea reportDetailsField;
    private javax.swing.JList<String> reportList;
    private javax.swing.JLabel reportListLabel;
    private javax.swing.JButton reportLoadAllBtn;
    private javax.swing.JButton reportPrint;
    private javax.swing.JButton reportSearchBtn;
    private javax.swing.JTextField reportSearchField;
    private javax.swing.JPanel reportTab;
    private javax.swing.JTabbedPane tabPanePanel;
    private javax.swing.JTextArea txaTooldescription;
    private javax.swing.JTextField txtInvSearch;
    // End of variables declaration//GEN-END:variables

    private void setVisisble(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
