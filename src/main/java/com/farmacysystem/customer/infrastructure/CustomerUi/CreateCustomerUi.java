package com.farmacysystem.customer.infrastructure.CustomerUi;

import com.farmacysystem.customer.application.CreateCustomerUseCase;
import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.identificationtypes.application.FindAllIdentificationTypesUseCase;
import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;
import com.farmacysystem.identificationtypes.domain.service.IdentificationTypeService;
import com.farmacysystem.identificationtypes.infrastructure.IdentificationTypeRepository;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CreateCustomerUi extends JFrame {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerCrudUi customerCrudUi;
    
    // Components
    private JTextField jTextField1; // ID Number
    private JComboBox<String> jComboBox1; // ID Type
    private JTextField jTextField2; // First Name
    private JTextField jTextField3; // Last Name
    private JFormattedTextField jTextField4; // BirthDate
    private JTextField jTextField5; // Age
    private JTextField jTextField6; // City ID
    private JTextField jTextField7; // Neighborhood ID
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateCustomerUi(CreateCustomerUseCase createCustomerUseCase,CustomerCrudUi customerCrudUi) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void frmRegCustomer() {
        IdentificationTypeService identificationTypeService = new IdentificationTypeRepository();
        FindAllIdentificationTypesUseCase findAllIdentificationTypesUseCase = new FindAllIdentificationTypesUseCase(identificationTypeService);
        initComponents(findAllIdentificationTypesUseCase);
        setVisible(true);
    }

    private void initComponents(FindAllIdentificationTypesUseCase findAllIdentificationTypesUseCase) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Customer");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Customer");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jComboBox1 = new JComboBox<>();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();

        // Configurar el campo de fecha con MaskFormatter
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            jTextField4 = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
            jTextField4 = new JFormattedTextField(); // Fallback en caso de error
        }

        // Agregar tipos de identificación al combobox
        List<IdentificationType> identificationTypes = findAllIdentificationTypesUseCase.execute();
        for (IdentificationType identificationType : identificationTypes) {
            jComboBox1.addItem(identificationType.getDescription());
        }

        // Configurar restricciones para el campo de edad
        jTextField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
                if (jTextField5.getText().length() >= 2) {
                    e.consume();
                }
            }
        });

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveCustomer());
        jButton3.addActionListener(e -> {
            dispose();
            customerCrudUi.showFrame();
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ID Number:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("ID Type:"), 2, 0);
        addComponent(jComboBox1, 2, 1);
        addComponent(new JLabel("First Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("Last Name:"), 4, 0);
        addComponent(jTextField3, 4, 1);
        addComponent(new JLabel("Birth Date:"), 5, 0);
        addComponent(jTextField4, 5, 1);
        addComponent(new JLabel("Age:"), 6, 0);
        addComponent(jTextField5, 6, 1);
        addComponent(new JLabel("City ID:"), 7, 0);
        addComponent(jTextField6, 7, 1);
        addComponent(new JLabel("Neighborhood ID:"), 8, 0);
        addComponent(jTextField7, 8, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
    }

    private void addComponent(Component component, int row, int col) {
        addComponent(component, row, col, 1);
    }

    private void addComponent(Component component, int row, int col, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        add(component, gbc);
    }

    private void saveCustomer() {
        try {
            Customer customer = new Customer();
            customer.setIdentificationNumber(jTextField1.getText());
            customer.setTypeID(jComboBox1.getSelectedItem().toString());
            customer.setFirstName(jTextField2.getText());
            customer.setLastName(jTextField3.getText());

            String birthDateText = jTextField4.getText();
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedBirthDate = outputFormat.format(inputFormat.parse(birthDateText));
            customer.setBirthDate(formattedBirthDate);

            customer.setAge(Integer.parseInt(jTextField5.getText()));
            customer.setCityID(Integer.parseInt(jTextField6.getText()));
            customer.setNeighborhoodID(Integer.parseInt(jTextField7.getText()));

            createCustomerUseCase.execute(customer);
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
    }
}