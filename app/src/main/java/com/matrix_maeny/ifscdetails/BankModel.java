package com.matrix_maeny.ifscdetails;


/*
       "IFSC": "KARB0000001"
 "BANK": "Karnataka Bank",
    "BANKCODE": "KARB",

* "BRANCH": "Karnataka Bank IMPS",
   "ADDRESS": "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002","MICR": "",
   "CONTACT": "+918242228222",
       "CENTRE": "DAKSHINA KANNADA",

   "DISTRICT": "DAKSHINA KANNADA",
    "CITY": "MANGALORE",
    "STATE": "KARNATAKA",
 "RTGS": true,
    "SWIFT": "",
    "UPI": true,

    "NEFT": true,
    "IMPS": true,
* */
public class BankModel {

    private String ifsc, bank, bankCode, branch,micr, address,centre,district, contact, city, state, swift;//, neft, imps;
    private boolean rtgs, upi, neft, imps;

    public static BankModel model = null;

    public BankModel(String ifsc, String bank, String bankCode, String branch,String micr, String address, String centre,String district,
                     String contact, String city, String state, boolean rtgs, String swift,
                     boolean upi, boolean neft, boolean imps) {
        this.ifsc = ifsc;
        this.bank = bank;
        this.bankCode = bankCode;
        this.branch = branch;
        this.address = address;
        this.contact = contact;
        this.city = city;
        this.state = state;
        this.rtgs = rtgs;
        this.swift = swift;
        this.upi = upi;
        this.neft = neft;
        this.imps = imps;
        this.centre = centre;
        this.district = district;
        this.micr = micr;
    }

    public String getMicr() {
        return micr;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getDistrict() {
        return district;
    }

    public String getBank() {
        return bank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBranch() {
        return branch;
    }

    public String getAddress() {
        return address;
    }

    public String getCentre() {
        return centre;
    }

    public String getContact() {
        return contact;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getSwift() {
        return swift;
    }

    public boolean isRtgs() {
        return rtgs;
    }

    public boolean isUpi() {
        return upi;
    }

    public boolean isNeft() {
        return neft;
    }

    public boolean isImps() {
        return imps;
    }

    public static BankModel getModel() {
        return model;
    }
}
