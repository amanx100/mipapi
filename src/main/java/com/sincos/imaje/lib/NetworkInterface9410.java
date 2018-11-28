package com.sincos.imaje.lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetworkInterface9410 {

    private Communication communication = null;

    public NetworkInterface9410(Communication communication) {
        this.communication = communication;
    }

    public void connect() throws IOException {
            communication.connect();
            System.out.println("Printer Connected!");
    }

    public void disconnect() throws IOException {
            communication.disconnect();
            System.out.println("Printer Disconnected!");
    }

    public boolean isConnected() {
        return communication.isConnected();
    }

    public byte enquary(){
        List<Byte> arrayListTx = new ArrayList<Byte>();
        arrayListTx.add((byte)0x05);
        byte ack = 0;
        try {
            ack = communication.transmit(arrayListTx);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ack;
    }

    /*public void acknowledge() {
        byte ackByte = 0x06;
    }*/

    // Transmission methods

    public byte sendingMultitopValue() {
        return 0;
    }

    public byte sendingAnAcknowledgeFaults() {
        return 0;
    }

    public byte sendAJobSelectedAccordingToItsPosition() {
        return 0;
    }

    public byte sendATimeoutValueForExternalCommunication() {
        return 0;
    }

    public byte sendAJobToTheLibrary() {
        return 0;
    }

    public byte sendA9410_9450JobToTheLibraryOrCustomRank() {
        return 0;
    }

    public byte sendingAdvancedSettingsInformation() {
        return 0;
    }

    public byte stopOrStartThePrinter() {
        return 0;
    }

    public byte maintenance() {
        return 0;
    }

    public byte printAcknowledgementRequestFromComputer() {
        return 0;
    }

    public byte sendingDataPrintedByTheComputerMarkNRead9450Option() {
        return 0;
    }

    public byte negativePrintAcknowledgementRequestFromComputer() {
        return 0;
    }

    public byte sendAJobAndACustomFontFileToDirectoryStore() {
        return 0;
    }

    public byte updateThePrintingDataInThePrintUnit() {
        return 0;
    }
    //TODO 1
    public byte sendAJobForPrinting() {
        List<Byte> arrayListTx = new ArrayList<Byte>();
        arrayListTx.add((byte)0x94);
        arrayListTx.add((byte)0x80);
        arrayListTx.add((byte)0x00);
        arrayListTx.add((byte)0x00);

        byte ack = 0;
        try {
            ack = communication.transmit(arrayListTx);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ack;
    }

    public byte initializeCounters() {
        return 0;
    }

    public byte selectAJobByNumber() {
        return 0;
    }

    public String selectAJobByHisName(String jobName) {
        List<Byte> arrayListTx = new ArrayList<Byte>();
        arrayListTx.add((byte)0xA0);
        arrayListTx.add((byte)0x80);
        arrayListTx.add((byte) jobName.length());

        for (int i=0; i<jobName.length(); i++){
            arrayListTx.add((byte)jobName.charAt(i));
        }

        arrayListTx.add((byte) 0x00);

        List<Byte> returnData = null;
        try {
            returnData = communication.request(arrayListTx);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String retMsg="";
        switch (returnData.get(0)) {
            case 0x00: retMsg = "Message selection successful"; break;
            case 0x01: retMsg = "Message not present in the store"; break;
            case 0x02: retMsg = "No active production*"; break;
            case 0x03: retMsg = "Message not usable due to a faulty content"; break;
            case 0x04: retMsg = "Inconsistent production (distance A or B or C too small)*"; break;
            case 0x05: retMsg = "Algorithm, used by the message, not present in the store"; break;
            case 0x06: retMsg = "Font, used by the message, not present in the store"; break;
            default: retMsg = "Unknown Error";
        }
        return retMsg;
    }

    public byte stopOrStartTheJetOrStopThePrinter() {
        return 0;
    }

    public byte deleteAJob() {
        return 0;
    }

    public byte sendAnAutodatingTable() {
        return 0;
    }

    public byte sendExternalVariables(Map<Integer,String> data) {
        List<Byte> arrayListTx = new ArrayList<Byte>();
        arrayListTx.add((byte)0xE8);
        arrayListTx.add((byte)0x80);
        arrayListTx.add((byte)0x00); // will be updated after all length calculation
        for (Map.Entry<Integer, String> dt:data.entrySet()){
            arrayListTx.add(dt.getKey().byteValue());
            arrayListTx.add((byte)0x00);
            arrayListTx.add((byte)dt.getValue().length());

            for (int i=0; i<dt.getValue().length(); i++){
                arrayListTx.add((byte)dt.getValue().charAt(i));
            }
        }
        // Updating data
        arrayListTx.set(2, (byte)(arrayListTx.size()-3));
        // Adding CRC
        arrayListTx.add((byte)0x00);

        byte ack = 0;
        try {
            ack = communication.transmit(arrayListTx);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ack;
    }

    public byte nonDoublePrinting() {
        return 0;
    }

    public byte sendingACompleteJobForPrinting() {
        return 0;
    }

    public byte sendA9410_9450JobForPrinting() {
        return 0;
    }

    public byte promotionnalCoding() {
        return 0;
    }

    // Request Methods

    public byte requestPrinterStatus() {
        return 0;
    }

    public byte requestWarningsFaults_InkCircuitHeadPrinting() {
        return 0;
    }

    public byte requestAdvancedSettingsInformation() {
        return 0;
    }

    public byte requestJetStatus() {
        return 0;
    }

    public byte requestPrintingStatus() {
        return 0;
    }

    public byte requestForInformationOfMessageInLibrary() {
        return 0;
    }

    public byte requestInformationConcerningAUTOOrCUSTOMModeLibrary() {
        return 0;
    }

    public long requestOfTheValueOfTheTotalPrintCounter() {
        List<Byte> arrayListTx = new ArrayList<Byte>();
        arrayListTx.add((byte)0x56);
        arrayListTx.add((byte)0x80);
        arrayListTx.add((byte)0x00);
        arrayListTx.add((byte)0x00);

        List<Byte> arrayListRx = null;
        try {
            arrayListRx = communication.request(arrayListTx);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((arrayListRx.get(0)&0x00FF)<<24) | ((arrayListRx.get(1)&0x00FF)<<16) | ((arrayListRx.get(2)&0x00FF)<<8) | (arrayListRx.get(3)&0x00FF);
    }


    public byte requestForAJobByNumberInTheStore() {
        return 0;
    }

    public byte requestCurrentValueOfCounters() {
        return 0;
    }

    public byte requestForTypesOfCharacterGeneratorsAvailableInThePrinter() {
        return 0;
    }

    public byte requestForCurrentValueOfTheAutodating() {
        return 0;
    }

    public byte requestSoftwareVersions() {
        return 0;
    }

    public byte requestForAutodatingParameters() {
        return 0;
    }

    public byte requestShiftCode4Parameters_CustomShiftCode() {
        return 0;
    }

    public byte requestForCurrentPrintingCounterParameters() {
        return 0;
    }

    public byte requestCartridgeTagGeneralInformation() {
        return 0;
    }

    public byte requestForGeneralParametersByDefault() {
        return 0;
    }

    public byte requestForHistoryOfTheThirtyFaults() {
        return 0;
    }

    public byte requestWarningsOrFaults_numberAndCodes() {
        return 0;
    }

    public byte requestActiveJobNumber() {
        return 0;
    }

    public byte requestForAuAutoDactingTable() {
        return 0;
    }

    public byte requestForTheLanguagesOfTheMonthToBePrinted() {
        return 0;
    }

}
