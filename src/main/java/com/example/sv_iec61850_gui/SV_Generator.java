package com.example.sv_iec61850_gui;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import PacketForm.Send;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class SV_Generator {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField DstMACfield;
    @FXML
    private Button GenButt;
    @FXML
    private Button pasteBut;
    @FXML
    private CheckBox Iag;
    @FXML
    private CheckBox Iai;
    @FXML
    private CheckBox Iaq;
    @FXML
    private CheckBox Iau;
    @FXML
    private CheckBox Ibg;
    @FXML
    private CheckBox Ibi;
    @FXML
    private CheckBox Ibq;
    @FXML
    private CheckBox Ibu;
    @FXML
    private CheckBox Icg;
    @FXML
    private CheckBox Ici;
    @FXML
    private CheckBox Icq;
    @FXML
    private CheckBox Icu;
    @FXML
    private CheckBox Ing;
    @FXML
    private CheckBox Ini;
    @FXML
    private CheckBox Inq;
    @FXML
    private CheckBox Inu;
    @FXML
    private TextField RMSIafield;
    @FXML
    private TextField RMSIbfield;
    @FXML
    private TextField RMSIcfield;
    @FXML
    private TextField RMSUafield;
    @FXML
    private TextField RMSUbfield;
    @FXML
    private TextField RMSUcfield;
    @FXML
    private TextField SrcMACfield;
    @FXML
    private CheckBox Uag;
    @FXML
    private CheckBox Uai;
    @FXML
    private CheckBox Uaq;
    @FXML
    private CheckBox Uau;
    @FXML
    private CheckBox Ubg;
    @FXML
    private CheckBox Ubi;
    @FXML
    private CheckBox Ubq;
    @FXML
    private CheckBox Ubu;
    @FXML
    private CheckBox Ucg;
    @FXML
    private CheckBox Uci;
    @FXML
    private CheckBox Ucq;
    @FXML
    private CheckBox Ucu;
    @FXML
    private CheckBox Ung;
    @FXML
    private CheckBox Uni;
    @FXML
    private CheckBox Unq;
    @FXML
    private CheckBox Unu;
    @FXML
    private TextField appIDfield;
    @FXML
    private ComboBox<?> freqfield;
    @FXML
    private TextField phaseIa;
    @FXML
    private TextField phaseIb;
    @FXML
    private TextField phaseIc;
    @FXML
    private TextField phaseUa;
    @FXML
    private TextField phaseUb;
    @FXML
    private TextField phaseUc;
    @FXML
    private TextField svIDfield;
    @FXML
    private TextField svNumfield;
    @FXML
    private Text errortext;
    int[] qual;
    int[] meas;
    boolean flg;
    byte[] appID;

    @FXML
    void initialize() {
        TextField[] data = new TextField[]{SrcMACfield, DstMACfield, svIDfield, appIDfield, svNumfield,
        RMSIafield, phaseIa, RMSIbfield, phaseIb, RMSIcfield, phaseIc,
        RMSUafield, phaseUa, RMSUbfield, phaseUb, RMSUcfield, phaseUc};

        pasteBut.setOnAction(event -> { pasteData(data);});
        GenButt.setOnAction(event -> {
            saveData();
            String[] valid = checkValidity();

            qual = change();
            meas = getMeas();
            appID = getAppID();
            for (int i=0; i< valid.length; i++){
                if (valid[i]!="") flg = true;
                else {
                    flg = false;
                    i= valid.length;
                }
            }
            try {
                Send packet = new Send(DstMACfield.getText(),
                                           SrcMACfield.getText(),
                                           svIDfield.getText(),
                                           appID,
                                           Short.parseShort(svNumfield.getText()),
                                           meas, qual);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            /** Конец нажима кнопки **/
        });
    }

    public int[] getMeas(){
            int[] mes = new int[12];
            mes[0] = Integer.parseInt(RMSIafield.getText());
            mes[1] = Integer.parseInt(phaseIa.getText());
            mes[2] = Integer.parseInt(RMSIbfield.getText());
            mes[3] = Integer.parseInt(phaseIb.getText());
            mes[4] = Integer.parseInt(RMSIcfield.getText());
            mes[5] = Integer.parseInt(phaseIc.getText());
            mes[6] = Integer.parseInt(RMSUafield.getText());
            mes[7] = Integer.parseInt(phaseUa.getText());
            mes[8] = Integer.parseInt(RMSUbfield.getText());
            mes[9] = Integer.parseInt(phaseUb.getText());
            mes[10] = Integer.parseInt(RMSUcfield.getText());
            mes[11] = Integer.parseInt(phaseUc.getText());
            return mes;
        }
    public int[] change(){
            int[] q = new int[]{0,0,0,0,0,0,0,0};

            if (Iag.isSelected()) {
                q[0] = 0;
            } else if (Iai.isSelected()) {
                q[0] = 1;
            } else if (Iau.isSelected()) {
                q[0] = 10;
            } else if (Iaq.isSelected()) {
                q[0] = 11;
            }

            if (Ibg.isSelected()) {
                q[1] = 0;
            } else if (Ibi.isSelected()) {
                q[1] = 1;
            } else if (Ibu.isSelected()) {
                q[1] = 10;
            } else if (Ibq.isSelected()) {
                q[1] = 11;
            }

            if (Icg.isSelected()) {
                q[2] = 0;
            } else if (Ici.isSelected()) {
                q[2] = 1;
            } else if (Icu.isSelected()) {
                q[2] = 10;
            } else if (Icq.isSelected()) {
                q[2] = 11;
            }

            if (Ing.isSelected()) {
                q[3] = 0;
            } else if (Ini.isSelected()) {
                q[3] = 1;
            } else if (Inu.isSelected()) {
                q[3] = 10;
            } else if (Inq.isSelected()) {
                q[3] = 11;
            }

            if (Uag.isSelected()) {
                q[4] = 0;
            } else if (Uai.isSelected()) {
                q[4] = 1;
            } else if (Uau.isSelected()) {
                q[4] = 10;
            } else if (Uaq.isSelected()) {
                q[4] = 11;
            }

            if (Ubg.isSelected()) {
                q[5] = 0;
            } else if (Ubi.isSelected()) {
                q[5] = 1;
            } else if (Ubu.isSelected()) {
                q[5] = 10;
            } else if (Ubq.isSelected()) {
                q[5] = 11;
            }

            if (Ucg.isSelected()) {
                q[6] = 0;
            } else if (Uci.isSelected()) {
                q[6] = 1;
            } else if (Ucu.isSelected()) {
                q[6] = 10;
            } else if (Ucq.isSelected()) {
                q[6] = 11;
            }

            if (Ung.isSelected()) {
                q[7] = 0;
            } else if (Uni.isSelected()) {
                q[7] = 1;
            } else if (Unu.isSelected()) {
                q[7] = 10;
            } else if (Unq.isSelected()) {
                q[7] = 11;
            }
            return q;
        }
    public String[] checkValidity(){
            String[] errors = new String[17];
            if (SrcMACfield.getText().length()!=17) {
                errors[0] = "Invalid source MAC address";
            }else{
                if (SrcMACfield.getText().matches("([0-9A-F]{2}:){5}[0-9A-F]{2}")){
                    errors[0]="";
                }else errors[0] = "Invalid source MAC adress";
            }
            if (DstMACfield.getText().length()!=17){
                errors[1] = "Invalid destination MAC address";
            }else{
                if (DstMACfield.getText().matches("01:0C:CD:04:0[01]:[0-9A-F]{2}")){
                    errors[1]="";
                }else errors[1] = "Invalid destination MAC address";
            }
            if ((svIDfield.getText().length()==10)&&(svIDfield.getText().matches("[a-zA-Z0-9]{10}"))){
                errors[2]="";
            }else errors[2] = "Invalid svID";

            if ((appIDfield.getText().length()==4)&&(appIDfield.getText().matches("[0-9A-F]{4}"))){
                errors[3]="";
            }else errors[3] = "Invalid APPID";

            if(svNumfield.getText().matches("[0-9]}")){
                errors[4]="";
            }else errors[4] = "Invalid numbers of SV";

            if(RMSIafield.getText().matches("[0-9]")){
                errors[5]="";
            }else errors[5] = "Invalid value of Ia";

            if(RMSIbfield.getText().matches("[0-9]")){
                errors[6]="";
            }else errors[6] = "Invalid value of Ib";

            if(RMSIcfield.getText().matches("[0-9]")){
                errors[7]="";
            }else errors[7] = "Invalid value of Ic";

            if(RMSUafield.getText().matches("[0-9]")){
                errors[8]="";
            }else errors[8] = "Invalid value of Ua";

            if(RMSUbfield.getText().matches("[0-9]")){
                errors[9]="";
            }else errors[9] = "Invalid value of Ub";

            if(RMSUcfield.getText().matches("[0-9]")){
                errors[10]="";
            }else errors[10] = "Invalid value of Uc";

            if(phaseIa.getText().matches("[0-9]")){
                errors[11]="";
            }else errors[11] = "Invalid value of PhaseIa";

            if(phaseIb.getText().matches("[0-9]")){
                errors[12]="";
            }else errors[12] = "Invalid value of PhaseIb";

            if(phaseIc.getText().matches("[0-9]")){
                errors[13]="";
            }else errors[13] = "Invalid value of PhaseIc";

            if(phaseUa.getText().matches("[0-9]")){
                errors[14]="";
            }else errors[14] = "Invalid value of PhaseUa";

            if(phaseUb.getText().matches("[0-9]")){
                errors[15]="";
            }else errors[15] = "Invalid value of PhaseUb";

            if(phaseUc.getText().matches("[0-9]")){
                errors[16]="";
            }else errors[16] = "Invalid value of PhaseUc";

            for (String a: errors) {
                System.out.println(a);
            }
            return errors;
        }

        public byte[] getAppID(){
            StringBuilder str = new StringBuilder(appIDfield.getText());
            byte[] finb = new byte[2];
            String[] mas = new String[2];
            mas[0] = str.substring(0, 2);
            mas[1] = str.substring(2);
            for (int i = 0; i < 2; i++) {
                finb[i] = (byte) Integer.parseInt(mas[i], 16);
            }
            return finb;
        }

        public void saveData(){
            File file = new File("Data.txt");
            try {
                if (file.exists()) file.createNewFile();
                PrintWriter pw = new PrintWriter(file);
                pw.println(SrcMACfield.getText());
                pw.println(DstMACfield.getText());
                pw.println(svIDfield.getText());
                pw.println(appIDfield.getText());
                pw.println(svNumfield.getText());
                pw.println(RMSIafield.getText());
                pw.println(phaseIa.getText());
                pw.println(RMSIbfield.getText());
                pw.println(phaseIb.getText());
                pw.println(RMSIcfield.getText());
                pw.println(phaseIc.getText());
                pw.println(RMSUafield.getText());
                pw.println(phaseUa.getText());
                pw.println(RMSUbfield.getText());
                pw.println(phaseUb.getText());
                pw.println(RMSUcfield.getText());
                pw.println(phaseUc.getText());
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void pasteData(TextField[] data){
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("Data.txt"));
                String line;
                int i=0;
                while ((line = br.readLine())!=null){
                    data[i].setText(line);
                    i++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}




