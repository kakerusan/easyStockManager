package thirdGroup.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import thirdGroup.dao.GoodsmanagementImp;
import thirdGroup.dao.StoragemanagementImp;

public class GoodsSelect extends Index {

    private JTable table;
    private JComboBox id;

    JTextField IdText;
    JTextField name;
    JTextField style;
    JTextField Manufactor;
    JTextField s_id;

    public GoodsSelect(String name) {
        super(name);
        init();
    }

    public void init() {
        Font t = new Font("楷体", Font.BOLD, 24);
        final Font f = new Font("楷体", Font.BOLD, 15);
        JLabel title = new JLabel("商品信息");
        title.setFont(t);
        title.setBounds(230, 40, 100, 40);
        final Vector c = new Vector();
        //添加数据
        c.add("编号");
        c.add("商品名称");
        c.add("商品类型");
        c.add("商品数量");
        c.add("仓库编号");
        c.add("生产日期");
        c.add("制造商");
        //初始化界面元素

        JLabel goodsId = new JLabel("商品编号");
        goodsId.setFont(f);
        goodsId.setBounds(160, 350, 80, 30);
        IdText = new JTextField();
        IdText.setBounds(240, 350, 220, 30);
        IdText.setFont(f);
        JLabel goodsname = new JLabel("商品名：");
        goodsname.setBounds(160, 400, 80, 30);
        goodsname.setFont(f);
        name = new JTextField();
        name.setBounds(240, 400, 220, 30);
        name.setFont(f);
        JLabel goodsstyle = new JLabel("类  型：");
        goodsstyle.setBounds(160, 460, 80, 30);
        goodsstyle.setFont(f);
        style = new JTextField();
        style.setBounds(240, 460, 220, 30);
        style.setFont(f);
        JLabel supplier = new JLabel("制造商");
        supplier.setBounds(160, 520, 80, 30);
        supplier.setFont(f);
        Manufactor = new JTextField();
        Manufactor.setBounds(240, 520, 220, 30);
        Manufactor.setFont(f);
        JLabel storageid = new JLabel("仓库号：");
        storageid.setFont(f);
        storageid.setBounds(160, 570, 80, 30);
        s_id = new JTextField();
        s_id.setFont(f);
        s_id.setBounds(240, 570, 220, 30);
        StoragemanagementImp s = new StoragemanagementImp();
        try {
            s.Query2("select * from storage");
            id = new JComboBox(StoragemanagementImp.vr);
        } catch (SQLException e2) {
            // TODO 自动生成的 catch 块
            e2.printStackTrace();
        }


        JButton search = new JButton("查找");
        JButton reset = new JButton("重置");
        search.setBounds(160, 620, 80, 30);
        reset.setBounds(300, 620, 80, 30);

        name.setText("");
        style.setText("");
        Manufactor.setText("");
        IdText.setText("");
        s_id.setText("");


        final GoodsmanagementImp g = new GoodsmanagementImp();
        //创建表格
        table = new JTable(GoodsmanagementImp.vec, c);
        table.setFont(f);
        table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 15));


        table.setRowHeight(30);
        // 为表格的所有列设置相同的渲染器，以实现居中显示
        // 自定义单元格渲染器以设置字体大小
        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // 设置字体大小
                setFont(new Font(getFont().getName(), getFont().getStyle(), 10));
                return this;
            }
        };

        renderer2.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(renderer2);
        }

        //为表格添加鼠标单击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取表格中的ID
                //获取每条记录所对应的id
                int idIndex = (int) table.getValueAt(table.getSelectedRow(), 0);
                String gname = (String) table.getValueAt(table.getSelectedRow(), 1);
                String gstyle = (String) table.getValueAt(table.getSelectedRow(), 2);
                String sid = table.getValueAt(table.getSelectedRow(), 4).toString();
                String supplier = table.getValueAt(table.getSelectedRow(), 6).toString();
                //将数据设置进文本框
                IdText.setText(String.valueOf(idIndex));
                name.setText(gname);
                style.setText(gstyle);
                s_id.setText(sid);
                Manufactor.setText(supplier);


                super.mouseClicked(e);
            }
        });

        //创建Jscrollpane
        final JScrollPane js = new JScrollPane(table);
        js.setBounds(50, 120, 450, 200);
        try {
            g.Query("select * from goods");
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                style.setText("");
                Manufactor.setText("");
                IdText.setText("");
                s_id.setText("");
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //编写sql语句
                String nameText = name.getText();
                String styleText = style.getText();
                String manufactorText = Manufactor.getText();
                String text = IdText.getText();
                String S_idText = s_id.getText();
                //查询主结构
                if (nameText.isEmpty() && styleText.isEmpty() && manufactorText.isEmpty()
                        && text.isEmpty() && S_idText.isEmpty()) {
                    try {
                        g.Query("select * from goods");
                        // 直接更新表格模型，而不是创建新的表格
                        table.setModel(new DefaultTableModel(GoodsmanagementImp.vec, c));
                    } catch (SQLException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    StringBuilder bd = new StringBuilder();
                    bd.append("select * from goods where ");
                    boolean b1 = false;
                    if (!nameText.isEmpty()) {
                        bd.append("goodsname='").append(nameText).append("' ");
                        b1 = true;
                    }
                    if (!styleText.isEmpty()) {
                        if (b1) {
                            bd.append("AND goodsstyle=\'").append(styleText).append("\' ");
                        } else {
                            bd.append("goodsstyle=\'").append(styleText).append("\' ");
                            b1 = true;
                        }
                    }
                    if (!manufactorText.isEmpty()) {
                        if (b1) {
                            bd.append("AND supplier='").append(manufactorText).append("\' ");
                        } else {
                            bd.append("supplier='").append(manufactorText).append("\' ");
                            b1 = true;
                        }
                    }
                    if (!text.isEmpty()) {
                        if (b1) {
                            bd.append("AND id= ").append(text).append(" ");
                        } else {
                            bd.append("id= ").append(text).append(" ");
                            b1 = true;
                        }
                    }
                    if (!S_idText.isEmpty()) {
                        if (b1) {
                            bd.append("AND storageID= ").append(S_idText).append(" ");
                        } else {
                            bd.append("storageID= ").append(S_idText).append(" ");

                        }
                    }
                    String sql = bd.toString();
                    try {
                        synchronized (GoodsSelect.class) {
                            g.Query(sql);
                            table.setModel(new DefaultTableModel(GoodsmanagementImp.vec, c));
                        }
                    } catch (SQLException e2) {
                        throw new RuntimeException(e2);
                    }
                    JTable new_table = new JTable(GoodsmanagementImp.vec, c);
                    new_table.setFont(f);
                    new_table.getTableHeader().setFont(f);
                    bd.delete(0, bd.length());
                    b1 = false;
                }
            }
        });


        index.add(title);
        index.add(js);
        index.add(goodsname);
        index.add(goodsstyle);
        index.add(supplier);
        index.add(name);
        index.add(style);
        index.add(Manufactor);
        index.add(search);
        index.add(reset);
        index.add(IdText);
        index.add(goodsId);
        index.add(storageid);
        index.add(s_id);
    }
}
