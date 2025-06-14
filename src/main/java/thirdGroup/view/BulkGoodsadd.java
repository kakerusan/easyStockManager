package thirdGroup.view;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import thirdGroup.bean.Goods;
import org.apache.poi.ss.usermodel.*;
import thirdGroup.dao.GoodsmanagementImp;


import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.XMLFormatter;


public class BulkGoodsadd extends IndexAdmin {
    JTextField src;
    JButton submit;
    JButton openButton;


    public BulkGoodsadd(String name) {
        super(name);
        init();
    }

    public void init() {

        Font d = new Font("楷体", Font.BOLD, 26);
        Font f = new Font("楷体", Font.BOLD, 20);
        JLabel goodstitle = new JLabel("批量添加商品");
        goodstitle.setFont(d);
        goodstitle.setBounds(200, 40, 200, 40);
    /*    JLabel BulkGoods = new JLabel("批量添加商品:");
        BulkGoods.setFont(f);
        BulkGoods.setBounds(100, 100, 150, 40);

        src = new JTextField("");
        src.setFont(new Font("楷体", Font.BOLD, 12));
        src.setBounds(250, 100, 200, 40);

        submit = new JButton("提交");
        submit.setFont(f);
        submit.setBounds(100, 200, 200, 40);*/
        JLabel warning = new JLabel("读取的.xlsx文件的格式需要按照：");
        warning.setFont(new Font("楷体", Font.BOLD, 16));
        warning.setBounds(100, 300, 300, 40);
        JLabel warning2 = new JLabel("商品名 商品种类 商品数量 储存仓库ID 生产日期 生产商");
        warning2.setFont(new Font("楷体", Font.BOLD, 14));
        warning2.setBounds(100, 350, 500, 40);

        //定义读取出来的商品信息


        //读取excel逻辑
        // 创建一个标签为"Open File"的按钮

        openButton = new JButton("文件浏览");
        openButton.setFont(f);
        openButton.setBounds(100, 150, 200, 40);
        index.add(openButton);
        index.add(goodstitle);
        //index.add(src);
        // index.add(BulkGoods);
        // index.add(submit);
        index.add(warning);
        index.add(warning2);
        // 为按钮添加点击事件监听器
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器对象
                JFileChooser fileChooser = new JFileChooser();
                // 显示文件选择对话框，并获取用户的选择结果
                int returnValue = fileChooser.showOpenDialog(null);
                // 判断用户是否选择了文件并点击了打开
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // 获取用户选择的文件
                    FileInputStream fileInputStream = null;
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        //创建文件读取
                        fileInputStream = new FileInputStream(selectedFile);
                        // 创建一个工作簿对象，该对象代表整个 Excel 文件
                        int choice = JOptionPane.showConfirmDialog(null,
                                "您确定要继续吗？",
                                "确认",
                                JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                            System.out.println("用户选择取消。");
                            return;
                        }

                        synchronized (BulkGoodsadd.class) {
                            Tika tika = new Tika();  //并发限制
                            String fileType = tika.detect(selectedFile);
                            if (!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(fileType)) {
                                JOptionPane.showMessageDialog(null, "读取的文件不是xlsx文件哦！");
                                return;
                            }
                            Workbook workbook = new XSSFWorkbook(fileInputStream);
                            // 获取第一个工作表（sheet）
                            Sheet sheet = workbook.getSheetAt(0);
                            //遍历总数
                            int rowSum = sheet.getLastRowNum()+1;
                            int success = 0;

                            // 遍历行
                            //foreach遍历

                            for (Row row : sheet) {
                                Goods goods = new Goods();
                                boolean isValidRow = true; // 标记当前行是否有效
                                Iterator<Cell> iterator = row.iterator();
                                for (int i = 0; i < 6 && iterator.hasNext(); i++) {
                                    Cell next = iterator.next();
                                    if (next.getCellType() == CellType.BLANK) {
                                        JOptionPane.showMessageDialog(null, "出现空白格！", "错误", JOptionPane.ERROR_MESSAGE);
                                        throw new RuntimeException();
                                    }
                                    switch (i) {
                                        case 0: // 商品名称（字符串）
                                            if (next.getCellType() == CellType.STRING) {
                                                String stringCellValue = next.getStringCellValue();
                                                goods.setGoodsname(stringCellValue);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "第1列数据格式有误，应为字符串", "错误", JOptionPane.ERROR_MESSAGE);
                                                isValidRow=false;
                                                i=7;
                                                break; // 如果格式错误，跳出当前行的处理
                                            }
                                            break;
                                        case 1: // 商品样式（字符串）
                                            if (next.getCellType() == CellType.STRING) {
                                                String stringCellValue1 = next.getStringCellValue();
                                                goods.setGoodsstyle(stringCellValue1);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "第2列数据格式有误，应为字符串", "错误", JOptionPane.ERROR_MESSAGE);
                                                isValidRow=false;
                                                i=7;
                                                break;
                                            }
                                            break;
                                        case 2: // 商品数量（数字）
                                            if (next.getCellType() == CellType.NUMERIC) {
                                                double numericCellValue1 = next.getNumericCellValue();
                                                // 注意：这里可能需要处理整数溢出的情况
                                                goods.setGoodsnumber((int) numericCellValue1);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "第3列数据格式有误，应为数字", "错误", JOptionPane.ERROR_MESSAGE);
                                                isValidRow=false;
                                                i=7;
                                                break;
                                            }
                                            break;
                                        case 3: // 库存ID（数字，但存储为字符串）
                                            if (next.getCellType() == CellType.NUMERIC) {
                                                double numericCellValue3 = next.getNumericCellValue();
                                                goods.setStorageID(Double.toString(numericCellValue3));
                                            } else {
                                                JOptionPane.showMessageDialog(null, "第4列数据格式有误，应为数字", "错误", JOptionPane.ERROR_MESSAGE);
                                                isValidRow=false;
                                                i=7;
                                                break;
                                            }
                                            break;
                                        case 4: // 制造日期（日期）
                                            if (next.getCellType() == CellType.NUMERIC) {
                                                Date dateCellValue = next.getDateCellValue(); // Apache POI 会自动根据样式解释数字为日期
                                                if (dateCellValue != null) {
                                                    goods.setDateOfManufacture(dateCellValue);
                                                } else {
                                                    // 如果无法解析为日期，可以处理为格式错误
                                                    JOptionPane.showMessageDialog(null, "第5列数据格式有误，应为日期", "错误", JOptionPane.ERROR_MESSAGE);
                                                    isValidRow=false;
                                                    i=7;
                                                    break;
                                                }
                                            }
                                            break;
                                        case 5: // 供应商（字符串）
                                            if (next.getCellType() == CellType.STRING) {
                                                String stringCellValue3 = next.getStringCellValue();
                                                goods.setSupplier(stringCellValue3);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "第6列数据格式有误，应为字符串", "错误", JOptionPane.ERROR_MESSAGE);
                                                isValidRow=false;
                                                i=7;
                                                break;
                                            }
                                            break;
                                        default:
                                            // 不应该到达这里，除非Excel文件的列数超过了6列
                                            break;
                                    }
                                }

                                if (isValidRow){
                                GoodsmanagementImp g = new GoodsmanagementImp();
                                g.Add(goods, "insert into goods(goodsname,goodsstyle,goodsnumber,storageID,dateOfManufacture,supplier)" + "values(?,?,?,?,?,?)");
                                success++;}
                            }
                            JOptionPane.showMessageDialog(null, "共 " + rowSum + " 行，添加成功： " + success + " 个数据！！");
                            fileInputStream.close();
                        }

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "文件未找到: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "读取异常（请确保输入的数据正确，且没有标头）: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "SQL异常: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "关闭文件流时出错: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    }


                }
            }
        });
    }
}


