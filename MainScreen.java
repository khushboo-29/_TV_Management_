import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen extends JFrame {
    //Panel1:subscriber information
    JPanel subscriberpanel;

    JTextField subName;
    JTextField subLastname;
    JTextField subMobile;
    JTextField subCity;

    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    //Panel2:subscription cycle
    JPanel cyclePanel;

    JLabel todayLBL;
    JLabel startCycleLBL;
    JLabel endcycleLBL;
    JLabel tvNumberLBL;

    SimpleDateFormat df;
    Date currentDate;

    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTvFLD;

    //Panel3 channel package
    JPanel packagesChannel;
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;

    JLabel packagesLBL;

    JButton subscribeBTN;

    //Panel4 Package details
    JPanel detailsPannel;
    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;

    //Panel5 fee and check
    JPanel feePanel;
    JLabel installFeeLBL;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;

    //Panel6 table
    JPanel p6Panel;
    JTable table;
    DefaultTableModel tableModel;

    //Panel7 action Panel
    JPanel p7Panel;
    JButton newBTN;
    JButton loadBTN;
    JButton saveBTN;

    //Classes and objects
    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice=0;
    int totalPrice;

    //Saving
    ArrayList<Subscription> listToSave=new ArrayList<>();
    File file;


    //constructor
    public MainScreen()  {
        /****************   PANEL 1       **********************/
        subscriberpanel=new JPanel();
        Border panel1Title=BorderFactory.createTitledBorder("Subscriber Details");
        subscriberpanel.setBorder(panel1Title);
        subscriberpanel.setBounds(15,15,300,200);
        subscriberpanel.setLayout(new GridLayout(4,4));

        //JLAbel
        nameLBL=new JLabel("Name: ");
        lastLBL=new JLabel("Last Name: ");
        mobileLBL=new JLabel("Mobile: ");
        cityLBL=new JLabel("City: ");

        //Textfield
        subName=new JTextField();
        subLastname=new JTextField();
        subMobile=new JTextField();
        subCity=new JTextField();

        //adding components to panel1
        subscriberpanel.add(nameLBL);
        subscriberpanel.add(subName);
        subscriberpanel.add(lastLBL);
        subscriberpanel.add(subLastname);
        subscriberpanel.add(mobileLBL);
        subscriberpanel.add(subMobile);
        subscriberpanel.add(cityLBL);
        subscriberpanel.add(subCity);

        //making opacity opaque
        subName.setOpaque(false);
        subLastname.setOpaque(false);
        subMobile.setOpaque(false);
        subCity.setOpaque(false);

        /****************   PANEL 2      **********************/

        //panel2
        cyclePanel=new JPanel();
        cyclePanel.setLayout(new GridLayout(14,1));
        cyclePanel.setBounds(15,230,300,400);

        Border panel2Title=BorderFactory.createTitledBorder("Subscription cycle");
        cyclePanel.setBorder(panel2Title);

        //components of panel2
        todayLBL=new JLabel();
        df=new SimpleDateFormat("dd/MM/yyyy");
        currentDate=new Date();
        todayLBL.setText("Today: "+df.format(currentDate));

        // Start cycle Date
        startCycleLBL=new JLabel("Start cycle Date (DD/MM/YY)");
        startCycleFLD=new JTextField();

        // End Cycle Date
        endcycleLBL=new JLabel("End cycle Date (DD/MM/YY)");
        endCycleFLD=new JTextField();

        // number of tv
        tvNumberLBL=new JLabel("Number of TV : ");
        numberTvFLD=new JTextField();

        //adding components of panel 2
        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endcycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(tvNumberLBL);
        cyclePanel.add(numberTvFLD);

        //making opacity
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTvFLD.setOpaque(false);

        /****************   PANEL 3       **********************/

        packagesChannel=new JPanel();
        packagesChannel.setLayout(new GridLayout(5,1));
        packagesChannel.setBounds(330,15,300,200);

        Border panel3Title=BorderFactory.createTitledBorder("Available Package");
        packagesChannel.setBorder(panel3Title);

        packagesLBL=new JLabel("Please select your package");

        sportsCHKBX=new JCheckBox("Sports Package");
        moviesCHKBX=new JCheckBox("Movies Package");
        docCHKBX=new JCheckBox("Documentary Package");

        subscribeBTN=new JButton("Subscribe");

        packagesChannel.add(packagesLBL);
        packagesChannel.add(sportsCHKBX);
        packagesChannel.add(moviesCHKBX);
        packagesChannel.add(docCHKBX);
        packagesChannel.add(subscribeBTN);

        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportsCHKBX.isSelected()){
                    DisplaySportsChannel();
                }else{
                    channelsAreaS.setText("");
                }

            }
        });
        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCHKBX.isSelected()){
                    DisplayMoviesChannles();
                }else{
                    channelsAreaM.setText("");
                }
            }
        });
        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(docCHKBX.isSelected()){
                    DisplayDocumentChannels();
                }else{
                    channelsAreaD.setText("");
                }
            }
        });
        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GetSubscriberData();
                }catch(Exception ee){

                }
            }
        });

                          /****************   PANEL 4    **********************/

        detailsPannel=new JPanel();
        detailsPannel.setBounds(330,230,300,400);
        detailsPannel.setLayout(new GridLayout(3,1));

        Border panel4Title=BorderFactory.createTitledBorder("Available Channels");
        detailsPannel.setBorder(panel4Title);

        channelsAreaS=new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM=new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);

        channelsAreaD=new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPannel.add(channelsAreaS);
        detailsPannel.add(channelsAreaM);
        detailsPannel.add(channelsAreaD);

                         /****************   PANEL 5     **********************/

        //panel2
        feePanel=new JPanel();
        feePanel.setLayout(new GridLayout(3,1));
        feePanel.setBounds(645,15,200,200);

        Border panel5Title=BorderFactory.createTitledBorder("Fee & Check");
        feePanel.setBorder(panel5Title);

        installFeeLBL=new JLabel("Installation Fee: ");
        packageFeeLBL=new JLabel("Package Fee: ");
        totalFeeLBL=new JLabel("Total Amount to pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);

        /****************   PANEL 6    **********************/

        //panel6
        p6Panel=new JPanel();
        p6Panel.setLayout(new GridLayout(3,1));
        p6Panel.setBounds(645,230,515,500);

        Border panel6Title=BorderFactory.createTitledBorder("Our Customers");
        p6Panel.setBorder(panel6Title);

        tableModel=new DefaultTableModel();

        table=new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Mobile Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        JScrollPane jScrollPane=new JScrollPane(table);
        
        p6Panel.add(jScrollPane);

        /****************   PANEL 7   **********************/

        //panel7
        p7Panel=new JPanel();
        p7Panel.setLayout(new GridLayout(3,1));
        p7Panel.setBounds(860,15,300,200);

        Border panel7Title=BorderFactory.createTitledBorder("Action");
        p7Panel.setBorder(panel7Title);

        newBTN=new JButton("New subscription");
        loadBTN=new JButton("Load subscription");
        saveBTN=new JButton("Save subscription");

        p7Panel.add(newBTN);
        p7Panel.add(saveBTN);
        p7Panel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });
        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ArrayList<Subscription> k= LoadDateFromDisk();
            }
        });
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        //adding panel to JFrame
        setLayout(null);
        add(subscriberpanel);     //Panel1
        add(cyclePanel);          //Panel2
        add(packagesChannel);     //Panel3
        add(detailsPannel);       //Panel4
        add(feePanel);            //Panel5
        add(p6Panel);             //Panel6
        add(p7Panel);             //panel7

    }


    /****************             METHODS         ***********************/

    private  int DisplayDocumentChannels(){
        DocumentaryChannel d1=new DocumentaryChannel("ABCD","HIN","DOC",25);
        DocumentaryChannel d2=new DocumentaryChannel("CNBC","ENG","DOC",20);
        DocumentaryChannel d3=new DocumentaryChannel("DDND","HIN","DOC",22);
        DocumentaryChannel d4=new DocumentaryChannel("DISC","ENG","DOC",15);
        DocumentaryChannel d5=new DocumentaryChannel("DDBH","HIN","DOC",10);

        ArrayList<DocumentaryChannel> documentaryChannels=new ArrayList<>();
        documentaryChannels.add(d1);
        documentaryChannels.add(d2);
        documentaryChannels.add(d3);
        documentaryChannels.add(d4);
        documentaryChannels.add(d5);

        String docChannelString= "";
        int packagePrice=0;

        for(int i=0; i< documentaryChannels.size(); i++) {
            docChannelString +=
                    "   " + documentaryChannels.get(i).getChannelName()
                            + "         " + documentaryChannels.get(i).getLanguage()
                            + "                 " + documentaryChannels.get(i).getPrice()
                            + "\n";
            packagePrice +=documentaryChannels.get(i).getPrice();
        }channelsAreaD.setText(docChannelString);
        return packagePrice;
    }
    private  int DisplayMoviesChannles() {
        MoviesChannel m1=new MoviesChannel("Star Gold","HIN","MOV",15);
        MoviesChannel m2=new MoviesChannel("Star Plus","HIN","MOV",10);
        MoviesChannel m3=new MoviesChannel("Set Max","HIN","MOV",25);
        MoviesChannel m4=new MoviesChannel("Zee Tv","HIN","MOV",20);
        MoviesChannel m5=new MoviesChannel("Sony TV","HIN","MOV",10);

        ArrayList<MoviesChannel> moviesChannels=new ArrayList<>();
        moviesChannels.add(m1);
        moviesChannels.add(m2);
        moviesChannels.add(m3);
        moviesChannels.add(m4);
        moviesChannels.add(m5);

        String movChannelString="";
        int packagePrice=0;
        for(int j=0;j< moviesChannels.size();j++){
            movChannelString +=
                    "     "+moviesChannels.get(j).getChannelName()
                    +"           "+moviesChannels.get(j).getLanguage()
                    +"              "+moviesChannels.get(j).getPrice()
                    +"\n";
            packagePrice +=moviesChannels.get(j).getPrice();

        }
channelsAreaM.setText(movChannelString);
        return packagePrice;
    }

    private  int DisplaySportsChannel() {
        SportChannel s1=new SportChannel("Star sports1","HIN","SPRT",10);
        SportChannel s2=new SportChannel("Star sports2","HIN","SPRT",15);
        SportChannel s3=new SportChannel("Star sports3","HIN","SPRT",20);
        SportChannel s4=new SportChannel("Star sports4","HIN","SPRT",30);
        SportChannel s5=new SportChannel("Star sports5","HIN","SPRT",20);

        ArrayList<SportChannel>sportChannels=new ArrayList<>();
        sportChannels.add(s1);
        sportChannels.add(s2);
        sportChannels.add(s3);
        sportChannels.add(s4);
        sportChannels.add(s5);

        String sprtChannelString="";
        int packagePrice=0;
        for(int k=0;k< sportChannels.size();k++){
            sprtChannelString +=
                    "    "+sportChannels.get(k).getChannelName()
            +"              "+sportChannels.get(k).getLanguage()
                    +"              "+sportChannels.get(k).getPrice()
                    +"\n";
            packagePrice +=sportChannels.get(k).getPrice();

        }
        channelsAreaS.setText(sprtChannelString);
        return packagePrice;
    }
    private void GetSubscriberData() throws ParseException {
        Date currentDate =new Date();

        //subscriber
        subscriber=new Subscriber(
                subName.getText(),subCity.getText(),Integer.parseInt(subMobile.getText()),subLastname.getText());

        //cycle
        Date startCycle=df.parse(startCycleFLD.getText());
        Date endCycle=df.parse((endCycleFLD.getText()));
        SubscriptionCycle cycle=new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle));

        //subscription
        subscription=new Subscription(
                Integer.parseInt(numberTvFLD.getText()),
                subscriber,cycle, df.format(currentDate));

        installFeeLBL.setText("Installation Fee : "+subscription.getTotalFee());
        
        showPrice();
    }

    private void showPrice() {
        if (docCHKBX.isSelected())
            packagesSelectedPrice += DisplayDocumentChannels();

        else if(moviesCHKBX.isSelected()){

        packagesSelectedPrice += DisplayMoviesChannles();
    }else if(sportsCHKBX.isSelected()){
            packagesSelectedPrice +=DisplaySportsChannel();}
            totalPrice = subscription.getTotalFee()+packagesSelectedPrice;
            totalFeeLBL.setText("Total Amount to Pay: Rs. "+totalPrice);


packageFeeLBL.setText("Packages Fee: Rs."+packagesSelectedPrice );
    }
    private void SaveSubscriptionToDisk() {
        listToSave.add(subscription);
        file=new File("d:\\myfile.dat");
        try{
            OutputStream os=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(os);

            //Saving the list of subscriptions
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void NewSubscription() {
        //fields are empty
        subName.setText("");
        subLastname.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTvFLD.setText("");

        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total Amount to pay: ");

        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);
        sportsCHKBX.setSelected(false);
    }

    private ArrayList<Subscription> LoadDateFromDisk() {
        ArrayList<Subscription> s=new ArrayList<>();
        file=new File("d:\\myfile.dat");
        try{
            InputStream is=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(is);

            s=(ArrayList)ois.readObject();
            ois.close();
            is.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(Subscription sub:s){
            DisplaySubscriptionsInTables(sub);
        }
return s;
    }

    private void DisplaySubscriptionsInTables(Subscription sub) {
        //Displaying Data From disk into Table
        tableModel.addRow(new Object[]{
                sub.getSubscriber().getfName(),
                sub.getSubscriber().getlName(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        } );
    }

    public static void main(String[] args) {
        MainScreen mainScreen=new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(20,10,1200,800);
    }
}
