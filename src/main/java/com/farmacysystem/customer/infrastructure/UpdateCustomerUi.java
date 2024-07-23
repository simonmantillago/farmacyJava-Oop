package com.farmacysystem.customer.infrastructure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import com.farmacysystem.customer.application.FindCustomerByIdUseCase;
import com.farmacysystem.customer.application.UpdateCustomerUseCase;
import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.entity.CustomerDto;
import com.farmacysystem.identificationtypes.application.FindAllIdentificationTypesUseCase;
import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;
import com.farmacysystem.identificationtypes.domain.service.IdentificationTypeService;
import com.farmacysystem.identificationtypes.infrastructure.IdentificationTypeRepository;

public class UpdateCustomerUi extends JFrame implements ActionListener {
    private JLabel lblId, lblIdType, lblFirstName, lblLastName, lblBirthDate, lblAge, lblCityId, lblNeighborhoodID;
    private JTextField txtId, txtFirstName, txtLastName, txtAge, txtCityId, txtNeighborhoodID;
    private JFormattedTextField txtBirthDate;
    private JButton findButton, saveButton, closeButton, resetButton;
    private JComboBox<String> comboBoxIdType;

    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    // Constructor actualizado
    public UpdateCustomerUi(FindCustomerByIdUseCase findCustomerByIdUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Customer Registry");
        setSize(500, 600);
        getContentPane().setBackground(new Color(200, 200, 200));

        initUIComponents();
        addUIComponents();
    }

    private void initUIComponents() {
        IdentificationTypeService identificationTypeService = new IdentificationTypeRepository();
        FindAllIdentificationTypesUseCase findAllIdentificationTypesUseCase = new FindAllIdentificationTypesUseCase(identificationTypeService);

        lblId = createLabel("ID to update", 35, 130, 300, 30);
        txtId = createTextField(170, 130, 190, 30);
        findButton = createButton("🔍", 365, 130, 60, 30, this);

        lblIdType = createLabel("TypeID", 35, 170, 100, 30);
        comboBoxIdType = createComboBox(135, 170, 290, 30);
        List<IdentificationType> identificationTypes = findAllIdentificationTypesUseCase.execute();
        for (IdentificationType identificationType : identificationTypes) {
            comboBoxIdType.addItem(identificationType.getDescription());
        }

        lblFirstName = createLabel("First Name", 35, 210, 100, 30);
        txtFirstName = createTextField(135, 210, 290, 30);

        lblLastName = createLabel("Last Name", 35, 250, 100, 30);
        txtLastName = createTextField(135, 250, 290, 30);

        lblBirthDate = createLabel("BirthDate", 35, 290, 100, 30);
        try {
            txtBirthDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
            txtBirthDate.setBounds(135, 290, 290, 30);
            txtBirthDate.setFont(new Font("Arial", Font.PLAIN, 18));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lblAge = createLabel("Age", 35, 330, 100, 30);
        txtAge = createTextField(135, 330, 290, 30);
        txtAge.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    JOptionPane.showMessageDialog(null, "Campo solo números", "Error", JOptionPane.ERROR_MESSAGE);
                    e.consume();
                }
            }
        });

        lblCityId = createLabel("CityId", 35, 370, 100, 30);
        txtCityId = createTextField(135, 370, 290, 30);

        lblNeighborhoodID = createLabel("NeighborhoodID", 35, 410, 150, 30);
        txtNeighborhoodID = createTextField(190, 410, 235, 30);

        saveButton = createButton("Update data", 55, 510, 120, 30, this);
        closeButton = createButton("Close", 305, 510, 120, 30, this);
        resetButton = createButton("Restablecer", 180, 510, 120, 30, this);
    }

    private void addUIComponents() {
        add(lblId);
        add(txtId);
        add(findButton);
        add(lblIdType);
        add(comboBoxIdType);
        add(lblFirstName);
        add(txtFirstName);
        add(lblLastName);
        add(txtLastName);
        add(lblBirthDate);
        add(txtBirthDate);
        add(lblAge);
        add(txtAge);
        add(lblCityId);
        add(txtCityId);
        add(lblNeighborhoodID);
        add(txtNeighborhoodID);
        add(saveButton);
        add(closeButton);
        add(resetButton);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(listener);
        return button;
    }

    private JComboBox<String> createComboBox(int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        return comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            handleFindButton();
        } else if (e.getSource() == saveButton) {
            handleSaveButton();
        } else if (e.getSource() == closeButton) {
            dispose();
        } else if (e.getSource() == resetButton) {
            resetForm();
        }
    }

    private void handleFindButton() {
        String IdToUpdate = txtId.getText();
        Optional<CustomerDto> customerToUpdate = findCustomerByIdUseCase.execute(IdToUpdate);
        if (customerToUpdate.isPresent()) {
            CustomerDto foundCustomer = customerToUpdate.get();
            String TypeIdBox = foundCustomer.getTypeDescription();

            try {
                comboBoxIdType.setSelectedItem(TypeIdBox);
                txtFirstName.setText(foundCustomer.getFirstName());
                txtLastName.setText(foundCustomer.getLastName());

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedBirthDate = outputFormat.format(inputFormat.parse(foundCustomer.getBirthDate()));
                txtBirthDate.setText(formattedBirthDate);
                txtAge.setText(Integer.toString(foundCustomer.getAge()));
                txtCityId.setText(Integer.toString(foundCustomer.getCityID()));
                txtNeighborhoodID.setText(Integer.toString(foundCustomer.getNeighborhoodID()));
                txtId.setEditable(false);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSaveButton() {
        try {
            // Convertir fecha desde dd/MM/yyyy a yyyy-MM-dd
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDateStr = txtBirthDate.getText();
            java.util.Date date = inputFormat.parse(birthDateStr);
            String formattedBirthDate = outputFormat.format(date);
    
            Customer newCustomer = new Customer();
            newCustomer.setIdentificationNumber(txtId.getText());
            newCustomer.setFirstName(txtFirstName.getText());
            newCustomer.setLastName(txtLastName.getText());
            newCustomer.setBirthDate(formattedBirthDate);  // Usar la fecha en formato yyyy-MM-dd
            newCustomer.setAge(Integer.parseInt(txtAge.getText()));
            newCustomer.setCityID(Integer.parseInt(txtCityId.getText()));
            newCustomer.setNeighborhoodID(Integer.parseInt(txtNeighborhoodID.getText()));
            newCustomer.setTypeID(comboBoxIdType.getSelectedItem().toString());
    
            updateCustomerUseCase.execute(newCustomer);
    
            JOptionPane.showMessageDialog(this, "Customer updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            txtId.setEditable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating customer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void resetForm() {
        txtId.setText("");
        comboBoxIdType.setSelectedIndex(0);
        txtFirstName.setText("");
        txtLastName.setText("");
        txtBirthDate.setText("");
        txtAge.setText("");
        txtCityId.setText("");
        txtNeighborhoodID.setText("");
    }

   
}
