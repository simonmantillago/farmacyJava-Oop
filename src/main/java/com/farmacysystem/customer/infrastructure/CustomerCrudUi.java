package com.farmacysystem.customer.infrastructure;

import com.farmacysystem.customer.application.CreateCustomerUseCase;
import com.farmacysystem.customer.application.DeleteCustomerUseCase;
import com.farmacysystem.customer.application.FindCustomerByIdUseCase;
import com.farmacysystem.customer.application.UpdateCustomerUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerCrudUi {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase; // Agregado para UpdateCustomerUseCase
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    

    public CustomerCrudUi(CreateCustomerUseCase createCustomerUseCase, FindCustomerByIdUseCase findCustomerByIdUseCase,
            UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }


    public void showCrudOptions() {
        JFrame frame = new JFrame("Customer CRUD Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JButton btnCreate = new JButton("Create Customer");
        btnCreate.setFont(new Font("Arial", Font.PLAIN, 18));
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCustomerUi customerUi = new CreateCustomerUi(createCustomerUseCase);
                customerUi.frmRegCustomer(); // Mostrar la ventana
                frame.dispose(); // Cerrar la ventana actual si es necesario
            }
        });
        panel.add(btnCreate);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnFind = new JButton("Find Customer");
        btnFind.setFont(new Font("Arial", Font.PLAIN, 18));
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindCustomerUi findCustomerUi = new FindCustomerUi(findCustomerByIdUseCase);
                findCustomerUi.showFindCustomer();
                frame.dispose();
            }
        });
        panel.add(btnFind);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Agregar botón para actualizar clientes
        JButton btnUpdate = new JButton("Update Customer");
        btnUpdate.setFont(new Font("Arial", Font.PLAIN, 18));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCustomerUi updateCustomerUi = new UpdateCustomerUi(findCustomerByIdUseCase, updateCustomerUseCase);
                updateCustomerUi.setVisible(true);  // Hacer visible la ventana
                frame.dispose();
            }
        });
        panel.add(btnUpdate);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Agregar botón para actualizar clientes
        JButton btnDelete = new JButton("Delete Customer");
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 18));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCustomerUi deleteCustomerUi = new DeleteCustomerUi(deleteCustomerUseCase);
                deleteCustomerUi.showDeleteCustomer();  // Hacer visible la ventana
                frame.dispose();
            }
        });
        panel.add(btnDelete);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
