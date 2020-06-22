package com.example.samsung.sample;

public class Pibu {
    private String REFINE_ROADNM_ADDR;
    private String BIZPLC_NM;
   private  String REFINE_WGS84_LAT;
   private  String REFINE_WGS84_LOGT;








    public Pibu  (String BIZPLC_NM, String REFINE_WGS84_LAT, String REFINE_WGS84_LOGT,String REFINE_ROADNM_ADDR)
        {
            this.REFINE_ROADNM_ADDR =REFINE_ROADNM_ADDR;
            this.BIZPLC_NM = BIZPLC_NM;
            this.REFINE_WGS84_LAT = REFINE_WGS84_LAT;
            this.REFINE_WGS84_LOGT= REFINE_WGS84_LOGT;
        }

    public String getBIZPLC_NM() {
        return BIZPLC_NM;
    }

    public void setBIZPLC_NM(String BIZPLC_NM) {
        this.BIZPLC_NM = BIZPLC_NM;
    }

    public String getREFINE_WGS84_LAT() {
        return REFINE_WGS84_LAT;
    }

    public void setREFINE_WGS84_LAT(String REFINE_WGS84_LAT) {
        this.REFINE_WGS84_LAT = REFINE_WGS84_LAT;
    }

    public String getREFINE_WGS84_LOGT() {
        return REFINE_WGS84_LOGT;
    }

    public void setREFINE_WGS84_LOGT(String REFINE_WGS84_LOGT) {
        this.REFINE_WGS84_LOGT = REFINE_WGS84_LOGT;
    }


    public String getREFINE_ROADNM_ADDR() {
        return REFINE_ROADNM_ADDR;
    }

    public void setREFINE_ROADNM_ADDR(String REFINE_ROADNM_ADDR) {
        this.REFINE_ROADNM_ADDR = REFINE_ROADNM_ADDR;
    }

    }




