package thirdGroup.view;

import thirdGroup.bean.Goods;
import thirdGroup.dao.GoodsmanagementImp;
import thirdGroup.dao.StoragemanagementImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class Goodsadd extends IndexAdmin {
    JTextField name;
    JTextField style;
    JTextField number;
    JTextField dateManu;
    JTextField Manufactor;

    JComboBox id;
    Goods goods = new Goods();

    public Goodsadd(String name) {
        super(name);
        init();
    }

    public void init() {
        Font d = new Font("楷体", Font.BOLD, 26);
        Font f = new Font("楷体", Font.BOLD, 20);
        JLabel goodstitle = new JLabel("添加商品");
        JLabel goodsname = new JLabel("商品名：");
        name = new JTextField();
        JLabel goodsstyle = new JLabel("类  型：");
        style = new JTextField();
        JLabel goodsnumber = new JLabel("数  量：");
        number = new JTextField();
        JLabel dateOfManufacture = new JLabel("生产日期");
        dateManu = new JTextField();
        JLabel sample = new JLabel("日期格式：“年-月-日” 示例： 2024-01-01 ");
        JLabel supplier = new JLabel("制造商");
        Manufactor = new JTextField();
        JLabel storageid = new JLabel("仓库名：");
               StoragemanagementImp s = new StoragemanagementImp();
        try {
            s.Query2("select * from storage");
            id = new JComboBox(StoragemanagementImp.vr);
        } catch (SQLException e2) {
            // TODO 自动生成的 catch 块
            e2.printStackTrace();
        }
        //初始化图形界面
        JButton submit = new JButton("提交");
        JButton reset = new JButton("重置");
        goodstitle.setBounds(200, 40, 200, 40);
        goodstitle.setFont(d);
        goodsname.setBounds(100, 100, 200, 40);
        goodsname.setFont(f);
        name.setBounds(180, 100, 200, 40);
        name.setFont(f);
        goodsstyle.setBounds(100, 150, 200, 40);
        goodsstyle.setFont(f);
        style.setBounds(180, 150, 200, 40);
        style.setFont(f);
        goodsnumber.setBounds(100, 200, 200, 40);
        goodsnumber.setFont(f);
        number.setBounds(180, 200, 200, 40);
        number.setFont(f);
        storageid.setBounds(100, 250, 200, 40);
        storageid.setFont(f);
        id.setBounds(180, 250, 200, 40);
        id.setFont(f);
        dateOfManufacture.setBounds(100, 300, 200, 40);
        dateOfManufacture.setFont(f);
        dateManu.setBounds(180, 300, 200, 40);
        dateManu.setFont(f);
        sample.setBounds(100, 350, 400, 40);
        sample.setFont(new Font("楷体", Font.BOLD, 12));
        supplier.setBounds(100, 400, 200, 40);
        supplier.setFont(f);
        Manufactor.setBounds(180, 400, 200, 40);
        Manufactor.setFont(f);
        submit.setBounds(100, 450, 160, 40);
        submit.setFont(f);
        reset.setBounds(260, 450, 160, 40);
        reset.setFont(f);


        index.add(goodstitle);
        index.add(goodsname);
        index.add(name);
        index.add(goodsstyle);
        index.add(style);
        index.add(goodsnumber);
        index.add(number);
        index.add(storageid);
        index.add(id);
        index.add(dateOfManufacture);
        index.add(dateManu);
        index.add(supplier);
        index.add(sample);
        index.add(Manufactor);
        index.add(submit);
        index.add(reset);

        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                name.setText("");
                style.setText("");
                number.setText("");
                dateManu.setText("");
                Manufactor.setText("");
            }
        });

        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Goodsadd.class) { //并发限制锁
                    // TODO 自动生成的方法存根
                    if (name.getText().equals("") ||
                            style.getText().equals("") ||
                            number.getText().equals("") || dateManu.getText().equals("") || Manufactor.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "请完整输入内容");
                    } else if (!dateManu.getText().toString().trim().matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                        JOptionPane.showMessageDialog(null, "日期格式有误，需要重新输入");
                    } else {
                        goods.setGoodsname(name.getText());
                        goods.setGoodsstyle(style.getText());
                        goods.setGoodsnumber(Integer.parseInt(number.getText()));
                        Vector selectedItem = (Vector) id.getSelectedItem();
                        goods.setStorageID(selectedItem.get(1).toString());
                        goods.setDateOfManufacture(dateManu.getText());
                        goods.setSupplier(Manufactor.getText());
                        GoodsmanagementImp g = new GoodsmanagementImp();
                        try {
                            g.Add(goods, "insert into goods(goodsname,goodsstyle,goodsnumber,storageID,dateOfManufacture,supplier)" + "values(?,?,?,?,?,?)");
                            name.setText("");
                            style.setText("");
                            number.setText("");
                            dateManu.setText("");
                            Manufactor.setText("");
                            JOptionPane.showMessageDialog(null, "添加成功");
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }}

            }
        });
    };
}




