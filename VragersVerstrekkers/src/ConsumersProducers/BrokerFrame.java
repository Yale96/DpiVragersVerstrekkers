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
import Models.Resultaat;
import Utils.Gateway;
import Utils.GatewayTopic;
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
public class BrokerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<JListLine> listModel = new DefaultListModel<JListLine>();
    private JList<JListLine> list;

    private List<Validator> validators;

    private Gateway gatewayFirst;
    private Gateway gatewaySecond;
    private Gateway gateWayThird;
    private GatewayTopic gatewayTopic;
    private GatewayTopic gatewayTopicTwo;
    private Gateway Factoring;
    private Gateway AchtergesteldKapitaal;
    private Gateway VerstrekkerEenReply;
    private Gateway bank;
    private Gateway UitFondsen;
    private Gateway CrowdFunding;
    private Gateway Leasing;
    private List<Gateway> queueNames;
    private double totaalAangevraagd;
    private Validator v;
    List<CheckReply> checkReplys;

    private CheckFinanciering checkFinanciering;
    private CheckedFinanciering checkedFinanciering;
    private CheckReply checkReply;
    private List<String> checkReplyers;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
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
    public BrokerFrame() {
        validators = new ArrayList<>();
        totaalAangevraagd = 0.0;
        checkReplys = new ArrayList<>();
        checkReplyers = new ArrayList<>();
        queueNames = new ArrayList<>();
//        gatewayFirst = new Gateway("first.CheckFinanciering", "first.Checked", "niks") {
//            @Override
//            public void messageReceived(RequestReply rr) {
//                //aggregator(rr);
//            }
//        };
        gatewaySecond = new Gateway("first.CheckFinanciering", "first.Checked", "niks") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("RECEIVED ON GATEWAYSECOND!!!!!");
                CheckReply cr = (CheckReply) rr.getReply();
                CheckFinanciering cf = (CheckFinanciering) rr.getRequest();
                add(cf, cr);

                list.repaint();
                if (cr != null) {
                    aggregator(rr);
                }
            }
        };
        VerstrekkerEenReply = new Gateway("VerstrekkerEenReply.VerstrekkerReplyt", "VerstrekkerEenReply.LastBrokert", "Niks") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
            }
        };
        gatewayTopic = new GatewayTopic("first") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
            }
        };
        Factoring = new Gateway("Factoring.VerstrekkerReply", "Factoring.LastBroker", "Factoring") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(Factoring);

        //
        AchtergesteldKapitaal = new Gateway("AchtergesteldKapitaal.VerstrekkerReply", "AchtergesteldKapitaal.LastBroker", "AchtergesteldKapitaal") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(AchtergesteldKapitaal);
        
        bank = new Gateway("bank.VerstrekkerReply", "bank.LastBroker", "bank") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(bank);
        
        UitFondsen = new Gateway("UitFondsen.VerstrekkerReply", "UitFondsen.LastBroker", "UitFondsen") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(UitFondsen);
        
        CrowdFunding = new Gateway("CrowdFunding.VerstrekkerReply", "CrowdFunding.LastBroker", "CrowdFunding") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(CrowdFunding);
        
        Leasing = new Gateway("Leasing.VerstrekkerReply", "Leasing.LastBroker", "Leasing") {
            @Override
            public void messageReceived(RequestReply rr) {
                //aggregator(rr);
                System.out.println("WEER TERUG IN BROKER!!!!!");
                FinancieringsReply f = (FinancieringsReply) rr.getReply();

                System.out.println("Ontvangen bedrag::: " + f.getBedrag());
                v.fromVerstrekkers.add(rr);
                aggregatorToClient(rr);
            }
        };
        queueNames.add(Leasing);

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
        gatewayFirst = new Gateway("LoanRequest.Client", "LoanReply.Broker", "Niks") {
            @Override
            public void messageReceived(RequestReply rr) {
                Financiering financiering = (Financiering) rr.getRequest();
                totaalAangevraagd += financiering.getBedrag();
                add(financiering);
                double i = financiering.getBedrag();
                v = new Validator(financiering.getHash());
                //HIER WORDEN CHECK EN EN CHECKEDFINANCIERINGEN GEMAAKT!!!
                //BankInterestRequest bankInterestRequest = new BankInterestRequest(loanRequest.getAmount(), loanRequest.getTime());
                checkFinanciering = new CheckFinanciering(financiering.getBedrag(), financiering.getSamenvatting(), financiering.getTypeFinanciering());
                checkFinanciering.setHash(financiering.getHash());
                checkedFinanciering = new CheckedFinanciering(financiering.getBedrag(), financiering.getOmschrijving());
                checkedFinanciering.setHash(financiering.getHash());
                add(financiering, checkFinanciering);
                RequestReply rTwo = new RequestReply<CheckFinanciering, CheckReply>(checkFinanciering, null);

                //LoanRequest request = (LoanRequest) rTwo.getRequest();
                double b = checkFinanciering.getBedrag();
                String s = "Debug";
                gatewayTopic.postMessage(rTwo);
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
            if (rr.getFinanciering() == financiering) {
                return rr;
            }
        }

        return null;
    }

    private JListLine getCheckRequestReply(CheckReply financiering) {

        for (int i = 0; i < listModel.getSize(); i++) {
            JListLine rr = listModel.get(i);
            if (rr.getCheckReply() == financiering) {
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

    public void add(CheckReply financiering) {
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
        JListLine rr = getCheckRequestReply(checkReply);
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
    private void aggregatorToClient(RequestReply rr) {
        Validator val = new Validator();
        FinancieringsReply bire = (FinancieringsReply) rr.getReply();

        for (Validator v : validators) {
            if (v.getHash().equals(bire.getHash())) {
                val = v;
            }
        }
        String s = "Debug";
        double totalAmount = 0.0;
        if (val.toVerstrekkers.size() == val.fromVerstrekkers.size()) {
            for (RequestReply r : val.fromVerstrekkers) {
                String scda = "Debug";
                FinancieringsReply f = (FinancieringsReply) r.getReply();
                totalAmount += f.getBedrag();
            }
            String ssss = "Debug";
            if (totalAmount >= totaalAangevraagd) {
                //gatewayFirst.postMessage(lowestInterest);
                Resultaat positief = new Resultaat("FINANCIERING GESLAAGD");
                positief.setHash(val.getHash());
                RequestReply r = new RequestReply<Financiering, Resultaat>(null, positief);
                gatewayFirst.postMessage(r);
                String ssts = "debug";
                validators.clear();
                totaalAangevraagd = 0.0;
            }
            if (totalAmount < totaalAangevraagd) {
                Resultaat negatief = new Resultaat("FINANCIERING GEFAALD");
                negatief.setHash(val.getHash());
                RequestReply r = new RequestReply<Financiering, Resultaat>(null, negatief);
                gatewayFirst.postMessage(r);
                String ssts = "debug";
                validators.clear();
                totaalAangevraagd = 0.0;
                //queueNames.clear();
            }
        }
    }

    private void aggregator(RequestReply rr) {
        RequestReply rTwo = new RequestReply<CheckedFinanciering, FinancieringsReply>(checkedFinanciering, null);
        String st = "Debug";

        CheckReply replys = (CheckReply) rr.getReply();

        checkReplys.add(replys);

        if (checkReplys.size() == queueNames.size()) {
            String ssss = "Debug";
            String sss = "Debug";
            for (CheckReply reply : checkReplys) {
                if (reply.getAnswer() == true) {
                    checkReplyers.add(reply.getSender());
                }
            }
            if (checkReplyers.size() != 0) {
                for (String s : checkReplyers) {
                    v.toVerstrekkers.add(s);
                    findGatewayByName(s).postMessage(rTwo);
                    checkReplys.clear();
                }
            }
            else
            {
                Resultaat negatief = new Resultaat("FINANCIERING GEFAALD WEGENS GEEN INTERESSE");
                negatief.setHash(v.getHash());
                RequestReply r = new RequestReply<Financiering, Resultaat>(null, negatief);
                gatewayFirst.postMessage(r);
                validators.clear();
                checkReplys.clear();
                checkReplyers.clear();
                totaalAangevraagd = 0.0;
            }
        }
        checkReplyers.clear();
        validators.add(v);
    }

    private Gateway findGatewayByName(String name) {
        for (Gateway g : queueNames) {
            if (g.getName().equals(name)) {
                System.out.println("GATEWAY NAME::: " + g.getName());
                return g;
            }
        }
        return null;
    }
}
