/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumersProducers;

import Models.Financiering;
import Models.FinancieringsReply;
import Models.RequestReply;
import Models.Resultaat;
import Models.TypeFinanciering;
import Utils.Gateway;
import Utils.HashGenerator;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Yannick van Leeuwen
 */
public class AanvragerFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfSSN;
    private DefaultListModel<RequestReply<Financiering, Resultaat>> listModel = new DefaultListModel<RequestReply<Financiering, Resultaat>>();
    private JList<RequestReply<Financiering, Resultaat>> requestReplyList;

    private JTextField tfAmount;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField tfTime;
    private JComboBox<TypeFinanciering> tfDropdown;

    private Gateway gateway;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AanvragerFrame frame = new AanvragerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AanvragerFrame(){
        gateway = new Gateway("LoanReply.Broker", "LoanRequest.Client", "Test"){
            @Override
            public void messageReceived(RequestReply rr){
//                int index = getRequestReply((Financiering) rr.getRequest());
//                RequestReply rr2 = listModel.get(index);
//                rr2.setReply(rr.getReply());
//                requestReplyList.repaint();
                Resultaat s = (Resultaat) rr.getReply();
                getRequestReply(s);
                System.out.println(s);
            }
        };

        setTitle("Aanvrager");
        TypeFinanciering defaultt = new TypeFinanciering(0, "Selecteer Type");
        TypeFinanciering typeOne = new TypeFinanciering(1, "Type een");
        TypeFinanciering typeTwo = new TypeFinanciering(2, "Type two");
        TypeFinanciering typeThree = new TypeFinanciering(3, "Type three");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 30, 30, 30, 30, 0};
        gbl_contentPane.rowHeights = new int[]{30, 30, 30, 30, 30};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblBody = new JLabel("Bedrag");
        GridBagConstraints gbc_lblBody = new GridBagConstraints();
        gbc_lblBody.insets = new Insets(0, 0, 5, 5);
        gbc_lblBody.gridx = 0;
        gbc_lblBody.gridy = 0;
        contentPane.add(lblBody, gbc_lblBody);
        
        TypeFinanciering[] types = {defaultt, typeOne, typeTwo, typeThree};
        
        tfSSN = new JTextField();
        GridBagConstraints gbc_tfSSN = new GridBagConstraints();
        gbc_tfSSN.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfSSN.insets = new Insets(0, 0, 5, 5);
        gbc_tfSSN.gridx = 1;
        gbc_tfSSN.gridy = 0;
        contentPane.add(tfSSN, gbc_tfSSN);
        tfSSN.setColumns(10);

        lblNewLabel = new JLabel("Samenvatting");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        tfAmount = new JTextField();
        GridBagConstraints gbc_tfAmount = new GridBagConstraints();
        gbc_tfAmount.anchor = GridBagConstraints.NORTH;
        gbc_tfAmount.insets = new Insets(0, 0, 5, 5);
        gbc_tfAmount.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfAmount.gridx = 1;
        gbc_tfAmount.gridy = 1;
        contentPane.add(tfAmount, gbc_tfAmount);
        tfAmount.setColumns(10);

        lblNewLabel_1 = new JLabel("Omschrijving");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 2;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

        tfTime = new JTextField();
        GridBagConstraints gbc_tfTime = new GridBagConstraints();
        gbc_tfTime.insets = new Insets(0, 0, 5, 5);
        gbc_tfTime.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfTime.gridx = 1;
        gbc_tfTime.gridy = 2;
        contentPane.add(tfTime, gbc_tfTime);
        tfTime.setColumns(20);
        
        JLabel lblDropdown = new JLabel("Type financiering");
        GridBagConstraints gbc_lblDropdown = new GridBagConstraints();
        gbc_lblDropdown.anchor = GridBagConstraints.EAST;
        gbc_lblDropdown.insets = new Insets(0, 0, 5, 5);
        gbc_lblDropdown.gridx = 0;
        gbc_lblDropdown.gridy = 3;
        contentPane.add(lblDropdown, gbc_lblDropdown);
        
        tfDropdown = new JComboBox<TypeFinanciering>(types);
        GridBagConstraints tfDropdownn = new GridBagConstraints();
        tfDropdownn.insets = new Insets(0, 0, 5, 5);
        tfDropdownn.fill = GridBagConstraints.HORIZONTAL;
        tfDropdownn.gridx = 1;
        tfDropdownn.gridy = 3;
        contentPane.add(tfDropdown, tfDropdownn);
        
        JButton btnQueue = new JButton("Vraag financiering aan");
        btnQueue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double bedrag = Double.parseDouble(tfSSN.getText());
                String samenvatting = tfAmount.getText();
                String omschrijving = tfTime.getText();
                TypeFinanciering t = (TypeFinanciering) tfDropdown.getSelectedItem();

                Financiering financiering = new Financiering(bedrag, samenvatting, omschrijving, t);
                financiering.setHash(HashGenerator.generate());
                RequestReply rr = new RequestReply<Financiering, Resultaat>(financiering, null);
                listModel.addElement(rr);
                gateway.postMessage(rr);
                String s = "Debug";
                tfSSN.setText("");
                tfAmount.setText("");
                tfTime.setText("");
            }
        });
        GridBagConstraints gbc_btnQueue = new GridBagConstraints();
        gbc_btnQueue.insets = new Insets(0, 0, 5, 5);
        gbc_btnQueue.gridx = 2;
        gbc_btnQueue.gridy = 3;
        contentPane.add(btnQueue, gbc_btnQueue);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 7;
        gbc_scrollPane.gridwidth = 6;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 4;
        contentPane.add(scrollPane, gbc_scrollPane);

        requestReplyList = new JList<RequestReply<Financiering, Resultaat>>(listModel);
        scrollPane.setViewportView(requestReplyList);
    }

    private int getRequestReply(Resultaat financiering) {

        for (int i = 0; i < listModel.getSize(); i++) {
            RequestReply<Financiering, Resultaat> rr = listModel.get(i);
            if (rr.getRequest().getHash().equals(financiering.getHash())) {
                listModel.clear();
                listModel.addElement(rr);
                return i;
            }
        }
        return -1;
    }
}
