package thirdGroup.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Goods {
    private int id;
    private String goodsname;
    private String goodsstyle;
    private int goodsnumber;
    private String storageID;
    private Date dateOfManufacture;
    private String supplier;


    public Goods() {
    }

    public Goods(int id, String goodsname, String goodsstyle, int goodsnumber, String storageID, Date dateOfManufacture, String supplier) {
        this.id = id;
        this.goodsname = goodsname;
        this.goodsstyle = goodsstyle;
        this.goodsnumber = goodsnumber;
        this.storageID = storageID;
        this.dateOfManufacture = dateOfManufacture;
        this.supplier = supplier;
    }

    /**
     * 获取
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return goodsname
     */
    public String getGoodsname() {
        return goodsname;
    }

    /**
     * 设置
     *
     * @param goodsname
     */
    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    /**
     * 获取
     *
     * @return goodsstyle
     */
    public String getGoodsstyle() {
        return goodsstyle;
    }

    /**
     * 设置
     *
     * @param goodsstyle
     */
    public void setGoodsstyle(String goodsstyle) {
        this.goodsstyle = goodsstyle;
    }

    /**
     * 获取
     *
     * @return goodsnumber
     */
    public int getGoodsnumber() {
        return goodsnumber;
    }

    /**
     * 设置
     *
     * @param goodsnumber
     */
    public void setGoodsnumber(int goodsnumber) {
        this.goodsnumber = goodsnumber;
    }

    /**
     * 获取
     *
     * @return storageID
     */
    public String getStorageID() {
        return storageID;
    }

    /**
     * 设置
     *
     * @param storageID
     */
    public void setStorageID(String storageID) {
        this.storageID = storageID;
    }

    /**
     * 获取
     *
     * @return dateOfManufacture
     */
    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    /**
     * 设置
     *
     * @param dateOfManufacture
     */
    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public void setDateOfManufacture(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.dateOfManufacture = parse;
    }

    /**
     * 获取
     *
     * @return supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置
     *
     * @param supplier
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Goods{id = " + id + ", goodsname = " + goodsname + ", goodsstyle = " + goodsstyle + ", goodsnumber = " + goodsnumber + ", storageID = " + storageID + ", dateOfManufacture = " + dateOfManufacture + ", supplier = " + supplier + "}";
    }
}
