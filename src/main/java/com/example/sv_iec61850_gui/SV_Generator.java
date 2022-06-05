package com.example.sv_iec61850_gui;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import PacketForm.Send;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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
    int[] qual;
    int[] meas;
    byte[] appID;

    @FXML
    void initialize() {
        TextField[] data = new TextField[]{SrcMACfield, DstMACfield, svIDfield, appIDfield, svNumfield,
                RMSIafield, phaseIa, RMSIbfield, phaseIb, RMSIcfield, phaseIc,
                RMSUafield, phaseUa, RMSUbfield, phaseUb, RMSUcfield, phaseUc};

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error SV_Generator");
        pasteBut.setOnAction(event -> {
            pasteData(data);
        });
        GenButt.setOnAction(event -> {
            saveData();
            checkValidity(alert);
            qual = change();
            meas = getMeas();
            appID = getAppID();
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

    public int[] getMeas() {
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

    public int[] change() {
        int[] q = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

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

    public void checkValidity(Alert alert) {
        if ((SrcMACfield.getText().length() != 17)||(!SrcMACfield.getText().matches("([0-9A-F]{2}:){5}[0-9A-F]{2}"))) {
            alert.setContentText("Invalid source MAC");
            alert.showAndWait();
        }
        if ((DstMACfield.getText().length() != 17)||(!DstMACfield.getText().matches("01:0C:CD:04:0[01]:[0-9A-F]{2}"))) {
            alert.setContentText("Invalid destination MAC");
            alert.showAndWait();
        }
        if ((svIDfield.getText().length() != 10) || (!svIDfield.getText().matches("[a-zA-Z0-9]{10}"))) {
            alert.setContentText("Invalid svID");
            alert.showAndWait();
        }

        if ((appIDfield.getText().length() != 4) || (!appIDfield.getText().matches("[0-9A-F]{4}"))) {
            alert.setContentText("Invalid appID");
            alert.showAndWait();
        }

        if (!svNumfield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid numbers of SV");
            alert.showAndWait();
        }

        if (!RMSIafield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Ia");
            alert.showAndWait();
        }

        if (!RMSIbfield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Ib");
            alert.showAndWait();
        }

        if (!RMSIcfield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Ic");
            alert.showAndWait();
        }

        if (!RMSUafield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Ua");
            alert.showAndWait();
        }

        if (!RMSUbfield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Ub");
            alert.showAndWait();
        }

        if (!RMSUcfield.getText().matches("[0-9]{1,}")) {
            alert.setContentText("Invalid value of Uc");
            alert.showAndWait();
        }

        if (!phaseIa.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ia");
            alert.showAndWait();
        }

        if (!phaseIb.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ib");
            alert.showAndWait();
        }

        if (!phaseIc.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ic");
            alert.showAndWait();
        }

        if (!phaseUa.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ua");
            alert.showAndWait();
        }

        if (!phaseUb.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ub");
            alert.showAndWait();
        }

        if (!phaseUc.getText().matches("-?[0-9]{1,}")) {
            alert.setContentText("Invalid value of phase Ua");
            alert.showAndWait();
        }
        }

        public byte[] getAppID () {
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

        public void saveData () {
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
        public void pasteData (TextField[] data){
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("Data.txt"));
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
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




