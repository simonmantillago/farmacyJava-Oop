package com.farmacysystem.customer.infrastructure.CustomerUi;

import com.farmacysystem.customer.application.FindCustomerByIdUseCase;
import com.farmacysystem.customer.domain.entity.CustomerDto;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class FindCustomerUi extends JFrame {
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerCrudUi customerCrudUi;
    private JTextField txtId;
    private JTextArea resultArea;

    public FindCustomerUi(FindCustomerByIdUseCase findCustomerByIdUseCase, CustomerCrudUi customerCrudUi) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void showFindCustomer() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Customer");
        setSize(500, 500);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Find Customer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Customer ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findCustomer());
        addComponent(btnFind, 2, 0, 2);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> {
            dispose();
            customerCrudUi.showFrame();
        });
        addComponent(btnClose, 4, 0, 2);
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
        gbc.insets = new Insets(5, 5, 5, 5);
        add(component, gbc);
    }

    private void findCustomer() {
        String customerId = txtId.getText();
        Optional<CustomerDto> customerDtoOpt = findCustomerByIdUseCase.execute(customerId);
        if (customerDtoOpt.isPresent()) {
            CustomerDto customerDto = customerDtoOpt.get();
            String message = String.format(
                "Customer found:\n\n" +
                "ID: %s\n" +
                "Type: %s\n" +
                "Name: %s %s\n" +
                "Age: %d\n" +
                "Birth Date: %s\n" +
                "City: %s\n" +
                "Neighborhood: %s",
                customerDto.getIdentificationNumber(),
                customerDto.getTypeDescription(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getAge(),
                customerDto.getBirthDate(),
                customerDto.getCityName(),
                customerDto.getNeighborhoodName()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Customer not found!");
        }
    }
}