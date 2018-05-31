/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsumersProducers;

import Models.CheckFinanciering;
import Models.CheckReply;
import Models.CheckedFinanciering;
import Models.Financiering;
import Models.FinancieringsReply;
import Models.RequestReply;
import Utils.Gateway;
import Utils.Validator;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Yannick van Leeuwen
 */
public class BrokerFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<JListLine> listModel = new DefaultListModel<JListLine>();
    private JList<JListLine> list;
    
    
    //Gateway abn = new Gateway("ABNBank.BankInterestReply", "ABNBank.BankInterestRequest");
    //Gateway rabo = new Gateway("RaboBank.BankInterestReply", "RaboBank.BankInterestRequest");
    //Gateway ing = new Gateway("INGBank.BankInterestReply", "INGBank.BankInterestRequest");
    private List<Validator> validators;
    
    private Gateway one;
    private Gateway two;
    private Gateway three;
    private Gateway four;
    private Gateway five;
    private Gateway six;
    private Gateway gateway;
    private Gateway gateway2;
    private Gateway gateway3;
    private Gateway gateway4;
    private CheckFinanciering checkFinanciering;
    private CheckedFinanciering checkedFinanciering;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BrokerFrame frame = new BrokerFrame();
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
    public BrokerFrame(){
       validators = new ArrayList<>();
       one = new Gateway("ONE.CheckFinanciering", "ONE.Checked") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
            }
        };
       
