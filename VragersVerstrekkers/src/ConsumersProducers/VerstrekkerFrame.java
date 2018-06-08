/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumersProducers;

import Models.CheckFinanciering;
import Models.CheckReply;
import Models.CheckedFinanciering;
import Models.RequestReply;
import Models.TypeFinanciering;
import Utils.ConsumeTopic;
import Utils.Gateway;
import Utils.GatewayTopic;
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
public class VerstrekkerFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JComboBox<String> tfResponder;
    private JComboBox<String> tfDropdown;
    private JPanel contentPane;
    private JTextField tfReply;
    private JTextField tfSender;
    private static DefaultListModel<RequestReply<CheckFinanciering, CheckReply>> listModel = new DefaultListModel<RequestReply<CheckFinanciering, CheckReply>>();
    private Gateway VerstrekkerEen;
    private Gateway gateway;
    private GatewayTopic gatewayTopic;
    private Gateway gatewayReply;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    VerstrekkerFrame frame = new VerstrekkerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VerstrekkerFrame() {
        String[] types = {"Selecteer uw antwoord", "Interessant", "Niet Interessant"};
        String[] responders = {"Selecteer wie u bent", "Reponder 1", "Responder 2" , "Responder 3", "Responder 4", "Responder 5", "Responder 6"};
        gateway = new Gateway("first.Checked", "first.CheckFinanciering", "test") {
            @Override
            public void messageReceived(RequestReply rr) {
                
            }
        };
        gatewayReply = new Gateway("second.Checked", "second.CheckFinanciering", "test") {
            @Override
            public void messageReceived(RequestReply rr) {
                
            }
        };
        VerstrekkerEen = new Gateway("VerstrekkerEen.LastBroker", "VerstrekkerEen.VerstrekkerReply", "VerstrekkerEen") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("REICEIVED MESSAGE IN VERSTREKKER!!!!");
                System.out.println("REICEIVED REQUEST::::::: " + rr.getRequest());
                listModel.addElement(rr);
                tfReply.setVisible(true);
            }
        };
        gatewayTopic = new GatewayTopic("first") {
            @Override
            public void messageReceived(RequestReply rr) {
                CheckFinanciering checkFinanciering = (CheckFinanciering) rr.getRequest();
                listModel.addElement(rr);
                tfReply.setVisible(false);
                }
        };

        setTitle("VerstrekkerEen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 684, 619);
        contentPane = new JPanel();

        contentPane.setBorder(
                new EmptyBorder(50, 5, 200, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{46, 31, 86, 30, 89, 0};
        gbl_contentPane.rowHeights = new int[]{233, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};

        contentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 5;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;

        contentPane.add(scrollPane, gbc_scrollPane);

        JList<RequestReply<CheckFinanciering, CheckReply>> list = new JList<RequestReply<CheckFinanciering, CheckReply>>(listModel);

        scrollPane.setViewportView(list);

        JLabel lblNewLabel = new JLabel("type reply");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 0;

        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        tfReply = new JTextField();
        GridBagConstraints gbc_tfReply = new GridBagConstraints();
        gbc_tfReply.gridwidth = 2;
        gbc_tfReply.insets = new Insets(0, 0, 0, 5);
        gbc_tfReply.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfReply.gridx = 1;
        gbc_tfReply.gridy = 2;

        contentPane.add(tfReply, gbc_tfReply);
        
        JLabel lblDropdown = new JLabel("Antwoord");
        GridBagConstraints gbc_lblDropdown = new GridBagConstraints();
        gbc_lblDropdown.anchor = GridBagConstraints.EAST;
        gbc_lblDropdown.insets = new Insets(0, 0, 5, 5);
        gbc_lblDropdown.gridx = 3;
        gbc_lblDropdown.gridy = 2;
        contentPane.add(lblDropdown, gbc_lblDropdown);
        
        tfDropdown = new JComboBox<String>(types);
        GridBagConstraints tfDropdownn = new GridBagConstraints();
        tfDropdownn.insets = new Insets(0, 0, 5, 5);
        tfDropdownn.fill = GridBagConstraints.HORIZONTAL;
        tfDropdownn.gridx = 1;
        tfDropdownn.gridy = 3;
        contentPane.add(tfDropdown, tfDropdownn);
        tfReply.setColumns(10);
        
        JButton btnSendReply = new JButton("send reply");

        btnSendReply.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b;
                RequestReply<CheckFinanciering, CheckReply> rr = list.getSelectedValue();
                //double interest = Double.parseDouble((tfReply.getText()));
                if(tfDropdown.getSelectedItem().equals("Interessant"))
                {
                    b = true;
                }
                else
                    b = false;
                CheckReply reply = new CheckReply(b, getTitle());
                reply.setHash(rr.getRequest().getHash());
                if (rr != null && reply != null) {
                    rr.setReply(reply);
                    list.repaint();
                    if(rr.getReply().getClass() == CheckReply.class)
                    {
                        gateway.postMessage(rr);
                    }
                }
            }
        }
        );
        GridBagConstraints gbc_btnSendReply = new GridBagConstraints();
        gbc_btnSendReply.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnSendReply.gridx = 4;
        gbc_btnSendReply.gridy = 1;
        contentPane.add(btnSendReply, gbc_btnSendReply);
    }
}