//        abn = new Gateway("ABNBank.BankInterestRequest", "ABNBank.BankInterestReply") {
//            @Override
//            public void messageReceived(RequestReply rr) {
//                aggregator(rr);
//            }
//        };
//        
//        rabo = new Gateway("RaboBank.BankInterestRequest", "RaboBank.BankInterestReply") {
//            @Override
//            public void messageReceived(RequestReply rr) {
//                aggregator(rr);
//            }
//        };
//        
//        ing = new Gateway("INGBank.BankInterestRequest", "INGBank.BankInterestReply") {
//            @Override
//            public void messageReceived(RequestReply rr) {
//                aggregator(rr);
//            }
//        };
        
        gateway = new Gateway("LoanRequest.Client", "LoanReply.Broker") {
            @Override
            public void messageReceived(RequestReply rr) {
                Financiering financiering = (Financiering) rr.getRequest();
                add(financiering);
                double i = financiering.getBedrag();
                //HIER WORDEN CHECK EN EN CHECKEDFINANCIERINGEN GEMAAKT!!!
                //BankInterestRequest bankInterestRequest = new BankInterestRequest(loanRequest.getAmount(), loanRequest.getTime());
                checkFinanciering = new CheckFinanciering(financiering.getBedrag(), financiering.getOmschrijving(), financiering.getTypeFinanciering());
                checkFinanciering.setHash(financiering.getHash());
                checkedFinanciering = new CheckedFinanciering(financiering.getBedrag(), financiering.getOmschrijving());
                checkedFinanciering.setHash(financiering.getHash());
                add(financiering, checkFinanciering);
                RequestReply rTwo = new RequestReply<CheckFinanciering, CheckReply>(checkFinanciering, null);
                
                //LoanRequest request = (LoanRequest) rTwo.getRequest();
                double b = checkFinanciering.getBedrag();
                String s = "Debug";
                one.postMessage(rTwo);
//                Validator v = new Validator(bankInterestRequest.getHash());
//                if(loanRequest.getAmount() >= 200000 && loanRequest.getAmount() <= 300000 && loanRequest.getTime() <= 20)
//                {
//                    abn.postMessage(rTwo);
//                    v.toBanks.add("ABN");
//                }
//                if(loanRequest.getAmount() <= 250000 && loanRequest.getTime() <= 15)
//                {
//                    rabo.postMessage(rTwo);
//                    v.toBanks.add("RABO");
//                }
//                if(loanRequest.getAmount() <= 100000 && loanRequest.getTime() <= 10)
//                {
//                    ing.postMessage(rTwo);
//                    v.toBanks.add("ING");
//                }
//                validators.add(v);
            }
        };
        
        setTitle("Bemiddelaar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{46, 31, 86, 30, 89, 0};
        gbl_contentPane.rowHeights = new int[]{233, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        contentPane.add(scrollPane, gbc_scrollPane);

        list = new JList<JListLine>(listModel);
        scrollPane.setViewportView(list);
    }

    private JListLine getRequestReply(Financiering financiering) {

        for (int i = 0; i < listModel.getSize(); i++) {
            JListLine rr = listModel.get(i);
            if (rr.getFinanciering()== financiering) {
                return rr;
            }
        }

        return null;
    }
    
    private JListLine getCheckRequestReply(CheckFinanciering financiering) {

        for (int i = 0; i < listModel.getSize(); i++) {
            JListLine rr = listModel.get(i);
            if (rr.getCheckFinanciering()== financiering) {
                return rr;
            }
        }

        return null;
    }

    private JListLine getBankRequestReply(CheckFinanciering checkFinanciering) {

        for (int i = 0; i < listModel.getSize(); i++) {
            JListLine rr = listModel.get(i);
            if (rr.getCheckFinanciering().getHash().equals(checkFinanciering.getHash())) {
                return rr;
            }
        }
        return null;
    }

    public void add(Financiering financiering) {
        listModel.addElement(new JListLine(financiering));
    }

    public void add(Financiering financiering, CheckFinanciering checkFinanciering) {
        JListLine rr = getRequestReply(financiering);
        if (rr != null && checkFinanciering != null) {
            rr.setCheckFinanciering(checkFinanciering);
            list.repaint();
        }
    }
    
    public void add(CheckFinanciering financiering, CheckReply checkReply) {
        JListLine rr = getCheckRequestReply(financiering);
        if (rr != null && checkReply != null) {
            rr.setCheckReply(checkReply);
            list.repaint();
        }
    }
    
//    private void aggregator(RequestReply rr) {
//        double startval = -10;
//        Validator val = new Validator();
//        BankInterestRequest bir = (BankInterestRequest) rr.getRequest();
//        BankInterestReply bire = (BankInterestReply) rr.getReply();
//        boolean founded = false;
//        JListLine jls = getBankRequestReply(bir);
//        add(jls.getLoanRequest(), bire);
//        FinancieringsReply fr = new FinancieringsReply();
//        lr.setInterest(bire.getInterest());
//        lr.setHash(bire.getHash());
//        lr.setQuoteID(bire.getQuoteId());
//        RequestReply rr2 = new RequestReply(jls.getLoanRequest(), lr);
//
//        for (Validator v : validators) {
//            if (v.getHash().equals(bire.getHash())) {
//                val = v;
//            }
//        }
//        String s = "Debug";
//        
//        for (RequestReply reqRep : val.fromBanks) {
//            if (((LoanReply) reqRep.getReply()).getQuoteID().equals(((LoanReply) rr2.getReply()).getQuoteID())) {
//                founded = true;
//            }
//        }
//        if (founded == false){
//            val.fromBanks.add(rr2);  
//        }
//        if (val.toBanks.size() == val.fromBanks.size()) 
//        {
//            String ssss = "Debug";
//            RequestReply lowestInterest = new RequestReply();
//            String sss = "Debug";
//            for (RequestReply reqRep : val.fromBanks) {
//                double bankInteresReply = ((LoanReply) reqRep.getReply()).getInterest();
//                String ss = "Debug";
//                if(startval < 0)
//                {
//                    startval = bankInteresReply;
//                    lowestInterest = reqRep;
//                }
//                else if(bankInteresReply < startval) {
//                    startval = bankInteresReply;
//                    lowestInterest = reqRep;
//                    String sssss = "Debug";
//                }
//            }
//            gateway.postMessage(lowestInterest);
//            validators.remove(val);
//        }
//    }
}
